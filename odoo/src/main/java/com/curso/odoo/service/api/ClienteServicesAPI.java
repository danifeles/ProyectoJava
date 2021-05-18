package com.curso.odoo.service.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.curso.odoo.model.Cliente;


public interface ClienteServicesAPI {
	Page<Cliente> getAll(Pageable pageable);

}
