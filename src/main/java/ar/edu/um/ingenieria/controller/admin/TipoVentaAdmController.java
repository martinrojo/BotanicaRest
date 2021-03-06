package ar.edu.um.ingenieria.controller.admin;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.um.ingenieria.domain.TipoVenta;
import ar.edu.um.ingenieria.service.impl.TipoVentaServiceImpl;

@RestController
@RequestMapping("/admin/tipoventas")
@Secured({ "ROLE_ADMIN" })
public class TipoVentaAdmController {

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

	@PostMapping("/")
	public ResponseEntity<Void> insert(String nombre, String descripcion) {
		TipoVenta tipoVenta = new TipoVenta();
		tipoVenta.setNombre(nombre);
		tipoVenta.setDescripcion(descripcion);
		tipoVentaServiceImpl.create(tipoVenta);
		logger.info("Creado tipo de venta");
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@PostMapping("/edit/")
	public ResponseEntity<Void> edit(Integer id, String nombre, String descripcion) {
		if (tipoVentaServiceImpl.findById(id) == null) {
			logger.info("No existe el tipo de venta ID: " + id);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} else {
			TipoVenta tipoVenta = tipoVentaServiceImpl.findById(id);
			tipoVenta.setNombre(nombre);
			tipoVenta.setDescripcion(descripcion);
			tipoVentaServiceImpl.update(tipoVenta);
			logger.info("Actualizado el tipo de venta " + tipoVenta);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		if (tipoVentaServiceImpl.findById(id) == null) {
			logger.info("No existe el tipo de venta ID: " + id);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} else {
			tipoVentaServiceImpl.remove(tipoVentaServiceImpl.findById(id));
			logger.info("borrado el tipo de venta ID: " + id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}

	}
}
