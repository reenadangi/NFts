package com.user.art.repositories;


import org.springframework.data.repository.CrudRepository;

import com.user.art.models.Rating;


public interface RatingRepository  extends CrudRepository<Rating, Long> {
	
}
