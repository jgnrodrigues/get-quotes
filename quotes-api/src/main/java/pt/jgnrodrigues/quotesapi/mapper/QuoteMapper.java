package pt.jgnrodrigues.quotesapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import pt.jgnrodrigues.quotesapi.data.Quote;
import pt.jgnrodrigues.quotesapi.dto.QuoteDTO;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface QuoteMapper {
    QuoteDTO toDto(Quote entity);
}
