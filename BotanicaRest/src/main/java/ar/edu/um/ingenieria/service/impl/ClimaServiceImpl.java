package ar.edu.um.ingenieria.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.um.ingenieria.domain.Clima;

@Service
public class ClimaServiceImpl extends ServiceImpl<Clima, Integer> {

	@Override
	public Clima create(Clima entity) {
		// TODO Auto-generated method stub
		return super.create(entity);
	}

	@Override
	public void remove(Clima entity) {
		// TODO Auto-generated method stub
		super.remove(entity);
	}

	@Override
	public Clima update(Clima entity) {
		// TODO Auto-generated method stub
		return super.update(entity);
	}

	@Override
	public Clima findById(Integer id) {
		// TODO Auto-generated method stub
		return super.findById(id);
	}

	@Override
	public List<Clima> findAll() {
		// TODO Auto-generated method stub
		return super.findAll();
	}

}
