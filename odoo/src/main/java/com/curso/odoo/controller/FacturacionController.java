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
import com.curso.odoo.model.Factura;
import com.curso.odoo.model.Presupuesto;
import com.curso.odoo.model.actividad;
import com.curso.odoo.model.estado;
import com.curso.odoo.model.estadopago;
import com.curso.odoo.repositorio.ActividadRepositorio;
import com.curso.odoo.repositorio.ClienteRepositorio;
import com.curso.odoo.repositorio.EstadoPagoRepositorio;
import com.curso.odoo.repositorio.EstadoRepositorio;
import com.curso.odoo.repositorio.FacturaRepositorio;
import com.curso.odoo.service.api.FacturacionServiceAPI;

@Controller
public class FacturacionController {
	
	@Autowired
	ActividadRepositorio actrepo1;
	@Autowired
	ClienteRepositorio clirepo;
	@Autowired
	EstadoRepositorio estrepo1;
	@Autowired
	EstadoPagoRepositorio estrepo2;
	@Autowired
	FacturaRepositorio facrepo;
	
	@GetMapping("/formfacturacion")
	public String FormFacturacion(Model modelo)
	{
		List<Factura> factura = facrepo.findAll();
		
		for (Factura x: factura) {

			x.getCodigofactura();
			x.getFechafactura();
			x.getFechavencimiento();
			x.getImpuestos();
			x.getTotal();
			x.getCliente().getNombrecliente();
			x.getActividad().getNombreactividad();
			x.getEstado().getNombreestado();
			x.getEstadopago().getNombreestadopago();
		}
	
		modelo.addAttribute("factura", factura);
		return "FormFacturacion";
	}
	
	@PostMapping("/formfacturacion")
	public String formPost(@RequestParam("numero") int numero,
						  @RequestParam("cliente") int cliente, 
						  @RequestParam("factura") String factura, 
						  @RequestParam("fechavencimiento") String fechavencimiento,
						  @RequestParam("actividad") int actividad,
						  @RequestParam("impuestos") int impuestos,
						  @RequestParam("total") int total,
						  @RequestParam("estado") int estado,
						  @RequestParam("estadopago") int estadopago) {
		
		System.out.println(numero);
		Factura x = new Factura();
		
		//Buscar el codigo del cliente en la BBDD y guardarlo en la factura
		Optional<Cliente> c1= clirepo.findById(cliente);
	
		x.setCliente(c1.get());
      
		Optional<actividad> c2= actrepo1.findById(actividad);
		x.setActividad(c2.get());
		
		Optional<estado> c3= estrepo1.findById(estado);
		x.setEstado(c3.get());
		
		Optional<estadopago> c4= estrepo2.findById(estadopago);
		x.setEstadopago(c4.get());
		
		x.setCodigofactura(numero);
		x.setFechafactura(factura);
		x.setFechavencimiento(fechavencimiento);
		x.setImpuestos(impuestos);
		x.setTotal(total);
		
		facrepo.save(x); 
		
		return "FormFacturacion";
	}
	
	@Autowired
	private FacturacionServiceAPI facServiceAPI;
	@GetMapping("/facturacion")
    public String facturacion(@RequestParam Map<String, Object> params, Model modelo)
    {
		
       int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;
		
		PageRequest pageRequest = PageRequest.of(page, 10);
		
		Page<Factura> pagePersona = facServiceAPI.getAll(pageRequest);
		int totalPage = pagePersona.getTotalPages();
		if(totalPage > 0) {
			List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
			modelo.addAttribute("pages", pages);
		}
		System.out.println("Hola");

		
	
		
		modelo.addAttribute("factura", pagePersona.getContent());
		modelo.addAttribute("current", page + 1);
		modelo.addAttribute("next", page + 2);
		modelo.addAttribute("prev", page);
		modelo.addAttribute("last", totalPage);
  	  return "Facturacion";
    }
	
