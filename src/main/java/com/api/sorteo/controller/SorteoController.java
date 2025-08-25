package com.api.sorteo.controller;
import com.api.sorteo.beans.*;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface SorteoController {
	ResponseEntity<List<Clientes>>getClientes(Integer sorteoId);
	ResponseEntity<List<Sorteos>>getSorteos();
	ResponseEntity<Clientes> realizarSorteo(Integer sorteoId);
	ResponseEntity<Sorteos> getSorteoById(Integer sorteoId);
}
