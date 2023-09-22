package pt.jgnrodrigues.quotesimporter.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@ConfigurationProperties(prefix = "batch")

public record BatchProperties (
    Integer pageSize,
    String collection,
    Integer maxCount
){
    @ConstructorBinding
    public BatchProperties(Integer pageSize, String collection, Integer maxCount) {
        this.collection = collection;
        this.maxCount = maxCount;
        this.pageSize = pageSize;
    }
}
