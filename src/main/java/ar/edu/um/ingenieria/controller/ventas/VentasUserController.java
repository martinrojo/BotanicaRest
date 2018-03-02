package ar.edu.um.ingenieria.controller.ventas;

import java.util.List;

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
@Secured({"ROLE_USER" , "ROLE_VENDEDOR", "ROLE_ADMIN"})
public class VentasUserController { // contiene solo los metodos disponibles para los usuarios
	@Autowired
	private VentaServiceImpl ventaServiceImpl;
	@Autowired
	private TipoVentaServiceImpl tipoVentaServiceImpl;

	@GetMapping("/")
	public ResponseEntity<List<Venta>> findAll() {
		return new ResponseEntity<List<Venta>>(ventaServiceImpl.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Venta> findById(@PathVariable Integer id) {
		if(ventaServiceImpl.findById(id)==null)
			return new ResponseEntity<Venta>(HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<Venta>(ventaServiceImpl.findById(id),HttpStatus.OK);
	}
	
	@GetMapping("/tipoventa/{id}")
	public ResponseEntity<List<Venta>> findByTipo(@PathVariable Integer id) {
		if (tipoVentaServiceImpl.findById(id) == null) {
			return new ResponseEntity<List<Venta>>(ventaServiceImpl.findAll(), HttpStatus.OK);
		} else {
			TipoVenta tipoVenta = tipoVentaServiceImpl.findById(id);
			if(tipoVenta.getVentas() == null) {
				new ResponseEntity<List<Venta>>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<Venta>>(tipoVenta.getVentas(), HttpStatus.OK);
		}
	}
	
}
