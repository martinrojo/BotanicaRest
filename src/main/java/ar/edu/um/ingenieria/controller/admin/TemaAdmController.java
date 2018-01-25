package ar.edu.um.ingenieria.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.um.ingenieria.domain.Tema;
import ar.edu.um.ingenieria.manager.TemaManager;
import ar.edu.um.ingenieria.service.impl.TemaServiceImpl;

@RestController
@RequestMapping("/admin/tema")
public class TemaAdmController {

	@Autowired
	private TemaServiceImpl temaServiceImpl;
	@Autowired
	private TemaManager temaManager;
	
	@GetMapping("/{id}")
	public ResponseEntity<List<Tema>> findAll() {
		return new ResponseEntity<List<Tema>>(temaServiceImpl.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Tema> edit(@PathVariable Integer id) {
		return new ResponseEntity<Tema>(temaManager.findById(id),HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void>  delete(@PathVariable Integer id) {
		temaManager.delete(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
