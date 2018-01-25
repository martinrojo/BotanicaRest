package ar.edu.um.ingenieria.manager;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.um.ingenieria.domain.Categoria;
import ar.edu.um.ingenieria.service.impl.CategoriaServiceImpl;

@Service
public class CategoriaManager {
	
	private static final Logger logger = LoggerFactory.getLogger(CategoriaManager.class);
	
	@Autowired
	private CategoriaServiceImpl categoriaServiceImpl;
	
	public void create(Categoria categoria) {
		categoriaServiceImpl.create(categoria);
		
	}

	public List<Categoria> findAll(){
		try {
			return categoriaServiceImpl.findAll();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	
	public Categoria findById(Integer id) {
		return categoriaServiceImpl.findById(id);
	}

	public void delete(Integer id) {
		Categoria categoria = categoriaServiceImpl.findById(id);	
		categoriaServiceImpl.remove(categoria);
	}
}
