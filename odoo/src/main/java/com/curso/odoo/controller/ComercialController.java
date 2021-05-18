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
import com.curso.odoo.model.comercial;
import com.curso.odoo.repositorio.ComercialRepositorio;
import com.curso.odoo.repositorio.FacturaRepositorio;
import com.curso.odoo.repositorio.PresupuestoRepositorio;
import com.curso.odoo.service.api.ClienteServicesAPI;
import com.curso.odoo.service.api.ComercialServicesAPI;

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
	
	@Autowired
	private  ComercialServicesAPI  comerServiceAPI;
	
	@GetMapping("/comercial")
    public String  Formcomercial (@RequestParam Map<String, Object> params, Model modelo)
    {
		
       int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;
		
		PageRequest pageRequest = PageRequest.of(page, 10);
		
		Page<comercial> pagePersona = comerServiceAPI.getAll(pageRequest);
		int totalPage = pagePersona.getTotalPages();
		if(totalPage > 0) {
			List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
			modelo.addAttribute("pages", pages);
		}
		System.out.println("Hola");

		
		
		modelo.addAttribute("comercial", pagePersona.getContent());
		modelo.addAttribute("current", page + 1);
		modelo.addAttribute("next", page + 2);
		modelo.addAttribute("prev", page);
		modelo.addAttribute("last", totalPage);
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
	@PostMapping("/buscarcomercial") 
	public String buscarcomercial(@Param("buscar") String buscar,@Param("filtro") Integer filtro, Model modelo)
	{
		if (filtro == 1) {
		List<comercial> com1 = comrepo1.findByCodigocomercial (Integer.parseInt(buscar));

		
		for (comercial x: com1) {
			x.getCodigocomercial();
			x.getNombrecomercial();
		}
	
		modelo.addAttribute("comercial", com1);
		} else if (filtro == 2) {
			List<comercial> com1 = comrepo1.findByNombrecomercialContaining (buscar);

			
			for (comercial x: com1) {
				x.getCodigocomercial();
				x.getNombrecomercial();
			}
		
			modelo.addAttribute("comercial", com1);
		} else if(filtro == 0){
			
			List<comercial> comercial = comrepo1.findAll();
			
			for (comercial x: comercial) {

				x.getCodigocomercial();
				x.getNombrecomercial();
				
			}
		
			modelo.addAttribute("comercial", comercial);
		}
		
  	  return "comercial";
    }

}
