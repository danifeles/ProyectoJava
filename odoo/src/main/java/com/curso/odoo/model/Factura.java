package com.curso.odoo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="factura")
public class Factura {

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codigofactura;
	private String fechafactura;
	private String fechavencimiento;
	private Integer impuestos;
	private Integer total;
	@OneToOne
	@JoinColumn(name="codigocliente")
	private Cliente cliente;
	@OneToOne
	@JoinColumn(name="codigoactividad")
	private actividad actividad;
	@OneToOne
	@JoinColumn(name="codigoestado")
	private estado estado;
	@OneToOne
	@JoinColumn(name="codigoestadopago")
	private estadopago estadopago;
	
	
	public Integer getCodigofactura() {
		return codigofactura;
	}
	public void setCodigofactura(Integer codigofactura) {
		this.codigofactura = codigofactura;
	}
	public String getFechafactura() {
		return fechafactura;
	}
	public void setFechafactura(String fechafactura) {
		this.fechafactura = fechafactura;
	}
	public String getFechavencimiento() {
		return fechavencimiento;
	}
	public void setFechavencimiento(String fechavencimiento) {
		this.fechavencimiento = fechavencimiento;
	}
	public Integer getImpuestos() {
		return impuestos;
	}
	public void setImpuestos(Integer impuestos) {
		this.impuestos = impuestos;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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
	public estadopago getEstadopago() {
		return estadopago;
	}
	public void setEstadopago(estadopago estadopago) {
		this.estadopago = estadopago;
	}
}
