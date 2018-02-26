package ar.edu.um.ingenieria.manager;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.um.ingenieria.domain.Tema;
import ar.edu.um.ingenieria.service.impl.TemaServiceImpl;

@Service
public class TemaManager {

	@Autowired
	private TemaServiceImpl temaServiceImpl;
	
	public void create(Tema tema) {
		temaServiceImpl.create(tema);
	}

	public List<Tema> findAll(){
		return temaServiceImpl.findAll();
	}
	
	public Tema findById(Integer id) {
		return temaServiceImpl.findById(id);
	}

	public void delete(Integer id) {
		Tema tema = temaServiceImpl.findById(id);	
		temaServiceImpl.remove(tema);
	}
}
