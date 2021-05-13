package com.curso.odoo.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curso.odoo.model.comercial;

public interface ComercialRepositorio extends JpaRepository<comercial, Integer> {

	List<comercial> findByCodigocomercial (int codigo);
	List<comercial> findByNombrecomercialContaining(String nombre); 
}
