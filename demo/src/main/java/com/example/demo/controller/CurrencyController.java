package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Currency;
import com.example.demo.service.CurrencyService;

@RestController
@RequestMapping("/currencyApi")
public class CurrencyController {

	@Autowired
    private CurrencyService service;
	
	@RequestMapping("")
    public List<Currency> findAll(){
        return service.findAll();
    }
	
	@RequestMapping(value="/{code}")
	 public Currency read(@PathVariable String code) {
		return service.findById(code);
	 }
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	 public Currency create(@RequestBody Currency Currency) {
		return service.save(Currency);
	 }
	
	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	 public Currency update(@RequestBody Currency Currency) {
		return service.save(Currency);
	 }	
	
	@RequestMapping(value = "/{code}", method = RequestMethod.DELETE)
	 public void delete(@PathVariable String code) {
		service.delete(code); 
	 }	
	
	
}
