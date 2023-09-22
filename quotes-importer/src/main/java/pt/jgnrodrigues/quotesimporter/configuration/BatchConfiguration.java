package pt.jgnrodrigues.quotesimporter.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder;
import org.springframework.batch.item.database.AbstractPagingItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.transaction.PlatformTransactionManager;

import pt.jgnrodrigues.quotesimporter.client.QuoteClient;
import pt.jgnrodrigues.quotesimporter.configuration.properties.BatchProperties;
import pt.jgnrodrigues.quotesimporter.data.Quote;
import pt.jgnrodrigues.quotesimporter.dto.QuoteDTO;
import pt.jgnrodrigues.quotesimporter.mapper.QuoteMapper;
import pt.jgnrodrigues.quotesimporter.processor.QuoteProcessor;
import pt.jgnrodrigues.quotesimporter.reader.QuotePageReader;

@Configuration
public class BatchConfiguration {

    private final BatchProperties properties;

    public BatchConfiguration(BatchProperties properties) {
        this.properties = properties;
    }
    
    @Bean
    Job quoteJob(JobRepository jobRepository, 
                 Step step) {
        return new JobBuilder("quoteJob", jobRepository)
                    .flow(step)
                    .end()
                    .build();
    }

    @Bean
    Step quoteStep(JobRepository jobRepository,
                   PlatformTransactionManager transactionManager,
                   AbstractPagingItemReader<QuoteDTO> reader,
                   ItemProcessor<QuoteDTO, Quote> processor,
                   MongoItemWriter<Quote> writer) {
        return new StepBuilder("quoteStep", jobRepository)
                    .<QuoteDTO, Quote>chunk(properties.size(), transactionManager)
                    .reader(reader)
                    .processor(processor)
                    .writer(writer)
                    .build();
    }

    @Bean
    AbstractPagingItemReader<QuoteDTO> reader(QuoteClient client){
        var reader = new QuotePageReader(client);
        reader.setPageSize(properties.size());
        reader.setMaxItemCount(properties.maxCount());
        return reader;
    }

    @Bean
    ItemProcessor<QuoteDTO, Quote> processor(QuoteMapper mapper) {
        return new QuoteProcessor(mapper);
    }

    @Bean 
    MongoItemWriter<Quote> writer(MongoTemplate template) {
        return new MongoItemWriterBuilder<Quote>()
            .template(template)
            .collection(properties.collection())
            .build();
    }

    
}
