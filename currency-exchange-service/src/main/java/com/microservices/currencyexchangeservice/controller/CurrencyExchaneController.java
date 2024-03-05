package com.microservices.currencyexchangeservice.controller;


import com.microservices.currencyexchangeservice.entity.CurrencyExchange;
import com.microservices.currencyexchangeservice.repository.CurrencyExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class CurrencyExchaneController {
    @Autowired
    private CurrencyExchangeRepository repository;
    @Autowired
    private Environment environment;
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange getExchageValue(
            @PathVariable String from,
            @PathVariable String to
    ){
        // CurrencyExchange currencyExchange = new CurrencyExchange(112212L,from,to, BigDecimal.valueOf(51));
        CurrencyExchange currencyExchange = repository.findByFromAndTo(from,to);
        if (currencyExchange == null){
            throw new RuntimeException("unable to find data") ;
        }
        String port = environment.getProperty("local.server.port");
        currencyExchange.setEnvironment(port);
        return currencyExchange;
    }
}
