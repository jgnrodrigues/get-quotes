package pt.jgnrodrigues.quotesapi.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pt.jgnrodrigues.quotesapi.data.Quote;
import pt.jgnrodrigues.quotesapi.repository.QuoteRepository;

@Service
@RequiredArgsConstructor
public class QuoteService {
    private final QuoteRepository repository;

    public Page<Quote> getAll(Pageable pageable){
        return repository.findAll(pageable);
    }

    public Page<Quote> getAllByAuthor(String author, Pageable pageable){
        return repository.findAllByAuthor(author, pageable);
    }

    public Optional<Quote> getQuote(String id){
        return repository.findById(id);
    }
}
