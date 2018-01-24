package ar.edu.um.ingenieria.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.um.ingenieria.domain.Usuario;


@Service
public class UsuarioServiceImpl extends ServiceImpl<Usuario, Integer>{

	@Override
	public Usuario create(Usuario entity) {
		//BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	//	entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
		return super.create(entity);
	}

	@Override
	public void remove(Usuario entity) {
		
		super.remove(entity);
	}

	@Override
	public Usuario update(Usuario entity) {
		
		return super.update(entity);
	}

	@Override
	public Usuario findById(Integer id) {
	
		return super.findById(id);
	}

	@Override
	public List<Usuario> findAll() {
		return super.findAll();
	}

	
}
