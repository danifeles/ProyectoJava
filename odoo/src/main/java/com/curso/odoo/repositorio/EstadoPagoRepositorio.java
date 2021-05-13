package com.curso.odoo.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curso.odoo.model.estadopago;

public interface EstadoPagoRepositorio extends JpaRepository<estadopago, Integer> {
	
	List<estadopago> findByCodigoestadopago (int codigo);
	List<estadopago> findByNombreestadopagoContaining (String nombre); 
}
