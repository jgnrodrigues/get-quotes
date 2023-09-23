package pt.jgnrodrigues.quotesapi.dto;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "quotes")
@Getter
@Setter
public class QuoteDTO extends RepresentationModel<QuoteDTO>{
    @JsonProperty("_id")
    private String _id;
    private String quote;
    private String author;    
    private String genre;
    @JsonProperty("__v")
    private Long __v;    
}
