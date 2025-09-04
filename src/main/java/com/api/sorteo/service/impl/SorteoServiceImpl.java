package com.api.sorteo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.sorteo.beans.Clientes;
import com.api.sorteo.beans.InvalidLoginException;
import com.api.sorteo.beans.Sorteos;
import com.api.sorteo.beans.Tickets;
import com.api.sorteo.beans.Usuarios;
import com.api.sorteo.beans.LoginRequest;
import com.api.sorteo.beans.Premios;
import com.api.sorteo.beans.RegisterRequest;
import com.api.sorteo.dao.SorteoDAO;
import com.api.sorteo.service.SorteoService;

@Service
public class SorteoServiceImpl implements SorteoService {
	@Autowired
	SorteoDAO sorteoDao;

	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	@Override
	public List<Clientes> getClientes(Integer sorteoId) {
		List<Clientes> clientes = new ArrayList<>();
		clientes = sorteoDao.getClientes(sorteoId);
		return clientes;
	}

	@Override
	public List<Sorteos> getSorteos() {
		List<Sorteos> sorteos = new ArrayList<>();
		sorteos = sorteoDao.getSorteos();
		return sorteos;
	}
	
	@Override
	public List<Premios> getPremios() {
		List<Premios> premios = new ArrayList<>();
		premios = sorteoDao.getPremios();
		
		return premios;
	}

	@Override
	public Sorteos getSorteoById(Integer sorteoId) {
		Sorteos sorteos = new Sorteos();
		sorteos = sorteoDao.getSorteoById(sorteoId);
		return sorteos;
	}

	@Override
	public Clientes realizarSorteo(Integer idSorteo) {
		List<Clientes> participantes = sorteoDao.getClientes(idSorteo);

		Sorteos sorteo = sorteoDao.getSorteoById(idSorteo);

		if (participantes.isEmpty()) {
			throw new RuntimeException("No hay participantes en el sorteo " + idSorteo);
		}

		List<Clientes> candidatos = participantes.stream().filter(
				c -> sorteo.getGenero().equalsIgnoreCase("U") || sorteo.getGenero().equalsIgnoreCase(c.getSexo()))
				.toList();

		Random random = new Random();
		int indexGanador = random.nextInt(candidatos.size());

		return participantes.get(indexGanador);
	}

	@Override
	public Usuarios getAuthentication(LoginRequest request) {
		Usuarios usuario = new Usuarios();
		usuario = sorteoDao.getLogin(request.getUsername(), request.getPassword());
		
		if(usuario == null) {
	            throw new InvalidLoginException(400,"Usuario no encontrado");
		}
		
		  if (!encoder.matches(request.getPassword(), usuario.getPassword())) {
	            throw new InvalidLoginException(401, "Contraseña incorrecta");
	        }
		return usuario;
	}
	
	@Override
	public Boolean getRegister(RegisterRequest request) {
		Boolean register = sorteoDao.getRegister(request);
		return register;
	}
	
	@Override
	public Premios getPremioById(Integer premioId) {
		Premios premio = new Premios();
		premio = sorteoDao.getPremioById(premioId);
		return premio;
	}
	
	@Override
	public List<Tickets> getTicketsStatus(Integer sorteoId) {
		List<Tickets> tickets = new ArrayList<>();
		tickets = sorteoDao.getTicketsStatus(sorteoId);
		
		return tickets;
	}
}
