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

import ar.edu.um.ingenieria.domain.Respuesta;
import ar.edu.um.ingenieria.domain.Tema;
import ar.edu.um.ingenieria.service.impl.RespuestaServiceImpl;
import ar.edu.um.ingenieria.service.impl.TemaServiceImpl;
import ar.edu.um.ingenieria.service.impl.UsuarioSecurityServiceImpl;
import ar.edu.um.ingenieria.service.impl.UsuarioServiceImpl;

@RestController
@RequestMapping("/foro/respuesta")
@Secured({ "ROLE_USER", "ROLE_VENDEDOR", "ROLE_ADMIN" })
public class RespuestaController {

	@Autowired
	private RespuestaServiceImpl respuestaServiceImpl;
	@Autowired
	private TemaServiceImpl temaServiceImpl;
	@Autowired
	private UsuarioServiceImpl usuarioServiceImpl;
	@Autowired
	private UsuarioSecurityServiceImpl usuarioSecurity;

	private static final Logger logger = Logger.getLogger(RespuestaServiceImpl.class);

	@GetMapping("/leer/{id}")
	public ResponseEntity<List<Respuesta>> findByTema(@PathVariable Integer id) {
		if (temaServiceImpl.findById(id) == null) {
			logger.info("No existe el tema de ID:" + id);
			return new ResponseEntity<List<Respuesta>>(HttpStatus.CONFLICT);
		}
		Tema tema = temaServiceImpl.findById(id);
		if (tema.getRespuestas() == null) {
			logger.info("No hay respuestas en el tema de ID:" + id);
			new ResponseEntity<List<Respuesta>>(HttpStatus.NO_CONTENT);
		}
		logger.info("Datos tema:" + tema.getRespuestas());
		return new ResponseEntity<List<Respuesta>>(tema.getRespuestas(), HttpStatus.OK);

	}

	@GetMapping("/{id}")
	public ResponseEntity<Respuesta> findById(@PathVariable Integer id) {
		if (respuestaServiceImpl.findById(id) == null) {
			logger.info("No existe la respuesta de ID:" + id);
			return new ResponseEntity<Respuesta>(HttpStatus.NO_CONTENT);
		}
		logger.info("Datos respuesta:" + respuestaServiceImpl.findById(id));
		return new ResponseEntity<Respuesta>(respuestaServiceImpl.findById(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Void> create(String texto, Integer idTema) {
		if (temaServiceImpl.findById(idTema) == null) {
			logger.info("No existe el tema de ID:" + idTema);
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT-0300"));
		calendar.add(Calendar.HOUR, -3);
		Respuesta respuesta = new Respuesta();
		respuesta.setTexto(texto);
		respuesta.setFecha(calendar.getTime());
		respuesta.setUsuario(usuarioServiceImpl.findById(usuarioSecurity.GetIdUser()));
		respuesta.setTema(temaServiceImpl.findById(idTema));
		respuestaServiceImpl.create(respuesta);
		logger.info("Respueta creada con exito:" + respuesta);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@PostMapping("/edit")
	public ResponseEntity<Void> edit(Integer id, String texto, Integer idTema) {
		if (respuestaServiceImpl.findById(id) == null) {
			logger.info("No existe la respuesta de ID:" + id);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		if (temaServiceImpl.findById(idTema) == null) {
			logger.info("No existe el tema de ID:" + idTema);
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		if (respuestaServiceImpl.findById(id).getUsuario() == usuarioServiceImpl
				.findById(usuarioSecurity.GetIdUser())) {
			Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT-0300"));
			calendar.add(Calendar.HOUR, -3);
			Respuesta respuesta = respuestaServiceImpl.findById(id);
			respuesta.setTexto(texto);
			respuesta.setFecha(calendar.getTime());
			respuesta.setUsuario(usuarioServiceImpl.findById(usuarioSecurity.GetIdUser()));
			respuesta.setTema(temaServiceImpl.findById(idTema));
			respuestaServiceImpl.update(respuesta);
			logger.info("Respueta actualizada con exito: " + respuesta);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		logger.info("Respuesta no actualizada. El usuario no es propietario de la respuesta.");
		return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		if (respuestaServiceImpl.findById(id) == null) {
			logger.info("No existe la respuesta de ID:" + id);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		if (respuestaServiceImpl.findById(id).getUsuario() == usuarioServiceImpl
				.findById(usuarioSecurity.GetIdUser())) {
			logger.info("Respuesta borrado con exito:" + respuestaServiceImpl.findById(id));
			respuestaServiceImpl.remove(respuestaServiceImpl.findById(id));
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		logger.info("Respuesta no borrada. El usuario no es propietario de la respuesta.");
		return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}

}
