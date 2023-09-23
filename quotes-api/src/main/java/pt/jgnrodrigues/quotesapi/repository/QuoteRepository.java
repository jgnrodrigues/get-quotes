package pt.jgnrodrigues.quotesapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import pt.jgnrodrigues.quotesapi.data.Quote;

public interface QuoteRepository extends MongoRepository<Quote, String>{
    
    Page<Quote> findAllByAuthor(Pageable pageable);
}
