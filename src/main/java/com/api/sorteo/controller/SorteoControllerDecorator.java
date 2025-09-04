package com.api.sorteo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.sorteo.beans.Clientes;
import com.api.sorteo.beans.ErrorResponse;
import com.api.sorteo.beans.InvalidLoginException;
import com.api.sorteo.beans.Sorteos;
import com.api.sorteo.beans.Tickets;
import com.api.sorteo.beans.Usuarios;
import com.api.sorteo.beans.LoginRequest;
import com.api.sorteo.beans.Premios;
import com.api.sorteo.beans.RegisterRequest;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/${version}/sorteo")
public class SorteoControllerDecorator implements SorteoController {

	@Autowired
	SorteoController sorteoControllerDelegate;

	private static final Logger logger = LoggerFactory.getLogger(SorteoControllerDecorator.class);

	@GetMapping(value = "/clientes", produces = { MediaType.APPLICATION_JSON_VALUE })
	@Operation(description = "Obtener lista de participantes")
	@Override
	public ResponseEntity<List<Clientes>> getClientes(@RequestParam(value = "sorteo_id") Integer sorteoId) {
		return sorteoControllerDelegate.getClientes(sorteoId);
	}

	@GetMapping(value = "/sorteos", produces = { MediaType.APPLICATION_JSON_VALUE })
	@Operation(description = "Obtener lista de sorteos")
	@Override
	public ResponseEntity<List<Sorteos>> getSorteos() {
		return sorteoControllerDelegate.getSorteos();
	}
	
	@GetMapping(value = "/premios", produces = { MediaType.APPLICATION_JSON_VALUE })
	@Operation(description = "Obtener lista de sorteos")
	@Override
	public ResponseEntity<List<Premios>> getPremios() {
		return sorteoControllerDelegate.getPremios();
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
	public ResponseEntity<Clientes> realizarSorteo(@RequestParam(value = "sorteo_id") Integer sorteoId) {
		return sorteoControllerDelegate.realizarSorteo(sorteoId);
	}

	@PostMapping(value = "/login", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@Operation(description = "Validamos el login")
	@Override
	public ResponseEntity getAuthentication(@RequestBody LoginRequest request) {
		try {
			ResponseEntity<Usuarios> usuario = sorteoControllerDelegate.getAuthentication(request);
			return ResponseEntity.ok(usuario);
		} catch (InvalidLoginException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(new ErrorResponse(e.getStatus(), e.getMessage())); 
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno");
		}
	}
	
	@PostMapping(value = "/register", consumes = { MediaType.APPLICATION_JSON_VALUE }, 
			produces = {MediaType.APPLICATION_JSON_VALUE})
	@Operation(description = "Registramos al usuario")
	@Override
	public ResponseEntity<Boolean> getRegister(@RequestBody RegisterRequest request) {
		ResponseEntity<Boolean> register = sorteoControllerDelegate.getRegister(request);
		return register;
	}
	
	@GetMapping(value = "/premioById", produces = { MediaType.APPLICATION_JSON_VALUE })
	@Operation(description = "Obtener lista de sorteos")
	@Override
	public ResponseEntity<Premios> getPremioById(@RequestParam(value = "premio_id") Integer sorteoId) {
		return sorteoControllerDelegate.getPremioById(sorteoId);
	}
	@GetMapping(value = "/tickets", produces = { MediaType.APPLICATION_JSON_VALUE })
	@Operation(description = "Obtener lista de tickets")
	@Override
	public ResponseEntity<List<Tickets>> getTicketsStatus(@RequestParam(value =  "premio_id") Integer sorteoId) {
		return sorteoControllerDelegate.getTicketsStatus(sorteoId);
	}
	
}
