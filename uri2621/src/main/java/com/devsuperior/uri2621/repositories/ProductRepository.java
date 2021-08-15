package com.devsuperior.uri2621.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.devsuperior.uri2621.dtos.ProductMinDTO;
import com.devsuperior.uri2621.entities.Product;
import com.devsuperior.uri2621.projections.ProductMinProjection;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query(nativeQuery = true, value = "SELECT prod.name "
			+ "FROM products prod "
			+ "LEFT JOIN providers prov ON (prod.id_providers = prov.id) "
			+ "WHERE prod.amount BETWEEN :min AND :max "
			+ "	AND UPPER(prov.name) LIKE CONCAT(UPPER(:providerNameBeginingWith), '%') ")
	List<ProductMinProjection> search1(Integer min, Integer max, String providerNameBeginingWith);
	
	@Query("SELECT new com.devsuperior.uri2621.dtos.ProductMinDTO(prod.name) "
			+ "FROM Product prod "
			+ "WHERE prod.amount BETWEEN :min AND :max "
			+ "	AND UPPER(prod.provider.name) LIKE CONCAT(UPPER(:providerNameBeginingWith), '%') ")
	List<ProductMinDTO> search2(Integer min, Integer max, String providerNameBeginingWith);
}
