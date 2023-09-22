package pt.jgnrodrigues.quotesimporter.dto;

import java.util.List;

public record ResponseDTO (
    Integer statusCode,
    String message,
    PaginationDTO pagination,
    Long totalQuotes,
    List<QuoteDTO> data
) {}
