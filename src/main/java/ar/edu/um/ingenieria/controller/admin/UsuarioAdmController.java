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
import ar.edu.um.ingenieria.service.impl.UsuarioServiceImpl;

@RestController
@RequestMapping("/admin/usuarios")
public class UsuarioAdmController {

	@Autowired
	private UsuarioServiceImpl usuarioServiceImpl;
	@Autowired
	private UsuarioManager usuarioManager;
	
	@GetMapping
	public ResponseEntity<List<Usuario>> findAll() {
		return new ResponseEntity<List<Usuario>>(usuarioServiceImpl.findAll(), HttpStatus.OK);
	}
	//falta logica de: existe o no existe?
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> findById(@PathVariable Integer id) {
		return new ResponseEntity<Usuario>(usuarioManager.findById(id),HttpStatus.OK);
	}
	//falta logica de: existe o no existe?
	@DeleteMapping("/{id}")
	public ResponseEntity<Void>  delete(@PathVariable Integer id) {
		Usuario usuario = usuarioServiceImpl.findById(id);
		usuarioServiceImpl.remove(usuario);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
