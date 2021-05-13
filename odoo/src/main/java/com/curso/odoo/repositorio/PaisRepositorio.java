package com.curso.odoo.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curso.odoo.model.Pais;

public interface PaisRepositorio extends JpaRepository<Pais, Integer> {
	
	List<Pais> findByCodigopais (Integer codigo);
	List<Pais> findByNombrepais (String nombre); 

}
