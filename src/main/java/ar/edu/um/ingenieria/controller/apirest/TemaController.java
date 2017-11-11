package ar.edu.um.ingenieria.controller.apirest;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import ar.edu.um.ingenieria.repository.TemaRepository;
import ar.edu.um.ingenieria.domain.Tema;

@RestController
@RequestMapping("/temas")
public class TemaController {
	private static final Logger logger = LoggerFactory.getLogger(TemaController.class);
	@Autowired
	private TemaRepository temaRepository;
	
	@GetMapping //lo usamos para devolver datos
	public List<Tema> indexPage() {
		logger.info("datos de clima: {}", temaRepository.findAll());
		return temaRepository.findAll();
	}
	
	@GetMapping("/{id}") 
	public Tema show(@PathVariable Integer id) {
		logger.info("datos de tema: {}", temaRepository.findAll());
		return temaRepository.getOne(id);
	}
}
