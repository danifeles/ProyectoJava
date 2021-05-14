package com.curso.odoo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.curso.odoo.model.registro;
import com.curso.odoo.repositorio.RegistroRepositorio;

@Controller
public class IndexController {

	@Autowired
	RegistroRepositorio registro;
	
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
				modelo.addAttribute("LoginOk", usuario);
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
}
