package com.api.sorteo.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.api.sorteo.beans.Usuarios;

public class UsuarioMapper implements RowMapper<Usuarios> {

	@Override
	public Usuarios mapRow(ResultSet rs, int rowNum) throws SQLException {
		Usuarios u = new Usuarios();
		u.setId(rs.getInt("id"));
		u.setUsuario(rs.getString("correo"));
		u.setNombre(rs.getString("nombre"));
		u.setPassword(rs.getString("contrasena"));
		
		return u;
	}
}
