package com.curso.odoo.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.curso.odoo.model.Cliente;
import com.curso.odoo.model.Presupuesto;
import com.curso.odoo.model.actividad;
import com.curso.odoo.model.comercial;
import com.curso.odoo.model.estado;
import com.curso.odoo.repositorio.ActividadRepositorio;
import com.curso.odoo.repositorio.ClienteRepositorio;
import com.curso.odoo.repositorio.ComercialRepositorio;
import com.curso.odoo.repositorio.EstadoPagoRepositorio;
import com.curso.odoo.repositorio.EstadoRepositorio;
import com.curso.odoo.repositorio.PresupuestoRepositorio;
import com.curso.odoo.service.api.PresupuestoServiceAPI;

@Controller
public class VentasController {
	
	@Autowired
	ActividadRepositorio actrepo1;
	@Autowired
	ClienteRepositorio clirepo;
	@Autowired
	EstadoRepositorio estrepo1;
	@Autowired
	EstadoPagoRepositorio estrepo2;
	@Autowired
	PresupuestoRepositorio prerepo;
	@Autowired
	ComercialRepositorio comrepo1;
	
	@GetMapping("/formventas")
	public String FormVentas1(Model modelo)
	{
		List<Presupuesto> presu = prerepo.findAll();
		
		for (Presupuesto x: presu) {

			x.getCodigopresupuesto();
			x.getFechapresupuesto();
			x.getCliente().getNombrecliente();
			x.getComercial().getNombrecomercial();
			x.getTotal();
			x.getActividad().getNombreactividad();
			x.getEstado().getNombreestado();
		}
	
		modelo.addAttribute("presupuesto", presu);
		return "FormVentas";
	}
	
	@PostMapping("/formventas")
	public String formventas(@RequestParam("numero") int numero,
						  @RequestParam("fecha") String fecha, 
						  @RequestParam("cliente") int cliente, 
						  @RequestParam("comercial") int comercial, 
						  @RequestParam("actividad") int actividad,
						  @RequestParam("total") int total,
						  @RequestParam("estado") int estado) {
		
		Presupuesto x = new Presupuesto();  
		
		//Buscar el codigo del cliente en la BBDD y guardarlo en la factura
		Optional<Cliente> c1= clirepo.findById(cliente);
	
		x.setCliente(c1.get());
      
		Optional<actividad> c2= actrepo1.findById(actividad);
		x.setActividad(c2.get());
		
		Optional<estado> c3= estrepo1.findById(estado);
		x.setEstado(c3.get());
		
		Optional<comercial> c4= comrepo1.findById(comercial);
		x.setComercial(c4.get());
		
		x.setCodigopresupuesto(numero);
		x.setFechapresupuesto(fecha);
		x.setTotal(total);
		
		prerepo.save(x); 
		
		return "FormVentas";
	}
	
	@Autowired
	private PresupuestoServiceAPI preServiceAPI;
	
	@GetMapping("/ventas")
    public String ventas(@RequestParam Map<String, Object> params, Model modelo)
    {
		
       int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;
		
		PageRequest pageRequest = PageRequest.of(page, 10);
		
		Page<Presupuesto> pagePersona = preServiceAPI.getAll(pageRequest);
		int totalPage = pagePersona.getTotalPages();
		if(totalPage > 0) {
			List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
			modelo.addAttribute("pages", pages);
		}
		System.out.println("Hola");

		List<Presupuesto> presu = prerepo.findAll(Sort.by("Fechapresupuesto").descending());
		if (presu.isEmpty())
		{			System.out.println("La lists esta vacia");
		}
		for (Presupuesto x: presu) {

			x.getCodigopresupuesto();
			x.getFechapresupuesto();
			x.getCliente().getNombrecliente();
			x.getComercial().getNombrecomercial();
			x.getActividad().getNombreactividad();
			x.getTotal();
			x.getEstado().getNombreestado();
		}
	
		
		modelo.addAttribute("presupuesto", pagePersona.getContent());
		modelo.addAttribute("current", page + 1);
		modelo.addAttribute("next", page + 2);
		modelo.addAttribute("prev", page);
		modelo.addAttribute("last", totalPage);
  	  return "ventas";
    }
	
