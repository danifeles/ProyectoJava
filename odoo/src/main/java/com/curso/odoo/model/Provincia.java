package com.curso.odoo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="provincias")
public class Provincia {
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codigoprovincia;
	private String nombreprovincia;

	public int getCodigoprovincia() {
		return codigoprovincia;
	}

	public void setCodigoProvincia(int codigoProvincia) {
		this.codigoprovincia = codigoProvincia;
	}

	public String getNombreprovincia() {
		return nombreprovincia;
	}

	public void setNombreprovincia(String nombreProvincia) {
		this.nombreprovincia = nombreProvincia;
	}

}