package pt.jgnrodrigues.quotesimporter.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pt.jgnrodrigues.quotesimporter.dto.ResponseDTO;

@FeignClient(name = "quotes", url = "${quotes-client.url}")
public interface QuoteClient {

    @GetMapping(value = "/quotes", consumes = "application/json")
    ResponseDTO getQuotesPage(@RequestParam long page, @RequestParam long limit);
}
