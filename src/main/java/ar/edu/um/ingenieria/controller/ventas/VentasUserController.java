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
import ar.edu.um.ingenieria.domain.Venta;
import ar.edu.um.ingenieria.service.impl.TipoVentaServiceImpl;
import ar.edu.um.ingenieria.service.impl.VentaServiceImpl;

@RestController
@RequestMapping("/ventas")
@Secured({ "ROLE_USER", "ROLE_VENDEDOR", "ROLE_ADMIN" })
public class VentasUserController { // contiene solo los metodos disponibles para los usuarios
	@Autowired
	private VentaServiceImpl ventaServiceImpl;
	@Autowired
	private TipoVentaServiceImpl tipoVentaServiceImpl;

	private static final Logger logger = Logger.getLogger(VentaServiceImpl.class);

	@GetMapping("/")
	public ResponseEntity<List<Venta>> findAll() {
		if (tipoVentaServiceImpl.findAll().isEmpty()) {
			logger.info("No hay ventas");
			return new ResponseEntity<List<Venta>>(ventaServiceImpl.findAll(), HttpStatus.NO_CONTENT);
		}
		logger.info("Ventas: " + ventaServiceImpl.findAll());
		return new ResponseEntity<List<Venta>>(ventaServiceImpl.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Venta> findById(@PathVariable Integer id) {
		if (ventaServiceImpl.findById(id) == null) {
			logger.info("No existe la venta ID " + id);
			return new ResponseEntity<Venta>(HttpStatus.NO_CONTENT);
		}
		logger.info("Venta ID " + id + ": " + ventaServiceImpl.findById(id));
		return new ResponseEntity<Venta>(ventaServiceImpl.findById(id), HttpStatus.OK);
	}

	@GetMapping("/tipoventa/{id}")
	public ResponseEntity<List<Venta>> findByTipo(@PathVariable Integer id) {
		if (tipoVentaServiceImpl.findById(id) == null) {
			logger.info("No existe el tipo de venta ID " + id);
			return new ResponseEntity<List<Venta>>(ventaServiceImpl.findAll(), HttpStatus.OK);
		} else {
			TipoVenta tipoVenta = tipoVentaServiceImpl.findById(id);
			if (tipoVenta.getVentas().isEmpty()) {
				logger.info("El tipo de venta ID:" + id + "no continene ninguan venta");
				return new ResponseEntity<List<Venta>>(HttpStatus.NO_CONTENT);
			}
			logger.info("El tipo de venta ID:" + id + "continene las ventas " + tipoVenta.getVentas());
			return new ResponseEntity<List<Venta>>(tipoVenta.getVentas(), HttpStatus.OK);
		}
	}
}
