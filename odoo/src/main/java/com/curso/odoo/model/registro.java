package com.curso.odoo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="registro")
public class registro {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer codigoregistro;
		private String nombre;
		private String apellidos;
		private String usuario;
		private String telefono;
		private String correo;
		private String contrasena;
		public Integer getCodigoregistro() {
			return codigoregistro;
		}
		public void setCodigoregistro(Integer codigoregistro) {
			this.codigoregistro = codigoregistro;
		}
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public String getApellidos() {
			return apellidos;
		}
		public void setApellidos(String apellidos) {
			this.apellidos = apellidos;
		}
		public String getUsuario() {
			return usuario;
		}
		public void setUsuario(String usuario) {
			this.usuario = usuario;
		}
		public String getTelefono() {
			return telefono;
		}
		public void setTelefono(String telefono) {
			this.telefono = telefono;
		}
		public String getCorreo() {
			return correo;
		}
		public void setCorreo(String correo) {
			this.correo = correo;
		}
		public String getContrasena() {
			return contrasena;
		}
		public void setContrasena(String contrasena) {
			this.contrasena = contrasena;
		}
		
		

}
