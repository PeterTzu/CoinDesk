package com.example.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.demo.entity.Currency;
import com.example.demo.service.CurrencyService;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class DemoApplicationTests {

	@Autowired
    private CurrencyService service;
	
	@Autowired
    private MockMvc mockMvc;
	
	@Test
	void contextLoads() {
		/*
		System.out.println("TEST Good");
		assertThat(service).isNotNull();
		
		
		service.save(new Currency("USD", "美金"));
		service.save(new Currency("GBP", "英鎊"));
		service.save(new Currency("EUR", "歐元"));
		
		//findAll
		System.out.println(service.findAll());
		
		//save
		service.save(new Currency("USD", "美金Test"));
		service.save(new Currency("TWD", "新台幣"));
		
		System.out.println(service.findById("USD"));
		
		//delete
		service.delete("GBP");
		
		System.out.println(service.findAll());
		
		
		int number1 = 5;
        int number2 = 10;
        int excepted = 12;

        // 2. Act
        int actual = number1 + number2;

        // 3. Assert
        assertThat(actual).isEqualTo(excepted);*/
	}
	
	
	
	@Test
	public void coin() throws Exception {
		
		service.save(new Currency("USD", "美金"));
		service.save(new Currency("GBP", "英鎊"));
		service.save(new Currency("EUR", "歐元"));
		
		mockMvc.perform(get("/"))
		.andDo(print())
        .andExpect(status().isOk());
		
		mockMvc.perform(get("/coindesk"))
		.andDo(print())
		.andExpect(status().isOk());
		
		mockMvc.perform(get("/updatedTime"))
		.andDo(print())
		.andExpect(status().isOk());
		
		mockMvc.perform(get("/info"))
		.andDo(print())
		.andExpect(status().isOk());
		
	}

	@Test
	public void testCreateProduct() throws Exception {

	    JSONObject request = new JSONObject()
	            .put("code", "QWE")
	            .put("name", "測試");
	    
	    mockMvc.perform(
	    		MockMvcRequestBuilders.post("/currencyApi").content(request.toString()).contentType(MediaType.APPLICATION_JSON_VALUE))
	    .andDo(print())
	    .andExpect(status().isOk());
	    
	    mockMvc.perform(get("/currencyApi"))
	    .andDo(print())
	    .andExpect(status().isOk());
	    
	    mockMvc.perform(delete("/currencyApi/USD"))
	    .andDo(print())
	    .andExpect(status().isOk());
	    
	    mockMvc.perform(get("/currencyApi"))
	    .andDo(print())
	    .andExpect(status().isOk());
	}
}
