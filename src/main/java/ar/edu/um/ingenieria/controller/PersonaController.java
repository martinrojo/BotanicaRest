package ar.edu.um.ingenieria.controller;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.um.ingenieria.domain.Persona;
import ar.edu.um.ingenieria.service.impl.CategoriaServiceImpl;
import ar.edu.um.ingenieria.service.impl.PersonaServiceImpl;
import ar.edu.um.ingenieria.service.impl.UsuarioSecurityServiceImpl;
import ar.edu.um.ingenieria.service.impl.UsuarioServiceImpl;

@RestController
@RequestMapping("/persona")
@Secured({ "ROLE_USER", "ROLE_VENDEDOR", "ROLE_ADMIN" })
public class PersonaController {

	@Autowired
	private PersonaServiceImpl personaServiceImpl;

	@Autowired
	private UsuarioServiceImpl usuarioServiceImpl;

	@Autowired
	private UsuarioSecurityServiceImpl usuarioSecurity;

	private static final Logger logger = Logger.getLogger(CategoriaServiceImpl.class);

	@GetMapping()
	public ResponseEntity<Persona> indexPage() {
		if (usuarioServiceImpl.findById(usuarioSecurity.GetIdUser()).getPersona() == null) {
			logger.info("No existe la persona de ID:" + usuarioSecurity.GetIdUser());
			return new ResponseEntity<Persona>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Persona>(usuarioServiceImpl.findById(usuarioSecurity.GetIdUser()).getPersona(),
				HttpStatus.NO_CONTENT);
	}

	@PutMapping
	public ResponseEntity<Void> update(String apellido, String nombre, Date fechaNacimiento, Integer id) {
		if (usuarioServiceImpl.findById(id) == null) {
			logger.info("No existe el usuario de ID:" + id);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		Persona persona = new Persona();
		persona.setApellido(apellido);
		persona.setNombre(nombre);
		persona.setFechaNacimiento(fechaNacimiento);
		persona.setUsuario(usuarioServiceImpl.findById(id));
		personaServiceImpl.update(persona);
		logger.info("Se actualizo la persona con exito." + persona);
		;
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
