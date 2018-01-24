package ar.edu.um.ingenieria.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.um.ingenieria.domain.Seguimiento;

@Service
public class SeguimientoServiceImpl extends ServiceImpl<Seguimiento, Integer>{
	
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
	
}
