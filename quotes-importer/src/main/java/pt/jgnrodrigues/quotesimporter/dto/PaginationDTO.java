package pt.jgnrodrigues.quotesimporter.dto;

public record PaginationDTO (
    Long currentPage,
    Long nextPage,
    Long totalPages
){}

