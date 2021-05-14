package com.curso.odoo.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.curso.odoo.model.Factura;
import com.curso.odoo.model.Presupuesto;

public interface FacturaRepositorio extends JpaRepository<Factura, Integer>{
		
	List<Factura> findByCodigofactura (int codigo);
	List<Factura> findByClienteNombreclienteContaining (String nombre); 
	List<Factura> findByClienteCodigocliente (int codigocliente);
	List<Factura> findByEstadoCodigoestado(int codigoestado); 
	List<Factura> findByActividadCodigoactividad(int codigoactividad);
	List<Factura> findByEstadopagoCodigoestadopago(int codigoactividad);

}
