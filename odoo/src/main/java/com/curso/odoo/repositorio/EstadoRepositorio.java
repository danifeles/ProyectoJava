package com.curso.odoo.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curso.odoo.model.estado;

public interface EstadoRepositorio extends JpaRepository<estado, Integer> {
	
	List<estado> findByCodigoestado (int codigo);
	List<estado> findByNombreestadoContaining(String nombre); 
}
