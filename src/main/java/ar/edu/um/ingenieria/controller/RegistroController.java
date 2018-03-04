package ar.edu.um.ingenieria.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ar.edu.um.ingenieria.domain.Persona;
import ar.edu.um.ingenieria.domain.Usuario;
import ar.edu.um.ingenieria.repository.UsuarioRepository;
import ar.edu.um.ingenieria.service.impl.RolServiceImpl;
import ar.edu.um.ingenieria.service.impl.UsuarioServiceImpl;

@RestController
@RequestMapping("/registro")
public class RegistroController {
	@Autowired
	private UsuarioServiceImpl usuarioServiceImpl;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private RolServiceImpl rolServiceImpl;

	@PostMapping
	public ResponseEntity<Void> agregar(String apellido, String nombre, Date fechaNacimiento, String email,
			String user, String password) 
	{
		if (usuarioRepository.findUsermail(email) == null) 
		{
			if (usuarioRepository.findUsername(user) == null) 
			{
			Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT-3:00"));
			calendar.add(Calendar.HOUR, -3);
			Usuario usuario = new Usuario();
			Persona persona = new Persona();
				persona.setApellido(apellido);
				persona.setNombre(nombre);
				persona.setFechaNacimiento(fechaNacimiento);
				usuario.setEmail(email);
				usuario.setUser(user);
				usuario.setPassword(password);
				usuario.setRol(rolServiceImpl.findById(2));
				usuario.setLastPasswordResetDate(calendar.getTime());
				persona.setUsuario(usuario);
				usuarioServiceImpl.create(persona, usuario);
				return new ResponseEntity<Void>(HttpStatus.OK);
			}
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	} else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
}
}

