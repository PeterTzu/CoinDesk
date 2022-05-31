package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CurrencyDao;
import com.example.demo.entity.Currency;


@Service
public class CurrencyService {

	@Autowired
	CurrencyDao currencyDao;

	public List<Currency> findAll() {
		var it = currencyDao.findAll();

        var currencys = new ArrayList<Currency>();
        
        it.forEach(e -> currencys.add(e));

        return currencys;
	}

	public Currency findById(String code) {
		return currencyDao.findById(code).get();
	}
	
	public Currency save(Currency currency) {
		return currencyDao.save(currency);
	}

	public void delete(String code) {
		currencyDao.deleteById(code);
	}
	
}
