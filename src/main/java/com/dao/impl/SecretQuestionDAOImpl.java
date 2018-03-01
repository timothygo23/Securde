package com.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.beans.SecretQuestion;
import com.dao.SecretQuestionDAO;

public class SecretQuestionDAOImpl implements SecretQuestionDAO{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public void add(SecretQuestion secretQuestion) {
		sessionFactory.getCurrentSession().save(secretQuestion);
	}

	@Transactional
	public void edit(SecretQuestion secretQuestion) {
		sessionFactory.getCurrentSession().update(secretQuestion);
	}

	@Transactional
	public void delete(int secret_id) {
		sessionFactory.getCurrentSession().delete(getSecretQuestion(secret_id));
	}

	@Transactional
	public SecretQuestion getSecretQuestion(int secret_id) {
		return (SecretQuestion)sessionFactory.getCurrentSession().get(SecretQuestion.class, secret_id);
	}

	@Transactional
	public List<SecretQuestion> getAllSecretQuestions() {
		List<SecretQuestion> secretQuestion = null;
		
		if (sessionFactory != null) {
			Session session = sessionFactory.getCurrentSession();
			
			secretQuestion = session.createQuery("from secretQuestion", SecretQuestion.class).getResultList();
		}
		
		return secretQuestion;
	}
}
