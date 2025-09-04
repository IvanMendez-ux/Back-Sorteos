package com.api.sorteo.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.api.sorteo.beans.Clientes;
import com.api.sorteo.beans.Sorteos;
import com.api.sorteo.beans.Tickets;
import com.api.sorteo.beans.Usuarios;
import com.api.sorteo.beans.LoginRequest;
import com.api.sorteo.beans.Premios;
import com.api.sorteo.beans.RegisterRequest;
import com.api.sorteo.controller.SorteoController;
import com.api.sorteo.service.SorteoService;

import org.springframework.web.bind.annotation.RequestBody;

@Component
public class ControllerSorteoImpl implements SorteoController {
	@Autowired
	SorteoService sorteoService;

	@Override
	public ResponseEntity<List<Clientes>> getClientes(Integer sorteoId) {
		List<Clientes> result = sorteoService.getClientes(sorteoId);

		return ResponseEntity.ok(result);
	}

	@Override
	public ResponseEntity<List<Sorteos>> getSorteos() {
		List<Sorteos> result = sorteoService.getSorteos();

		return ResponseEntity.ok(result);
	}
	
	@Override
	public ResponseEntity<List<Premios>> getPremios() {
		List<Premios> result = sorteoService.getPremios();

		return ResponseEntity.ok(result);
	}

	@Override
	public ResponseEntity<Sorteos> getSorteoById(Integer sorteoId) {
		Sorteos result = sorteoService.getSorteoById(sorteoId);

		return ResponseEntity.ok(result);
	}

	@Override
	public ResponseEntity<Clientes> realizarSorteo(Integer sorteoId) {
		Clientes result = sorteoService.realizarSorteo(sorteoId);

		return ResponseEntity.ok(result);
	}

	@Override
	public ResponseEntity<Usuarios> getAuthentication(@RequestBody LoginRequest request) {
		Usuarios result = sorteoService.getAuthentication(request);
		return ResponseEntity.ok(result);
	}
	
	@Override
	public ResponseEntity<Boolean> getRegister(@RequestBody RegisterRequest request) {
		Boolean result = sorteoService.getRegister(request);
		return ResponseEntity.ok(result);
	}
	
	@Override
	public ResponseEntity<Premios> getPremioById(Integer premioId) {
		Premios result = sorteoService.getPremioById(premioId);

		return ResponseEntity.ok(result);
	}
	
	@Override
	public ResponseEntity<List<Tickets>> getTicketsStatus(Integer sorteoId) {
		List<Tickets> result = sorteoService.getTicketsStatus(sorteoId);

		return ResponseEntity.ok(result);
	}
}
