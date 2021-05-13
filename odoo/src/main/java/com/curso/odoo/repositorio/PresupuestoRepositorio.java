package com.curso.odoo.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curso.odoo.model.Presupuesto;

public interface PresupuestoRepositorio extends JpaRepository<Presupuesto, Integer> {
	
	List<Presupuesto> findByCodigopresupuesto (Integer buscarnumero); 
	List<Presupuesto> findByClienteNombreclienteContaining (String buscarcliente); 


}
