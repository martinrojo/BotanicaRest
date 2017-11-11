package ar.edu.um.ingenieria.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.um.ingenieria.domain.Rol;
import ar.edu.um.ingenieria.service.impl.RolServiceImpl;
@Service
public class RolManager {
	
	@Autowired
	private RolServiceImpl rolServiceImpl;
	
	
	
	public Rol findById(int parseInt) {
		return rolServiceImpl.findById(parseInt);
	}
	
	public List<Rol> findWithOutAdmin(){
		return rolServiceImpl.findWithOutAdmin();
	}

}
