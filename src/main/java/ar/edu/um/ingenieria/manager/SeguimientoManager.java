package ar.edu.um.ingenieria.manager;

import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.um.ingenieria.domain.Planta;
import ar.edu.um.ingenieria.domain.Seguimiento;
import ar.edu.um.ingenieria.service.impl.EstadoServiceImpl;
import ar.edu.um.ingenieria.service.impl.EtapaServiceImpl;
import ar.edu.um.ingenieria.service.impl.PlantaServiceImpl;
import ar.edu.um.ingenieria.service.impl.SeguimientoServiceImpl;
import ar.edu.um.ingenieria.service.impl.TareaServiceImpl;
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
	@Autowired
	private EtapaServiceImpl etapaServiceImpl;
	@Autowired
	private TareaServiceImpl tareaServiceImpl;
	
	public void create(Integer usuario, Integer planta) {
		Seguimiento seguimiento = new Seguimiento();		
		seguimiento.setEstado(estadoServiceImpl.findById(1));
		seguimiento.setTarea(tareaServiceImpl.findById(1));
		seguimiento.setEtapas(etapaServiceImpl.findById(1));
		seguimiento.setUsuario(usuarioServiceImpl.findById(usuario));
		seguimiento.setPlanta(plantaServiceImpl.findById(planta));
		seguimientoServiceImpl.create(seguimiento);
	}
	
	public void create(Integer usuario, Integer planta, Integer estado, Integer tarea, Integer etapa) {
		Calendar calendar = Calendar.getInstance();
		Seguimiento seguimiento = new Seguimiento();
		Planta planta1 = plantaServiceImpl.findById(planta);
		seguimiento.setTarea(tareaServiceImpl.findById(tarea));
		seguimiento.setEtapas(etapaServiceImpl.findById(etapa));
		seguimiento.setEstado(estadoServiceImpl.findById(estado));
		seguimiento.setUsuario(usuarioServiceImpl.findById(usuario));
		seguimiento.setPlanta(planta1);
		calendar.setTime(seguimiento.getUltimoRiego());
      	//calendar.add(Calendar.HOUR, planta1.getTiempoRiego()); 
		seguimiento.setProximo_riego(calendar.getTime());
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