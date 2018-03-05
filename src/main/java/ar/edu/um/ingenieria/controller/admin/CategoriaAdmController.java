package ar.edu.um.ingenieria.controller.admin;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.um.ingenieria.domain.Categoria;
import ar.edu.um.ingenieria.repository.CategoriaRepository;
import ar.edu.um.ingenieria.service.impl.CategoriaServiceImpl;


@RestController
@RequestMapping("/admin/categoria")
@Secured({"ROLE_ADMIN"})
public class CategoriaAdmController {
	
	@Autowired
	private CategoriaServiceImpl categoriaServiceImpl;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	private static final Logger logger = Logger.getLogger(CategoriaServiceImpl.class);
	
	@GetMapping
	public ResponseEntity<List<Categoria>> findAll() {
		if (categoriaServiceImpl.findAll() == null) {
			logger.info("No hay categorias.");
			return new ResponseEntity<List<Categoria>>(HttpStatus.NO_CONTENT);
		}
		logger.info("Lista de categorias:" + categoriaServiceImpl.findAll());
		return new ResponseEntity<List<Categoria>>(categoriaServiceImpl.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Categoria> findById(@PathVariable Integer id) {
		if(categoriaServiceImpl.findById(id)==null) {
			logger.info("No hay categoria de ID: " + id);
			return new ResponseEntity<Categoria>(HttpStatus.NO_CONTENT);
		}
		else {
			logger.info("Categoria de ID: " + id + categoriaServiceImpl.findById(id));
			return new ResponseEntity<Categoria>(categoriaServiceImpl.findById(id),HttpStatus.OK);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void>  delete(@PathVariable Integer id) {
		if(categoriaServiceImpl.findById(id)==null) {
			logger.info("No hay categoria de ID: " + id);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		else {
			logger.info("Categoria borrada con exito: " + categoriaServiceImpl.findById(id));
			categoriaServiceImpl.remove(categoriaServiceImpl.findById(id));
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}
	
	@PostMapping
	public ResponseEntity<Void> create(String nombre, String descripcion){
		Categoria categoria = new Categoria();
		if(categoriaRepository.findCategoriaByName(nombre) != null) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		} else {
			categoria.setNombre(nombre);
			categoria.setDescripcion(descripcion);
			categoriaServiceImpl.create(categoria);
			logger.info("Categoria creada con exito: " + categoria);
			return new ResponseEntity<Void> (HttpStatus.OK);
		}
	}
	
	@PostMapping("/edit/")
	public ResponseEntity<Void> edit(Integer id, String nombre, String descripcion) {
		if (categoriaServiceImpl.findById(id) == null) {
			logger.info("No existe la categoria de ID:" + id);
			return new ResponseEntity<Void> (HttpStatus.NO_CONTENT);
		} else {
			Categoria categoria = categoriaServiceImpl.findById(id);
			categoria.setNombre(nombre);
			categoria.setDescripcion(descripcion);
			categoriaServiceImpl.update(categoria);
			logger.info("Categoria actualizada con exito:" + categoria);
			return new ResponseEntity<Void>(HttpStatus.OK);	
		}	
	}
}
