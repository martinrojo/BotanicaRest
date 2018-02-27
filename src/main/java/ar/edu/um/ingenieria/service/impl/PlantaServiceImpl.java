package ar.edu.um.ingenieria.service.impl;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.um.ingenieria.domain.Planta;
import ar.edu.um.ingenieria.domain.Seguimiento;

@Service
public class PlantaServiceImpl extends ServiceImpl<Planta, Integer>{

	@Autowired
	private ClimaServiceImpl climaServiceImpl;
	
	@Autowired
	private PlantaServiceImpl plantaServiceImpl;
	
	@Autowired 
	private SueloServiceImpl sueloServiceImpl;
	
	@Autowired
	private TemporadaServiceImpl temporadaServiceImpl;
	
	@Autowired
	private TipoPlantaServiceImpl tipoPlantaServiceImpl;
	
	
	@Override
	public Planta create(Planta entity) {
		// TODO Auto-generated method stub
		return super.create(entity);
	}

	@Override
	public void remove(Planta entity) {
		// TODO Auto-generated method stub
		super.remove(entity);
	}

	@Override
	public Planta update(Planta entity) {
		// TODO Auto-generated method stub
		return super.update(entity);
	}

	@Override
	public Planta findById(Integer id) {
		// TODO Auto-generated method stub
		return super.findById(id);
	}

	@Override
	public List<Planta> findAll() {
		// TODO Auto-generated method stub
		return super.findAll();
	}
	
	
	
	
	public void create(String nombre, Integer suelo, Integer clima, Integer temporada, String descripcion, Time tiempoRiego, Integer tipoPlanta) {
		
		Planta planta = new Planta();
		
		planta.setNombre(nombre);
		planta.setDescripcion(descripcion);
		planta.setSuelo(sueloServiceImpl.findById(1));
		planta.setClima(climaServiceImpl.findById(1));
		planta.setTemporada(temporadaServiceImpl.findById(1));
		planta.setTiempoRiego(tiempoRiego);
		planta.setTipo(tipoPlantaServiceImpl.findById(1));
	
		plantaServiceImpl.create(planta);
	}
	
	public void update (Integer Planta, String nombre, Integer suelo, Integer clima, Integer temporada, String descripcion, Time tiempoRiego, Integer tipoPlanta)
	{
		Planta planta = plantaServiceImpl.findById(Planta);
		planta.setNombre(nombre);
		planta.setSuelo(sueloServiceImpl.findById(suelo));
		planta.setClima(climaServiceImpl.findById(clima));
		planta.setTemporada(temporadaServiceImpl.findById(temporada));
		planta.setTiempoRiego(tiempoRiego);
		planta.setTipo(tipoPlantaServiceImpl.findById(tipoPlanta));
		
		plantaServiceImpl.create(planta);
	}
	
	public List<Planta> findByUser(Integer planta)
	{
		return plantaServiceImpl.findById(planta).getPlanta();
	}

	public void delete(Integer id) {
		Planta planta = plantaServiceImpl.findById(id);		
		plantaServiceImpl.remove(planta);
	}
}
