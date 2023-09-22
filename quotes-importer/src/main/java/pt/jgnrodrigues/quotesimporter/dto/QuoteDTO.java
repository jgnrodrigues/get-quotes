package pt.jgnrodrigues.quotesimporter.dto;

public record QuoteDTO(
    String _id,
    String quoteText,
    String quoteAuthor,
    String quoteGenre,
    Integer __v
) {}
