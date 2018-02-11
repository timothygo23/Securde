package com.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.beans.SampleEntity;

@Component
public class SampleDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional //<- Spring manages transaction (i.e. no need to roll back)
	public void add(SampleEntity entity){
		if(sessionFactory == null){
			System.out.println("NULL");
		}else{
			Session session = sessionFactory.getCurrentSession();
			session.save(entity);
		}
	}
}
