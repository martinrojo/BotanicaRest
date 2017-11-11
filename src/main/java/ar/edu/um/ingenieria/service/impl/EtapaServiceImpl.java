package ar.edu.um.ingenieria.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.um.ingenieria.domain.Etepa;

@Service
public class EtapaServiceImpl extends ServiceImpl<Etepa, Integer>{

	@Override
	public Etepa create(Etepa entity) {
		// TODO Auto-generated method stub
		return super.create(entity);
	}

	@Override
	public void remove(Etepa entity) {
		// TODO Auto-generated method stub
		super.remove(entity);
	}

	@Override
	public Etepa update(Etepa entity) {
		// TODO Auto-generated method stub
		return super.update(entity);
	}

	@Override
	public Etepa findById(Integer id) {
		// TODO Auto-generated method stub
		return super.findById(id);
	}

	@Override
	public List<Etepa> findAll() {
		// TODO Auto-generated method stub
		return super.findAll();
	}
	
}
