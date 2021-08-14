package com.devsuperior.uri2611.respositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.devsuperior.uri2611.dto.MovieMinDTO;
import com.devsuperior.uri2611.entities.Movie;
import com.devsuperior.uri2611.projections.MovieMinProjection;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

	@Query(nativeQuery = true, value = "SELECT m.id, m.name "
			+ "FROM movies m "
			+ "JOIN genres g ON (m.id_genres = g.id) "
			+ "WHERE UPPER(g.description) = UPPER(:genreName)")
	List<MovieMinProjection> search1(String genreName);
	
	@Query("SELECT new com.devsuperior.uri2611.dto.MovieMinDTO(movie.id, movie.name) "
			+ "FROM Movie movie "
			+ "WHERE UPPER(movie.genre.description) = UPPER(:genreName)")
	List<MovieMinDTO> search2(String genreName);
	
}
