package com.api.sorteo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.sorteo.beans.Clientes;
import com.api.sorteo.beans.Sorteos;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/${version}/sorteo")
public class SorteoControllerDecorator implements SorteoController{

	@Autowired
	SorteoController sorteoControllerDelegate;
	
	@GetMapping(value = "/clientes", produces = { MediaType.APPLICATION_JSON_VALUE })
	@Operation(description = "Obtener lista de participantes")
	@Override
	public ResponseEntity<List<Clientes>> getClientes(@RequestParam(value = "sorteo_id")Integer sorteoId) {
		return sorteoControllerDelegate.getClientes(sorteoId);
	}
	
	@GetMapping(value = "/sorteos", produces = { MediaType.APPLICATION_JSON_VALUE })
	@Operation(description = "Obtener lista de sorteos")
	@Override
	public ResponseEntity<List<Sorteos>> getSorteos() {
		return sorteoControllerDelegate.getSorteos();
	}
	
	@GetMapping(value = "/sorteosById", produces = { MediaType.APPLICATION_JSON_VALUE })
	@Operation(description = "Obtener lista de sorteos")
	@Override
	public ResponseEntity<Sorteos> getSorteoById(Integer sorteoId) {
		return sorteoControllerDelegate.getSorteoById(sorteoId);
	}
	
	@GetMapping(value = "/realizarSorteo", produces = { MediaType.APPLICATION_JSON_VALUE })
	@Operation(description = "Realizamos el sorteo")
	@Override
	public ResponseEntity<Clientes> realizarSorteo(@RequestParam(value = "sorteo_id")Integer sorteoId) {
		return sorteoControllerDelegate.realizarSorteo(sorteoId);
	}

}
