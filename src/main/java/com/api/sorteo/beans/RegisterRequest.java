package com.api.sorteo.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class RegisterRequest {
	@JsonProperty("nombreApellido")
	String nombre;
	@JsonProperty("sexo")
	String sexo;
	@JsonProperty("nroDocumento")
	String ci;
	@JsonProperty("correo")	
	String correo;
	@JsonProperty("telefono")
	String nroTelefono;
	@JsonProperty("password")
	String contrasena;
	        
}
