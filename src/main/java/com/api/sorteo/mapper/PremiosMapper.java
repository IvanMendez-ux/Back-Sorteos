package com.api.sorteo.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

import org.springframework.jdbc.core.RowMapper;

import com.api.sorteo.beans.Premios;

public class PremiosMapper implements RowMapper<Premios> {

	@Override
	public Premios mapRow(ResultSet rs, int i) throws SQLException {
		Premios premios = new Premios();
		premios.setNombre(rs.getString("nombre"));
		premios.setId(rs.getInt("id"));
		premios.setDescripcion(rs.getString("descripcion"));
		byte[] bytes = rs.getBytes("imagen");
		if (bytes != null) {
			premios.setImagen(Base64.getEncoder().encodeToString(bytes)); 
		}
		premios.setPrecio(rs.getString("precio_ticket"));
		premios.setEstado(rs.getInt("estado"));
		premios.setCant_tickets(rs.getInt("total_tickets"));
		
		return premios;
	}
}