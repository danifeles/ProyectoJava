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
import com.curso.odoo.model.Pais;
import com.curso.odoo.model.Presupuesto;
import com.curso.odoo.model.Provincia;
import com.curso.odoo.model.actividad;
import com.curso.odoo.model.estado;
import com.curso.odoo.model.estadopago;
import com.curso.odoo.repositorio.ClienteRepositorio;
import com.curso.odoo.repositorio.FacturaRepositorio;
import com.curso.odoo.repositorio.PaisRepositorio;
import com.curso.odoo.repositorio.PresupuestoRepositorio;
import com.curso.odoo.repositorio.ProvinciaRepositorio;
import com.curso.odoo.service.api.ClienteServicesAPI;
import com.curso.odoo.service.api.PresupuestoServiceAPI;

@Controller
public class ClientesController {
	
	@Autowired
	ClienteRepositorio clirepo;
	@Autowired
	ProvinciaRepositorio provinciarepo;
	@Autowired
	PaisRepositorio paisrepo;
	@Autowired
	FacturaRepositorio facrepo;
	@Autowired
	PresupuestoRepositorio prerepo;
	
	@GetMapping("/formulario")
	public String FormClientes(Model modelo)
	{
		List<Cliente> cliente = clirepo.findAll();
		
		for (Cliente x: cliente) {

			x.getCategorias();
			x.getPais().getNombrepais();
			x.getProvincia().getNombreprovincia();
		}
	
		modelo.addAttribute("cliente", cliente);
		return "Formulario";
	}
	
	
	@Autowired
	private  ClienteServicesAPI  cliServiceAPI;
	
	@GetMapping("/clientes")
    public String  FormClientes (@RequestParam Map<String, Object> params, Model modelo)
    {
		
       int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;
		
		PageRequest pageRequest = PageRequest.of(page, 10);
		
		Page<Cliente> pagePersona = cliServiceAPI.getAll(pageRequest);
		int totalPage = pagePersona.getTotalPages();
		if(totalPage > 0) {
			List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
			modelo.addAttribute("pages", pages);
		}
		System.out.println("Hola");

		
		
		modelo.addAttribute("cliente", pagePersona.getContent());
		modelo.addAttribute("current", page + 1);
		modelo.addAttribute("next", page + 2);
		modelo.addAttribute("prev", page);
		modelo.addAttribute("last", totalPage);
  	  return "clientes";
    }
	
	@PostMapping("/formcliente")
	public String formclientes2(@RequestParam("tipocliente") String tipocliente,
							@RequestParam("nombre") String nombre,
							@Param("apellidos") String apellidos,
						  @RequestParam("calle1") String calle1, 
						  @Param("calle2") String calle2,
						  @RequestParam("ciudad") String ciudad, 
						  @RequestParam("provincia") int provincia,
						  @RequestParam("CP") int codigopostal,
						  @RequestParam("pais") int pais,
						  @RequestParam("NIF") String nif,
						  @Param("tel") String tel,
						  @Param("mov") String mov,
						  @RequestParam("correo") String correo,
						  @Param("website") String enlaceweb,
						  @Param("categorias") String categorias
						  ) {
		
		Cliente x = new Cliente();
		
		Optional<Provincia> c1 = provinciarepo.findById(provincia);
		
		x.setProvincia(c1.get());
		
		Optional<Pais> c2 = paisrepo.findById(pais);
		
		x.setPais(c2.get());
		
		x.setTipocliente(tipocliente);
		x.setNombrecliente(nombre);
		x.setApellidoscliente(apellidos);
		x.setCalle(calle1);
		x.setCalle1(calle2);
		x.setCiudad(ciudad);
		x.setCodigopostal(codigopostal);
		x.setNif(nif);
		x.setTelefono(tel);
		x.setMovil(mov);
		x.setEmail(correo);
		x.setPaginaweb(enlaceweb);
		x.setCategorias(categorias);
		
		clirepo.save(x); 
		
		return "Formulario";
	}
	
	
	@GetMapping("/borrarcliente/{codigo}")
	public String borrarcliente(@PathVariable("codigo") String cod)
	{
		
		List<Factura> listafactura = facrepo.findByClienteCodigocliente(Integer.parseInt(cod));
		for(Factura i:listafactura) {
			facrepo.deleteById(i.getCodigofactura());
		}
		
		List<Presupuesto> listaventas = prerepo.findByClienteCodigocliente(Integer.parseInt(cod));
		for(Presupuesto i:listaventas) {
			facrepo.deleteById(i.getCodigopresupuesto());
		}
		
		clirepo.deleteById(Integer.parseInt(cod));
		
		return "redirect:/clientes"; 
		
	}
	
