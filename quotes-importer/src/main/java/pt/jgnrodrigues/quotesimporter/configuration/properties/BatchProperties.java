package pt.jgnrodrigues.quotesimporter.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@ConfigurationProperties(prefix = "batch")

public record BatchProperties (
    Integer size,
    String collection,
    Integer maxCount
){
    @ConstructorBinding
    public BatchProperties(Integer size, String collection, Integer maxCount) {
        this.collection = collection;
        this.maxCount = maxCount;
        this.size = size;
    }
}
