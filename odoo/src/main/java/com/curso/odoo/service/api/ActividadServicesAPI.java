package com.curso.odoo.service.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.curso.odoo.model.actividad;



public interface ActividadServicesAPI {
	Page<actividad> getAll(Pageable pageable);
}
