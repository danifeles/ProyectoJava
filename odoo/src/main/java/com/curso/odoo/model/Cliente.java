package com.curso.odoo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="cliente")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codigocliente;
	private String nombrecliente;
	private String apellidoscliente;
	private String tipocliente;
	private String photo;
	
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public void setCodigopostal(Integer codigopostal) {
		this.codigopostal = codigopostal;
	}
	@OneToOne
	@JoinColumn(name="codigoprovincia")
	private Provincia provincia;
	@OneToOne
	@JoinColumn(name="codigopais")
	private Pais pais;
	
	public Provincia getProvincia() {
		return provincia;
	}
	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}
	public Pais getPais() {
		return pais;
	}
	public void setPais(Pais pais) {
		this.pais = pais;
	}
	private String calle;
	private String calle1;
	private String ciudad;
	private Integer codigopostal;
	private String nif;
	private String telefono;
	private String movil;
	private String email;
	private String paginaweb;
	private String categorias;
	
	public void setCodigocliente(Integer codigocliente) {
		this.codigocliente = codigocliente;
	}
	
	public Integer getCodigocliente() {
		return codigocliente;
	}
	public String getNombrecliente() {
		return nombrecliente;
	}
	public void setNombrecliente(String nombrecliente) {
		this.nombrecliente = nombrecliente;
	}
	public String getApellidoscliente() {
		return apellidoscliente;
	}
	public void setApellidoscliente(String apellidoscliente) {
		this.apellidoscliente = apellidoscliente;
	}
	public String getTipocliente() {
		return tipocliente;
	}
	public void setTipocliente(String tipocliente) {
		this.tipocliente = tipocliente;
	}
	
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public String getCalle1() {
		return calle1;
	}
	public void setCalle1(String calle1) {
		this.calle1 = calle1;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public int getCodigopostal() {
		return codigopostal;
	}
	
	public String getNif() {
		return nif;
	}
	public void setNif(String nif) {
		this.nif = nif;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getMovil() {
		return movil;
	}
	public void setMovil(String movil) {
		this.movil = movil;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPaginaweb() {
		return paginaweb;
	}
	public void setPaginaweb(String paginaweb) {
		this.paginaweb = paginaweb;
	}
	public String getCategorias() {
		return categorias;
	}
	public void setCategorias(String categorias) {
		this.categorias = categorias;
	}
}
