package pt.jgnrodrigues.quotesimporter.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pt.jgnrodrigues.quotesimporter.data.Quote;
import pt.jgnrodrigues.quotesimporter.dto.QuoteDTO;
import pt.jgnrodrigues.quotesimporter.mapper.QuoteMapper;

@RequiredArgsConstructor
@Slf4j
public class QuoteProcessor implements ItemProcessor<QuoteDTO, Quote>{

    private final QuoteMapper quoteMapper;

    @Override
    public Quote process(@NonNull QuoteDTO item) throws Exception {
        Quote quote = quoteMapper.toEntity(item);
        log.info("Processed quote: {}", quote);
        return quote;
    }    
}
