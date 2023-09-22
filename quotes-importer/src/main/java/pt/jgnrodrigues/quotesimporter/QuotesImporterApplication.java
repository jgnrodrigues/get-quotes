package pt.jgnrodrigues.quotesimporter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@ConfigurationPropertiesScan("pt.jgnrodrigues.quotesimporter.configuration.properties")
@EnableFeignClients(basePackages = "pt.jgnrodrigues.quotesimporter.client")
public class QuotesImporterApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuotesImporterApplication.class, args);
	}

}
