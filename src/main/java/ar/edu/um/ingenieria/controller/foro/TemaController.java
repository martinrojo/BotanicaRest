package ar.edu.um.ingenieria.controller.foro;

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
@RequestMapping("/foro/tema")
@Secured({ "ROLE_USER", "ROLE_VENDEDOR", "ROLE_ADMIN" })
public class TemaController {

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
	public ResponseEntity<List<Tema>> findByCategoria(@PathVariable Integer id) {
		if (categoriaServiceImpl.findById(id) == null) {
			logger.info("No existe la categoria de ID:" + id);
			return new ResponseEntity<List<Tema>>(HttpStatus.CONFLICT);
		} else {
			Categoria categoria = categoriaServiceImpl.findById(id);
			if (categoria.getTemas() == null) {
				logger.info("No hay temas en la categoria de ID:" + id);
				return new ResponseEntity<List<Tema>>(HttpStatus.NO_CONTENT);
			}
			logger.info("Datos tema:" + categoria.getTemas());
			return new ResponseEntity<List<Tema>>(categoria.getTemas(), HttpStatus.OK);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Tema> findById(@PathVariable Integer id) {
		if (temaServiceImpl.findById(id) == null) {
			logger.info("No existe el tema de ID:" + id);
			return new ResponseEntity<Tema>(HttpStatus.NO_CONTENT);
		}
		logger.info("Datos tema:" + temaServiceImpl.findById(id));
		return new ResponseEntity<Tema>(temaServiceImpl.findById(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Void> insert(String titulo, String texto, Integer idCategoria) {
		if (categoriaServiceImpl.findById(idCategoria) == null) {
			logger.info("No existe la categoria de ID:" + idCategoria);
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT-0300"));
		calendar.add(Calendar.HOUR, -3);
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
	public ResponseEntity<Void> edit(String titulo, Boolean cerrado, String texto, Integer idCategoria, Integer id) {
		if (temaServiceImpl.findById(id) == null) {
			logger.info("No existe el tema de ID:" + id);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		if (categoriaServiceImpl.findById(idCategoria) == null) {
			logger.info("No existe la categoria de ID:" + idCategoria);
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		if (temaServiceImpl.findById(id).getUsuario() == usuarioServiceImpl.findById(usuarioSecurity.GetIdUser())) {
			Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT-0300"));
			calendar.add(Calendar.HOUR, -3);
			Tema tema = temaServiceImpl.findById(id);
			tema.setTitulo(titulo);
			tema.setUsuario(usuarioServiceImpl.findById(usuarioSecurity.GetIdUser()));
			tema.setCerrado(cerrado);
			tema.setTexto(texto);
			tema.setCategoria(categoriaServiceImpl.findById(idCategoria));
			tema.setFecha(calendar.getTime());
			temaServiceImpl.create(tema);
			logger.info("Tema actualizado con exito:" + tema);
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
		logger.info("Tema no actualizado. El usuario no es propietario del tema.");
		return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		if (temaServiceImpl.findById(id) == null) {
			logger.info("No existe el tema de ID:" + id);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		if (temaServiceImpl.findById(id).getUsuario() == usuarioServiceImpl.findById(usuarioSecurity.GetIdUser())) {
			logger.info("Tema borrado con exito:" + temaServiceImpl.findById(id));
			temaServiceImpl.remove(temaServiceImpl.findById(id));
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		logger.info("Tema no borrado. El usuario no es propietario del tema.");
		return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}
}