package com.api.sorteo.mapper;


import org.springframework.jdbc.core.RowMapper;

import com.api.sorteo.beans.Tickets;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class TicketsMapper implements RowMapper<Tickets> {
    @Override
    public Tickets mapRow(ResultSet rs, int rowNum) throws SQLException {
        Tickets ticket = new Tickets();
        ticket.setId(rs.getInt("id"));
        ticket.setPremioId(rs.getInt("premio_id"));
        ticket.setNumero(rs.getInt("numero"));
        ticket.setEstado(rs.getInt("estado"));
        
        int usuarioId = rs.getInt("usuario_id");
        if (!rs.wasNull()) {
        	ticket.setUsuarioId(usuarioId);
        }

        return ticket;
    }
}
