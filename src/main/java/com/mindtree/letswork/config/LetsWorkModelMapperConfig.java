/**
 * Containing Bean of ModelMapper
 * Will help to convert entity to dto and vis-a-versa
 */
package com.mindtree.letswork.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LetsWorkModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
