package com.practice.stock.stockservice.resource;

import com.practice.stock.stockservice.model.Stock;
import com.practice.stock.stockservice.model.StockQuote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/stock")
public class StockResource {

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("/{username}")
    public List<Quote> getStock(@PathVariable("username") final String username) throws IOException {
        ResponseEntity<List<String>> quoteResponse = restTemplate.exchange("http://db-service/rest/db/" + username
                , HttpMethod.GET, null, new ParameterizedTypeReference<List<String>>() {});

        List<String> quotes = quoteResponse.getBody();
         return quotes.stream().map(quote ->{
            Stock stock = getStockRate(quote);
            return new Quote(quote,stock.getPrice());
         }).collect(Collectors.toList());

    }

    private Stock getStockRate(String quote){

            return new Stock(new BigDecimal(100));

    }
}
