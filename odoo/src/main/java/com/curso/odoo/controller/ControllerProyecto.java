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

import com.curso.odoo.model.registro;
import com.curso.odoo.model.Cliente;
import com.curso.odoo.model.actividad;
import com.curso.odoo.model.comercial;
import com.curso.odoo.model.estado;
import com.curso.odoo.model.estadopago;
import com.curso.odoo.model.Factura;
import com.curso.odoo.model.Pais;
import com.curso.odoo.model.Presupuesto;
import com.curso.odoo.model.Provincia;
import com.curso.odoo.repositorio.ActividadRepositorio;
import com.curso.odoo.repositorio.ClienteRepositorio;
import com.curso.odoo.repositorio.ComercialRepositorio;
import com.curso.odoo.repositorio.EstadoPagoRepositorio;
import com.curso.odoo.repositorio.EstadoRepositorio;
import com.curso.odoo.repositorio.FacturaRepositorio;
import com.curso.odoo.repositorio.PaisRepositorio;
import com.curso.odoo.repositorio.PresupuestoRepositorio;
import com.curso.odoo.repositorio.ProvinciaRepositorio;
import com.curso.odoo.repositorio.RegistroRepositorio;
@Controller


public class ControllerProyecto {
	
	@Autowired
	PresupuestoRepositorio prerepo;
	@Autowired
	FacturaRepositorio facrepo;
	@Autowired
	ClienteRepositorio clirepo;
	@Autowired
	ActividadRepositorio actrepo1;
	@Autowired
	EstadoRepositorio estrepo1;
	@Autowired
	ComercialRepositorio comrepo1;
	@Autowired
	EstadoPagoRepositorio estrepo2;
	@Autowired
	RegistroRepositorio registro;
	@Autowired
	ProvinciaRepositorio provinciarepo;
	@Autowired
	PaisRepositorio paisrepo;
	
	@GetMapping("/")
    public String inicio(Model modelo)
    {
		boolean loginMal = false;
		boolean loginMalUsuario = false;
		modelo.addAttribute("loginMal", loginMal);
		modelo.addAttribute("loginMalUsuario", loginMalUsuario);
  	  return "Index";
    }
	
	@PostMapping("/")
    public String login(@RequestParam String usuario, @RequestParam String contrasena, Model modelo)
    {
		boolean loginMal = false;
		boolean loginMalUsuario = false;
		
		
		Optional<registro> c1 = registro.findByUsuario(usuario);
		
		 if(c1.isPresent()	)	{
			if(contrasena.equals(c1.get().getContrasena()))
			{
				return "principal";
			} 
			else 
			{
				loginMal = true;
			}
		 }
		 else 
		 {
			 	loginMalUsuario = true;
		 }
			 	
		modelo.addAttribute("loginMal", loginMal);
		modelo.addAttribute("loginMalUsuario", loginMalUsuario);
		
		 return "Index";
    }
	
	@GetMapping("/registro")
    public String registro ()
    {
  	  return "Registro";
    }
	
	@GetMapping("/principal")
    public String principal() 
    {
  	  return "principal";
    }
	
