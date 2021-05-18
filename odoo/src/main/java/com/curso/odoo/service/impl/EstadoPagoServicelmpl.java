package com.curso.odoo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import com.curso.odoo.model.estadopago;
import com.curso.odoo.repositorio.EstadoPagoRepositorio;
import com.curso.odoo.service.api.EstadoPagoServicesAPI;


@Service
public class EstadoPagoServicelmpl implements EstadoPagoServicesAPI {
	
	@Autowired
	EstadoPagoRepositorio estrepo2;

	@Override
	public Page<estadopago> getAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return estrepo2.findAll(pageable);
	}
	
}
