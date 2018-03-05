package ar.edu.um.ingenieria.controller.admin;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.um.ingenieria.domain.Persona;
import ar.edu.um.ingenieria.service.impl.PersonaServiceImpl;
import ar.edu.um.ingenieria.service.impl.UsuarioServiceImpl;

@RestController
@RequestMapping("/admin/personas")
@Secured({"ROLE_ADMIN"})
public class PersonaAdmController {
	@Autowired
	private PersonaServiceImpl personaServiceImpl;
	
	@Autowired
	private UsuarioServiceImpl usuarioServiceImpl;
	
	private static final Logger logger = Logger.getLogger(PersonaAdmController.class);

	@GetMapping
	public List<Persona> indexPage()
	{
		logger.info("Datos de las plantas:"+personaServiceImpl.findAll());
		return personaServiceImpl.findAll();
	}
	
	@GetMapping("/{id}")
	public Persona show(@PathVariable Integer id)
	{
		logger.info("Datos de la planta:"+personaServiceImpl.findById(id));
		return personaServiceImpl.findById(id);
	}
	
	@PostMapping("/create")
	public ResponseEntity<Void> agregar(String apellido, String nombre, Date fechaNacimiento, Integer idUsuario) {
		boolean isEmpty = true;
		List<Persona> personas = personaServiceImpl.findAll();
		for (int i = 0;i < personas.size();i++)
		{
			if (nombre.equals(personas.get(i).getNombre()) && (personas.get(i).getApellido() == apellido))
					{
				isEmpty = false;
					}
		}
		if (isEmpty == true)
		{
			Persona persona = new Persona();
			persona.setNombre(nombre);
			persona.setApellido(apellido);
			persona.setFechaNacimiento(fechaNacimiento);
			persona.setUsuario(usuarioServiceImpl.findById(idUsuario));
			logger.info("Persona creada con exito");
			personaServiceImpl.create(persona);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			logger.info("Persona existente");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
	}

	//Put es para actualizar, patch es para actualizar parcialmente	
	@PostMapping("/update")
	public ResponseEntity<Void> update(String apellido, String nombre, Date fechaNacimiento, Integer idPersona) {
		if (personaServiceImpl.findById(idPersona) == null) {
			logger.info("No existe esa persona");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		Persona persona = personaServiceImpl.findById(idPersona);
		persona.setNombre(nombre);
		persona.setApellido(apellido);
		persona.setFechaNacimiento(fechaNacimiento);
		personaServiceImpl.update(persona);
		logger.info("Persona actualizada");
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@PostMapping
	public Persona add(@RequestBody Persona persona) {
		return personaServiceImpl.create(persona);
	}

	@PutMapping(value = "/{id}")
	public Persona update(@RequestBody Persona persona, @PathVariable Integer id) {
		persona.setId(id);
		return personaServiceImpl.update(persona);
	}

	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable Integer id) {
		personaServiceImpl.remove(personaServiceImpl.findById(id));
	}
}
