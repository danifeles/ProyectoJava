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
import com.curso.odoo.model.actividad;
import com.curso.odoo.repositorio.ActividadRepositorio;
import com.curso.odoo.repositorio.FacturaRepositorio;
import com.curso.odoo.repositorio.PresupuestoRepositorio;

@Controller
public class ActividadController {
	
	@Autowired
	ActividadRepositorio actrepo1;
	@Autowired
	FacturaRepositorio facrepo;
	@Autowired
	PresupuestoRepositorio prerepo;
	
	@PostMapping("/actividad")
	public String formPost(@RequestParam("nombre_actividad") String c1,
						  @RequestParam("codigo_actividad") int c2) {
		
		
		com.curso.odoo.model.actividad actividad_1 = new com.curso.odoo.model.actividad();
		
		actividad_1.setNombreactividad(c1);
		actividad_1.setCodigoactividad(c2);
		
		actrepo1.save(actividad_1);
		
		return "Formactividad";
	}
	
	@GetMapping("/formactividad")
    public String Formactividad()
    {
  	  return "Formactividad";
    }
	@GetMapping("/actividad")
    public String actividad(Model modelo)
    {
		Iterable<actividad> actividad = actrepo1.findAll();
		
		for (actividad x: actividad) {

			x.getCodigoactividad();
			x.getNombreactividad();
			
		}
	
		modelo.addAttribute("actividad", actividad);
  	  return "actividad";
    }
	
	@GetMapping("/borraractividad/{codigo}")
	public String borraractividad(@PathVariable("codigo") String cod)
	{
		List<Factura> listafactura = facrepo.findByActividadCodigoactividad(Integer.parseInt(cod));
		for(Factura i:listafactura) {
			facrepo.deleteById(i.getCodigofactura());
		}
		
		List<Presupuesto> listaventas = prerepo.findByActividadCodigoactividad(Integer.parseInt(cod));
		for(Presupuesto i:listaventas) {
			prerepo.deleteById(i.getCodigopresupuesto());
		}
		actrepo1.deleteById(Integer.parseInt(cod));		
		
		return "redirect:/actividad"; 
		
	}
	@PostMapping("/buscaractividad")
	public String buscaractividad(@Param("buscar") String buscar,@Param("filtro") Integer filtro, Model modelo)
	{
		if(filtro == 1) {
		List<actividad> act1 = actrepo1.findByCodigoactividad (Integer.parseInt(buscar));

		
		for (actividad x: act1) {
			x.getCodigoactividad();
			x.getNombreactividad();
		}
	
		modelo.addAttribute("actividad", act1);
		} else if (filtro == 2) {
			List<actividad> act1 = actrepo1.findByNombreactividadContaining (buscar);

			for (actividad x: act1) {
				x.getCodigoactividad();
				x.getNombreactividad();
			}
		
			modelo.addAttribute("actividad", act1);
			
		} else if(filtro == 0){
			Iterable<actividad> actividad = actrepo1.findAll();
			
			for (actividad x: actividad) {

				x.getCodigoactividad();
				x.getNombreactividad();
				
			}
		
			modelo.addAttribute("actividad", actividad);
		}
		
  	  return "actividad";
    }

}
