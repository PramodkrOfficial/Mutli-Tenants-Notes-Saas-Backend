package com.notessaas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableMongoAuditing
@RestController
public class NotesSaasBackendApplication {

    @RequestMapping("/status")
    public String home() {
        return "Spring Boot Application is running";
    }
	public static void main(String[] args) {
		SpringApplication.run(NotesSaasBackendApplication.class, args);
	}

}
