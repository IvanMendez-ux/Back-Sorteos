package com.api.sorteo.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.api.sorteo.beans.Clientes;

public class ClientsMapper implements RowMapper<Clientes> {

	@Override
	public Clientes mapRow(ResultSet rs, int i) throws SQLException {
		Clientes clientes = new Clientes();
		clientes.setNombre(rs.getString("NOMBRE"));
		clientes.setSexo(rs.getString("SEXO"));
		clientes.setId(rs.getInt("ID"));
		clientes.setId_sorteo(rs.getInt("ID_SORTEO"));

		return clientes;
	}

}