	@GetMapping("/conversaciones")
    public String conversaciones ()
    {
  	  return "conversaciones";
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
		
		@GetMapping("/borrarfacturacion/{codigo}")
		public String borrarfacturacion(@PathVariable("codigo") String cod)
		{
			
			facrepo.deleteById(Integer.parseInt(cod));
			System.out.println(cod);
			
			
			return "redirect:/facturacion"; 
			
		}
	
	@GetMapping("/ajustes")
    public String ajustes()
    {
  	  return "Ajustes";
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
		
		clirepo.deleteById(Integer.parseInt(cod));
		System.out.println(cod);
		
		
		return "redirect:/clientes"; 
		
	}
	
	@PostMapping("/buscarclientes")
	public String buscarclientes(@Param("buscarcliente") String buscarcliente, Model modelo)
	{
		
		List<Cliente> cliente = clirepo.findByNombreclienteContaining (buscarcliente);

		
		for (Cliente x: cliente) {

			x.getNombrecliente();
			x.getPhoto();
			
		} 
		modelo.addAttribute("cliente", cliente);

		return "clientes";
	}
	
	@GetMapping("/facturacion")
    public String facturacion(Model modelo)
    {
		System.out.println("Hola");

		List<Factura> factura = facrepo.findAll(Sort.by("Fechafactura").descending());
		
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
  	  return "Facturacion";
    }
	
	@PostMapping("/facturacionnumero")
	public String facturacionventas(@Param("buscarnumero") int buscarnumero, Model modelo)
	{
		
		List<Factura> fac1 = facrepo.findByCodigofactura (buscarnumero);

		
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
  	  return "Facturacion";
    }

		@PostMapping("/facturacioncliente")
		public String facturacionventas(@Param("buscarcliente") String buscarcliente, Model modelo)
				{
		List<Factura> fac2 = facrepo.findByClienteNombreclienteContaining(buscarcliente);

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
  	  return "Facturacion";
    }
		
	@GetMapping("/formestado")
    public String FormEstado()
    {
  	  return "FormEstado";
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
		
		actrepo1.deleteById(Integer.parseInt(cod));
		System.out.println(cod);
		
		
		return "redirect:/actividad"; 
		
	}
	@PostMapping("/buscarcodigoactividad")
	public String buscarcodigoactividad(@Param("codigoactividad") int codigoactividad, Model modelo)
	{
		
		List<actividad> act1 = actrepo1.findByCodigoactividad (codigoactividad);

		
		for (actividad x: act1) {
			x.getCodigoactividad();
			x.getNombreactividad();
		}
	
		modelo.addAttribute("actividad", act1);
  	  return "actividad";
    }

		@PostMapping("/nombreactividad")
		public String nombreactividad(@Param("buscarnombre") String buscarnombre, Model modelo)
				{
			List<actividad> act1 = actrepo1.findByNombreactividadContaining (buscarnombre);

		for (actividad x: act1) {
			x.getCodigoactividad();
			x.getNombreactividad();
		}
	
		modelo.addAttribute("actividad", act1);
  	  return "actividad";
    }
		
		@GetMapping("/comercial")
	    public String comercial(Model modelo)
	    {
			List<comercial> comercial = comrepo1.findAll();
			
			for (comercial x: comercial) {

				x.getCodigocomercial();
				x.getNombrecomercial();
				
			}
		
			modelo.addAttribute("comercial", comercial);
	  	  return "comercial";
	    }
		
		@GetMapping("/borrarcomercial/{codigo}")
		public String borrarcomercial(@PathVariable("codigo") String cod)
		{
			
			comrepo1.deleteById(Integer.parseInt(cod));
			System.out.println(cod);
			
			
			return "redirect:/comercial"; 
			
		}
		@PostMapping("/buscarcodigocomercial")
		public String buscarcodigocomercial(@Param("codigocomercial") int codigocomercial, Model modelo)
		{
			
			List<comercial> com1 = comrepo1.findByCodigocomercial (codigocomercial);

			
			for (comercial x: com1) {
				x.getCodigocomercial();
				x.getNombrecomercial();
			}
		
			modelo.addAttribute("comercial", com1);
	  	  return "comercial";
	    }

			@PostMapping("/nombrecomercial")
			public String nombrecomercial(@Param("buscarcomercial") String buscarcomercial, Model modelo)
					{
				List<comercial> com1 = comrepo1.findByNombrecomercialContaining (buscarcomercial);

			for (comercial x: com1) {
				x.getCodigocomercial();
				x.getNombrecomercial();
			}
		
			modelo.addAttribute("comercial", com1);
	  	  return "comercial";
	    }
			
			@GetMapping("/estado")
		    public String estado(Model modelo)
		    {
				List<estado> estado = estrepo1.findAll();
				
				for (estado x: estado) {

					x.getCodigoestado();
					x.getNombreestado();
					
				}
			
				modelo.addAttribute("estado", estado);
		  	  return "estado";
		    }
			
			@GetMapping("/borrarestado/{codigo}")
			public String borrarestado(@PathVariable("codigo") String cod)
			{
				
				estrepo1.deleteById(Integer.parseInt(cod));
				System.out.println(cod);
				
				
				return "redirect:/estado"; 
				
			}
			@PostMapping("/buscarcodigoestado")
			public String buscarcodigoestado(@Param("codigoestado") int codigoestado, Model modelo)
			{
				
				List<estado> est1 = estrepo1.findByCodigoestado (codigoestado);

				
				for (estado x: est1) {
					x.getCodigoestado();
					x.getNombreestado();
				}
			
				modelo.addAttribute("estado", est1);
		  	  return "estado";
		    }

				@PostMapping("/nombreestado")
				public String nombreestado(@Param("buscarestado") String buscarestado, Model modelo)
						{
					List<estado> est1 = estrepo1.findByNombreestadoContaining (buscarestado);

				for (estado x: est1) {
					x.getCodigoestado();
					x.getNombreestado();
				}
			
				modelo.addAttribute("estado", est1);
		  	  return "estado";
		    }
				
				@GetMapping("/estadopago")
			    public String estadopago(Model modelo)
			    {
					List<estadopago> estadopago = estrepo2.findAll();
					
					for (estadopago x: estadopago) {

						x.getCodigoestadopago();
						x.getNombreestadopago();
						
					}
				
					modelo.addAttribute("estadopago", estadopago);
			  	  return "estadopago";
			    }
				
				@GetMapping("/borrarestadopago/{codigo}")
				public String borrarestadopago(@PathVariable("codigo") String cod)
				{
					
					estrepo2.deleteById(Integer.parseInt(cod));
					System.out.println(cod);
					
					
					return "redirect:/estadopago"; 
					
				}
				@PostMapping("/buscarcodigoestadopago")
				public String buscarcodigoestadopago(@Param("codigoestadopago") int codigoestadopago, Model modelo)
				{
					
					List<estadopago> est2 = estrepo2.findByCodigoestadopago (codigoestadopago);

					
					for (estadopago x: est2) {
						x.getCodigoestadopago();
						x.getNombreestadopago();
					}
				
					modelo.addAttribute("estadopago", est2);
			  	  return "estadopago";
			    }

					@PostMapping("/nombreestadopago")
					public String nombreestadopago(@Param("buscarestadopago") String buscarestadopago, Model modelo)
							{
						List<estadopago> est2 = estrepo2.findByNombreestadopagoContaining (buscarestadopago);

					for (estadopago x: est2) {
						x.getCodigoestadopago();
						x.getNombreestadopago();
					}
				
					modelo.addAttribute("estadopago", est2);
			  	  return "estadopago";
			    }
					
	@GetMapping("/formactividad")
    public String Formactividad()
    {
  	  return "Formactividad";
    }
	
	@GetMapping("/formcomercial")
    public String Formcomercial()
    {
  	  return "Formcomercial";
    }
	@GetMapping("/formestadopago")
    public String FormEstadoPago()
    {
  	  return "FormEstadoPago";
    }
	
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
      
		Optional<actividad> c2= actrepo.findById(actividad);
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
      
		Optional<actividad> c2= actrepo.findById(actividad);
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
	
	@Autowired
	private ActividadRepositorio actrepo;
	
	
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
	private ComercialRepositorio comrepo;
	
	
	@PostMapping("/comercial")
	public String formCom(@RequestParam("nombre_comercial") String c1, @RequestParam("apellidos_comercial") String c2,
						  @RequestParam("codigo_comercial") int c3) {
		
		
		com.curso.odoo.model.comercial comercial_1 = new com.curso.odoo.model.comercial();
		
		comercial_1.setNombrecomercial(c1);
		comercial_1.setApellidoscomercial(c2);
		comercial_1.setCodigocomercial(c3);
		
		comrepo.save(comercial_1);
		
		return "Formcomercial";
	}
	
	@Autowired
	private EstadoRepositorio estrepo;
	
	
	@PostMapping("/estado")
	public String formEst(@RequestParam("nombre_estado") String c1,
						  @RequestParam("codigo_estado") int c2) {
		
		
		com.curso.odoo.model.estado estado_1 = new com.curso.odoo.model.estado();
		
		estado_1.setNombreestado(c1);
		estado_1.setCodigoestado(c2);
		
		estrepo.save(estado_1);
		
		return "FormEstado";
	}
	
	@Autowired
	private EstadoPagoRepositorio estpagorepo;
	
	
	@PostMapping("/estadopago")
	public String formEstPago(@RequestParam("nombre_estadopago") String c1,
						  @RequestParam("codigo_estadopago") int c2) {
		
		
		com.curso.odoo.model.estadopago estadopago_1 = new com.curso.odoo.model.estadopago();
		
		estadopago_1.setNombreestadopago(c1);
		estadopago_1.setCodigoestadopago(c2);
		
		estpagorepo.save(estadopago_1);
		
		return "FormEstadoPago";
	}

		@PostMapping("/registro")
		public String registro(@RequestParam("nombre") String nombre,
								@RequestParam("apellidos") String apellidos,
								@RequestParam("usuario") String usuario,
								@RequestParam("tel") String tel,
								@RequestParam("correo") String correo,
								@RequestParam("contrasena") String contrasena) {
	
	
	registro registro_1 = new registro();
	
	registro_1.setNombre(nombre);
	registro_1.setApellidos(apellidos);
	registro_1.setUsuario(usuario);
	registro_1.setTelefono(tel);
	registro_1.setCorreo(correo);
	registro_1.setContrasena(contrasena);


	registro.save(registro_1);
	
	return "registro";
	}
		
	
};