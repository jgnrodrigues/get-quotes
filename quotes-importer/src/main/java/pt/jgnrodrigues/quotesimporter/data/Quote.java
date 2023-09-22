package pt.jgnrodrigues.quotesimporter.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Document(collection = "quotes")
public class Quote {

    @Id
    private String _id;
    private String quote;

    @Indexed(unique = false)
    private String author;
    
    private String genre;
    private Long __v;
}
