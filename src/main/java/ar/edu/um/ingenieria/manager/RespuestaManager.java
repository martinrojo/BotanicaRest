package ar.edu.um.ingenieria.manager;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.um.ingenieria.domain.Respuesta;
import ar.edu.um.ingenieria.service.impl.RespuestaServiceImpl;

@Service
public class RespuestaManager {

	private static final Logger logger = LoggerFactory.getLogger(RespuestaManager.class);
	
	@Autowired
	private RespuestaServiceImpl respuestaServiceImpl;
	
	public void create(Respuesta respuesta) {
		respuestaServiceImpl.create(respuesta);
		
	}

	public List<Respuesta> findAll(){
		try {
			return respuestaServiceImpl.findAll();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	
	public Respuesta findById(Integer id) {
		return respuestaServiceImpl.findById(id);
	}

	public void delete(Integer id) {
		Respuesta respuesta = respuestaServiceImpl.findById(id);	
		respuestaServiceImpl.remove(respuesta);
	}
}
