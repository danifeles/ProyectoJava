package com.curso.odoo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="paises")
public class Pais {
	
	@Id
	private Integer codigopais;
	private String nombrepais;
	
	
	public int getCodigopais() {
		return codigopais;
	}
	public void setCodigopais(int codigopais) {
		this.codigopais = codigopais;
	}
	public String getNombrepais() {
		return nombrepais;
	}
	public void setNombrepais(String nombrepais) {
		this.nombrepais = nombrepais.toUpperCase();
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "El codigo del pais es: "+getCodigopais()+" y el nombre del pais es: "+getNombrepais();
	}
	
	
	
	

}
