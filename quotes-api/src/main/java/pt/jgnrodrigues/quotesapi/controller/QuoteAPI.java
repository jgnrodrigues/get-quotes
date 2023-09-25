package pt.jgnrodrigues.quotesapi.controller;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import pt.jgnrodrigues.quotesapi.dto.QuoteDTO;

@RestController
@RequestMapping("/quote")
@Tag(name = "Quotes", description = "Quotes endpoints")
public interface QuoteAPI {
    
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Gets all quotes. It is also possible to filter by author.", responses = {
            @ApiResponse(responseCode = "200", description = "Page of Quotes")
        })
    ResponseEntity<PagedModel<QuoteDTO>> getAll(@Parameter(name = "author", description = "Author name", in = ParameterIn.QUERY) @RequestParam(required = false) String author, @ParameterObject Pageable pageable);

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Gets th Quote with the specific ID", responses = {
            @ApiResponse(responseCode = "200", description = "Quote"),
            @ApiResponse(responseCode = "404", description = "The quote with the requested ID does not exist.", content = @Content)
        })
    ResponseEntity<QuoteDTO> getQuote(@Parameter(name = "id", description = "Quote's ID", in = ParameterIn.PATH) @PathVariable String id);

}
