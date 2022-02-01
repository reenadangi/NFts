package com.user.art.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="arts")
public class Art {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotNull
    @Size(min = 2, max = 200)
	private String artName;
	
	@NotNull
    @Size(min = 2, max = 1000)
	private String description;
	
	
	private String artUrl;
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User artist;

	
	  @OneToMany(mappedBy="artRated",cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	  @JsonIgnore
	  private List<Rating> ratings;
	  
	@Transient
	private long avgRating;

	  


	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getartName() {
		return artName;
	}



	public void setartName(String artName) {
		this.artName = artName;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}





	public List<Rating> getRatings() {
		return ratings;
	}



	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}
	
	
	
	public User getArtist() {
		return artist;
	}



	public void setArtist(User artist) {
		this.artist = artist;
	}



	public String getArtName() {
		return artName;
	}



	public void setArtName(String artName) {
		this.artName = artName;
	}



	public String getArtUrl() {
		return artUrl;
	}



	public void setArtUrl(String artUrl) {
		this.artUrl = artUrl;
	}



	public int GetAvgRating() {
		int sum=0;
		for(int i=0;i<this.ratings.size();i++) {
			sum+=ratings.get(i).getRating();
		}
		
		if (sum==0) {
			return 0;
		}
		else {
		return sum/this.ratings.size();
		}
	}



	public long getAvgRating() {
		int sum=0;
		for(int i=0;i<this.ratings.size();i++) {
			sum+=ratings.get(i).getRating();
		}
		
		if (sum==0) {
			return 0;
		}
		else {
		return sum/this.ratings.size();
		}
	}



	public void setAvgRating(long avgRating) {
		this.avgRating = avgRating;
	}

	
	 
	
	
}
