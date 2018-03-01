package ar.edu.um.ingenieria.controller.foro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.um.ingenieria.domain.Categoria;
import ar.edu.um.ingenieria.domain.Tema;
import ar.edu.um.ingenieria.repository.TemaRepository;
import ar.edu.um.ingenieria.service.impl.TemaServiceImpl;

@RestController
@RequestMapping("/foro/tema")
@Secured({"ROLE_USER" , "ROLE_VENDEDOR", "ROLE_ADMIN"})
public class TemaController {

	@Autowired
	private TemaServiceImpl temaServiceImpl;
	@Autowired
	private TemaRepository temaRepository;
	
	@GetMapping
	public ResponseEntity<List<Tema>> findByCategoria(@RequestBody Categoria categoria){
		if(temaRepository.findTemasByCategoria(categoria.getId()) == null) {
			return new ResponseEntity<List<Tema>>(HttpStatus.BAD_REQUEST);
		}else {
			return new ResponseEntity<List<Tema>>(temaRepository.findTemasByCategoria(categoria.getId()), HttpStatus.OK);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Tema> findById(@PathVariable Integer id){
		return new ResponseEntity<Tema>(temaServiceImpl.findById(id),HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(Tema tema){
		temaServiceImpl.create(tema);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> edit(@RequestBody Tema tema){
		temaServiceImpl.update(tema);
		return new ResponseEntity<Void>(HttpStatus.OK);		
	}
}