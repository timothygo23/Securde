package com.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.beans.ReviewRating;
import com.dao.ReviewRatingDAO;

public class ReviewRatingDAOImpl implements ReviewRatingDAO{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public void add(ReviewRating reviewRating) {
		sessionFactory.getCurrentSession().save(reviewRating);
	}

	@Transactional
	public void edit(ReviewRating reviewRating) {
		sessionFactory.getCurrentSession().update(reviewRating);
	}

	@Transactional
	public void delete(int rating_id) {
		sessionFactory.getCurrentSession().delete(getReviewRating(rating_id));
	}

	@Transactional
	public ReviewRating getReviewRating(int rating_id) {
		return (ReviewRating)sessionFactory.getCurrentSession().get(ReviewRating.class, rating_id);
	}

	@Transactional
	public List<ReviewRating> getAllReviewRatings() {
		List<ReviewRating> reviewRating = null;
		
		if (sessionFactory != null) {
			Session session = sessionFactory.getCurrentSession();
			
			reviewRating = session.createQuery("from reviewRating", ReviewRating.class).getResultList();
		}
		
		return reviewRating;
	}
}
