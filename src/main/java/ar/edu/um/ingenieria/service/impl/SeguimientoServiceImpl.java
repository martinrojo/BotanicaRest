package ar.edu.um.ingenieria.service.impl;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.edu.um.ingenieria.domain.Seguimiento;

@Service
public class SeguimientoServiceImpl extends ServiceImpl<Seguimiento, Integer>{
	
	@Autowired
	private UsuarioServiceImpl usuarioServiceImpl;
	@Autowired
	private SeguimientoServiceImpl seguimientoServiceImpl;
	@Autowired
	private EstadoServiceImpl estadoServiceImpl;
	@Autowired
	private PlantaServiceImpl plantaServiceImpl;
	@Autowired
	private EtapaServiceImpl etapaServiceImpl;
	@Autowired
	private TareaServiceImpl tareaServiceImpl;
	
	@Override
	public Seguimiento create(Seguimiento entity) {
		// TODO Auto-generated method stub
		return super.create(entity);
	}

	@Override
	public void remove(Seguimiento entity) {
		// TODO Auto-generated method stub
		super.remove(entity);
	}

	@Override
	public Seguimiento update(Seguimiento entity) {
		// TODO Auto-generated method stub
		return super.update(entity);
	}

	@Override
	public Seguimiento findById(Integer id) {
		// TODO Auto-generated method stub
		return super.findById(id);
	}

	@Override
	public List<Seguimiento> findAll() {
		// TODO Auto-generated method stub
		return super.findAll();
	}
	
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

	public void delete(Integer id) {
		Seguimiento seguimiento = seguimientoServiceImpl.findById(id);		
		seguimientoServiceImpl.remove(seguimiento);
	}
}
