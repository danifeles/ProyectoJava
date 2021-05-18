package com.curso.odoo.service.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import com.curso.odoo.model.comercial;

public interface ComercialServicesAPI {

	Page<comercial> getAll(Pageable pageable);
}
