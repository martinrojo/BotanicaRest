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

import ar.edu.um.ingenieria.domain.Categoria;
import ar.edu.um.ingenieria.manager.CategoriaManager;
import ar.edu.um.ingenieria.service.impl.CategoriaServiceImpl;

@RestController
@RequestMapping("/admin/categoria")
public class CategoriaAdmController {
	
	@Autowired
	private CategoriaServiceImpl categoriaServiceImpl;
	@Autowired
	private CategoriaManager categoriaManager;
	
	@GetMapping
	public ResponseEntity<List<Categoria>> findAll() {
		return new ResponseEntity<List<Categoria>>(categoriaServiceImpl.findAll(), HttpStatus.OK);
	}
	
	//falta logica de: existe o no existe?
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> findById(@PathVariable Integer id) {
		return new ResponseEntity<Categoria>(categoriaManager.findById(id),HttpStatus.OK);
	}
	
	//falta logica de: existe o no existe?
	@DeleteMapping("/{id}")
	public ResponseEntity<Void>  delete(@PathVariable Integer id) {
		categoriaManager.delete(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}