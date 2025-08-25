package com.api.sorteo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.sorteo.beans.Clientes;
import com.api.sorteo.beans.Sorteos;
import com.api.sorteo.dao.SorteoDAO;
import com.api.sorteo.service.SorteoService;

@Service 
public class SorteoServiceImpl implements SorteoService {
	@Autowired
	SorteoDAO sorteoDao;
	
	@Override
	public List<Clientes> getClientes(Integer sorteoId) {
		List<Clientes> clientes = new ArrayList<>(); 
		clientes = sorteoDao.getClientes(sorteoId);
		return clientes;
	} 

	@Override
	public List<Sorteos> getSorteos() {
		List<Sorteos> sorteos = new ArrayList<>(); 
		sorteos = sorteoDao.getSorteos();
		return sorteos;
	} 
	
	@Override
	public Sorteos getSorteoById(Integer sorteoId) {
		Sorteos sorteos = new Sorteos(); 
		sorteos = sorteoDao.getSorteoById(sorteoId);
		return sorteos;
	} 
	
	@Override
	public Clientes realizarSorteo(Integer idSorteo) {
        List<Clientes> participantes = sorteoDao.getClientes(idSorteo);
        
        Sorteos sorteo = sorteoDao.getSorteoById(idSorteo);

        if (participantes.isEmpty()) {
            throw new RuntimeException("No hay participantes en el sorteo " + idSorteo);
        }
        
        List<Clientes> candidatos = participantes.stream()
                .filter(c -> sorteo.getGenero().equalsIgnoreCase("U") || 
                             sorteo.getGenero().equalsIgnoreCase(c.getSexo()))
                .toList();

        Random random = new Random();
        int indexGanador = random.nextInt(candidatos.size());

        return participantes.get(indexGanador);
    }
}
