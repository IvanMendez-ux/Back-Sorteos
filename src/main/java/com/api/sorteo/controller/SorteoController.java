package com.api.sorteo.controller;

import com.api.sorteo.beans.*;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface SorteoController {
	ResponseEntity<List<Clientes>> getClientes(Integer sorteoId);

	ResponseEntity<List<Sorteos>> getSorteos();

	ResponseEntity<Clientes> realizarSorteo(Integer sorteoId);

	ResponseEntity<Sorteos> getSorteoById(Integer sorteoId);

	ResponseEntity<Usuarios> getAuthentication(@RequestBody LoginRequest request);
	
	ResponseEntity<Boolean> getRegister(@RequestBody RegisterRequest request);

	ResponseEntity<List<Premios>> getPremios();
	
	ResponseEntity<Premios> getPremioById(@RequestParam Integer premioId);
	
	ResponseEntity<List<Tickets>> getTicketsStatus(@RequestParam Integer sorteoId);
}