	@PostMapping("/buscarclientes")  
	public String buscarclientes(@Param("buscar") String buscar,@Param("filtro") Integer filtro, Model modelo)
	{
		System.out.println(filtro);
		
		if(filtro == 1) {
		List<Cliente> cliente = clirepo.findByCodigocliente (Integer.parseInt(buscar));
		
		
		for (Cliente x: cliente) {

			x.getCodigocliente();
			x.getNif();
			x.getTelefono();

		} 
		modelo.addAttribute("cliente", cliente);
		} else if(filtro == 2) {
			List<Cliente> cliente = clirepo.findByNombreclienteContaining(buscar);

			for (Cliente x: cliente) {

				x.getCodigocliente();
				x.getNif();
				x.getTelefono();
			} 

			modelo.addAttribute("cliente", cliente);
		} else if(filtro == 0){
			List<Cliente> cliente = clirepo.findAll();
			if (cliente.isEmpty())
			{			System.out.println("La lists esta vacia");
			}
				for (Cliente x: cliente) {

					x.getCodigocliente();
					x.getNif();
					x.getTelefono();
				} 
				modelo.addAttribute("cliente", cliente);

			}

		return "clientes";
	}
	
	@GetMapping("/FormularioEditar/{codigo}")
	public String editarmodal(@PathVariable("codigo") String cod, Model modelo)
	{	
		
		List<Cliente> cli = clirepo.findByCodigocliente(Integer.parseInt(cod));
		for (Cliente x: cli) {
			x.getCodigocliente();
			x.getNombrecliente();
			x.getApellidoscliente();
			x.getCalle();
			x.getCiudad();
			x.getCategorias();
			x.getCodigopostal();
			x.getEmail();
			x.getMovil();
			x.getNif();
			x.getPais();
			x.getPaginaweb();
			x.getProvincia();
			x.getTelefono();
			x.getTipocliente();
			x.getPhoto();
			
		}
		
		modelo.addAttribute("editarcliente", cli);
		
		List<Cliente> cli1 = clirepo.findAll();
		
		for (Cliente x: cli1) {
			
			x.getCategorias();
			x.getPais().getNombrepais();
			x.getProvincia().getNombreprovincia();
			
		}
	
		modelo.addAttribute("cliente", cli1);

		return "FormularioEditar";
	}
	
	@GetMapping ("FormularioEditar") 
	public String FormularioEditar() {
		return "FormularioEditar";
	}


@PostMapping("/FormularioEditar")
public String formPost1(@RequestParam("numero") int numero,
		@RequestParam("tipocliente") String tipocliente,
		@RequestParam("nombre") String nombre,
		@RequestParam("apellidos") String apellidos,
	  @RequestParam("calle1") String calle1, 
	  @Param("calle2") String calle2,
	  @RequestParam("ciudad") String ciudad, 
	  @RequestParam("provincia") Integer provincia,
	  @RequestParam("CP") Integer codigopostal,
	  @RequestParam("pais") Integer pais,
	  @RequestParam("NIF") String nif,
	  @RequestParam("tel") String tel,
	  @RequestParam("mov") String mov,
	  @RequestParam("correo") String correo,
	  @RequestParam("website") String enlaceweb,
	  @RequestParam("categorias") String categorias
	  ) {

System.out.println(nombre);
System.out.println(apellidos);
System.out.println(calle1);
System.out.println(provincia);
System.out.println(nif);
System.out.println(mov);
System.out.println(tel);
System.out.println(correo);
System.out.println(pais);
System.out.println(ciudad);

System.out.println(codigopostal);
	
Cliente x = new Cliente();

Optional<Provincia> c1 = provinciarepo.findById(provincia);

x.setProvincia(c1.get());

Optional<Pais> c2 = paisrepo.findById(pais);

x.setPais(c2.get());

x.setCodigocliente(numero);
x.setTipocliente(tipocliente);
x.setNombrecliente(nombre);
x.setApellidoscliente(apellidos);
x.setCalle(calle1);
x.setCalle1(calle2);
x.setCiudad(ciudad);
x.setCodigopostal(codigopostal);
x.setNif(nif);
x.setTelefono(tel);
x.setMovil(mov);
x.setEmail(correo);
x.setPaginaweb(enlaceweb);
x.setCategorias(categorias);

clirepo.save(x); 


return "redirect:/clientes";
}
}
