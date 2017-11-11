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

import ar.edu.um.ingenieria.domain.Usuario;
import ar.edu.um.ingenieria.manager.UsuarioManager;

@RestController
@RequestMapping("/admin")
public class UsuarioAdmController {

	@Autowired
	private UsuarioManager usuarioManager;

	@GetMapping("/usuario/")
	public ResponseEntity<List<Usuario>> findAll() {
		return new ResponseEntity<List<Usuario>>(usuarioManager.findAll(), HttpStatus.OK);
	}

	@GetMapping("/usuario/{id}")
	public ResponseEntity<Usuario> edit(@PathVariable Integer id) {
		return new ResponseEntity<Usuario>(usuarioManager.findById(id),HttpStatus.OK);
	}

	@DeleteMapping("/usuario/{id}")
	public ResponseEntity<Void>  delete(@PathVariable Integer id) {
		usuarioManager.delete(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
