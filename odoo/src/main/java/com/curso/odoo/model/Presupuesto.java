package com.curso.odoo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="presupuesto")
public class Presupuesto {
	
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codigopresupuesto;
	private String fechapresupuesto;
	private int total;
	@OneToOne
	@JoinColumn(name="codigocliente")
	private Cliente cliente;
	@OneToOne
	@JoinColumn(name="codigocomercial")
	private comercial comercial;
	@OneToOne
	@JoinColumn(name="codigoactividad")
	private actividad actividad;
	@OneToOne
	@JoinColumn(name="codigoestado")
	private estado estado;
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public comercial getComercial() {
		return comercial;
	}

	public void setComercial(comercial comercial) {
		this.comercial = comercial;
	}

	public actividad getActividad() {
		return actividad;
	}

	public void setActividad(actividad actividad) {
		this.actividad = actividad;
	}

	public estado getEstado() {
		return estado;
	}

	public void setEstado(estado estado) {
		this.estado = estado;
	}

	public void setCodigopresupuesto(Integer codigopresupuesto) {
		this.codigopresupuesto = codigopresupuesto;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	public int getCodigopresupuesto() {
		return codigopresupuesto;
	}
	
	public String getFechapresupuesto() {
		return fechapresupuesto;
	}
	public void setFechapresupuesto(String fechapresupuesto) {
		this.fechapresupuesto = fechapresupuesto;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
}

