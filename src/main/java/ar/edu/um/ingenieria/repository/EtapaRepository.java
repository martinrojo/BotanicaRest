package ar.edu.um.ingenieria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.um.ingenieria.domain.Etepa;

@Repository
public interface EtapaRepository  extends JpaRepository<Etepa, Integer> {
	
}