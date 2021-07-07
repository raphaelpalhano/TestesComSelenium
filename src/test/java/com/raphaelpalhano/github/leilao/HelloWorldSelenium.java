package com.raphaelpalhano.github.leilao;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class HelloWorldSelenium {
	static WebDriver driver;
	
	@BeforeEach
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver(); 
	}
	
	@Test
	public void helloWorld() {
		driver.navigate().to("http://localhost:8090/");
	}
	
	@AfterAll
	public static void quit() {
		driver.quit();
	}

}
