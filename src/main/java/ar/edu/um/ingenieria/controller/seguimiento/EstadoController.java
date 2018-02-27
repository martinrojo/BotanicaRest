package ar.edu.um.ingenieria.controller.seguimiento;

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

import ar.edu.um.ingenieria.domain.Estado;

import ar.edu.um.ingenieria.repository.EstadoRepository;

@RestController
@RequestMapping("/estados")
public class EstadoController {
	
private static final Logger logger = LoggerFactory.getLogger(EstadoController.class);
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@GetMapping
    public List<Estado> indexPage() {
		logger.info("datos de Estado: {}", estadoRepository.findAll());
	    return estadoRepository.findAll();
    }
	
	@GetMapping("/{id}")
    public Estado show(@PathVariable Integer id) {
		logger.info("datos de Estado: {}", estadoRepository.findAll());
	    return estadoRepository.getOne(id);
    }
	
	@PostMapping
    public Estado add(@RequestBody Estado estado) {
	    return estadoRepository.saveAndFlush(estado);
    }
	
	@PutMapping(value = "/{id}")
    public Estado update(@RequestBody Estado estado, @PathVariable Integer id) {
		estado.setId(id);
	    return estadoRepository.saveAndFlush(estado);
    }
	
	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable Integer id) {
		estadoRepository.delete(id);
	}

}
