package com.api.sorteo.beans;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Premios {
 Integer id;
 String nombre;
 String descripcion;
 String precio;
 String imagen;
 Integer estado;
 Integer cant_tickets;
}
