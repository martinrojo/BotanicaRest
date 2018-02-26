package ar.edu.um.ingenieria.controller.foro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.um.ingenieria.domain.Respuesta;
import ar.edu.um.ingenieria.domain.Tema;
import ar.edu.um.ingenieria.repository.RespuestaRepository;
import ar.edu.um.ingenieria.service.impl.RespuestaServiceImpl;

@RestController
@RequestMapping("foro/respuesta")
public class RespuestaController {
	@Autowired
	private RespuestaServiceImpl respuestaServiceImpl;
	@Autowired
	private RespuestaRepository respuestaRepository;
	
	@GetMapping
	public ResponseEntity<List<Respuesta>> findByTema (Tema tema){		
		return new ResponseEntity<List<Respuesta>>(respuestaRepository.findRespuestaByTema(tema.getId()), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody Respuesta respuesta){
		respuestaServiceImpl.create(respuesta);
		return new ResponseEntity<Void> (HttpStatus.OK);
	}
}
