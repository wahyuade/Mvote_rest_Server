package mvote.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@SpringBootApplication
public class MvoteRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(MvoteRestApplication.class, args);
	}
}
