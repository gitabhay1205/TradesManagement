package com.config.boot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.config.boot.dao.TradeRepository;
import com.config.boot.exception.InvalidTradeException;
import com.config.boot.model.Trade;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TradeService {

	private static final Logger log = LoggerFactory.getLogger(TradeService.class);

	@Autowired
	TradeRepository tradeRepository;

	public ResponseEntity<String> checkValidTrade(Trade trade) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		ResponseEntity<String> response = null;
		if (validateMaturityDate(trade)) {

			Optional<Trade> exsitingTrade = tradeRepository.findById(trade.getTradeId());
			if (exsitingTrade.isPresent()) {

				if (validateVersion(trade, exsitingTrade.get())) {

					response = new ResponseEntity<String>("{\"message\":\"Valid Trade, Accepted\"}", headers,
							HttpStatus.ACCEPTED);
				} else {
					throw new InvalidTradeException(trade.getTradeId()+" rejected as higher version for same trade id already exist");
				}
			} else {
				response = new ResponseEntity<String>("{\"message\":\"Valid Trade, Accepted\"}", headers,
						HttpStatus.ACCEPTED);
			}

		} else {
			throw new InvalidTradeException(trade.getTradeId()+"  rejected as it has maturity date less than current Date");
		}
		return response;
	}

	private boolean validateVersion(Trade trade, Trade oldTrade) {
		if (trade.getVersion() >= oldTrade.getVersion()) {
			return true;
		}
		return false;
	}

	// 2. Store should not allow the trade which has less maturity date then today date
	private boolean validateMaturityDate(Trade trade) {
		return trade.getMaturityDate().isBefore(LocalDate.now()) ? false : true;
	}

	public void persist(Trade trade) {
		trade.setCreatedDate(LocalDate.now());
		tradeRepository.save(trade);
	}

	public List<Trade> findAll() {
		return tradeRepository.findAll();
	}

	public void updateExpiryFlagOfTrade() {

		tradeRepository.findAll().stream().forEach(t -> {
			if (!validateMaturityDate(t)) {
				t.setExpiredFlag("Y");
				log.info("Trade which needs to updated {}", t);
				tradeRepository.save(t);
			}
		});
	}
}
