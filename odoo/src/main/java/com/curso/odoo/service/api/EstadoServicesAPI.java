package com.curso.odoo.service.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import com.curso.odoo.model.estado;

public interface EstadoServicesAPI {

	Page<estado> getAll(Pageable pageable);
}
