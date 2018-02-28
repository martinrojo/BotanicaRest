package ar.edu.um.ingenieria.controller.seguimiento;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ar.edu.um.ingenieria.domain.Estado;
import ar.edu.um.ingenieria.service.impl.EstadoServiceImpl;

@RestController
@RequestMapping("/estados")
public class EstadoController {
	
	private static final Logger logger = LoggerFactory.getLogger(EstadoController.class);
	
	@Autowired
	private EstadoServiceImpl estadoServiceImpl;
	
	/*@GetMapping
    public List<Estado> indexPage() {
		logger.info("datos de Estado: {}", estadoServiceImpl.findByUser(id));
	    return estadoServiceImpl.findByUser(id);
    }*/
	
	@GetMapping("/{id}")
    public Estado show(@PathVariable Integer id) {
		logger.info("datos de Estado: {}", estadoServiceImpl.findAll());
	    return estadoServiceImpl.findById(id);
    }
	
	@PostMapping("/create")
	public ResponseEntity<Void> agregar(String nombre, String descripcion) {
		estadoServiceImpl.create(nombre, descripcion);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
//Put es para actualizar, patch es para actualizar parcialmente	
	@PostMapping("/update")
	public ResponseEntity<Void> agregar(Integer estado, String nombre, String descripcion) {
		estadoServiceImpl.update(estado, nombre, descripcion);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@PostMapping
	public Estado add(@RequestBody Estado estado) {
		return estadoServiceImpl.create(estado);
	}

	@PutMapping(value = "/{id}")
	public Estado update(@RequestBody Estado estado, @PathVariable Integer id) {
		estado.setId(id);
		return estadoServiceImpl.update(estado);
	}

	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable Integer id) {
		estadoServiceImpl.remove(estadoServiceImpl.findById(id));
	}
	
}
