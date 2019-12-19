package se.iths.complexjavaproject.mudders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import se.iths.complexjavaproject.mudders.service.StartupService;

@SpringBootApplication()
public class MuddersApplication implements CommandLineRunner {

	@Autowired
	StartupService startupService;

	public static void main(String[] args) {
		SpringApplication.run(MuddersApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		startupService.populateDbIfNeeded();
	}
}
