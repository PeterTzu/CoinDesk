package com.example.demo.dao;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.repository.CrudRepository;
import com.example.demo.entity.Currency;

@RestController
public interface CurrencyDao extends CrudRepository<Currency, String> {
}

