package com.dao;

import java.util.List;

import com.beans.ReviewRating;

public interface ReviewRatingDAO {
	public void add(ReviewRating reviewRating);
	public void edit(ReviewRating reviewRating);
	public void delete(int rating_id);
	public ReviewRating getCart(int rating_id);
	public List<ReviewRating> getAllReviewRatings();	
}
