package net.javaguides.springboot;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Spring Boot REST API Documentation",
				description = "Hi, this is our first spring boot application from scratch",
				version = "1.0.0",
				contact = @Contact(
						name = "Zain Badar", email = "Zain@gmail.com", url = "localhost:9090/api/v1/user"
				),
				license = @License(
						name = "Apache 2.0",
						url = "localhost:9090/api/v1/user"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Spring boot user Management System",
				url = "localhost:9090/api/v1/user/user_management.html"
		)

)
public class SpringbootRestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootRestApiApplication.class, args);
    }

}
