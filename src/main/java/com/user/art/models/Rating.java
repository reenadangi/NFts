package com.user.art.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="ratings")
public class Rating {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotNull
    private int rating;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User ratedBy;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="art_id")
    private Art artRated;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public User getRatedBy() {
		return ratedBy;
	}

	public void setRatedBy(User ratedBy) {
		this.ratedBy = ratedBy;
	}

	public Art getArtRated() {
		return artRated;
	}

	public void setArtRated(Art artRated) {
		this.artRated = artRated;
	}

	
}