	@PostMapping("/buscarventas")  
	public String buscarventas1(@Param("buscar") String buscar,@Param("filtro") Integer filtro, Model modelo)
	{
		System.out.println(filtro);
		
		if(filtro == 1) {
		List<Presupuesto> presu1 = prerepo.findByCodigopresupuesto (Integer.parseInt(buscar));
		
		
		for (Presupuesto x: presu1) {

			x.getCodigopresupuesto();
			x.getFechapresupuesto();
			x.getCliente().getNombrecliente();
			x.getComercial().getNombrecomercial();
			x.getActividad().getNombreactividad();
			x.getTotal();
			x.getEstado().getNombreestado();

		} 
		modelo.addAttribute("presupuesto", presu1);
		} else if(filtro == 2) {
			List<Presupuesto> presu2 = prerepo.findByClienteNombreclienteContaining(buscar);

			for (Presupuesto x: presu2) {

				x.getCodigopresupuesto();
				x.getFechapresupuesto();
				x.getCliente().getNombrecliente();
				x.getComercial().getNombrecomercial();
				x.getActividad().getNombreactividad();
				x.getTotal();
				x.getEstado().getNombreestado();
			} 

				modelo.addAttribute("presupuesto", presu2);
		} else if(filtro == 0){
			List<Presupuesto> presu = prerepo.findAll(Sort.by("Fechapresupuesto").descending());
			if (presu.isEmpty())
			{			System.out.println("La lists esta vacia");
			}
			for (Presupuesto x: presu) {

				x.getCodigopresupuesto();
				x.getFechapresupuesto();
				x.getCliente().getNombrecliente();
				x.getComercial().getNombrecomercial();
				x.getActividad().getNombreactividad();
				x.getTotal();
				x.getEstado().getNombreestado();
			}
		
			
			modelo.addAttribute("presupuesto", presu);
		}

		return "ventas";
	}

	
		@GetMapping("/borrarpresupuesto/{codigo}")
		public String borrar(@PathVariable("codigo") String cod)
		{
			
			prerepo.deleteById(Integer.parseInt(cod));
			System.out.println(cod);
			
			
			return "redirect:/ventas"; 
			
		}
		
		@GetMapping("/FormVentasEditar/{codigo}")
		public String editarmodal(@PathVariable("codigo") String cod, Model modelo)
		{	
			
			List<Presupuesto> presu1 = prerepo.findByCodigopresupuesto(Integer.parseInt(cod));
			for (Presupuesto x: presu1) {

				x.getCodigopresupuesto();
				x.getFechapresupuesto();
				x.getCliente().getNombrecliente();
				x.getComercial().getNombrecomercial();
				x.getActividad().getNombreactividad();
				x.getTotal();
				x.getEstado().getNombreestado();
			}
			
			modelo.addAttribute("editarventas", presu1);
			
			List<Presupuesto> presu = prerepo.findAll();
			
			for (Presupuesto x: presu) {

				x.getComercial().getNombrecomercial();
				x.getActividad().getNombreactividad();
				x.getEstado().getNombreestado();
			}
		
			modelo.addAttribute("presupuesto", presu);

			return "FormVentasEditar";
		}
		
		@GetMapping ("FormVentasEditar") 
		public String FormVentasEditar() {
			return "FormVentasEditar";
		}
		
		@PostMapping("/FormVentasEditar")
		public String Editarform(@RequestParam("numero") int numero,
							  @RequestParam("fecha") String fecha, 
							  @RequestParam("cliente") int cliente, 
							  @RequestParam("comercial") int comercial, 
							  @RequestParam("actividad") int actividad,
							  @RequestParam("total") int total,
							  @RequestParam("estado") int estado) {
			
			Presupuesto x = new Presupuesto();  
			
			//Buscar el codigo del cliente en la BBDD y guardarlo en la factura
			Optional<Cliente> c1= clirepo.findById(cliente);
		
			x.setCliente(c1.get());
	      
			Optional<actividad> c2= actrepo1.findById(actividad);
			x.setActividad(c2.get());
			
			Optional<estado> c3= estrepo1.findById(estado);
			x.setEstado(c3.get());
			
			Optional<comercial> c4= comrepo1.findById(comercial);
			x.setComercial(c4.get());
			
			x.setCodigopresupuesto(numero);
			x.setFechapresupuesto(fecha);
			x.setTotal(total);
			
			prerepo.save(x); 
			
			return "redirect:/ventas";
		}
}
