package ar.edu.um.ingenieria.manager;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.edu.um.ingenieria.domain.Seguimiento;
import ar.edu.um.ingenieria.service.impl.EstadoServiceImpl;
import ar.edu.um.ingenieria.service.impl.EtapaServiceImpl;
import ar.edu.um.ingenieria.service.impl.PlantaServiceImpl;
import ar.edu.um.ingenieria.service.impl.SeguimientoServiceImpl;
import ar.edu.um.ingenieria.service.impl.TareaServiceImpl;
import ar.edu.um.ingenieria.service.impl.UsuarioServiceImpl;

@Service
public class SeguimientoManager {
	
	private static final Logger logger = LoggerFactory.getLogger(SeguimientoManager.class);
	
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
	
	
	public void create(Integer usuario, Integer planta, Integer estado) {
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT-3:00"));
		Seguimiento seguimiento = new Seguimiento();
		long tiempoActual = calendar.getTimeInMillis()-10800000	, horasFaltantes = plantaServiceImpl.findById(planta).getTiempoRiego().getTime(),
				suma = tiempoActual + horasFaltantes;
		calendar.setTimeInMillis(suma);
		seguimiento.setEtapa(etapaServiceImpl.findById(1));
		seguimiento.setTarea(tareaServiceImpl.findById(1));
		seguimiento.setEstado(estadoServiceImpl.findById(estado));
		seguimiento.setUsuario(usuarioServiceImpl.findById(usuario));
		seguimiento.setPlanta(plantaServiceImpl.findById(planta));
		seguimiento.setProximoRiego(calendar.getTime());
		seguimientoServiceImpl.create(seguimiento);
	}
	
	public void update (Integer Seguimiento, Integer planta, Integer estado, Integer tarea, Integer etapa)
	{
		Seguimiento seguimiento = seguimientoServiceImpl.findById(Seguimiento);
		seguimiento.setTarea(tareaServiceImpl.findById(tarea));
		seguimiento.setEtapa(etapaServiceImpl.findById(etapa));
		seguimiento.setEstado(estadoServiceImpl.findById(estado));
		seguimiento.setPlanta(plantaServiceImpl.findById(planta));
		seguimientoServiceImpl.create(seguimiento);
	}
	
	public List<Seguimiento> findByUser(Integer usuario)
	{
		return usuarioServiceImpl.findById(usuario).getSeguimiento();
	}

	public Seguimiento findById(Integer id) {
		return seguimientoServiceImpl.findById(id);
	}

	public void delete(Integer id) {
		Seguimiento seguimiento = seguimientoServiceImpl.findById(id);		
		seguimientoServiceImpl.remove(seguimiento);
	}
	
	public List<Seguimiento> findAll(){
		try {
			return seguimientoServiceImpl.findAll();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
}