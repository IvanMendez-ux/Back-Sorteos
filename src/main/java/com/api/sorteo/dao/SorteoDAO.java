package com.api.sorteo.dao;

import java.util.List;

import com.api.sorteo.beans.Clientes;
import com.api.sorteo.beans.Sorteos;

public interface SorteoDAO {
	List<Clientes> getClientes(Integer sorteoId);
	List<Sorteos> getSorteos();
	Sorteos getSorteoById(Integer idSorteo);
}
