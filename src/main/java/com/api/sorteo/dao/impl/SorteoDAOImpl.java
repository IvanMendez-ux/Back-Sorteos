package com.api.sorteo.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.api.sorteo.beans.Clientes;
import com.api.sorteo.beans.Sorteos;
import com.api.sorteo.dao.SorteoDAO;
import com.api.sorteo.mapper.ClientsMapper;
import com.api.sorteo.mapper.SorteosMapper;

@Repository
public class SorteoDAOImpl implements SorteoDAO {
// ====================== Querys =============
	private static final String GET_CLIENTS_BY_SORTEO_ID = "SELECT * FROM CLIENTES WHERE ID_SORTEO = ?";
	private static final String GET_SORTEOS = "SELECT * FROM SORTEO WHERE ESTADO = 1";
	private static final String GET_SORTEO_BY_ID = "SELECT * FROM SORTEO WHERE ESTADO = 1 AND ID = ?";
	 
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
	public Sorteos getSorteoById(Integer idSorteo) {
		Sorteos sorteos = new Sorteos();
		try {
			sorteos = jdbcTemplate.queryForObject(GET_SORTEO_BY_ID, new SorteosMapper(), idSorteo);
        } catch (Exception e) {
			logger.error("Error al obtener sorteos", e.getMessage(), e);
		}
		return sorteos;
	}

}
