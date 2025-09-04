package com.api.sorteo.dao;

import java.util.List;

import com.api.sorteo.beans.Clientes;
import com.api.sorteo.beans.Premios;
import com.api.sorteo.beans.RegisterRequest;
import com.api.sorteo.beans.Sorteos;
import com.api.sorteo.beans.Tickets;
import com.api.sorteo.beans.Usuarios;

public interface SorteoDAO {
	List<Clientes> getClientes(Integer sorteoId);

	List<Sorteos> getSorteos();
	
	List<Premios> getPremios();

	Sorteos getSorteoById(Integer idSorteo);

	Usuarios getLogin(String user, String password);

	Boolean getRegister(RegisterRequest request);
	
	Premios getPremioById(Integer premioId);

	List<Tickets> getTicketsStatus(Integer sorteoId);
	
}
