package pt.jgnrodrigues.quotesimporter.reader;

import org.springframework.batch.item.database.AbstractPagingItemReader;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pt.jgnrodrigues.quotesimporter.client.QuoteClient;
import pt.jgnrodrigues.quotesimporter.dto.QuoteDTO;
import pt.jgnrodrigues.quotesimporter.dto.ResponseDTO;

@RequiredArgsConstructor
@Slf4j
public class QuotePageReader extends AbstractPagingItemReader<QuoteDTO>{

    private final QuoteClient quoteClient;

    @Override
    protected void doReadPage() {
        int page = getPage() + 1;
        int limit = getPageSize();
        log.info("Reading page: {}, limit: {}", page, limit);
        ResponseDTO response = quoteClient.getQuotesPage(page + 1, limit);

        results = response.data();
    }
    
}
