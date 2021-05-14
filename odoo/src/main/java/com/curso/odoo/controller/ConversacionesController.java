package com.curso.odoo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ConversacionesController {
	@GetMapping("/conversaciones")
    public String conversaciones ()
    {
  	  return "conversaciones";
    }
}
