package com.example.demo.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.service.CurrencyService;

@RestController
public class CoinController {

	
	@Autowired
	private CurrencyService service;

	@RequestMapping("/")
    public String hello(){
        return "向全世界說聲Spring Boot 很高興認識你!";
    }
	
	@RequestMapping("/coindesk")
	public String getCoindesk() {

		return getCoindeskFromApi();
	}

	@RequestMapping("/info")
	public String getInfo() {
		// 幣別相關資訊（幣別，幣別中文名稱，以及匯率）。
		JSONObject coindeskJson = new JSONObject(getCoindeskFromApi());
		JSONObject USD = coindeskJson.getJSONObject("bpi").getJSONObject("USD");
		JSONObject GBP = coindeskJson.getJSONObject("bpi").getJSONObject("GBP");
		JSONObject EUR = coindeskJson.getJSONObject("bpi").getJSONObject("EUR");

		JSONArray list = new JSONArray();

		Map m = new HashMap();
		m.put("code", USD.getString("code"));
		m.put("name", service.findById(USD.getString("code")).getName());
		m.put("rate", USD.getString("rate"));

		list.put(new JSONObject(m));

		m = new HashMap();
		m.put("code", GBP.getString("code"));
		m.put("name", service.findById(GBP.getString("code")).getName());
		m.put("rate", GBP.getString("rate"));

		list.put(new JSONObject(m));

		m = new HashMap();
		m.put("code", EUR.getString("code"));
		m.put("name", service.findById(EUR.getString("code")).getName());
		m.put("rate", EUR.getString("rate"));

		list.put(new JSONObject(m));

		return list.toString();
	}

	@RequestMapping("/updatedTime")
	public String getUpdatedTime() {
		// 更新時間（時間格式範例：1990/01/01 00:00:00）
		JSONObject coindeskJson = new JSONObject(getCoindeskFromApi());
		LocalDateTime localDateTime = LocalDateTime
				.parse(coindeskJson.getJSONObject("time").getString("updatedISO").substring(0, 19));

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		String formatDateTime = localDateTime.format(formatter);

		return formatDateTime;
	}

	private String getCoindeskFromApi() {
		final String uri = "https://api.coindesk.com/v1/bpi/currentprice.json";

		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(uri, String.class);

		return result;
	}
}
