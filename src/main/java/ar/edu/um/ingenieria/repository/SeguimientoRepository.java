package ar.edu.um.ingenieria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ar.edu.um.ingenieria.domain.Estado;
import ar.edu.um.ingenieria.domain.Etapa;
import ar.edu.um.ingenieria.domain.Planta;
import ar.edu.um.ingenieria.domain.Seguimiento;
import ar.edu.um.ingenieria.domain.Tarea;
import ar.edu.um.ingenieria.domain.Usuario;

@Repository
public interface SeguimientoRepository extends JpaRepository<Seguimiento, Integer>{
	/*@Query("SELECT u FROM Seguimiento u WHERE u.usuario=:userId u.planta=:plantaId u.estado=:estadoId u.etapa=:etapaId u.tarea=:tareaId")
	Usuario findSeguimiento(@Param("userId") Integer userId,@Param("plantaId") Integer plantaId ,@Param("estadoId")Integer estadoId,@Param("etapaId") Integer etapaId,@Param("tareaId") Integer tareaId);
	
	@Query("SELECT u FROM Seguimiento u WHERE u.planta=:plantaId")
	Planta findPlantaId(@Param("plantaId") Integer plantaId);
	
	@Query("SELECT u FROM Seguimiento u WHERE u.estado=:estadoId")
	Estado findEstadoId(@Param("estadoId") Integer estadoId);
	
	@Query("SELECT u FROM Seguimiento u WHERE u.etapa=:etapaId")
	Etapa findEtapaId(@Param("etapaId") Integer etapaId);
	
	@Query("SELECT u FROM Seguimiento u WHERE u.tarea=:tareaId")
	Tarea findTareaId(@Param("tareaId") Integer tareaId);*/
	
}
