package com.curso.odoo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@GetMapping("/ventas")
    public String ventas(Model modelo)
    {
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
	
		
		modelo.addAttribute("presupuesto", presu);
  	  return "ventas";
    }
	
	@PostMapping("/ventasnumero")
	public String buscarventas(@Param("buscarnumero") int buscarnumero, Model modelo)
	{
		
		List<Presupuesto> presu1 = prerepo.findByCodigopresupuesto (buscarnumero);

		
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

		return "ventas";
	}

		@PostMapping("/ventascliente")
		public String buscarventas(@Param("buscarcliente") String buscarcliente, Model modelo)
				{
		List<Presupuesto> presu2 = prerepo.findByClienteNombreclienteContaining(buscarcliente);

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
					
			return "ventas";
		}
	
		@GetMapping("/borrarpresupuesto/{codigo}")
		public String borrar(@PathVariable("codigo") String cod)
		{
			
			prerepo.deleteById(Integer.parseInt(cod));
			System.out.println(cod);
			
			
			return "redirect:/ventas"; 
			
		}
}
