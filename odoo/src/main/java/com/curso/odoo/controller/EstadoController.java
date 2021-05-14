package com.curso.odoo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.curso.odoo.model.Factura;
import com.curso.odoo.model.Presupuesto;
import com.curso.odoo.model.estado;
import com.curso.odoo.repositorio.EstadoRepositorio;
import com.curso.odoo.repositorio.FacturaRepositorio;
import com.curso.odoo.repositorio.PresupuestoRepositorio;

@Controller
public class EstadoController {
	
	@Autowired
	EstadoRepositorio estrepo1;
	@Autowired
	FacturaRepositorio facrepo;
	@Autowired
	PresupuestoRepositorio prerepo;
	
	@PostMapping("/estado")
	public String formEst(@RequestParam("nombre_estado") String c1,
						  @RequestParam("codigo_estado") int c2) {
		
		
		com.curso.odoo.model.estado estado_1 = new com.curso.odoo.model.estado();
		
		estado_1.setNombreestado(c1);
		estado_1.setCodigoestado(c2);
		
		estrepo1.save(estado_1);
		
		return "FormEstado";
	}
	@GetMapping("/formestado")
    public String FormEstado()
    {
  	  return "FormEstado";
    }
	
	@GetMapping("/estado")
    public String estado(Model modelo)
    {
		List<estado> estado = estrepo1.findAll();
		
		for (estado x: estado) {

			x.getCodigoestado();
			x.getNombreestado();
			
		}
	
		modelo.addAttribute("estado", estado);
  	  return "estado";
    }
	
	@GetMapping("/borrarestado/{codigo}")
	public String borrarestado(@PathVariable("codigo") String cod)
	{
		List<Factura> listafactura = facrepo.findByEstadoCodigoestado(Integer.parseInt(cod));
		for(Factura i:listafactura) {
			facrepo.deleteById(i.getCodigofactura());
		}
		
		List<Presupuesto> listaventas = prerepo.findByEstadoCodigoestado(Integer.parseInt(cod));
		for(Presupuesto i:listaventas) {
			prerepo.deleteById(i.getCodigopresupuesto());
		}
		estrepo1.deleteById(Integer.parseInt(cod));
		
		
		return "redirect:/estado"; 
		
	}
	@PostMapping("/buscarcodigoestado")
	public String buscarcodigoestado(@Param("codigoestado") int codigoestado, Model modelo)
	{
		
		List<estado> est1 = estrepo1.findByCodigoestado (codigoestado);

		
		for (estado x: est1) {
			x.getCodigoestado();
			x.getNombreestado();
		}
	
		modelo.addAttribute("estado", est1);
  	  return "estado";
    }

		@PostMapping("/nombreestado")
		public String nombreestado(@Param("buscarestado") String buscarestado, Model modelo)
				{
			List<estado> est1 = estrepo1.findByNombreestadoContaining (buscarestado);

		for (estado x: est1) {
			x.getCodigoestado();
			x.getNombreestado();
		}
	
		modelo.addAttribute("estado", est1);
  	  return "estado";
    }
}
