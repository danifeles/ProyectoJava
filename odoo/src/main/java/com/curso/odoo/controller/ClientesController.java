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
import com.curso.odoo.model.Factura;
import com.curso.odoo.model.Pais;
import com.curso.odoo.model.Presupuesto;
import com.curso.odoo.model.Provincia;
import com.curso.odoo.repositorio.ClienteRepositorio;
import com.curso.odoo.repositorio.FacturaRepositorio;
import com.curso.odoo.repositorio.PaisRepositorio;
import com.curso.odoo.repositorio.PresupuestoRepositorio;
import com.curso.odoo.repositorio.ProvinciaRepositorio;

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
						  @Param("enlaceweb") String enlaceweb,
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
	@GetMapping("/clientes")
    public String clientes(Model modelo)
    {
		List<Cliente> cliente = clirepo.findAll();
		
		for (Cliente x: cliente) {

			x.getCodigocliente();
			x.getNif();
			x.getTelefono();
			
		}
	
		modelo.addAttribute("cliente", cliente);
  	  return "clientes";
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
}
