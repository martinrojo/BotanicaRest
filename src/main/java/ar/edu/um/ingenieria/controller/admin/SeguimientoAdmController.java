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
import ar.edu.um.ingenieria.domain.Seguimiento;
import ar.edu.um.ingenieria.service.impl.SeguimientoServiceImpl;

@RestController
@RequestMapping("/admin/seguimientos")
public class SeguimientoAdmController {
	
	@Autowired
	private SeguimientoServiceImpl seguimientoServiceImpl;

	private static final Logger logger = LoggerFactory.getLogger(SeguimientoAdmController.class);
//Get devuelve todos
	@GetMapping
	public List<Seguimiento> indexPage() {
		logger.info("datos de seguimiento: {}", seguimientoServiceImpl.findAll());
		return seguimientoServiceImpl.findAll();
	}

	@GetMapping("/{id}")
	public Seguimiento show(@PathVariable Integer id) {
		logger.info("datos de seguimiento: {}", seguimientoServiceImpl.findById(id));
		return seguimientoServiceImpl.findById(id);
	}
	
	@PostMapping("/create")
	public ResponseEntity<Void> agregar(Integer usuario, Integer planta, Integer estado) {
		seguimientoServiceImpl.create(usuario, planta, estado);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
//Put es para actualizar, patch es para actualizar parcialmente	
	@PostMapping("/update")
	public ResponseEntity<Void> agregar(Integer usuario, Integer planta, Integer estado, Integer tarea, Integer etapa) {
		seguimientoServiceImpl.update(usuario, planta, estado,tarea, etapa);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@PostMapping
	public Seguimiento add(@RequestBody Seguimiento seguimiento) {
		return seguimientoServiceImpl.create(seguimiento);
	}

	@PutMapping(value = "/{id}")
	public Seguimiento update(@RequestBody Seguimiento seguimiento, @PathVariable Integer id) {
		seguimiento.setId(id);
		return seguimientoServiceImpl.update(seguimiento);
	}

	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable Integer id) {
		seguimientoServiceImpl.remove(seguimientoServiceImpl.findById(id));
	}
}
