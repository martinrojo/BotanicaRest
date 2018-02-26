package ar.edu.um.ingenieria.controller.ventas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.um.ingenieria.domain.Venta;
import ar.edu.um.ingenieria.manager.VentaManager;
import ar.edu.um.ingenieria.service.impl.VentaServiceImpl;

@RestController
@RequestMapping("/ventas")
public class VentasController {  // no funciona nada
	@Autowired
	private VentaManager ventaManager;
	private VentaServiceImpl ventaServiceImpl;
	
	@GetMapping("/ventas/")
	public ResponseEntity<List<Venta>> findAll() {
		return new ResponseEntity<List<Venta>>(ventaServiceImpl.findAll(), HttpStatus.OK);
	}

	@GetMapping("/ventas/{id}")
	public ResponseEntity<Venta> edit(@PathVariable Integer id) {
		return new ResponseEntity<Venta>(ventaServiceImpl.findById(id),HttpStatus.OK);
	}
	
	@PostMapping("/crear")
	public ResponseEntity<Void> agregar(Venta venta) {
		ventaServiceImpl.create(venta);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PostMapping("/actulizar")
	public ResponseEntity<Void> actulizar(Venta venta) {
		ventaServiceImpl.create(venta);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void>  delete(@PathVariable Integer id) {
		ventaManager.delete(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
	
}
