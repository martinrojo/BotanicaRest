package ar.edu.um.ingenieria.controller.foro;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ar.edu.um.ingenieria.domain.Respuesta;
import ar.edu.um.ingenieria.service.impl.RespuestaServiceImpl;
import ar.edu.um.ingenieria.service.impl.TemaServiceImpl;

@RestController
@RequestMapping("/foro/respuesta")
@Secured({"ROLE_USER" , "ROLE_VENDEDOR", "ROLE_ADMIN"})
public class RespuestaController {
	
	@Autowired
	private RespuestaServiceImpl respuestaServiceImpl;
	@Autowired
	private TemaServiceImpl temaServiceImpl;
	
	@GetMapping()
	public ResponseEntity<List<Respuesta>> findByTema (Integer id){		
		return new ResponseEntity<List<Respuesta>>(temaServiceImpl.findById(id).getRespuestas(),HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody Respuesta respuesta){
		respuestaServiceImpl.create(respuesta);
		return new ResponseEntity<Void> (HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		respuestaServiceImpl.remove(respuestaServiceImpl.findById(id));
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
}
