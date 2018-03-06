package ar.edu.um.ingenieria.controller.ventas;

import java.text.ParseException;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.um.ingenieria.domain.Usuario;
import ar.edu.um.ingenieria.domain.Venta;
import ar.edu.um.ingenieria.repository.VentaRepository;
import ar.edu.um.ingenieria.service.impl.TipoVentaServiceImpl;
import ar.edu.um.ingenieria.service.impl.UsuarioSecurityServiceImpl;
import ar.edu.um.ingenieria.service.impl.UsuarioServiceImpl;
import ar.edu.um.ingenieria.service.impl.VentaServiceImpl;

@RestController
@RequestMapping("/ventas/vendedor")
@Secured({ "ROLE_VENDEDOR", "ROLE_ADMIN" }) // contiene los metodos para el vendedor no es disponible para los usuarios
											// comunes
public class VentasVendedorController {
	@Autowired
	private VentaServiceImpl ventaServiceImpl;
	@Autowired
	private VentaRepository ventaRepository;
	@Autowired
	private UsuarioServiceImpl usuarioServiceImpl;
	@Autowired
	private TipoVentaServiceImpl tipoVentaServiceImpl;
	@Autowired
	private UsuarioSecurityServiceImpl usuarioSecurityServiceImpl;

	private static final Logger logger = Logger.getLogger(VentaServiceImpl.class);

	@GetMapping("/")
	public ResponseEntity<List<Venta>> findAll() {
		Usuario usuario = usuarioServiceImpl.findById(usuarioSecurityServiceImpl.GetIdUser());
		if (ventaRepository.findVentaByUsuario(usuario).isEmpty()) {
			logger.info("No hay ventas del vendedor: " + usuario);
			return new ResponseEntity<List<Venta>>(ventaRepository.findVentaByUsuario(usuario), HttpStatus.NO_CONTENT);
		}
		logger.info("Ventas del usuario: " + usuario + " " + ventaRepository.findVentaByUsuario(usuario));
		return new ResponseEntity<List<Venta>>(ventaRepository.findVentaByUsuario(usuario), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Venta> findById(@PathVariable Integer id) {
		Usuario usuario = usuarioServiceImpl.findById(usuarioSecurityServiceImpl.GetIdUser());
		if (ventaServiceImpl.findById(id) == null) {
			logger.info("No existe la venta ID " + id);
			return new ResponseEntity<Venta>(HttpStatus.NO_CONTENT);
		}
		if (ventaServiceImpl.findById(id).getUsuario() != usuario) {
			logger.info("La venta ID " + id + " no pertenece al usuario " + usuario);
			return new ResponseEntity<Venta>(HttpStatus.CONFLICT);
		}
		logger.info("Venta ID " + id + ": " + ventaServiceImpl.findById(id));
		return new ResponseEntity<Venta>(ventaServiceImpl.findById(id), HttpStatus.OK);
	}

	@GetMapping("/tipoventa/{id}")
	public ResponseEntity<List<Venta>> findByTipo(@PathVariable Integer id) {
		Usuario usuario = usuarioServiceImpl.findById(usuarioSecurityServiceImpl.GetIdUser());
		if (tipoVentaServiceImpl.findById(id) == null) {
			logger.info("No existe el tipo de venta ID " + id);
			return new ResponseEntity<List<Venta>>(ventaServiceImpl.findAll(), HttpStatus.NO_CONTENT);
		}
		if (ventaRepository.findVentaByUsuarioAndTemaVenta(usuario, tipoVentaServiceImpl.findById(id)).isEmpty()) {
			logger.info("El usario " + usuario + " no contine ventas del tipo de venta "
					+ tipoVentaServiceImpl.findById(id));
			return new ResponseEntity<List<Venta>>(HttpStatus.NO_CONTENT);
		}
		logger.info("Ventas del tipo" + tipoVentaServiceImpl.findById(id) + " del usuario " + usuario);
		return new ResponseEntity<List<Venta>>(
				ventaRepository.findVentaByUsuarioAndTemaVenta(usuario, tipoVentaServiceImpl.findById(id)),
				HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<Void> insert(String producto, String descripcion, Boolean cerrado, Integer tipoVentaId)
			throws ParseException {
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT-3:00"));
		calendar.add(Calendar.HOUR, -3);
		Venta venta = new Venta();
		venta.setProducto(producto);
		venta.setDescripcion(descripcion);
		venta.setUsuario(usuarioServiceImpl.findById(usuarioSecurityServiceImpl.GetIdUser()));
		venta.setCerrado(cerrado);
		venta.setFecha(calendar.getTime());
		venta.setTipoVenta(tipoVentaServiceImpl.findById(tipoVentaId));
		ventaServiceImpl.create(venta);
		logger.info("Creado la venta: " + venta);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@PostMapping("/edit/")
	public ResponseEntity<Void> edit(Integer id, String producto, String descripcion, Boolean cerrado,
			Integer tipoVentaId) throws ParseException {
		Usuario usuario = usuarioServiceImpl.findById(usuarioSecurityServiceImpl.GetIdUser());
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT-3:00"));
		calendar.add(Calendar.HOUR, -3);
		if (ventaServiceImpl.findById(id) == null) {
			logger.info("No existe la venta id " + id);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		if (ventaServiceImpl.findById(id).getUsuario() != usuario) {
			logger.info("La venta id " + id + " no pertenese al usuario: " + usuario);
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		}
		Venta venta = ventaServiceImpl.findById(id);
		venta.setProducto(producto);
		venta.setDescripcion(descripcion);
		venta.setCerrado(cerrado);
		venta.setFecha(calendar.getTime());
		venta.setTipoVenta(tipoVentaServiceImpl.findById(tipoVentaId));
		ventaServiceImpl.update(venta);
		logger.info("Actualizada la venta: " + venta);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@GetMapping("/close/{id}")
	public ResponseEntity<Void> close(@PathVariable Integer id) {
		Usuario usuario = usuarioServiceImpl.findById(usuarioSecurityServiceImpl.GetIdUser());
		if (ventaServiceImpl.findById(id) == null) {
			logger.info("No existe la venta id " + id);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		if (ventaServiceImpl.findById(id).getUsuario() != usuario) {
			logger.info("La venta id " + id + " no pertenese al usuario: " + usuario);
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		}
		Venta venta = ventaServiceImpl.findById(id);
		venta.setCerrado(true);
		ventaServiceImpl.update(ventaServiceImpl.findById(id));
		logger.info("Cerrada la venta id: " + id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@GetMapping("/open/{id}")
	public ResponseEntity<Void> open(@PathVariable Integer id) {
		Usuario usuario = usuarioServiceImpl.findById(usuarioSecurityServiceImpl.GetIdUser());
		if (ventaServiceImpl.findById(id) == null) {
			logger.info("No existe la venta ID: " + id);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		if (ventaServiceImpl.findById(id).getUsuario() != usuario) {
			logger.info("La venta id " + id + " no pertenese al usuario: " + usuario);
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		}
		Venta venta = ventaServiceImpl.findById(id);
		venta.setCerrado(false);
		ventaServiceImpl.update(ventaServiceImpl.findById(id));
		logger.info("Abierta la venta ID: " + id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
