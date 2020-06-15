package com.longma.cursomc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.longma.cursomc.domain.PaymentWithCard;
import com.longma.cursomc.domain.PaymentWithWallet;

@Configuration
public class JacksonConfig {

	// Source: https://stackoverflow.com/questions/41452598/overcome-can-not-construct-instance-of-interfaceclass-without-hinting-the-pare
	
	@Bean
	public Jackson2ObjectMapperBuilder objectMapperBuilder() {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
			public void configure(ObjectMapper objectMapper) {
				objectMapper.registerSubtypes(PaymentWithWallet.class);
				objectMapper.registerSubtypes(PaymentWithCard.class);
				super.configure(objectMapper);
			}
		};
		return builder;
	}
}
