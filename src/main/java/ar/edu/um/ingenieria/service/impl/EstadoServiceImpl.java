package ar.edu.um.ingenieria.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.um.ingenieria.domain.Estado;

@Service
public class EstadoServiceImpl extends ServiceImpl<Estado, Integer>{

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
}
