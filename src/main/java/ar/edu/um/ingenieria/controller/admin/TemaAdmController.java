package ar.edu.um.ingenieria.controller.admin;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

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
import ar.edu.um.ingenieria.domain.Tema;
import ar.edu.um.ingenieria.service.impl.CategoriaServiceImpl;
import ar.edu.um.ingenieria.service.impl.TemaServiceImpl;
import ar.edu.um.ingenieria.service.impl.UsuarioSecurityServiceImpl;
import ar.edu.um.ingenieria.service.impl.UsuarioServiceImpl;

@RestController
@RequestMapping("/admin/tema")
@Secured({"ROLE_ADMIN"})
public class TemaAdmController {

	@Autowired
	private TemaServiceImpl temaServiceImpl;
	@Autowired
	private UsuarioServiceImpl usuarioServiceImpl;
	@Autowired
	private CategoriaServiceImpl categoriaServiceImpl;
	@Autowired
	private UsuarioSecurityServiceImpl usuarioSecurity;
	
	private static final Logger logger = Logger.getLogger(TemaServiceImpl.class);
	
	@GetMapping("/leer/{id}")
	public ResponseEntity<List<Tema>> findByCategoria(@PathVariable Integer id){
		if (categoriaServiceImpl.findById(id) == null) {
			logger.info("No existe la categoria de ID:" + id);
			return new ResponseEntity<List<Tema>>(HttpStatus.NO_CONTENT);
		}else {
			Categoria categoria = categoriaServiceImpl.findById(id);
			if(categoria.getTemas() == null) {
				logger.info("No hay temas en la categoria de ID:" + id);
				new ResponseEntity<List<Tema>>(HttpStatus.NO_CONTENT);
			}
			logger.info("Datos tema:" + categoria.getTemas());
			return new ResponseEntity<List<Tema>>(categoria.getTemas(), HttpStatus.OK);
		}		
	}

	@GetMapping("/{id}")
	public ResponseEntity<Tema> findById(@PathVariable Integer id) {
		if(temaServiceImpl.findById(id)==null) {
			logger.info("No existe el tema de ID:" + id);
			return new ResponseEntity<Tema>(HttpStatus.NO_CONTENT);
		}
		logger.info("Datos tema:" + temaServiceImpl.findById(id));
		return new ResponseEntity<Tema>(temaServiceImpl.findById(id),HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Void> insert(String titulo, String texto, Integer idCategoria) {
		if (categoriaServiceImpl.findById(idCategoria) == null) {
			logger.info("No existe la categoria de ID:" + idCategoria);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT-0300"));
		Tema tema = new Tema();
		tema.setTitulo(titulo);
		tema.setUsuario(usuarioServiceImpl.findById(usuarioSecurity.GetIdUser()));
		tema.setCerrado(false);
		tema.setTexto(texto);
		tema.setCategoria(categoriaServiceImpl.findById(idCategoria));
		tema.setFecha(calendar.getTime());
		temaServiceImpl.create(tema);
		logger.info("Tema creado con exito:" + tema);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@PostMapping("/edit")
	public ResponseEntity<Void> edit(String titulo, Boolean cerrado, Integer idUsuario, String texto, Integer idCategoria, Integer id) {
		if (temaServiceImpl.findById(id) == null) {
			logger.info("No existe el tema de ID:" + id);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		if (categoriaServiceImpl.findById(idCategoria) == null) {
			logger.info("No existe la categoria de ID:" + idCategoria);
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT-0300"));
		Tema tema = temaServiceImpl.findById(id);
		tema.setTitulo(titulo);
		tema.setUsuario(usuarioServiceImpl.findById(idUsuario));
		tema.setCerrado(cerrado);
		tema.setTexto(texto);
		tema.setCategoria(categoriaServiceImpl.findById(idCategoria));
		tema.setFecha(calendar.getTime());
		temaServiceImpl.create(tema);
		logger.info("Tema actualizado con exito:" + tema);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void>  delete(@PathVariable Integer id) {
		if(temaServiceImpl.findById(id)==null) {
			logger.info("No existe el tema de ID:" + id);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		logger.info("Tema borrado con exito:" + temaServiceImpl.findById(id));
		temaServiceImpl.remove(temaServiceImpl.findById(id));
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping("/close/{id}")
	public ResponseEntity<Void>  close(@PathVariable Integer id) {
		if(temaServiceImpl.findById(id)==null)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		else {
			Tema tema = temaServiceImpl.findById(id);
			tema.setCerrado(true);
			logger.info("Tema cerrado con exito:" + temaServiceImpl.findById(id).getId());
			temaServiceImpl.update(temaServiceImpl.findById(id));
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}
	
	@GetMapping("/open/{id}")
	public ResponseEntity<Void>  open(@PathVariable Integer id) {
		if(temaServiceImpl.findById(id)==null)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		else {
			Tema tema = temaServiceImpl.findById(id);
			tema.setCerrado(false);
			logger.info("Tema abierto con exito:" + temaServiceImpl.findById(id).getId());
			temaServiceImpl.update(temaServiceImpl.findById(id));
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}
}
