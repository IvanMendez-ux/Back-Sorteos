package com.api.sorteo.service;

import java.util.List;

import com.api.sorteo.beans.*;

public interface SorteoService {
	List<Clientes> getClientes(Integer sorteoId);

	List<Sorteos> getSorteos();

	Clientes realizarSorteo(Integer sorteoId);

	Sorteos getSorteoById(Integer sorteoId);

	Usuarios getAuthentication(LoginRequest request);
	
	Boolean getRegister(RegisterRequest request);

	List<Premios> getPremios();
	
	Premios getPremioById(Integer premioId);
	
	List<Tickets> getTicketsStatus(Integer sorteoId);
}
