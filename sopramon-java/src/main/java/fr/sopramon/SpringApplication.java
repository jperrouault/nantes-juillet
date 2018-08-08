package fr.sopramon;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import fr.sopramon.config.JpaConfig;

public class SpringApplication {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext myContext =
				new AnnotationConfigApplicationContext(JpaConfig.class);
		
		myContext.getBeanFactory()
			.createBean(Principal.class)
			.run(args);
		
		myContext.close();
	}
}
