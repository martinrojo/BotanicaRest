package ar.edu.um.ingenieria.controller.foro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.um.ingenieria.domain.Tema;
import ar.edu.um.ingenieria.service.impl.TemaServiceImpl;

@RestController
@RequestMapping("/foro/tema")
public class TemaController {

	@Autowired
	private TemaServiceImpl temaServiceImpl;
	
	@GetMapping
	public ResponseEntity<List<Tema>> findAll(){
		return new ResponseEntity<List<Tema>>(temaServiceImpl.findAll(), HttpStatus.OK);
	}
	
	//falta logica de: existe o no existe?
	@GetMapping("{id}")
	public ResponseEntity<Tema> findById(@PathVariable Integer id){
		return new ResponseEntity<Tema>(temaServiceImpl.findById(id),HttpStatus.OK);
	}
}
