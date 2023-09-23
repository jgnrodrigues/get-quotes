package pt.jgnrodrigues.quotesapi.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import pt.jgnrodrigues.quotesapi.dto.QuoteDTO;
import pt.jgnrodrigues.quotesapi.service.QuoteHateoasService;

@RestController
@RequiredArgsConstructor
public class QuoteController implements QuoteAPI{
    private final QuoteHateoasService quoteService;

    @Override
    public ResponseEntity<PagedModel<QuoteDTO>> getAll(String author, Pageable pageable) {
        if (StringUtils.isBlank(author)){
            return ResponseEntity.ok(quoteService.getAll(pageable));
        }

        return ResponseEntity.ok(quoteService.getAllByAuthor(author, pageable));
    }

    @Override
    public ResponseEntity<QuoteDTO> getQuote(String id) {
        return quoteService.getQuote(id)
            .map(quote -> ResponseEntity.ok(quote))
            .orElse(ResponseEntity.notFound().build());
    }
    
}
