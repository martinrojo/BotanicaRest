package ar.edu.um.ingenieria.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.um.ingenieria.domain.Venta;

@Service
public class VentaServiceImpl extends ServiceImpl<Venta, Integer> {

	@Override
	public Venta create(Venta entity) {
		// TODO Auto-generated method stub
		return super.create(entity);
	}

	@Override
	public void remove(Venta entity) {
		// TODO Auto-generated method stub
		super.remove(entity);
	}

	@Override
	public Venta update(Venta entity) {
		// TODO Auto-generated method stub
		return super.update(entity);
	}

	@Override
	public Venta findById(Integer id) {
		// TODO Auto-generated method stub
		return super.findById(id);
	}

	@Override
	public List<Venta> findAll() {
		// TODO Auto-generated method stub
		return super.findAll();
	}

}
