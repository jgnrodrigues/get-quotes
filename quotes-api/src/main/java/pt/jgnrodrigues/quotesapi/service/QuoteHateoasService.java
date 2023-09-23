package pt.jgnrodrigues.quotesapi.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pt.jgnrodrigues.quotesapi.assembler.QuoteAssembler;
import pt.jgnrodrigues.quotesapi.data.Quote;
import pt.jgnrodrigues.quotesapi.dto.QuoteDTO;

@Service
@RequiredArgsConstructor
public class QuoteHateoasService {
    private final QuoteService quoteService;
    private final QuoteAssembler quoteAssembler;
    private final PagedResourcesAssembler<Quote> pagedAssembler;

    public PagedModel<QuoteDTO> getAll(Pageable pageable) {
        Page<Quote> quotes = quoteService.getAll(pageable);

        return pagedAssembler.toModel(quotes, quoteAssembler);
    }

    public PagedModel<QuoteDTO> getAllByAuthor(String author, Pageable pageable) {
        Page<Quote> quotes = quoteService.getAllByAuthor(author, pageable);

        return pagedAssembler.toModel(quotes, quoteAssembler);
    }

    public Optional<QuoteDTO> getQuote(String id) {
        return quoteService.getQuote(id)
                    .map(quote -> quoteAssembler.toModel(quote));
    }
}
