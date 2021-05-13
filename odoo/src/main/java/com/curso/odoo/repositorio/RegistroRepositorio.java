package com.curso.odoo.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curso.odoo.model.registro;

public interface RegistroRepositorio extends JpaRepository<registro, Integer>{
	
	Optional<registro> findByUsuario (String usuario); 
	Optional<registro> findByContrasena (String contrasena); 
}
