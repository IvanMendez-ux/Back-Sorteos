package com.api.sorteo.service;
import java.util.List;

import com.api.sorteo.beans.*;

public interface SorteoService {
	List<Clientes> getClientes(Integer sorteoId);
	List<Sorteos> getSorteos();
	Clientes realizarSorteo(Integer sorteoId);
	Sorteos getSorteoById(Integer sorteoId);
}
