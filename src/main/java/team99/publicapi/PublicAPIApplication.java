package team99.publicapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import team99.publicapi.service.PublicAPIService;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class PublicAPIApplication {

	@Autowired
	private PublicAPIService publicAPIService;
	public static void main(String[] args) {
		SpringApplication.run(PublicAPIApplication.class, args);
	}

	@PostConstruct
	public void init() {
		publicAPIService.getUsers();
	}
}
