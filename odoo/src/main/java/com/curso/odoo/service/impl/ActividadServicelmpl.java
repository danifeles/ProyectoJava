package com.curso.odoo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.curso.odoo.model.actividad;
import com.curso.odoo.repositorio.ActividadRepositorio;
import com.curso.odoo.service.api.ActividadServicesAPI;


@Service
public class ActividadServicelmpl implements  ActividadServicesAPI{

	@Autowired
	ActividadRepositorio actrepo1;
	@Override
	public Page<actividad> getAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return actrepo1.findAll(pageable);
	}

}