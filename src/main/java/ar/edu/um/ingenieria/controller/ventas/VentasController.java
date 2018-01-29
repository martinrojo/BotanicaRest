package ar.edu.um.ingenieria.controller.ventas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.um.ingenieria.domain.Venta;
//import ar.edu.um.ingenieria.manager.VentaManager;
import ar.edu.um.ingenieria.service.impl.VentaServiceImpl;

@RestController
@RequestMapping("/ventas")
public class VentasController {  // no funciona nada
	//@Autowired
	//private VentaManager ventaManager;
	@Autowired
	private VentaServiceImpl ventaServiceImpl;
	
	@PostMapping("/crear")
	public ResponseEntity<Void> agregar(Venta venta) {
		System.out.println("daasdasdas "+venta);
		ventaServiceImpl.create(venta);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PostMapping("/actulizar")
	public ResponseEntity<Void> actulizar(Venta venta) {
		System.out.println("daasdasdas "+venta);
		ventaServiceImpl.create(venta);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
}
