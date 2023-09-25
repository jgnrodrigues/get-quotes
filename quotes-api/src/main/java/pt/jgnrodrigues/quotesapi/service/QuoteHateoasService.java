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

        return createPagedModel(quotes);
    }

    public PagedModel<QuoteDTO> getAllByAuthor(String author, Pageable pageable) {
        Page<Quote> quotes = quoteService.getAllByAuthor(author, pageable);

        return createPagedModel(quotes);
    }

    public Optional<QuoteDTO> getQuote(String id) {
        return quoteService.getQuote(id)
                    .map(quote -> quoteAssembler.toModel(quote));
    }

    private PagedModel<QuoteDTO> createPagedModel(Page<Quote> page) {
        if (!page.hasContent()) {
            return (PagedModel<QuoteDTO>) pagedAssembler.toEmptyModel(page, QuoteDTO.class);
        }

        return pagedAssembler.toModel(page, quoteAssembler);
    }
}
