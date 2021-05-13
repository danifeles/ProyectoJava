package com.curso.odoo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="estadopago")
public class estadopago {

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigoestadopago;
	private String nombreestadopago;
	public int getCodigoestadopago() {
		return codigoestadopago;
	}
	public void setCodigoestadopago(int codigoestadopago) {
		this.codigoestadopago = codigoestadopago;
	}
	public String getNombreestadopago() {
		return nombreestadopago;
	}
	public void setNombreestadopago(String nombreestadopago) {
		this.nombreestadopago = nombreestadopago;
	}  
}
