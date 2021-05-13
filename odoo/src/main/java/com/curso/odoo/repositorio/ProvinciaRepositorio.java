package com.curso.odoo.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curso.odoo.model.Provincia;

public interface ProvinciaRepositorio extends JpaRepository<Provincia, Integer>{
	List<Provincia> findByCodigoprovincia(int codigo);
	List<Provincia> findByNombreprovincia (String nombre); 
}

