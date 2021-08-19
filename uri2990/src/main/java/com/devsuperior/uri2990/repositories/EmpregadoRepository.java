package com.devsuperior.uri2990.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.devsuperior.uri2990.dto.EmpregadoDeptDTO;
import com.devsuperior.uri2990.entities.Empregado;
import com.devsuperior.uri2990.projections.EmpregadoDeptProjection;

@Repository
public interface EmpregadoRepository extends JpaRepository<Empregado, Long> {

	@Query(nativeQuery = true, value = "SELECT empreg.cpf, empreg.enome, depart.dnome "
			+ "FROM empregados empreg "
			+ "JOIN departamentos depart ON (empreg.dnumero = depart.dnumero) "
			+ "WHERE NOT EXISTS ( "
			+ "	SELECT trab.cpf_emp "
			+ "	FROM trabalha trab "
			+ "	WHERE trab.cpf_emp = empreg.cpf "
			+ ") "
			+ "ORDER BY empreg.cpf ")
	List<EmpregadoDeptProjection> search1();
	
	@Query("SELECT new com.devsuperior.uri2990.dto.EmpregadoDeptDTO(empreg.cpf, empreg.enome, empreg.departamento.dnome) "
			+ "FROM Empregado empreg "
			+ "WHERE empreg.cpf NOT IN ( "
			+ "	SELECT empreg.cpf "
			+ "	FROM Empregado empreg "
			+ "	JOIN empreg.projetosOndeTrabalha "
			+ ")"
			+ "ORDER BY empreg.cpf ")
	List<EmpregadoDeptDTO> search2();
}
