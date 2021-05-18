package com.curso.odoo.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.curso.odoo.model.Cliente;
import com.curso.odoo.model.Factura;
import com.curso.odoo.model.Presupuesto;
import com.curso.odoo.model.actividad;
import com.curso.odoo.repositorio.ActividadRepositorio;
import com.curso.odoo.repositorio.FacturaRepositorio;
import com.curso.odoo.repositorio.PresupuestoRepositorio;
import com.curso.odoo.service.api.ActividadServicesAPI;
import com.curso.odoo.service.api.ClienteServicesAPI;

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
	
	
	
	@Autowired
	private  ActividadServicesAPI  actiServiceAPI;
	
	@GetMapping("/actividad")
    public String  FormClientes (@RequestParam Map<String, Object> params, Model modelo)
    {
		
       int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;
		
		PageRequest pageRequest = PageRequest.of(page, 10);
		
		Page<actividad> pagePersona = actiServiceAPI.getAll(pageRequest);
		int totalPage = pagePersona.getTotalPages();
		if(totalPage > 0) {
			List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
			modelo.addAttribute("pages", pages);
		}
		System.out.println("Hola");

		
		
		modelo.addAttribute("actividad", pagePersona.getContent());
		modelo.addAttribute("current", page + 1);
		modelo.addAttribute("next", page + 2);
		modelo.addAttribute("prev", page);
		modelo.addAttribute("last", totalPage);
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
	
	@GetMapping("/FormActividadEditar/{codigo}")
	public String editarmodal(@PathVariable("codigo") String cod, Model modelo)
	{	
		
		List<com.curso.odoo.model.actividad> act = actrepo1.findByCodigoactividad(Integer.parseInt(cod));
		for (actividad x: act) {

			x.getCodigoactividad();
			x.getNombreactividad();
		}
		
		modelo.addAttribute("editaractividad", act);
		
		List<com.curso.odoo.model.actividad> act1 = actrepo1.findAll();
		
		for (actividad x: act1) {

			x.getCodigoactividad();
			x.getNombreactividad();
		}
	
		modelo.addAttribute("actividad", act1);

		return "FormActividadEditar";
	}
	
	@GetMapping ("FormActividadEditar") 
	public String FormActividadEditar() {
		return "FormActividadEditar";
	}

}
