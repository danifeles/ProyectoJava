package com.curso.odoo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="comercial")
public class comercial {
		
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codigocomercial;
	private String nombrecomercial;
	private String apellidoscomercial;

	public String getApellidoscomercial() {
		return apellidoscomercial;
	}
	public void setApellidoscomercial(String apellidoscomercial) {
		this.apellidoscomercial = apellidoscomercial;
	}
	public int getCodigocomercial() {
		return codigocomercial;
	}
	public void setCodigocomercial(Integer codigocomercial) {
		this.codigocomercial = codigocomercial;
	}
	public String getNombrecomercial() {
		return nombrecomercial;
	}
	public void setNombrecomercial(String nombrecomercial) {
		this.nombrecomercial = nombrecomercial;
	} 
}
