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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.um.ingenieria.domain.Categoria;
import ar.edu.um.ingenieria.service.impl.CategoriaServiceImpl;


@RestController
@RequestMapping("/admin/categoria")
public class CategoriaAdmController {
	
	@Autowired
	private CategoriaServiceImpl categoriaServiceImpl;

	private static final Logger logger = Logger.getLogger(CategoriaServiceImpl.class);
	
	@GetMapping
	public ResponseEntity<List<Categoria>> findAll() {
		return new ResponseEntity<List<Categoria>>(categoriaServiceImpl.findAll(), HttpStatus.OK);
	}
	

	@GetMapping("/{id}")
	public ResponseEntity<Categoria> findById(@PathVariable Integer id) {
		if(categoriaServiceImpl.findById(id)==null)
			return new ResponseEntity<Categoria>(HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<Categoria>(categoriaServiceImpl.findById(id),HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void>  delete(@PathVariable Integer id) {
		if(categoriaServiceImpl.findById(id)==null)
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		else {
			categoriaServiceImpl.remove(categoriaServiceImpl.findById(id));
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}
	
	@PostMapping
	public ResponseEntity<Void> create(String nombre, String descripcion){
		Categoria categoria = new Categoria();
		categoria.setNombre(nombre);
		categoria.setDescripcion(descripcion);
		categoriaServiceImpl.create(categoria);
		return new ResponseEntity<Void> (HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> edit(Categoria categoria) {
		categoriaServiceImpl.update(categoria);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
