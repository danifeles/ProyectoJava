package com.curso.odoo.repositorio;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.curso.odoo.model.Presupuesto;

public interface PresupuestoRepositorio extends JpaRepository<Presupuesto, Integer> {
	
	List<Presupuesto> findByCodigopresupuesto (Integer buscarnumero); 
	List<Presupuesto> findByClienteNombreclienteContaining (String buscarcliente); 
	List<Presupuesto> findByClienteCodigocliente (int codigocliente); 
	List<Presupuesto> findByComercialCodigocomercial (int codigocomercial); 
	List<Presupuesto> findByEstadoCodigoestado(int codigoestado); 
	List<Presupuesto> findByActividadCodigoactividad(int codigoactividad);
	
}
