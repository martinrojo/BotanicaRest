package ar.edu.um.ingenieria.controller.admin;

import java.util.List;
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

import ar.edu.um.ingenieria.domain.Categoria;
import ar.edu.um.ingenieria.domain.Estado;
import ar.edu.um.ingenieria.domain.Seguimiento;
import ar.edu.um.ingenieria.manager.CategoriaManager;
import ar.edu.um.ingenieria.service.impl.CategoriaServiceImpl;
import ar.edu.um.ingenieria.service.impl.EstadoServiceImpl;

@RestController
@RequestMapping("/admin/estado")
public class EstadoAdmController {
	
	@Autowired
	private EstadoServiceImpl estadoServiceImpl;

	private static final Logger logger = LoggerFactory.getLogger(EstadoAdmController.class); 
	
	//Get devuelve todos
		@GetMapping
		public List<Estado> indexPage() {
			logger.info("datos de estado: {}", estadoServiceImpl.findAll());
			return estadoServiceImpl.findAll();
		}

		@GetMapping("/{id}")
		public Estado show(@PathVariable Integer id) {
			logger.info("datos de estado: {}", estadoServiceImpl.findById(id));
			return estadoServiceImpl.findById(id);
		}
		
		@PostMapping("/create")
		public ResponseEntity<Void> agregar(String nombre, String descripcion, Integer planta) {
			estadoServiceImpl.create(nombre, descripcion, planta);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	//Put es para actualizar, patch es para actualizar parcialmente	
		@PostMapping("/update")
		public ResponseEntity<Void> agregar(Integer estado, String nombre, String descripcion, Integer planta) {
			estadoServiceImpl.update(estado, nombre, descripcion, planta);
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
