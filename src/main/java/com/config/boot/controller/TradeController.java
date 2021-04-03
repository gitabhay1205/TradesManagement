package com.config.boot.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.config.boot.model.Trade;
import com.config.boot.service.TradeService;

@RestController
public class TradeController {
	
    @Autowired
    TradeService tradeService;
    
    private static final Logger log = LoggerFactory.getLogger(TradeController.class);
    /*
     */
    @PostMapping("/trade")
    public ResponseEntity<String> tradeValidateStore(@RequestBody Trade trade){
       
    	ResponseEntity<String> response= tradeService.checkValidTrade(trade);
    	
    	if(202 == response.getStatusCodeValue()) {
    		tradeService.persist(trade);
    	} else
    	{
    		TradeController.log.error("Invalid Trade data received in json");
    		return response;
    	}
    	return response;
    }

    @GetMapping("/trade")
    public List<Trade> findAllTrades(){
        return tradeService.findAll();
    }
}
