package com.api.sorteo.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.api.sorteo.beans.Clientes;
import com.api.sorteo.beans.Sorteos;
import com.api.sorteo.controller.SorteoController;
import com.api.sorteo.service.SorteoService;

@Component
public class ControllerSorteoImpl implements SorteoController{
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
	public ResponseEntity<Sorteos> getSorteoById(Integer sorteoId) {
		Sorteos result = sorteoService.getSorteoById(sorteoId);
		
	    return ResponseEntity.ok(result);
	}

	@Override
	public ResponseEntity<Clientes> realizarSorteo(Integer sorteoId) {
		Clientes result = sorteoService.realizarSorteo(sorteoId);

	    return ResponseEntity.ok(result);
	}
}
