package ar.edu.um.ingenieria.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.um.ingenieria.domain.TipoVenta;
import ar.edu.um.ingenieria.service.impl.TipoVentaServiceImpl;

@RestController
@RequestMapping("/admin")
public class TipoVentaAdmController {

	@Autowired
	private TipoVentaServiceImpl tipoVentaServiceImpl;

	@GetMapping("/tipoventas/")
	public ResponseEntity<List<TipoVenta>> findAll() {
		return new ResponseEntity<List<TipoVenta>>(tipoVentaServiceImpl.findAll(), HttpStatus.OK);
	}

	@GetMapping("/tipoventas/{id}")
	public ResponseEntity<TipoVenta> edit(@PathVariable Integer id) {
		if(tipoVentaServiceImpl.findById(id)==null)
			return new ResponseEntity<TipoVenta>(HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<TipoVenta>(tipoVentaServiceImpl.findById(id),HttpStatus.OK);
	}

	@DeleteMapping("/tipoventas/{id}")
	public ResponseEntity<Void>  delete(@PathVariable Integer id) {
		if(tipoVentaServiceImpl.findById(id)==null)
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		else {
			tipoVentaServiceImpl.remove(tipoVentaServiceImpl.findById(id));
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		
	}
}

