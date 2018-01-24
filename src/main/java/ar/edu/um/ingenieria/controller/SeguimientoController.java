package ar.edu.um.ingenieria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("/")
public class SeguimientoController {
	@Autowired
	private SeguimientoManager seguimientoManager;
	
	@PostMapping("/seguimiento")
	public ResponseEntity<Void> agregar(Usuario usuario, Planta planta, Estado estado, Tarea tarea, Etapa etapa, Seguimiento seguimiento) {
		seguimientoManager.create(usuario, planta, estado, tarea, etapa, seguimiento);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
