package com.curso.odoo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.curso.odoo.model.Presupuesto;
import com.curso.odoo.repositorio.PresupuestoRepositorio;
import com.curso.odoo.service.api.PresupuestoServiceAPI;

@Service
public class PresupuestoServicelmpl  implements PresupuestoServiceAPI {

	@Autowired
	PresupuestoRepositorio prerepo;
	@Override
	public Page<Presupuesto> getAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return prerepo.findAll(pageable);
	}

}
