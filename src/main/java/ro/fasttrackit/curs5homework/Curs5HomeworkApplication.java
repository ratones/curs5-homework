package ro.fasttrackit.curs5homework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ConfigProperties.class)
public class Curs5HomeworkApplication {
	public static void main(String[] args) {
		SpringApplication.run(Curs5HomeworkApplication.class, args);
	}
}