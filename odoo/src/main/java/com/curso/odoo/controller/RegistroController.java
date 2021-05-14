package com.curso.odoo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.curso.odoo.model.registro;
import com.curso.odoo.repositorio.RegistroRepositorio;

@Controller
public class RegistroController {

	
	@Autowired
	RegistroRepositorio registro;
	
	@GetMapping("/registro")
	public String registro() {
		return "Registro";
	}

	@PostMapping("/registro")
	public String registro(@RequestParam("nombre") String nombre, @RequestParam("apellidos") String apellidos,
			@RequestParam("usuario") String usuario, @RequestParam("tel") String tel,
			@RequestParam("correo") String correo, @RequestParam("contrasena") String contrasena) {

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
}
