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
		calendar.setTimeInMillis(tiempoActual);
		seguimiento.setUltimoRiego(calendar.getTime());
		seguimientoServiceImpl.create(seguimiento);
	}
	
	public void update (Integer usuario, Integer planta, Integer estado, Integer tarea, Integer etapa,Integer seguimiento)
	{
		Seguimiento nativeSeguimiento = seguimientoServiceImpl.findById(seguimiento);
		nativeSeguimiento.setUsuario(usuarioServiceImpl.findById(usuario));
		nativeSeguimiento.setTarea(tareaServiceImpl.findById(tarea));
		nativeSeguimiento.setEtapa(etapaServiceImpl.findById(etapa));
		nativeSeguimiento.setEstado(estadoServiceImpl.findById(estado));
		nativeSeguimiento.setPlanta(plantaServiceImpl.findById(planta));
		seguimientoServiceImpl.create(nativeSeguimiento);
	}
	
	public void regar (Integer seguimiento_id) {
		Seguimiento seguimiento = seguimientoServiceImpl.findById(seguimiento_id);
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT-3:00"));
		long tiempoActual = calendar.getTimeInMillis()-10800000	, horasFaltantes = plantaServiceImpl.findById(seguimiento.getPlanta().getId()).getTiempoRiego().getTime(),
				suma = tiempoActual + horasFaltantes;
		calendar.setTimeInMillis(suma);
		seguimiento.setProximoRiego(calendar.getTime());
		calendar.setTimeInMillis(tiempoActual);
		seguimiento.setUltimoRiego(calendar.getTime());
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
