package ar.edu.um.ingenieria.manager;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.edu.um.ingenieria.domain.Estado;
import ar.edu.um.ingenieria.domain.Etapa;
import ar.edu.um.ingenieria.domain.Planta;
import ar.edu.um.ingenieria.domain.Seguimiento;
import ar.edu.um.ingenieria.domain.Tarea;
import ar.edu.um.ingenieria.domain.Usuario;
import ar.edu.um.ingenieria.service.impl.SeguimientoServiceImpl;

@Service
public class SeguimientoManager {
	
	private static final Logger logger = LoggerFactory.getLogger(UsuarioManager.class);
	
	@Autowired
	private SeguimientoServiceImpl seguimientoServiceImpl;
	
	public void create(Usuario usuario, Planta planta, Estado estado, Tarea tarea, Etapa etapa, Seguimiento seguimiento) {
		tarea.setEstado(estado);
		seguimiento.setEstado(estado);
		seguimiento.setEtapas((List<Etapa>) etapa);
		seguimiento.setUsuario(usuario);
		seguimiento.setPlanta(planta);
		seguimientoServiceImpl.create(seguimiento);
	}
	
	public List<Seguimiento> findAll(){
		try {
			return seguimientoServiceImpl.findAll();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	public Seguimiento findById(Integer id) {
		return seguimientoServiceImpl.findById(id);
	}

	public void delete(Integer id) {
		Seguimiento seguimiento = seguimientoServiceImpl.findById(id);		
		seguimientoServiceImpl.remove(seguimiento);
	}
}