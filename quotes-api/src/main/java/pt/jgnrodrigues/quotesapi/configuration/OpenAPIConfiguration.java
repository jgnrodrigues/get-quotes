package pt.jgnrodrigues.quotesapi.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
  info = @Info(
  title = "Quotes API",
  description = "Quotes service"),
  servers = @Server(url = "http://localhost:8080")
)
public class OpenAPIConfiguration {
    
}
