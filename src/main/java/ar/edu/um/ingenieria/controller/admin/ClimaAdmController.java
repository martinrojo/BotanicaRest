package ar.edu.um.ingenieria.controller.admin;

import java.util.List;
import org.apache.log4j.Logger;
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
import ar.edu.um.ingenieria.domain.Clima;
import ar.edu.um.ingenieria.service.impl.ClimaServiceImpl;
import ar.edu.um.ingenieria.service.impl.PlantaServiceImpl;

@RestController
@RequestMapping("/admin/climas")
public class ClimaAdmController {
	
	@Autowired
	private ClimaServiceImpl climaServiceImpl;
	
	private static final Logger logger = Logger.getLogger(PlantaServiceImpl.class);

	@GetMapping
	public List<Clima> indexPage()
	{
		logger.info("Datos de los climas:"+climaServiceImpl.findAll());
		return climaServiceImpl.findAll();
	}
	
	@GetMapping("/{id}")
	public Clima show(@PathVariable Integer id)
	{
		logger.info("Datos de la planta:"+climaServiceImpl.findById(id));
		return climaServiceImpl.findById(id);
	}
	
	/*@PostMapping("/create")
	public ResponseEntity<Void> agregar(String nombre, String descripcion) {
		Clima clima = new Clima();
		clima.setNombre(nombre);
		clima.setDescripcion(descripcion);
		climaServiceImpl.create(clima);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}*/
	
	@PostMapping("/create")
	public ResponseEntity<Void> agregar(String nombre, String descripcion) {
		climaServiceImpl.create(nombre, descripcion);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
	/*@PostMapping("/update")
	public ResponseEntity<Void> agregar(String nombre, String descripcion, Clima clima) {
		clima.setNombre(nombre);
		clima.setDescripcion(descripcion);
		climaServiceImpl.create(clima);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}*/
	
	@PostMapping("/update")
	public ResponseEntity<Void> agregar(Integer clima, String nombre, String descripcion) {
		climaServiceImpl.update(clima, nombre, descripcion);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@PostMapping
	public Clima add(@RequestBody Clima clima) {
		return climaServiceImpl.create(clima);
	}

	@PutMapping(value = "/{id}")
	public Clima update(@RequestBody Clima clima, @PathVariable Integer id) {
		clima.setId(id);
		return climaServiceImpl.update(clima);
	}

	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable Integer id) {
		climaServiceImpl.remove(climaServiceImpl.findById(id));
	}
}