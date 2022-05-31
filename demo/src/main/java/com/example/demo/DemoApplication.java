package com.example.demo;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dao.CurrencyDao;
import com.example.demo.entity.Currency;

@SpringBootApplication
public class DemoApplication {

	private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
//		getEmployees();

	}

	private static void getEmployees() {
		final String uri = "https://api.coindesk.com/v1/bpi/currentprice.json";

		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(uri, String.class);

		System.out.println(result);
	}

	@Bean
	public CommandLineRunner demo(CurrencyDao dao) {
		return (args) -> {
			// save a few Currencys
			
			dao.save(new Currency("USD", "美金"));
			dao.save(new Currency("GBP", "英鎊"));
			dao.save(new Currency("EUR", "歐元"));
			
			// fetch all Currencys
			log.info("Currencys found with findAll():");
			log.info("-------------------------------");
			for (Currency Currency : dao.findAll()) {
				log.info(Currency.toString());
			}
			log.info("");

			// fetch an individual Currency by ID
			Optional<Currency> Currency = dao.findById("USD");
			log.info("Currency found with findById(USD):");
			log.info("--------------------------------");
			log.info(Currency.toString());
			log.info("");

		};
	}
}
