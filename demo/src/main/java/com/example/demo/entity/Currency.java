package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Currency {

	

	@Id
	private String code;
	@Column
	private String name;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public Currency(String code, String name) {
		super();
		this.code = code;
		this.name = name;
	}
	
	public Currency() {
		super();
	}
	@Override
	public String toString() {
		return "Currency [code=" + code + ", name=" + name + "]";
	}
	
	
}
