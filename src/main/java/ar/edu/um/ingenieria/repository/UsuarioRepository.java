package ar.edu.um.ingenieria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ar.edu.um.ingenieria.domain.Usuario;

@Repository
public interface UsuarioRepository  extends JpaRepository<Usuario, Integer> {
	@Query("SELECT u FROM Usuario u WHERE u.user=:username")
	List<Usuario> findUsername(@Param("username") String username);

}
