package ar.edu.um.ingenieria.controller.admin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.um.ingenieria.domain.TipoVenta;
import ar.edu.um.ingenieria.domain.Venta;
import ar.edu.um.ingenieria.service.impl.TipoVentaServiceImpl;
import ar.edu.um.ingenieria.service.impl.UsuarioServiceImpl;
import ar.edu.um.ingenieria.service.impl.VentaServiceImpl;

@RestController
@RequestMapping("/admin/ventas")
@Secured({ "ROLE_ADMIN" })
public class VentaAdmController {

	@Autowired
	private VentaServiceImpl ventaServiceImpl;
	@Autowired
	private UsuarioServiceImpl usuarioServiceImpl;
	@Autowired
	private TipoVentaServiceImpl tipoVentaServiceImpl;

	private static final Logger logger = Logger.getLogger(VentaServiceImpl.class);

	@GetMapping("/")
	public ResponseEntity<List<Venta>> findAll() {
		if (tipoVentaServiceImpl.findAll().isEmpty()) {
			logger.info("No hay ventas");
			return new ResponseEntity<List<Venta>>(ventaServiceImpl.findAll(), HttpStatus.NO_CONTENT);
		}
		logger.info("Ventas: " + ventaServiceImpl.findAll());
		return new ResponseEntity<List<Venta>>(ventaServiceImpl.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Venta> findById(@PathVariable Integer id) {
		if (ventaServiceImpl.findById(id) == null) {
			logger.info("No existe la venta ID " + id);
			return new ResponseEntity<Venta>(HttpStatus.NO_CONTENT);
		}
		logger.info("Venta ID " + id + ": " + ventaServiceImpl.findById(id));
		return new ResponseEntity<Venta>(ventaServiceImpl.findById(id), HttpStatus.OK);
	}

	@GetMapping("/tipoventa/{id}")
	public ResponseEntity<List<Venta>> findByTipo(@PathVariable Integer id) {
		if (tipoVentaServiceImpl.findById(id) == null) {
			logger.info("No existe el tipo de venta ID " + id);
			return new ResponseEntity<List<Venta>>(ventaServiceImpl.findAll(), HttpStatus.OK);
		} else {
			TipoVenta tipoVenta = tipoVentaServiceImpl.findById(id);
			if (tipoVenta.getVentas().isEmpty()) {
				logger.info("El tipo de venta ID:" + id + "no continene ninguan venta");
				new ResponseEntity<List<Venta>>(HttpStatus.NO_CONTENT);
			}
			logger.info("El tipo de venta ID:" + id + "continene las ventas " + tipoVenta.getVentas());
			return new ResponseEntity<List<Venta>>(tipoVenta.getVentas(), HttpStatus.OK);
		}
	}

	@PostMapping("/")
	public ResponseEntity<Void> insert(String producto, String descripcion, Integer usuarioId, Boolean cerrado,
			String fecha, Integer tipoVentaId) throws ParseException {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// para trasformar la hora de
																						// string a date
		Venta venta = new Venta();
		venta.setProducto(producto);
		venta.setDescripcion(descripcion);
		venta.setUsuario(usuarioServiceImpl.findById(usuarioId));
		venta.setCerrado(cerrado);
		venta.setFecha(simpleDateFormat.parse(fecha));
		venta.setTipoVenta(tipoVentaServiceImpl.findById(tipoVentaId));
		ventaServiceImpl.create(venta);
		logger.info("Creado la venta: " + venta);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@PostMapping("/edit/")
	public ResponseEntity<Void> edit(Integer id, String producto, String descripcion, Integer usuarioId,
			Boolean cerrado, String fecha, Integer tipoVentaId) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (ventaServiceImpl.findById(id) == null) {
			logger.info("No existe la venta id " + id);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} else {
			Venta venta = ventaServiceImpl.findById(id);
			venta.setProducto(producto);
			venta.setDescripcion(descripcion);
			venta.setUsuario(usuarioServiceImpl.findById(usuarioId));
			venta.setCerrado(cerrado);
			venta.setFecha(simpleDateFormat.parse(fecha));
			System.out.println("\n\n" + venta.getFecha() + "\n\n");
			venta.setTipoVenta(tipoVentaServiceImpl.findById(tipoVentaId));
			ventaServiceImpl.update(venta);
			logger.info("Actualizada la venta: " + venta);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}

	@GetMapping("/close/{id}")
	public ResponseEntity<Void> close(@PathVariable Integer id) {
		if (ventaServiceImpl.findById(id) == null) {
			logger.info("No existe la venta id " + id);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} else {
			Venta venta = ventaServiceImpl.findById(id);
			venta.setCerrado(true);
			ventaServiceImpl.update(ventaServiceImpl.findById(id));
			logger.info("Cerrada la venta id: " + id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}

	@GetMapping("/open/{id}")
	public ResponseEntity<Void> open(@PathVariable Integer id) {
		if (ventaServiceImpl.findById(id) == null) {
			logger.info("No existe la venta ID: " + id);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} else {
			Venta venta = ventaServiceImpl.findById(id);
			venta.setCerrado(false);
			ventaServiceImpl.update(ventaServiceImpl.findById(id));
			logger.info("Abierta la venta ID: " + id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		if (ventaServiceImpl.findById(id) == null) {
			logger.info("No existe la venta ID " + id);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} else {
			ventaServiceImpl.remove(ventaServiceImpl.findById(id));
			logger.info("Borrada la venta ID: " + id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}

	}
}
