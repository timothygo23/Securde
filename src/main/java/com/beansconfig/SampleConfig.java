package com.beansconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.beans.SampleEntity;

@Configuration
public class SampleConfig {
	
	@Bean
	public SampleEntity sampleEntity(){
		return new SampleEntity();
	}
	
}
