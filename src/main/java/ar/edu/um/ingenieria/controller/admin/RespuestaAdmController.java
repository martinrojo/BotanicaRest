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
	//falta logica de: existe o no existe?
	@GetMapping("/{id}")
	public ResponseEntity<Respuesta> findById(@PathVariable Integer id) {
		return new ResponseEntity<Respuesta>(respuestaManager.findById(id),HttpStatus.OK);
	}
	//falta logica de: existe o no existe?
	@DeleteMapping("/{id}")
	public ResponseEntity<Void>  delete(@PathVariable Integer id) {
		respuestaManager.delete(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
