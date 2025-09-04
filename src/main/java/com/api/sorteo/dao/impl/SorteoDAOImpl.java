package com.api.sorteo.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.sorteo.beans.Clientes;
import com.api.sorteo.beans.Premios;
import com.api.sorteo.beans.RegisterRequest;
import com.api.sorteo.beans.Sorteos;
import com.api.sorteo.beans.Tickets;
import com.api.sorteo.beans.Usuarios;
import com.api.sorteo.dao.SorteoDAO;
import com.api.sorteo.mapper.ClientsMapper;
import com.api.sorteo.mapper.SorteosMapper;
import com.api.sorteo.mapper.UsuarioMapper;
import com.api.sorteo.mapper.PremiosMapper;
import com.api.sorteo.mapper.TicketsMapper;

@Repository
public class SorteoDAOImpl implements SorteoDAO {
// ====================== Querys =============
	private static final String GET_CLIENTS_BY_SORTEO_ID = "SELECT * FROM CLIENTES WHERE ID_SORTEO = ?";
	private static final String GET_SORTEOS = "SELECT * FROM SORTEO WHERE ESTADO = 1";
	private static final String GET_SORTEO_BY_ID = "SELECT * FROM SORTEO WHERE ESTADO = 1 AND ID = ?";
	private static final String POST_LOGIN = "SELECT * FROM USUARIOS WHERE CORREO = ?";
	private static final String GET_PREMIOS = "SELECT * FROM PREMIOS WHERE ESTADO = 1";
	private static final String GET_PREMIO_BY_ID = "SELECT * FROM PREMIOS WHERE ESTADO = 1 AND ID = ?";
	private static final String POST_REGISTER = "INSERT INTO USUARIOS (nombre, sexo, ci, correo, telefono, contrasena) VALUES( ?, ?, ?, ?, ?, ?)";

	private final JdbcTemplate jdbcTemplate;

	private static final Logger logger = LoggerFactory.getLogger(SorteoDAOImpl.class);

	public SorteoDAOImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Clientes> getClientes(Integer sorteoId) {
		List<Clientes> clientes = new ArrayList<>();
		try {
			logger.info("Buscando clientes con sorteoId={} (tipo={})", sorteoId, sorteoId.getClass().getName());
			clientes = jdbcTemplate.query(GET_CLIENTS_BY_SORTEO_ID, new ClientsMapper(), sorteoId);
			logger.info("Se obtuvieron {} Clientes para el sorteo {}", clientes.size(), sorteoId);

		} catch (Exception e) {
			logger.error("Error al obtener Clientes para el sorteo {}: {}", sorteoId, e.getMessage(), e);
		}
		return clientes;
	}

	@Override
	public List<Sorteos> getSorteos() {
		List<Sorteos> sorteos = new ArrayList<>();
		try {
			sorteos = jdbcTemplate.query(GET_SORTEOS, new SorteosMapper());
			logger.info("Se obtuvieron {} Clientes para el sorteo {}", sorteos.size());

		} catch (Exception e) {
			logger.error("Error al obtener sorteos", e.getMessage(), e);
		}
		return sorteos;
	}
	
	@Override
	public List<Premios> getPremios() {
		List<Premios> premios = new ArrayList<>();
		try {
			premios = jdbcTemplate.query(GET_PREMIOS, new PremiosMapper());
			logger.info("Se obtuvieron {} Clientes para el sorteo {}", premios.size());

		} catch (Exception e) {
			logger.error("Error al obtener sorteos", e.getMessage(), e);
		}
		return premios;
	}
	
	@Override
	public Premios getPremioById(Integer premioId) {
		Premios premio = new Premios();
		try {
			premio = jdbcTemplate.queryForObject(GET_PREMIO_BY_ID, new PremiosMapper(), premioId);
			String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().toUriString();
			premio.setImagen(baseUrl + premio.getImagen());

		} catch (Exception e) {
			logger.error("Error al obtener el premio: ", e.getMessage(), premioId);
		}
		return premio;
	}

