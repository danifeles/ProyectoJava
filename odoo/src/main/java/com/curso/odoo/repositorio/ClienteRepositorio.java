package com.curso.odoo.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curso.odoo.model.Cliente;

public interface ClienteRepositorio extends JpaRepository<Cliente, Integer>{
	List<Cliente> findByCodigocliente (int codigo);
	List<Cliente> findByNombreclienteContaining(String nombre);
}
