package ar.edu.um.ingenieria.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.um.ingenieria.domain.TipoPlanta;

@Service
public class TipoPlantaServiceImpl extends ServiceImpl<TipoPlanta, Integer> {

	@Override
	public TipoPlanta create(TipoPlanta entity) {
		// TODO Auto-generated method stub
		return super.create(entity);
	}

	@Override
	public void remove(TipoPlanta entity) {
		// TODO Auto-generated method stub
		super.remove(entity);
	}

	@Override
	public TipoPlanta update(TipoPlanta entity) {
		// TODO Auto-generated method stub
		return super.update(entity);
	}

	@Override
	public TipoPlanta findById(Integer id) {
		// TODO Auto-generated method stub
		return super.findById(id);
	}

	@Override
	public List<TipoPlanta> findAll() {
		// TODO Auto-generated method stub
		return super.findAll();
	}

}
