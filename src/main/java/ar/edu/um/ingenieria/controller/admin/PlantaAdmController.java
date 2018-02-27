package ar.edu.um.ingenieria.controller.admin;

import java.sql.Time;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ar.edu.um.ingenieria.domain.Planta;
import ar.edu.um.ingenieria.service.impl.ClimaServiceImpl;
import ar.edu.um.ingenieria.service.impl.PlantaServiceImpl;
import ar.edu.um.ingenieria.service.impl.SueloServiceImpl;
import ar.edu.um.ingenieria.service.impl.TemporadaServiceImpl;
import ar.edu.um.ingenieria.service.impl.TipoPlantaServiceImpl;

@RestController
@RequestMapping("/admin/plantas")
public class PlantaAdmController {
	
	@Autowired
	private PlantaServiceImpl plantaServiceImpl;
	
	@Autowired
	private SueloServiceImpl sueloServiceImpl;
	
	@Autowired
	private ClimaServiceImpl climaServiceImpl;
	
	@Autowired
	private TemporadaServiceImpl temporadaServiceImpl;
	
	@Autowired
	private TipoPlantaServiceImpl tipoPlantaServiceImpl;
	
	private static final Logger logger = Logger.getLogger(PlantaServiceImpl.class);

	@GetMapping
	public List<Planta> indexPage()
	{
		logger.info("Datos de las plantas:"+plantaServiceImpl.findAll());
		return plantaServiceImpl.findAll();
	}
	
	@GetMapping("/{id}")
	public Planta show(@PathVariable Integer id)
	{
		logger.info("Datos de la planta:"+plantaServiceImpl.findById(id));
		return plantaServiceImpl.findById(id);
	}
	
	@PostMapping("/create")
	public ResponseEntity<Void> agregar(String nombre, Integer suelo_id, Integer climas_id, Integer temporadas_id, String descripcion, Time tiempo_riego, Integer tipo_planta_id) {
		Planta planta = new Planta();
		planta.setNombre(nombre);
		planta.setSuelo(sueloServiceImpl.findById(suelo_id));
		planta.setClima(climaServiceImpl.findById(climas_id));
		planta.setTemporada(temporadaServiceImpl.findById(temporadas_id));
		planta.setDescripcion(descripcion);
		planta.setTiempoRiego(tiempo_riego);
		planta.setTipo(tipoPlantaServiceImpl.findById(tipo_planta_id));
		plantaServiceImpl.create(planta);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PostMapping("/update")
	public ResponseEntity<Void> agregar(String nombre, Integer suelo_id, Integer climas_id, Integer temporadas_id, String descripcion, Time tiempo_riego, Integer tipo_planta_id,Planta planta) {
		planta.setNombre(nombre);
		planta.setSuelo(sueloServiceImpl.findById(suelo_id));
		planta.setClima(climaServiceImpl.findById(climas_id));
		planta.setTemporada(temporadaServiceImpl.findById(temporadas_id));
		planta.setDescripcion(descripcion);
		planta.setTiempoRiego(tiempo_riego);
		planta.setTipo(tipoPlantaServiceImpl.findById(tipo_planta_id));
		List<Planta> plantita = plantaServiceImpl.findAll();
		boolean existe = false;
		for (int i = 1; i == plantita.size();i++)
		{
			if (plantita.get(i).equals(planta) == true)
			{
			 existe = true;
			}
		}
		if (existe == true)
		{
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		} else {
		plantaServiceImpl.create(planta);
		return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}

	@PostMapping
	public Planta add(@RequestBody Planta planta) {
		return plantaServiceImpl.create(planta);
	}

	@PutMapping(value = "/{id}")
	public Planta update(@RequestBody Planta planta, @PathVariable Integer id) {
		planta.setId(id);
		return plantaServiceImpl.update(planta);
	}

	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable Integer id) {
		plantaServiceImpl.remove(plantaServiceImpl.findById(id));
	}
}
