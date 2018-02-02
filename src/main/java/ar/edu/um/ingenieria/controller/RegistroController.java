package ar.edu.um.ingenieria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.um.ingenieria.domain.Persona;
import ar.edu.um.ingenieria.domain.Usuario;
import ar.edu.um.ingenieria.manager.UsuarioManager;
import ar.edu.um.ingenieria.repository.UsuarioRepository;

@RestController
@RequestMapping("/registro")
public class RegistroController {
	@Autowired
	private UsuarioManager usuarioManager;
	@Autowired
	private UsuarioRepository usuarioRepository;

	@PostMapping
	public ResponseEntity<Void> agregar(Usuario usuario, Persona persona) {
		if (usuarioRepository.findUsermail(usuario.getEmail()) == null) {
			if (usuarioRepository.findUsername(usuario.getUser()) == null) {
				usuarioManager.create(persona, usuario);
				return new ResponseEntity<Void>(HttpStatus.OK);
			}					
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}
}
