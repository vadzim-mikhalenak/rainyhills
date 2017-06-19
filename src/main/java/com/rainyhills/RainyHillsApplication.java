package com.rainyhills;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * @author Vadzim Mikhalenak
 */
@SpringBootApplication
public class RainyHillsApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(RainyHillsApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(applicationClass);
	}

	private static Class<RainyHillsApplication> applicationClass = RainyHillsApplication.class;
}
