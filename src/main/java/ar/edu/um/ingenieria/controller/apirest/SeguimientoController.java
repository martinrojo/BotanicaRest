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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.um.ingenieria.domain.Seguimiento;
import ar.edu.um.ingenieria.service.impl.SeguimientoServiceImpl;

@RestController
@RequestMapping(value = "/seguimiento" , method = RequestMethod.GET)
public class SeguimientoController {
	@Autowired
	private SeguimientoServiceImpl seguimientoService;
	

private static final Logger logger = LoggerFactory.getLogger(SeguimientoController.class);
	

	@GetMapping
    public List<Seguimiento> indexPage() {
		logger.info("datos de seguimiento: {}", seguimientoService.findAll());
	    return seguimientoService.findAll();
    }
	
	@GetMapping("/{id}")
    public Seguimiento show(@PathVariable Integer id) {
		logger.info("datos de seguimiento: {}", seguimientoService.findAll());
	    return seguimientoService.findById(id);
    }
	
	@PostMapping
    public Seguimiento add(@RequestBody Seguimiento seguimiento) {
	    return seguimientoService.create(seguimiento);
    }
	
	@PutMapping(value = "/{id}")
    public Seguimiento update(@RequestBody Seguimiento seguimiento, @PathVariable Integer id) {
		seguimiento.setId(id);
	    return seguimientoService.update(seguimiento);
    }
	
	
	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable Integer id) {
		seguimientoService.remove(seguimientoService.findById(id));
	}
}
