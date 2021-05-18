package com.curso.odoo.service.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.curso.odoo.model.estadopago;

public interface EstadoPagoServicesAPI {

	Page<estadopago> getAll(Pageable pageable);
}
