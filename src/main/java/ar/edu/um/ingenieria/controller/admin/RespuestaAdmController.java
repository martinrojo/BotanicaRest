package ar.edu.um.ingenieria.controller.admin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.um.ingenieria.domain.Respuesta;
import ar.edu.um.ingenieria.service.impl.RespuestaServiceImpl;
import ar.edu.um.ingenieria.service.impl.TemaServiceImpl;
import ar.edu.um.ingenieria.service.impl.UsuarioServiceImpl;

@RestController
@RequestMapping("/admin/respuesta")
public class RespuestaAdmController {
	
	@Autowired
	private RespuestaServiceImpl respuestaServiceImpl;
	
	@Autowired
	private UsuarioServiceImpl usuarioServiceImpl;
	
	@Autowired
	private TemaServiceImpl temaServiceImpl;

	@GetMapping
	public ResponseEntity<List<Respuesta>> findAll() {
		return new ResponseEntity<List<Respuesta>>(respuestaServiceImpl.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Respuesta> findById(@PathVariable Integer id) {
		if(respuestaServiceImpl.findById(id)==null)
			return new ResponseEntity<Respuesta>(HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<Respuesta>(respuestaServiceImpl.findById(id),HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void>  delete(@PathVariable Integer id) {
		if(respuestaServiceImpl.findById(id)==null)
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		else {
			respuestaServiceImpl.remove(respuestaServiceImpl.findById(id));
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}
	
	@PostMapping
	public ResponseEntity<Void> create(String texto, Integer temasId, Integer usuarioId, String fecha) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Respuesta respuesta = new Respuesta();
		respuesta.setTexto(texto);
		respuesta.setFecha(simpleDateFormat.parse(fecha));
		respuesta.setUsuario(usuarioServiceImpl.findById(usuarioId));
		respuesta.setTema(temaServiceImpl.findById(temasId));
		respuestaServiceImpl.create(respuesta);
		return new ResponseEntity<Void> (HttpStatus.OK);
	}
	
	@PostMapping("/edit/")
	public ResponseEntity<Void> edit(Integer id, String texto, Integer temasId, Integer usuarioId, String fecha) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (respuestaServiceImpl.findById(id) == null) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		} else {
			Respuesta respuesta = respuestaServiceImpl.findById(id);
			respuesta.setTexto(texto);
			respuesta.setFecha(simpleDateFormat.parse(fecha));
			respuesta.setUsuario(usuarioServiceImpl.findById(usuarioId));
			respuesta.setTema(temaServiceImpl.findById(temasId));
			respuestaServiceImpl.update(respuesta);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}
}
