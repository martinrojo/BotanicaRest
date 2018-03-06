package ar.edu.um.ingenieria.controller.ventas;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.um.ingenieria.domain.TipoVenta;
import ar.edu.um.ingenieria.service.impl.TipoVentaServiceImpl;

@RestController
@RequestMapping("/tipoventas")
@Secured({ "ROLE_USER", "ROLE_VENDEDOR", "ROLE_ADMIN" })
public class TipoVentaController {

	@Autowired
	private TipoVentaServiceImpl tipoVentaServiceImpl;

	private static final Logger logger = Logger.getLogger(TipoVentaServiceImpl.class);

	@GetMapping("/")
	public ResponseEntity<List<TipoVenta>> findAll() {
		if (tipoVentaServiceImpl.findAll().isEmpty()) {
			logger.info("No hay tipos de ventas");
			return new ResponseEntity<List<TipoVenta>>(tipoVentaServiceImpl.findAll(), HttpStatus.NO_CONTENT);
		}
		logger.info("Tipos de ventas: " + tipoVentaServiceImpl.findAll());
		return new ResponseEntity<List<TipoVenta>>(tipoVentaServiceImpl.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<TipoVenta> findById(@PathVariable Integer id) {
		if (tipoVentaServiceImpl.findById(id) == null) {
			logger.info("No existe el tipo de venta ID: " + id);
			return new ResponseEntity<TipoVenta>(HttpStatus.NO_CONTENT);
		}
		logger.info("Tipo de venta ID: " + id + ": " + tipoVentaServiceImpl.findById(id));
		return new ResponseEntity<TipoVenta>(tipoVentaServiceImpl.findById(id), HttpStatus.OK);
	}
}
