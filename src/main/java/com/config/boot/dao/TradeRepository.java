package com.config.boot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.config.boot.model.Trade;

@Repository
public interface TradeRepository extends JpaRepository<Trade,String> {
}
