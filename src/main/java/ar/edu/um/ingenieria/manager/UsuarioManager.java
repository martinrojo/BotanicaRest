package ar.edu.um.ingenieria.manager;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.um.ingenieria.domain.Persona;
import ar.edu.um.ingenieria.domain.Usuario;
import ar.edu.um.ingenieria.service.impl.PersonaServiceImpl;
import ar.edu.um.ingenieria.service.impl.UsuarioServiceImpl;
@Service
public class UsuarioManager {
	
	private static final Logger logger = LoggerFactory.getLogger(UsuarioManager.class);
	
	@Autowired
	private UsuarioServiceImpl usuarioServiceImpl;
	
	
	
	@Autowired
	private PersonaServiceImpl personaServiceImpl;
	
	public void create(Persona persona, Usuario usuario) {
		usuarioServiceImpl.create(usuario);
		persona.setUsuario(usuario);
		personaServiceImpl.create(persona);	
	}
	
	public List<Usuario> findAll(){
		try {
			return usuarioServiceImpl.findAll();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	public Usuario findById(Integer id) {
		return usuarioServiceImpl.findById(id);
	}

	public void delete(Integer id) {
		Usuario usuario = usuarioServiceImpl.findById(id);
		personaServiceImpl.remove(usuario.getPersona());		
		usuarioServiceImpl.remove(usuario);
	}
	
	

/*	public int isEquals(Usuario usuario) {
		int x=0;
		List<Usuario> usuarios = usuarioServiceImpl.findAll();
		for (int i = 0; i < usuarios.size(); i++) {
			if(usuarios.get(i).getEmail().equals(usuario.getEmail())) {
				x=1;
			}
			else{
				usuarioServiceImpl.create(usuario);
				return 0;
			}
		}
		return x;
	} */
}
