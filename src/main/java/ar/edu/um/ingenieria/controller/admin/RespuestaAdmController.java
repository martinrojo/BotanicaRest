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

import ar.edu.um.ingenieria.domain.Respuesta;
import ar.edu.um.ingenieria.manager.RespuestaManager;
import ar.edu.um.ingenieria.service.impl.RespuestaServiceImpl;

@RestController
@RequestMapping("/admin/respuesta")
public class RespuestaAdmController {
	
	@Autowired
	private RespuestaServiceImpl respuestaServiceImpl;
	@Autowired
	private RespuestaManager respuestaManager;
	
	@GetMapping
	public ResponseEntity<List<Respuesta>> findAll() {
		return new ResponseEntity<List<Respuesta>>(respuestaServiceImpl.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Respuesta> findById(@PathVariable Integer id) {
		if(respuestaServiceImpl.findById(id)==null)
			return new ResponseEntity<Respuesta>(HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<Respuesta>(respuestaManager.findById(id),HttpStatus.OK);
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
}
