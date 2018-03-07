package ar.edu.um.ingenieria.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ar.edu.um.ingenieria.domain.Persona;
import ar.edu.um.ingenieria.domain.Rol;
import ar.edu.um.ingenieria.domain.Usuario;
import ar.edu.um.ingenieria.repository.RolRepository;
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
	private RolRepository rolRepository;
	@Autowired
	private RolServiceImpl rolServiceImpl;

	private static final Logger logger = LoggerFactory.getLogger(RegistroController.class);

	@PostMapping
	public ResponseEntity<Void> agregar(String apellido, String nombre, Integer rolId, Date fechaNacimiento,
			String email, String user, String password) {

		if (usuarioRepository.findUsermail(email) == null) {
			if (usuarioRepository.findUsername(user) == null) {
				Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT-3:00"));
				calendar.add(Calendar.HOUR, -3);
				Usuario usuario = new Usuario();
				Persona persona = new Persona();
				Rol rol = new Rol();
				persona.setApellido(apellido);
				persona.setNombre(nombre);
				persona.setFechaNacimiento(fechaNacimiento);
				usuario.setEmail(email);
				usuario.setUser(user);
				usuario.setPassword(password);
				rol = rolServiceImpl.findById(rolId);
				for (Integer i = 0; i < rolRepository.findWithOutAdmin().size(); i++) {
					if (rol == rolRepository.findWithOutAdmin().get(i)) {
						usuario.setRol(rol);
					}
				}
				if (usuario.getRol() == null) {
					logger.info("Usuario intengando registrarse como administrador");
					return new ResponseEntity<Void>(HttpStatus.CONFLICT);
				}
				usuario.setLastPasswordResetDate(calendar.getTime());
				persona.setUsuario(usuario);
				usuarioServiceImpl.create(persona, usuario);
				logger.info("Usuario registrado satisfactoriamente" + usuario, persona);
				return new ResponseEntity<Void>(HttpStatus.OK);
			}
			logger.info("User en uso");
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} else {
			logger.info("Email ya registrado en uso");
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}
}
