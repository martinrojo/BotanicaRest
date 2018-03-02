package ar.edu.um.ingenieria.controller.admin;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import ar.edu.um.ingenieria.domain.Seguimiento;
import ar.edu.um.ingenieria.service.impl.SeguimientoServiceImpl;

@RestController
@RequestMapping("/admin/seguimientos")
@Secured({"ROLE_ADMIN"})
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
		List<Seguimiento> seguimientos = seguimientoServiceImpl.findAll();
		boolean isEmpty = true;
		for (int i = 0;i < seguimientos.size();i++)
		{
			if ((seguimientos.get(i).getUsuario().getId() == usuario) && (seguimientos.get(i).getPlanta().getId() == planta) &&(seguimientos.get(i).getEstado().getId() == estado))
					{
				isEmpty = false;
					}
		}
		if (isEmpty == true)
		{
			logger.info("Seguimiento creado con exito");
			seguimientoServiceImpl.create(usuario,planta,estado);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			logger.info("Seguimiento existente");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
	}
	@PostMapping("/{id}/regar")
	public ResponseEntity<Seguimiento> regar(@PathVariable Integer id) {
			seguimientoServiceImpl.regar(id);
			logger.info("Riego registrado con exito");
			return new ResponseEntity<Seguimiento>(seguimientoServiceImpl.findById(id),HttpStatus.OK);
	}
	
	@PostMapping("/{id}/suelo_preparado")
	public ResponseEntity<Seguimiento> sueloPreparado(@PathVariable Integer id) {
			seguimientoServiceImpl.sueloPreparado(id);
			logger.info("Suelo preparado con exito");
			return new ResponseEntity<Seguimiento>(seguimientoServiceImpl.findById(id),HttpStatus.OK);
	}
	
	@PostMapping("/{id}/sembrado")
	public ResponseEntity<Seguimiento> sembrado(@PathVariable Integer id) {
			seguimientoServiceImpl.sembrado(id);
			logger.info("Siembra realizado con exito");
			return new ResponseEntity<Seguimiento>(seguimientoServiceImpl.findById(id),HttpStatus.OK);
	}
	
	@PostMapping("/{id}/podar")
	public ResponseEntity<Seguimiento> podar(@PathVariable Integer id) {
			seguimientoServiceImpl.podar(id);
			logger.info("Poda establecida con exito");
			return new ResponseEntity<Seguimiento>(seguimientoServiceImpl.findById(id),HttpStatus.OK);
	}
	
	@PostMapping("/{id}/transplantar")
	public ResponseEntity<Seguimiento> transplantar(@PathVariable Integer id) {
			seguimientoServiceImpl.transplantar(id);
			logger.info("Transplante establecido con exito");
			return new ResponseEntity<Seguimiento>(seguimientoServiceImpl.findById(id),HttpStatus.OK);
	}
	
	@PostMapping("/{id}/cosechar")
	public ResponseEntity<Seguimiento> cosechar(@PathVariable Integer id) {
			seguimientoServiceImpl.cosechar(id);
			logger.info("Cosecha establecida con exito");
			return new ResponseEntity<Seguimiento>(seguimientoServiceImpl.findById(id),HttpStatus.OK);
	}
	
	@PostMapping("/{id}/abonar")
	public ResponseEntity<Seguimiento> abonar(@PathVariable Integer id) {
			seguimientoServiceImpl.abonar(id);
			logger.info("Abono establecido con exito");
			return new ResponseEntity<Seguimiento>(seguimientoServiceImpl.findById(id),HttpStatus.OK);
	}
//Put es para actualizar, patch es para actualizar parcialmente	
	@PostMapping("/update")
	public ResponseEntity<Void> agregar(Integer usuario, Integer planta, Integer estado, Integer tarea, Integer etapa, Integer seguimiento) {
		seguimientoServiceImpl.update(usuario, planta, estado,tarea, etapa,seguimiento);
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
