package com.example.textExtracter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

@SpringBootApplication
public class TextExtracterApplication {

	public static void main(String[] args) {
		SpringApplication.run(TextExtracterApplication.class, args);
	}

}
