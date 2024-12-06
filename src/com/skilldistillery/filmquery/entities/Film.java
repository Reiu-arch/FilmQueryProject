package com.skilldistillery.filmquery.entities;

import java.util.List;
import java.util.Objects;

public class Film {
	private int id;
	
	private String title;
	private String description;
	private Integer releaseYear;
	private int languageId;
	private int rentalDuration;
	private double rentalRate;
	private int rentalLength;
	private double replacementCost;
	private String rating;
	private String specialFeatures;
	private List<Actor> actor;
	
	
	public Film(int id, String title, String description, Integer releaseYear, int languageId, int rentalDuration,
			double rentalRate, int rentalLength, double replacementCost, String rating, String specialFeatures,
			List<Actor> actor) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.releaseYear = releaseYear;
		this.languageId = languageId;
		this.rentalDuration = rentalDuration;
		this.rentalRate = rentalRate;
		this.rentalLength = rentalLength;
		this.replacementCost = replacementCost;
		this.rating = rating;
		this.specialFeatures = specialFeatures;
		this.actor = actor;
	}
	public List<Actor> getActor() {
		return actor;
	}
	public void setActor(List<Actor> list) {
		this.actor = list;
	}
	public void setReleaseYear(Integer releaseYear) {
		this.releaseYear = releaseYear;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getReleaseYear() {
		return releaseYear;
	}
	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}
	public int getLanguageId() {
		return languageId;
	}
	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}
	public int getRentalDuration() {
		return rentalDuration;
	}
	public void setRentalDuration(int rentalDuration) {
		this.rentalDuration = rentalDuration;
	}
	public double getRentalRate() {
		return rentalRate;
	}
	public void setRentalRate(double rentalRate) {
		this.rentalRate = rentalRate;
	}
	public int getRentalLength() {
		return rentalLength;
	}
	public void setRentalLength(int rentalLength) {
		this.rentalLength = rentalLength;
	}
	public double getReplacementCost() {
		return replacementCost;
	}
	public void setReplacementCost(double replacementCost) {
		this.replacementCost = replacementCost;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getSpecialFeatures() {
		return specialFeatures;
	}
	public void setSpecialFeatures(String SpecialFeatures) {
		this.specialFeatures = SpecialFeatures;
	}
	@Override
	public int hashCode() {
		return Objects.hash(actor, description, id, languageId, rating, releaseYear, rentalDuration, rentalLength,
				rentalRate, replacementCost, specialFeatures, title);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		return Objects.equals(actor, other.actor) && Objects.equals(description, other.description) && id == other.id
				&& languageId == other.languageId && Objects.equals(rating, other.rating)
				&& Objects.equals(releaseYear, other.releaseYear) && rentalDuration == other.rentalDuration
				&& rentalLength == other.rentalLength
				&& Double.doubleToLongBits(rentalRate) == Double.doubleToLongBits(other.rentalRate)
				&& Double.doubleToLongBits(replacementCost) == Double.doubleToLongBits(other.replacementCost)
				&& Objects.equals(specialFeatures, other.specialFeatures) && Objects.equals(title, other.title);
	}
	public Film(int id, String title, String description, int releaseYear, int languageId, int rentalDuration,
			double rentalRate, int rentalLength, double replacementCost, String rating, String specialFeatures) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.releaseYear = releaseYear;
		this.languageId = languageId;
		this.rentalDuration = rentalDuration;
		this.rentalRate = rentalRate;
		this.rentalLength = rentalLength;
		this.replacementCost = replacementCost;
		this.rating = rating;
		this.specialFeatures = specialFeatures;
	}
	@Override
	public String toString() {
		return "Film [id=" + id + ", title=" + title + ", description=" + description + ", releaseYear=" + releaseYear
				+ ", languageId=" + languageId + ", rentalDuration=" + rentalDuration + ", rentalRate=" + rentalRate
				+ ", rentalLength=" + rentalLength + ", replacementCost=" + replacementCost + ", rating=" + rating
				+ ", specialFeatures=" + specialFeatures + ", actor=" + actor + "]";
	}
	public Film() {
		super();
	}
	
}
