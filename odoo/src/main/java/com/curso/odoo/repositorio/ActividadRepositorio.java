package com.curso.odoo.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curso.odoo.model.actividad;

public interface ActividadRepositorio extends JpaRepository<actividad, Integer> {
	
	List<actividad> findByCodigoactividad (int codigo);
	List<actividad> findByNombreactividadContaining(String nombre); 

}
