package com.curso.odoo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AjustesController {
	@GetMapping("/ajustes")
    public String ajustes()
    {
  	  return "Ajustes";
    }
}
