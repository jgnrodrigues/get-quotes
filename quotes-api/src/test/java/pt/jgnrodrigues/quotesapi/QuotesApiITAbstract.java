package pt.jgnrodrigues.quotesapi;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MongoDBContainer;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import pt.jgnrodrigues.quotesapi.data.Quote;
import pt.jgnrodrigues.quotesapi.repository.QuoteRepository;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class QuotesApiITAbstract {
    static MongoDBContainer mongoContainer = new MongoDBContainer("mongo:latest");

    @Autowired
    private MockMvc client;

    @Autowired
    protected QuoteRepository quoteRepository;

    @Autowired
    ResourceLoader resourceLoader;

    @Autowired
    protected ObjectMapper objectMapper;

    @DynamicPropertySource
    static void configureConnections(DynamicPropertyRegistry registry){
        registry.add("spring.data.mongodb.host", mongoContainer::getHost);
        registry.add("spring.data.mongodb.port", () -> mongoContainer.getMappedPort(27017));
    }

    @BeforeAll
    static void beforeAll() {
        startContainers();
    }

    private static void startContainers(){
        mongoContainer.start();
    }

    protected void configureClient(){
        RestAssuredMockMvc.mockMvc(client);
    }

    protected void addData() throws StreamReadException, DatabindException, IOException{
        Resource dataFile = resourceLoader.getResource("classpath:data.json");
        List<Quote> quotes = objectMapper.readValue(dataFile.getInputStream(), new TypeReference<List<Quote>>() {});

        quoteRepository.saveAll(quotes);
    }

    protected void cleanData() {
        quoteRepository.deleteAll();
    }
}
