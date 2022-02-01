package com.user.art.services;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.art.models.Art;
import com.user.art.models.Rating;
import com.user.art.repositories.ArtRepository;
import com.user.art.repositories.RatingRepository;

@Service
public class ArtService {

	@Autowired
	private ArtRepository artRepo;
	
	@Autowired
	private RatingRepository ratingRepo;

	// List all arts
	public List<Art> allArts() {
		List<Art> arts=artRepo.findAll();
		arts.sort(Comparator.comparing(Art::getAvgRating)
                .thenComparing(Comparator.comparing(Art::getAvgRating)).reversed());
		return artRepo.getArtsByRatingOrder();
//		return arts;
		}

	// Create new art
	public Art createArt(Art art) {
		System.out.println("Creating a art");
		return artRepo.save(art);
	}

	// Delete a art
	public void deleteArt(Long id) {
		artRepo.deleteById(id);
	}

	// Get One art
	public Art getOneArt(Long id) {
		return artRepo.findById(id).orElse(null);
	}

	// Update art
	public Art updateArt(Art art) {
		return artRepo.save(art);
	}

	//create a rating 
	public Rating addRating(Rating rating) {
		System.out.println("Creating a rating");
		return ratingRepo.save(rating);
	}
	

}
