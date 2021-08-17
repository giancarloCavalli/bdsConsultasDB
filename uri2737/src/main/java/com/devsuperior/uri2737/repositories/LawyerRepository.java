package com.devsuperior.uri2737.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.devsuperior.uri2737.entities.Lawyer;
import com.devsuperior.uri2737.projections.LawyerMinProjection;

@Repository
public interface LawyerRepository extends JpaRepository<Lawyer, Long> {

	//Union All is an invalid syntax in JPQL. U can walk around it with other methods.
	@Query(nativeQuery = true, value = "SELECT l.name, l.customers_number AS customersNumber "
			+ "FROM lawyers l "
			+ "WHERE l.customers_number = (SELECT MAX(l2.customers_number) FROM lawyers l2) "
			+ " "
			+ "UNION ALL "
			+ " "
			+ "SELECT l.name, l.customers_number "
			+ "FROM lawyers l "
			+ "WHERE l.customers_number = (SELECT MIN(l2.customers_number) FROM lawyers l2) "
			+ " "
			+ "UNION ALL "
			+ " "
			+ "SELECT 'Average', CAST(AVG(l.customers_number) AS INTEGER) "
			+ "FROM lawyers l ")
	List<LawyerMinProjection> search1();
}
