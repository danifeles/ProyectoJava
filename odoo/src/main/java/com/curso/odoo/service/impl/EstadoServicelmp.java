package com.curso.odoo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.curso.odoo.model.estado;
import com.curso.odoo.repositorio.EstadoRepositorio;
import com.curso.odoo.service.api.EstadoServicesAPI;

@Service
public class EstadoServicelmp  implements EstadoServicesAPI{
	
	@Autowired
	EstadoRepositorio estrepo1;

	@Override
	public Page<estado> getAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return  estrepo1.findAll(pageable);
	}

}
