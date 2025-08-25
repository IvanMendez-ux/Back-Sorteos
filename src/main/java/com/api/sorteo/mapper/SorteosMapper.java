package com.api.sorteo.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.api.sorteo.beans.Sorteos;

public class SorteosMapper implements RowMapper<Sorteos>{

	@Override
	public Sorteos mapRow(ResultSet rs, int i) throws SQLException {
		Sorteos sorteos = new Sorteos();
		sorteos.setPremio(rs.getString("PREMIO"));;
		sorteos.setId(rs.getInt("ID"));
		sorteos.setGenero(rs.getString("genero"));

		return sorteos;
	}
	
}