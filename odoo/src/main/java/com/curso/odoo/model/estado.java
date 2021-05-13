package com.curso.odoo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="estado")
public class estado {
	
	@Id
	private Integer codigoestado;
	private String nombreestado;
	public int getCodigoestado() {
		return codigoestado;
	}
	public void setCodigoestado(Integer codigoestado) {
		this.codigoestado = codigoestado;
	}
	public String getNombreestado() {
		return nombreestado;
	}
	public void setNombreestado(String nombreestado) {
		this.nombreestado = nombreestado;
	} 
}

