package ar.edu.um.ingenieria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ar.edu.um.ingenieria.domain.TipoVenta;
import ar.edu.um.ingenieria.domain.Usuario;
import ar.edu.um.ingenieria.domain.Venta;

@Repository
public interface VentaRepository  extends JpaRepository<Venta, Integer> {
	@Query("SELECT c FROM Venta c WHERE c.usuario=:Usuario")
	List<Venta> findVentaByUsuario(@Param("Usuario") Usuario usuario);
	
	@Query("SELECT c FROM Venta c WHERE c.usuario=:Usuario and c.tipo_venta_id=:TipoVenta")
	List<Venta> findVentaByUsuarioAndTemaVenta(@Param("Usuario") Usuario usuario, @Param("TipoVenta") TipoVenta tipoVenta);
}