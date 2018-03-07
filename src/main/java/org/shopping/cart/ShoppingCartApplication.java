package org.shopping.cart;


import org.h2.server.web.WebServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class ShoppingCartApplication {

	/**
	 * See CartCommandLineRunner Class for spring command line implementation function (main(String [] args).
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(ShoppingCartApplication.class, args);
	}
	
	
    /**
     * This function returns a bean which is used to register a web servlet with /console mapping.
     * 
     * The console is part of the in memory H2 database used for prototyping database development.
     * 
     * JPA/Hibernate will auto generate the tables for the purposes of this demo app.
     * 
     * H2 Console which can be viewed at http://localhost:8080/console
     * 
     * @return
     */
    @Bean
    ServletRegistrationBean<WebServlet> h2servletRegistration(){

        ServletRegistrationBean<WebServlet> registrationBean = new ServletRegistrationBean<WebServlet>(new WebServlet());

        registrationBean.addUrlMappings("/console/*");

        return registrationBean;

    }
}
