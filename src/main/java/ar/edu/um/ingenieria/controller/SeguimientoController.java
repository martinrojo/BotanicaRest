package ar.edu.um.ingenieria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.um.ingenieria.domain.Estado;
import ar.edu.um.ingenieria.domain.Etapa;
import ar.edu.um.ingenieria.domain.Planta;
import ar.edu.um.ingenieria.domain.Seguimiento;
import ar.edu.um.ingenieria.domain.Tarea;
import ar.edu.um.ingenieria.domain.Usuario;
import ar.edu.um.ingenieria.manager.SeguimientoManager;

@RestController
@RequestMapping("/seguimiento")
public class SeguimientoController {
	
	@Autowired
	private SeguimientoManager seguimientoManager;

	public ResponseEntity<List<Seguimiento>> findAll() {
		return new ResponseEntity<List<Seguimiento>>(seguimientoManager.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/seguimiento/nuevo")
	public ResponseEntity<Seguimiento> edit(@PathVariable Usuario usuario, Planta planta, Estado estado, Tarea tarea, Etapa etapa, Seguimiento seguimiento) {
		seguimientoManager.create(usuario, planta, estado, tarea, etapa, seguimiento);
		return new ResponseEntity<Seguimiento>(seguimientoManager.findById(seguimiento.getId()),HttpStatus.OK);
	}
	
	@GetMapping("/seguimiento/{id}")
	public ResponseEntity<Seguimiento> edit(@PathVariable Integer id) {
		return new ResponseEntity<Seguimiento>(seguimientoManager.findById(id),HttpStatus.OK);
	}
	
	@DeleteMapping("/seguimiento/{id}")
	public ResponseEntity<Void>  delete(@PathVariable Integer id) {
		seguimientoManager.delete(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
