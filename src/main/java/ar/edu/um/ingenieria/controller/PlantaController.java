package ar.edu.um.ingenieria.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.um.ingenieria.domain.Planta;
import ar.edu.um.ingenieria.repository.PlantaRepository;

@RestController
@RequestMapping("/plantas")
public class PlantaController {
	
private static final Logger logger = LoggerFactory.getLogger(PlantaController.class);
	
	@Autowired
	private PlantaRepository plantaRepository;
	
	@GetMapping
    public List<Planta> indexPage() {
		logger.info("datos de Planta: {}", plantaRepository.findAll());
	    return plantaRepository.findAll();
    }
	
	@GetMapping("/{id}")
    public Planta show(@PathVariable Integer id) {
		logger.info("datos de Planta: {}", plantaRepository.findAll());
	    return plantaRepository.getOne(id);
    }
}
