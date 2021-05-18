package com.curso.odoo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.curso.odoo.model.Cliente;
import com.curso.odoo.repositorio.ClienteRepositorio;
import com.curso.odoo.service.api.ClienteServicesAPI;


@Service
public class ClientesServiceImpl implements ClienteServicesAPI{

	@Autowired
	ClienteRepositorio clirepo;
	
	
	@Override
	public Page<Cliente> getAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return clirepo.findAll(pageable);
	}

}
