package com.api.sorteo.beans;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Tickets {
	private Integer id;
	private Integer premioId;
	private Integer numero;
	private Integer estado;
	private Integer usuarioId;
	private String codigoPago;

}
