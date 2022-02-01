package com.user.art.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.user.art.models.Art;


public interface ArtRepository  extends CrudRepository<Art, Long> {

	// Create
	// Read
	// Update
	// Delete
	
	List<Art> findAll(); 
	
	@Query(value="SELECT arts.*, AVG(ratings.rating) as avgRating FROM arts LEFT JOIN ratings ON arts.id=ratings.art_id GROUP BY arts.id ORDER BY avgRating DESC",nativeQuery = true)
	 List<Art> getArtsByRatingOrder(); 

	


	
	
	
	
	
	
	
	


	
}
