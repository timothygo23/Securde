package com.services;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.beans.SampleEntity;
import com.beansconfig.SampleConfig;

public class SampleService {
	
	
	public SampleEntity doSomething(){
		//access the bean
		ApplicationContext factory = new AnnotationConfigApplicationContext(SampleConfig.class);
		
		SampleEntity entity = factory.getBean(SampleEntity.class);
		entity.setName("tim");
		
		return entity;
	}
	
}
