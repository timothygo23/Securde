package com.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="review_rating")
public class ReviewRating {

	@Id
	@Column(name="rating_id")
	private int rating_id;
	@Column(name="account_id")
	private int account_id;
	@Column(name="product_id")
	private int product_id;
	@Column(name="rating_score")
	private int rating_score;
	@Column(name="review_description")
	private String review_description;
	
	public ReviewRating() {}
	
	public ReviewRating(int rating_id, int account_id, int product_id, int rating_score, String review_description) {
		this.rating_id = rating_id;
		this.account_id = account_id;
		this.product_id = product_id;
		this.rating_score = rating_score;
		this.review_description = review_description;
	}

	public int getRating_id() {
		return rating_id;
	}

	public void setRating_id(int rating_id) {
		this.rating_id = rating_id;
	}

	public int getAccount_id() {
		return account_id;
	}

	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public int getRating_score() {
		return rating_score;
	}

	public void setRating_score(int rating_score) {
		this.rating_score = rating_score;
	}

	public String getReview_description() {
		return review_description;
	}

	public void setReview_description(String review_description) {
		this.review_description = review_description;
	}
		
}
