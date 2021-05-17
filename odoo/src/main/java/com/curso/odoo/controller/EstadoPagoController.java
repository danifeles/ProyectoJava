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
import com.curso.odoo.model.estadopago;
import com.curso.odoo.repositorio.EstadoPagoRepositorio;
import com.curso.odoo.repositorio.FacturaRepositorio;
import com.curso.odoo.repositorio.PresupuestoRepositorio;

@Controller
public class EstadoPagoController {
	
	@Autowired
	EstadoPagoRepositorio estrepo2;
	@Autowired
	FacturaRepositorio facrepo;
	@Autowired
	PresupuestoRepositorio prerepo;
	
	@GetMapping("/formestadopago")
    public String FormEstadoPago()
    {
  	  return "FormEstadoPago";
    }
	@PostMapping("/estadopago")
	public String formEstPago(@RequestParam("nombre_estadopago") String c1,
						  @RequestParam("codigo_estadopago") int c2) {
		
		
		com.curso.odoo.model.estadopago estadopago_1 = new com.curso.odoo.model.estadopago();
		
		estadopago_1.setNombreestadopago(c1);
		estadopago_1.setCodigoestadopago(c2);
		
		estrepo2.save(estadopago_1);
		
		return "FormEstadoPago";
	}
	
	@GetMapping("/estadopago")
    public String estadopago(Model modelo)
    {
		List<estadopago> estadopago = estrepo2.findAll();
		
		for (estadopago x: estadopago) {

			x.getCodigoestadopago();
			x.getNombreestadopago();
			
		}
	
		modelo.addAttribute("estadopago", estadopago);
  	  return "estadopago";
    }
	
	@GetMapping("/borrarestadopago/{codigo}")
	public String borrarestadopago(@PathVariable("codigo") String cod)
	{
		List<Factura> listafactura = facrepo.findByEstadopagoCodigoestadopago(Integer.parseInt(cod));
		for(Factura i:listafactura) {
			facrepo.deleteById(i.getCodigofactura());
		}
		
		estrepo2.deleteById(Integer.parseInt(cod));
		System.out.println(cod);
		
		
		return "redirect:/estadopago"; 
		
	}
	@PostMapping("/buscarestadopago")
	public String buscarcodigoestadopago(@Param("buscar") String buscar,@Param("filtro") Integer filtro, Model modelo)
	{
		if (filtro == 1) {
		List<estadopago> est2 = estrepo2.findByCodigoestadopago (Integer.parseInt(buscar));
		
		for (estadopago x: est2) {
			x.getCodigoestadopago();
			x.getNombreestadopago();
		}
	
		modelo.addAttribute("estadopago", est2);
		} else if (filtro == 2) {
			List<estadopago> est2 = estrepo2.findByNombreestadopagoContaining (buscar);

			for (estadopago x: est2) {
				x.getCodigoestadopago();
				x.getNombreestadopago();
			}
		
			modelo.addAttribute("estadopago", est2);
		}
  	  return "estadopago";
    }

		@PostMapping("/nombreestadopago")
		public String nombreestadopago(@Param("buscarestadopago") String buscarestadopago, Model modelo)
				{
			List<estadopago> est2 = estrepo2.findByNombreestadopagoContaining (buscarestadopago);

		for (estadopago x: est2) {
			x.getCodigoestadopago();
			x.getNombreestadopago();
		}
	
		modelo.addAttribute("estadopago", est2);
  	  return "estadopago";
    }
}