	@Override
	public Sorteos getSorteoById(Integer idSorteo) {
		Sorteos sorteos = new Sorteos();
		try {
			sorteos = jdbcTemplate.queryForObject(GET_SORTEO_BY_ID, new SorteosMapper(), idSorteo);
		} catch (Exception e) {
			logger.error("Error al obtener sorteos", e.getMessage(), e);
		}
		return sorteos;
	}

	@Override
	public Usuarios getLogin(String user, String password) {
		Usuarios usuario = new Usuarios();
		try {
			PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

			logger.info("validar inicio de sesion con credenciales {} : {}", user, POST_LOGIN);
			usuario = jdbcTemplate.queryForObject(POST_LOGIN, new UsuarioMapper(), user);
			
			 if (!passwordEncoder.matches(password, usuario.getPassword())) {
		            logger.warn("Login fallido para usuario {} : contraseña incorrecta", user);
		            return null;
		        }
		} catch (EmptyResultDataAccessException e) {
			logger.warn("Login fallido para usuario {} : {}", user, e.getMessage());
			return null;
		} catch (Exception e) {
			logger.error("Error en autenticación para usuario {}: {}", user, e.getMessage(), e);
			return null;
		}
		return usuario;
	}
	
	@Override 
	public Boolean getRegister(RegisterRequest request) {
		try {
			PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	        String hashedPassword = passwordEncoder.encode(request.getContrasena());

			logger.info("iniciamos el registro con los parametros : {}", request);
			int rows = jdbcTemplate.update(
		            POST_REGISTER,  
		            request.getNombre(),
		            request.getSexo(),
		            request.getCi(),                      
		            request.getCorreo(),
		            request.getNroTelefono(),
		            hashedPassword
		        );

		        return rows > 0;
		} catch (Exception e) {
			logger.error("Error en autenticación para usuario {}: {}", request, e.getMessage(), e);
			return false;
		}
	}

	@Override
	public List<Tickets> getTicketsStatus(Integer sorteoId) {
	    // 1. Verificar si el premio existe y obtener total_tickets
	    String countPremioSql = "SELECT total_tickets FROM premios WHERE id = ? AND estado = 1";
	    Integer totalTickets;
	    try {
	        totalTickets = jdbcTemplate.queryForObject(countPremioSql, Integer.class, sorteoId);
	        if (totalTickets == null || totalTickets <= 0) {
	            throw new RuntimeException("El premio con ID " + sorteoId + " no está activo o no tiene tickets definidos.");
	        }
	    } catch (EmptyResultDataAccessException e) {
	        throw new RuntimeException("No se encontró un premio activo con ID: " + sorteoId);
	    }

	    // 2. Verificar si ya existen boletos para este premio
	    String countBoletosSql = "SELECT COUNT(*) FROM boletos WHERE premio_id = ?";
	    Integer boletosExistentes = jdbcTemplate.queryForObject(countBoletosSql, Integer.class, sorteoId);

	    if (boletosExistentes == 0) {
	        // 3. Si no hay boletos, insertar todos los tickets numerados
	        String insertSql = "INSERT INTO boletos (premio_id, numero, estado) VALUES (?, ?, 1)";
	        List<Object[]> batchArgs = new ArrayList<>();

	        for (int i = 1; i <= totalTickets; i++) {
	            batchArgs.add(new Object[]{sorteoId, i});
	        }

	        jdbcTemplate.batchUpdate(insertSql, batchArgs);
	        logger.info("Se insertaron {} boletos para el premio ID {}", totalTickets, sorteoId);
	    }

	    // 4. Obtener todos los boletos del premio
	    String selectSql = "SELECT id, premio_id, numero, estado, usuario_id, fecha_compra " +
	                       "FROM boletos WHERE premio_id = ? ORDER BY numero";
	    
	    List<Tickets> tickets = new ArrayList<>();
	    
	    tickets = jdbcTemplate.query(selectSql, new TicketsMapper(), sorteoId);
	    
	    return tickets;
	}

}