	@PostMapping("/buscarfactura")
	public String buscarfactura(@Param("buscar") String buscar,@Param("filtro") Integer filtro, Model modelo)
	{
		
		if (filtro == 1) {
		List<Factura> fac1 = facrepo.findByCodigofactura (Integer.parseInt(buscar));

		
		for (Factura x: fac1) {

			x.getCodigofactura();
			x.getFechafactura();
			x.getFechavencimiento();
			x.getImpuestos();
			x.getTotal();
			x.getCliente().getNombrecliente();
			x.getActividad().getNombreactividad();
			x.getEstado().getNombreestado();
			x.getEstadopago().getNombreestadopago();
		}
	
		modelo.addAttribute("factura", fac1);
		} else if (filtro == 2) {
			List<Factura> fac2 = facrepo.findByClienteNombreclienteContaining(buscar);

			for (Factura x: fac2) {

				x.getCodigofactura();
				x.getFechafactura();
				x.getFechavencimiento();
				x.getImpuestos();
				x.getTotal();
				x.getCliente().getNombrecliente();
				x.getActividad().getNombreactividad();
				x.getEstado().getNombreestado();
				x.getEstadopago().getNombreestadopago();
			}
		
			modelo.addAttribute("factura", fac2);
		} else if (filtro == 0) {
			List<Factura> factura = facrepo.findAll();
			
			for (Factura x: factura) {

				x.getCodigofactura();
				x.getFechafactura();
				x.getFechavencimiento();
				x.getImpuestos();
				x.getTotal();
				x.getCliente().getNombrecliente();
				x.getActividad().getNombreactividad();
				x.getEstado().getNombreestado();
				x.getEstadopago().getNombreestadopago();
			}
		
			modelo.addAttribute("factura", factura);
		}
		
  	  return "Facturacion";
    }

	@GetMapping("/borrarfacturacion/{codigo}")
	public String borrarfacturacion(@PathVariable("codigo") String cod)
	{
		
		facrepo.deleteById(Integer.parseInt(cod));
		System.out.println(cod);
		
		
		return "redirect:/facturacion"; 
	}
	
	@GetMapping("/nuevo")
    public String nuevo()
    {
		return "NewFile";
    }
	
	@GetMapping("/FormFacturacionEditar/{codigo}")
	public String editarmodal(@PathVariable("codigo") String cod, Model modelo)
	{	
		
		List<Factura> fac = facrepo.findByCodigofactura(Integer.parseInt(cod));
		for (Factura x: fac) {

			x.getCodigofactura();
			x.getFechafactura();
			x.getFechavencimiento();
			x.getImpuestos();
			x.getTotal();
			x.getCliente().getNombrecliente();
			x.getActividad().getNombreactividad();
			x.getEstado().getNombreestado();
			x.getEstadopago().getNombreestadopago();
		}
		
		modelo.addAttribute("editarfacturacion", fac);
		
		List<Factura> fac1 = facrepo.findAll();
		
		for (Factura x: fac1) {

			x.getCodigofactura();
			x.getFechafactura();
			x.getFechavencimiento();
			x.getImpuestos();
			x.getTotal();
			x.getCliente().getNombrecliente();
			x.getActividad().getNombreactividad();
			x.getEstado().getNombreestado();
			x.getEstadopago().getNombreestadopago();
		}
	
		modelo.addAttribute("factura", fac1);

		return "FormFacturacionEditar";
	}
	
	@GetMapping ("FormFacturacionEditar") 
	public String FormFacturacionEditar() {
		return "FormFacturacionEditar";
	}
	
	@PostMapping("/FormFacturacionEditar")
		public String formPost1(@Param("numero") Integer numero,
				  @Param("cliente") Integer cliente, 
				  @Param("factura") String factura, 
				  @Param("fechavencimiento") String fechavencimiento,
				  @Param("actividad") Integer actividad,
				  @Param("impuestos") Integer impuestos,
				  @Param("total") Integer total,
				  @Param("estado") Integer estado,
				  @Param("estadopago") Integer estadopago) {

System.out.println(numero);
System.out.println(cliente);
System.out.println(factura);
System.out.println(fechavencimiento);
System.out.println(actividad);
System.out.println(total);
System.out.println(estado);
System.out.println(estadopago);
System.out.println(impuestos);


Factura x = new Factura();

//Buscar el codigo del cliente en la BBDD y guardarlo en la factura
Optional<Cliente> c1= clirepo.findById(cliente);

x.setCliente(c1.get());

Optional<actividad> c2= actrepo1.findById(actividad);
x.setActividad(c2.get());

Optional<estado> c3= estrepo1.findById(estado);
x.setEstado(c3.get());

Optional<estadopago> c4= estrepo2.findById(estadopago);
x.setEstadopago(c4.get());

x.setCodigofactura(numero);
x.setFechafactura(factura);
x.setFechavencimiento(fechavencimiento);
x.setImpuestos(impuestos);
x.setTotal(total);

facrepo.save(x); 
		

		return "redirect:/facturacion";
	}
}
