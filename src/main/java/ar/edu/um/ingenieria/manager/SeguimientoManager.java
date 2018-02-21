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
import ar.edu.um.ingenieria.service.impl.EstadoServiceImpl;
import ar.edu.um.ingenieria.service.impl.EtapaServiceImpl;
import ar.edu.um.ingenieria.service.impl.PlantaServiceImpl;
import ar.edu.um.ingenieria.service.impl.SeguimientoServiceImpl;
import ar.edu.um.ingenieria.service.impl.UsuarioServiceImpl;

@Service
public class SeguimientoManager {
	
	private static final Logger logger = LoggerFactory.getLogger(UsuarioManager.class);
	
	@Autowired
	private SeguimientoServiceImpl seguimientoServiceImpl;
	@Autowired
	private UsuarioServiceImpl usuarioServiceImpl;
	@Autowired
	private EstadoServiceImpl estadoServiceImpl;
	@Autowired
	private PlantaServiceImpl plantaServiceImpl;
	
	public void create(Integer usuario, Integer planta, Integer estado/*, Tarea tarea*/) {
		Seguimiento seguimiento = new Seguimiento();
		//tarea.setEstado(estadoServiceImpl.findById(estado));
		seguimiento.setEstado(estadoServiceImpl.findById(estado));
		//seguimiento.setEtapas(etapaServiceImpl.findById(etapa));
		seguimiento.setUsuario(usuarioServiceImpl.findById(usuario));
		seguimiento.setPlanta(plantaServiceImpl.findById(planta));
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