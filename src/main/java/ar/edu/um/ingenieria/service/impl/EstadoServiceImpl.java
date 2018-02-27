package ar.edu.um.ingenieria.service.impl;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.edu.um.ingenieria.domain.Estado;
import ar.edu.um.ingenieria.domain.Seguimiento;


@Service
public class EstadoServiceImpl extends ServiceImpl<Estado, Integer>{
	
	@Autowired
	private EstadoServiceImpl estadoServiceImpl;
	
	@Autowired
	private PlantaServiceImpl plantaServiceImpl;
	
	@Autowired
	private SeguimientoServiceImpl seguimientoServiceImpl;
	
	@Override
	public Estado create(Estado entity) {
		// TODO Auto-generated method stub
		return super.create(entity);
	}

	@Override
	public void remove(Estado entity) {
		// TODO Auto-generated method stub
		super.remove(entity);
	}

	@Override
	public Estado update(Estado entity) {
		// TODO Auto-generated method stub
		return super.update(entity);
	}

	@Override
	public Estado findById(Integer id) {
		// TODO Auto-generated method stub
		return super.findById(id);
	}

	@Override
	public List<Estado> findAll() {
		// TODO Auto-generated method stub
		return super.findAll();
	}
	
	
	public void create(String nombre, String descripcion, Integer planta) {
		
		Estado estado = new Estado();
		
		estado.setNombre(nombre);
		estado.setDescripcion(descripcion);
		estado.setPlanta(plantaServiceImpl.findById(1));

		estadoServiceImpl.create(estado);
	}
	
	public void update (Integer Estado, String nombre, String descripcion, Integer planta)
	{
		Estado estado = estadoServiceImpl.findById(Estado);
		estado.setNombre(nombre);
		estado.setDescripcion(descripcion);
		estado.setPlanta(plantaServiceImpl.findById(planta));
		estadoServiceImpl.create(estado);
	}

	
	
	
}
