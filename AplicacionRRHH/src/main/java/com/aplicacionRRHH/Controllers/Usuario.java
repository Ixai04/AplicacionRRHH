package com.aplicacionRRHH.Controllers;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="usuario")
public class Usuario {

	Long id;
	String nombre;
	String rol;
}
