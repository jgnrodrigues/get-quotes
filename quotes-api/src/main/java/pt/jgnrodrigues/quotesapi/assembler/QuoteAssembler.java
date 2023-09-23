package pt.jgnrodrigues.quotesapi.assembler;

import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import pt.jgnrodrigues.quotesapi.controller.QuoteAPI;
import pt.jgnrodrigues.quotesapi.data.Quote;
import pt.jgnrodrigues.quotesapi.dto.QuoteDTO;
import pt.jgnrodrigues.quotesapi.mapper.QuoteMapper;

@Component
@RequiredArgsConstructor
public class QuoteAssembler implements RepresentationModelAssembler<Quote, QuoteDTO>{
    private final QuoteMapper quoteMapper;

    @Override
    public QuoteDTO toModel(Quote entity) {
        QuoteDTO model = quoteMapper.toDto(entity);

        model.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(QuoteAPI.class).getQuote(model.get_id())).withSelfRel());

        return model;
    }
    
}
