package ar.edu.um.ingenieria.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.um.ingenieria.domain.TipoPlanta;

@Service
public class TipoPlantaServiceImpl extends ServiceImpl<TipoPlanta, Integer>{
	
	@Override
	public TipoPlanta create (TipoPlanta entity) {
		return super.create(entity);
	}

	@Override
	public void remove (TipoPlanta entity) {
		super.remove(entity);
	}
	
	@Override
	public TipoPlanta update (TipoPlanta entity) {
		return super.update(entity);
	}
	
	@Override
	public TipoPlanta findById (Integer id) {
		return super.findById(id);
	}
	
	@Override
	public List<TipoPlanta> findAll() {
		return super.findAll();
	}
}
