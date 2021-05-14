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
import com.curso.odoo.model.comercial;
import com.curso.odoo.repositorio.ComercialRepositorio;
import com.curso.odoo.repositorio.FacturaRepositorio;
import com.curso.odoo.repositorio.PresupuestoRepositorio;

@Controller
public class ComercialController {
	
	@Autowired
	ComercialRepositorio comrepo1;
	@Autowired
	FacturaRepositorio facrepo;
	@Autowired
	PresupuestoRepositorio prerepo;
	
	@PostMapping("/comercial")
	public String formCom(@RequestParam("nombre_comercial") String c1, @RequestParam("apellidos_comercial") String c2,
						  @RequestParam("codigo_comercial") int c3) {
		
		
		com.curso.odoo.model.comercial comercial_1 = new com.curso.odoo.model.comercial();
		
		comercial_1.setNombrecomercial(c1);
		comercial_1.setApellidoscomercial(c2);
		comercial_1.setCodigocomercial(c3);
		
		comrepo1.save(comercial_1);
		
		return "Formcomercial";
	}
	@GetMapping("/formcomercial")
    public String Formcomercial()
    {
  	  return "Formcomercial";
    }
	
	@GetMapping("/comercial")
    public String comercial(Model modelo)
    {
		List<comercial> comercial = comrepo1.findAll();
		
		for (comercial x: comercial) {

			x.getCodigocomercial();
			x.getNombrecomercial();
			
		}
	
		modelo.addAttribute("comercial", comercial);
  	  return "comercial";
    }
	
	@GetMapping("/borrarcomercial/{codigo}")
	public String borrarcomercial(@PathVariable("codigo") String cod)
	{
		
		List<Presupuesto> listaventas = prerepo.findByComercialCodigocomercial(Integer.parseInt(cod));
		for(Presupuesto i:listaventas) {
			prerepo.deleteById(i.getCodigopresupuesto());
		}
		comrepo1.deleteById(Integer.parseInt(cod));
		System.out.println(cod);
		
		
		return "redirect:/comercial"; 
		
	}
	@PostMapping("/buscarcodigocomercial")
	public String buscarcodigocomercial(@Param("codigocomercial") int codigocomercial, Model modelo)
	{
		
		List<comercial> com1 = comrepo1.findByCodigocomercial (codigocomercial);

		
		for (comercial x: com1) {
			x.getCodigocomercial();
			x.getNombrecomercial();
		}
	
		modelo.addAttribute("comercial", com1);
  	  return "comercial";
    }

		@PostMapping("/nombrecomercial")
		public String nombrecomercial(@Param("buscarcomercial") String buscarcomercial, Model modelo)
				{
			List<comercial> com1 = comrepo1.findByNombrecomercialContaining (buscarcomercial);

		for (comercial x: com1) {
			x.getCodigocomercial();
			x.getNombrecomercial();
		}
	
		modelo.addAttribute("comercial", com1);
  	  return "comercial";
    }
}
