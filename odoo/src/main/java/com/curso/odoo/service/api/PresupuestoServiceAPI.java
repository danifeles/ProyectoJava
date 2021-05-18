package com.curso.odoo.service.api;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.curso.odoo.model.Presupuesto;

public interface PresupuestoServiceAPI {

	Page<Presupuesto> getAll(Pageable pageable);
}
