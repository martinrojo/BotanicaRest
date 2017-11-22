package ar.edu.um.ingenieria.controller.apirest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.um.ingenieria.domain.Usuario;
import ar.edu.um.ingenieria.service.impl.UsuarioServiceImpl;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);
	
	@Autowired
	private UsuarioServiceImpl usuarioService;
	
	@GetMapping
    public List<Usuario> indexPage() {
		logger.info("datos de usuario: {}", usuarioService.findAll());
	    return usuarioService.findAll();
    }
	
	@GetMapping("/{id}")
    public Usuario show(@PathVariable Integer id) {
		logger.info("datos de usuario: {}", usuarioService.findAll());
	    return usuarioService.findById(id);
    }
	
	@PostMapping
    public Usuario add(@RequestBody Usuario usuario) {
	    return usuarioService.create(usuario);
    }
	
	@PutMapping(value = "/{id}")
    public Usuario update(@RequestBody Usuario usuario, @PathVariable Integer id) {
		usuario.setId(id);
	    return usuarioService.update(usuario);
    }
	
	
	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable Integer id) {
		usuarioService.remove(usuarioService.findById(id));
	}
}
