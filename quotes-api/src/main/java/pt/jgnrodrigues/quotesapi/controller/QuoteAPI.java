package pt.jgnrodrigues.quotesapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import pt.jgnrodrigues.quotesapi.dto.QuoteDTO;

@RestController
@RequestMapping("/quote")
@Tag(name = "Quotes", description = "Quotes endpoints")
public interface QuoteAPI {
    
    @GetMapping()
    ResponseEntity<PagedModel<QuoteDTO>> getAll(@RequestParam(required = false) String author, Pageable pageable);

    @GetMapping(value = "/{id}")
    ResponseEntity<QuoteDTO> getQuote(@PathVariable String id);

}
