package ar.edu.um.ingenieria.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.um.ingenieria.domain.Pregunta;

@Service
public class PreguntaServiceImpl extends ServiceImpl<Pregunta, Integer>{

	@Override
	public Pregunta create(Pregunta entity) {
		// TODO Auto-generated method stub
		return super.create(entity);
	}

	@Override
	public void remove(Pregunta entity) {
		// TODO Auto-generated method stub
		super.remove(entity);
	}

	@Override
	public Pregunta update(Pregunta entity) {
		// TODO Auto-generated method stub
		return super.update(entity);
	}

	@Override
	public Pregunta findById(Integer id) {
		// TODO Auto-generated method stub
		return super.findById(id);
	}

	@Override
	public List<Pregunta> findAll() {
		// TODO Auto-generated method stub
		return super.findAll();
	}
}
