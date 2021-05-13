package com.curso.odoo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="actividad")
public class actividad {

		@Id
		//@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer codigoactividad;
		private String nombreactividad;
		public int getCodigoactividad() {
			return codigoactividad;
		}
		public void setCodigoactividad(Integer codigoactividad) {
			this.codigoactividad = codigoactividad;
		}
		public String getNombreactividad() {
			return nombreactividad;
		}
		public void setNombreactividad(String nombreactividad) {
			this.nombreactividad = nombreactividad;
		}
}
