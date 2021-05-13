package com.curso.odoo.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.curso.odoo.model.Factura;

public interface FacturaRepositorio extends JpaRepository<Factura, Integer>{
		
	List<Factura> findByCodigofactura (int codigo);
	List<Factura> findByClienteNombreclienteContaining (String nombre); 

}
