package com.curso.odoo.service.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import com.curso.odoo.model.Factura;

public interface FacturacionServiceAPI {
	Page<Factura> getAll(Pageable pageable);

}
