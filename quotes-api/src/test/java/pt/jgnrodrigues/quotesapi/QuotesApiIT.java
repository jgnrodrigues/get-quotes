package pt.jgnrodrigues.quotesapi;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.*;

import io.restassured.http.ContentType;

import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

@SpringBootTest
public class QuotesApiIT extends QuotesApiITAbstract{
    
    @BeforeEach
    void beforeEach() throws StreamReadException, DatabindException, IOException{
        addData();
        configureClient();
    }

    @AfterEach
    void afterEach() {
        cleanData();
    }

    @Test
    void Given_pagSize2_When_getAll_Then_Twopages() {
        given()
            .contentType(ContentType.JSON)
            .queryParam("page", 0)
            .queryParam("size", 2)
        .when()
            .get("/quote")
        .then()
            .statusCode(200)
            .body("page.size", is(2))
            .body("page.totalElements", is(3))
            .body("page.totalPages", is(2))
            .body("page.number", is(0))
            .body("_links.first.href", endsWith("/quote?page=0&size=2"))
            .body("_links.prev", nullValue())
            .body("_links.self.href", endsWith("/quote?page=0&size=2"))
            .body("_links.next.href", endsWith("/quote?page=1&size=2"))
            .body("_links.last.href", endsWith("/quote?page=1&size=2"))
            .body("_embedded.quotes", hasSize(2));

        given()
            .contentType(ContentType.JSON)
            .queryParam("page", 1)
            .queryParam("size", 2)
        .when()
            .get("/quote")
        .then()
            .statusCode(200)
            .body("page.size", is(2))
            .body("page.totalElements", is(3))
            .body("page.totalPages", is(2))
            .body("page.number", is(1))
            .body("_links.first.href", endsWith("/quote?page=0&size=2"))
            .body("_links.prev.href", endsWith("/quote?page=0&size=2"))
            .body("_links.self.href", endsWith("/quote?page=1&size=2"))
            .body("_links.next", nullValue())
            .body("_links.last.href", endsWith("/quote?page=1&size=2"))
            .body("_embedded.quotes", hasSize(1));
    }

    @Test
    void Given_SinglePage_When_getAll_Then_NoPageLinks(){
        given()
            .contentType(ContentType.JSON)
            .queryParam("page", 0)
            .queryParam("size", 3)
        .when()
            .get("/quote")
        .then()
            .statusCode(200)
            .body("page.size", is(3))
            .body("page.totalElements", is(3))
            .body("page.totalPages", is(1))
            .body("page.number", is(0))
            .body("_links.first", nullValue())
            .body("_links.prev", nullValue())
            .body("_links.next", nullValue())
            .body("_links.last", nullValue())
            .body("_links.self.href", endsWith("/quote?page=0&size=3"))
            .body("_embedded.quotes", hasSize(3));
    }

    @Test
    void Given_ExistingAuthor_When_getAllWithAuthor_Then_Quote(){
        given()
            .contentType(ContentType.JSON)
            .queryParam("author", "Amos Bronson Alcott")
        .when()
            .get("/quote")
        .then()
            .statusCode(200)
            .body("page.size", is(20))
            .body("page.totalElements", is(1))
            .body("page.totalPages", is(1))
            .body("page.number", is(0))
            .body("_links.first", nullValue())
            .body("_links.prev", nullValue())
            .body("_links.next", nullValue())
            .body("_links.last", nullValue())
            .body("_links.self.href", endsWith("?author=Amos%20Bronson%20Alcott&page=0&size=20"))
            .body("_embedded.quotes", hasSize(1))
            .body("_embedded.quotes[0]._id", is("5eb17aadb69dc744b4e70d6e"))
            .body("_embedded.quotes[0].quote", is("While one finds company in himself and his pursuits, he cannot feel old, no matter what his years may be."))
            .body("_embedded.quotes[0].author", is("Amos Bronson Alcott"))
            .body("_embedded.quotes[0].genre", is("age"))
            .body("_embedded.quotes[0].__v", is(0))
            .body("_embedded.quotes[0]._links.self.href", endsWith("/quote/5eb17aadb69dc744b4e70d6e"));
    }

    @Test
    void Given_NonexistingAuthor_When_getAllWithAuthor_Then_EmptyPage() {
        given()
            .contentType(ContentType.JSON)
            .queryParam("author", "Luís Vaz de Camões")
        .when()
            .get("/quote")
        .then()
            .statusCode(200)
            .body("page.size", is(20))
            .body("page.totalElements", is(0))
            .body("page.totalPages", is(0))
            .body("page.number", is(0))
            .body("_links.first", nullValue())
            .body("_links.prev", nullValue())
            .body("_links.next", nullValue())
            .body("_links.last", nullValue())
            .body("_links.self.href", endsWith("?author=Lu%C3%ADs%20Vaz%20de%20Cam%C3%B5es&page=0&size=20"))
            .body("_embedded.quotes", hasSize(0));
    }

    @Test
    void Given_ExistingId_When_getQuote_Then_Quote(){
        given()
            .contentType(ContentType.JSON)
            .pathParam("id", "5eb17aadb69dc744b4e70d6e")
        .when()
            .get("/quote/{id}")
        .then()
            .statusCode(200)
            .body("_id", is("5eb17aadb69dc744b4e70d6e"))
            .body("quote", is("While one finds company in himself and his pursuits, he cannot feel old, no matter what his years may be."))
            .body("author", is("Amos Bronson Alcott"))
            .body("genre", is("age"))
            .body("__v", is(0))
            .body("_links.self.href", endsWith("/quote/5eb17aadb69dc744b4e70d6e"));
    }

    @Test
    void Given_NonexistingId_When_getQuote_Then_NotFound(){
        given()
            .contentType(ContentType.JSON)
            .pathParam("id", "nonexistingid")
        .when()
            .get("/quote/{id}")
        .then()
            .statusCode(404);
    }
}
