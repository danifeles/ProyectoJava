package com.curso.odoo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.curso.odoo.model.comercial;
import com.curso.odoo.repositorio.ComercialRepositorio;
import com.curso.odoo.service.api.ComercialServicesAPI;
@Service
public class ComercialServicelmpl implements ComercialServicesAPI {
	@Autowired
	ComercialRepositorio comrepo1;

	@Override
	public Page<comercial> getAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return comrepo1.findAll(pageable);
	}
}
