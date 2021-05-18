package com.curso.odoo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.curso.odoo.model.Factura;
import com.curso.odoo.repositorio.FacturaRepositorio;
import com.curso.odoo.service.api.FacturacionServiceAPI;

@Service
public class FacturaServicelmpl  implements FacturacionServiceAPI {

	@Autowired
	FacturaRepositorio facrepo;
	@Override
	public Page<Factura> getAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return facrepo.findAll(pageable);
	}

}
