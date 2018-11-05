package org.mthu.interstellar;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class InterstellarApplication {	
    
	public static void main(String[] args) {
		SpringApplication.run(InterstellarApplication.class, args).start();;
	}
	
	//TODO Use spring batch job to load data from file
	//TODO USE spring AOP for logging.
	//TODO Add more unit tests and error checking
	
	
}
