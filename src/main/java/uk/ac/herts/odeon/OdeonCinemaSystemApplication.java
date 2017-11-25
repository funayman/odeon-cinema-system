package uk.ac.herts.odeon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import uk.ac.herts.odeon.service.MockInitService;

@SpringBootApplication
public class OdeonCinemaSystemApplication implements CommandLineRunner {

	@Autowired
	protected MockInitService initService;

	public static void main(String[] args) {
		SpringApplication.run(OdeonCinemaSystemApplication.class, args);
	}

	public void run(String...args) {
		initService.init();
	}
}
