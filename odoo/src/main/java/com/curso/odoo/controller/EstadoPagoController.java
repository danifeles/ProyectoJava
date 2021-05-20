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
import com.curso.odoo.model.estado;
import com.curso.odoo.model.estadopago;
import com.curso.odoo.repositorio.EstadoPagoRepositorio;
import com.curso.odoo.repositorio.FacturaRepositorio;
import com.curso.odoo.repositorio.PresupuestoRepositorio;
import com.curso.odoo.service.api.ClienteServicesAPI;
import com.curso.odoo.service.api.EstadoPagoServicesAPI;

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
	
	@Autowired
	private  EstadoPagoServicesAPI  estadopagoServiceAPI;
	
	@GetMapping("/estadopago")
    public String  Formestadopago (@RequestParam Map<String, Object> params, Model modelo)
    {
		
       int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;
		
		PageRequest pageRequest = PageRequest.of(page, 10);
		
		Page<estadopago> pagePersona = estadopagoServiceAPI.getAll(pageRequest);
		int totalPage = pagePersona.getTotalPages();
		if(totalPage > 0) {
			List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
			modelo.addAttribute("pages", pages);
		}
		System.out.println("Hola");

		
		
		modelo.addAttribute("estadopago", pagePersona.getContent());
		modelo.addAttribute("current", page + 1);
		modelo.addAttribute("next", page + 2);
		modelo.addAttribute("prev", page);
		modelo.addAttribute("last", totalPage);
  	  return "estadopago";
    }
	
	@GetMapping("/borrarestadopago/{codigo}")
	public String borrarestadopago(@PathVariable("codigo") String cod)
	{
		List<Factura> listafactura = facrepo.findByEstadopagoCodigoestadopago(Integer.parseInt(cod));
		if(listafactura.isEmpty()) {
			System.out.println("Está vacia");
		} else {
		for(Factura i:listafactura) {
			facrepo.deleteById(i.getCodigofactura());
		}
		}
		estrepo2.deleteById(Integer.parseInt(cod));
		
		
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
		} else if(filtro == 0){
			List<estadopago> estadopago = estrepo2.findAll();
			
			for (estadopago x: estadopago) {

				x.getCodigoestadopago();
				x.getNombreestadopago();
			}
		
			modelo.addAttribute("estadopago", estadopago);
		}
  	  return "estadopago";
    }
	
	@GetMapping("/FormEstadoPagoEditar/{codigo}")
	public String editarmodal(@PathVariable("codigo") String cod, Model modelo)
	{	
		
		List<estadopago> est = estrepo2.findByCodigoestadopago(Integer.parseInt(cod));
		for (estadopago x: est) {

			x.getCodigoestadopago();
			x.getNombreestadopago();
		}
		
		modelo.addAttribute("editarestadopago", est);
		
		List<estadopago> est1 = estrepo2.findAll();
		
		for (estadopago x: est1) {

			x.getCodigoestadopago();
			x.getNombreestadopago();
		}
	
		modelo.addAttribute("estadopago", est1);

		return "FormEstadoPagoEditar";
	}
	
	@GetMapping ("FormEstadoPagoEditar") 
	public String FormEstadoPagoEditar() {
		return "FormEstadoPagoEditar";
	}
	
	@PostMapping("/FormEstadoPagoEditar")
	public String Editarform(@RequestParam("nombre_estadopago") String c1,
						  @RequestParam("codigo_estadopago") int c2) {
		
		
		com.curso.odoo.model.estadopago estadopago_1 = new com.curso.odoo.model.estadopago();
		
		estadopago_1.setNombreestadopago(c1);
		estadopago_1.setCodigoestadopago(c2);
		
		estrepo2.save(estadopago_1);
		
		return "redirect:/estadopago";
	}
}
