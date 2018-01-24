package ar.edu.um.ingenieria.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.um.ingenieria.domain.Planta;

@Service
public class PlantaServiceImpl extends ServiceImpl<Planta, Integer>{

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
}
