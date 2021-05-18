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
import com.curso.odoo.model.estado;
import com.curso.odoo.repositorio.EstadoRepositorio;
import com.curso.odoo.repositorio.FacturaRepositorio;
import com.curso.odoo.repositorio.PresupuestoRepositorio;
import com.curso.odoo.service.api.ClienteServicesAPI;
import com.curso.odoo.service.api.EstadoServicesAPI;

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
	
	
	@Autowired
	private  EstadoServicesAPI  estaServiceAPI;
	
	@GetMapping("/estado")
    public String  FormClientes (@RequestParam Map<String, Object> params, Model modelo)
    {
		
       int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;
		
		PageRequest pageRequest = PageRequest.of(page, 10);
		
		Page<estado> pagePersona = estaServiceAPI.getAll(pageRequest);
		int totalPage = pagePersona.getTotalPages();
		if(totalPage > 0) {
			List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
			modelo.addAttribute("pages", pages);
		}
		System.out.println("Hola");

		
		
		modelo.addAttribute("estado", pagePersona.getContent());
		modelo.addAttribute("current", page + 1);
		modelo.addAttribute("next", page + 2);
		modelo.addAttribute("prev", page);
		modelo.addAttribute("last", totalPage);
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
	@PostMapping("/buscarestado")
	public String buscarestado(@Param("buscar") String buscar,@Param("filtro") Integer filtro, Model modelo)
	{
		if (filtro == 1) {
		List<estado> est = estrepo1.findByCodigoestado (Integer.parseInt(buscar));
		
		for (estado x: est) {
			x.getCodigoestado();
			x.getNombreestado();
		}
	
		modelo.addAttribute("estado", est);
		} else if (filtro == 2) {
			List<estado> est = estrepo1.findByNombreestadoContaining (buscar);

			for (estado x: est) {
				x.getCodigoestado();
				x.getNombreestado();
			}
		
			modelo.addAttribute("estado", est);
		} else if(filtro == 0){
			List<estado> estado = estrepo1.findAll();
			
			for (estado x: estado) {

				x.getCodigoestado();
				x.getNombreestado();
			}
		
			modelo.addAttribute("estado", estado);
		}
  	  return "estado";
    }
	
	@GetMapping("/FormEstadoEditar/{codigo}")
	public String editarmodal(@PathVariable("codigo") String cod, Model modelo)
	{	
		
		List<com.curso.odoo.model.estado> est = estrepo1.findByCodigoestado(Integer.parseInt(cod));
		for (estado x: est) {

			x.getCodigoestado();
			x.getNombreestado();
		}
		
		modelo.addAttribute("editarestado", est);
		
		List<com.curso.odoo.model.estado> est1 = estrepo1.findAll();
		
		for (estado x: est1) {

			x.getCodigoestado();
			x.getNombreestado();
		}
	
		modelo.addAttribute("estado", est1);

		return "FormEstadoEditar";
	}
	
	@GetMapping ("FormEstadoEditar") 
	public String FormEstadoEditar() {
		return "FormEstadoEditar";
	}

}
