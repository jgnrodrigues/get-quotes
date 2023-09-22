package pt.jgnrodrigues.quotesimporter.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import pt.jgnrodrigues.quotesimporter.data.Quote;
import pt.jgnrodrigues.quotesimporter.dto.QuoteDTO;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface QuoteMapper {
    
    @Mapping(target = "quote", source = "quoteText")
    @Mapping(target = "author", source = "quoteAuthor")
    @Mapping(target = "genre", source = "quoteGenre")
    Quote toEntity(QuoteDTO dto);
}
