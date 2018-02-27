package ar.edu.um.ingenieria.manager;

import org.springframework.stereotype.Service;

import ar.edu.um.ingenieria.domain.Venta;
import ar.edu.um.ingenieria.service.impl.VentaServiceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentaManager {
	
private static final Logger logger = LoggerFactory.getLogger(TemaManager.class);
	
	@Autowired
	private VentaServiceImpl ventaServiceImpl;

}
