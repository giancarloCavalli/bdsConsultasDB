package com.devsuperior.uri2609.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.uri2609.dto.CategorySumDTO;
import com.devsuperior.uri2609.entities.Category;
import com.devsuperior.uri2609.projections.CategorySumProjection;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	@Query(nativeQuery = true, value = "SELECT cat.name, SUM(prod.amount) "
			+ "FROM products prod "
			+ "JOIN categories cat ON (prod.id_categories = cat.id) "
			+ "GROUP BY cat.id")
	List<CategorySumProjection> search1();

	@Query("SELECT new com.devsuperior.uri2609.dto.CategorySumDTO(prod.category.name, SUM(prod.amount)) "
			+ "FROM Product prod "
			+ "GROUP BY prod.category.name")
	List<CategorySumDTO> search2();
}
