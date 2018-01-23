package ar.edu.um.ingenieria.controller.apirest;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import ar.edu.um.ingenieria.repository.ClimaRepository;
import ar.edu.um.ingenieria.domain.Clima;

@RestController
@RequestMapping("/climas")
public class ClimaController {
	private static final Logger logger = LoggerFactory.getLogger(ClimaController.class);
	@Autowired
	private ClimaRepository climaRepository;
	
	@GetMapping //lo usamos para devolver datos
	public List<Clima> indexPage() { //devolver DTO del servicio que queremos mostrar en la aplicacion
		logger.info("datos de clima: {}", climaRepository.findAll());
		return climaRepository.findAll();
	}
	
	@GetMapping("/{id}") 
	public Clima show(@PathVariable Integer id) {
		logger.info("datos de usuario: {}", climaRepository.findAll());
		return climaRepository.getOne(id);
	}
	
	@PostMapping //lo usamos para agregar
	public Clima add(@RequestBody Clima clima) {
		return climaRepository.saveAndFlush(clima);
	}
	
	@PutMapping(value = "/{id}") //para actualizar
	public Clima update(@RequestBody Clima clima, @PathVariable Integer id) {
		clima.setId(id);
		return climaRepository.saveAndFlush(clima);
	}
	
	@DeleteMapping(value = "/{id}") //para borrar
	public void delete(@PathVariable Integer id) {
		climaRepository.delete(id);
	}
}
