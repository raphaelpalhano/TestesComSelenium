package pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PageObjectBase {
	protected WebDriver driver;
	
	public PageObjectBase(WebDriver driver){
		 System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		 
		 if(driver == null) {
			 this.driver = new ChromeDriver();
		 }else {
			 this.driver = driver;
		 }
		 this.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS).pageLoadTimeout(10, TimeUnit.SECONDS);
		 
		 
	}
	
	public void fechar() {
		 driver.quit();
	}
	
	public boolean verificandoInformacoesRepetidas(String nome, String valor, String data) {
		return driver.getPageSource().contains(nome) && driver.getPageSource().contains(valor) && driver.getPageSource().contains(data);	
		
		
	}
	
	
}
