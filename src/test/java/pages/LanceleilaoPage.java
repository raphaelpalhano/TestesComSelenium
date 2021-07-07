package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LanceleilaoPage extends PageObjectBase{
	public static final String URL_LANCE = "http://localhost:8090/leiloes/";
	
	
	public LanceleilaoPage(WebDriver driver) {
		super(driver);
	}
	
	
	public boolean verificandoURL(String numeroDeLeilao) {
		return driver.getCurrentUrl().equals(URL_LANCE + numeroDeLeilao);
	}
	
	
	public LanceleilaoPage preenchendoOCampoDeLance(String valor) {
		driver.findElement(By.id("valor")).sendKeys(valor);
		return new LanceleilaoPage(driver);
		
	}
	
	public void clicandoNoBotaoDarLance() {
		driver.findElement(By.id("btnDarLance")).click();
	}
	
	
	public String mensagemDeLanceDoLeilao() {
		return driver.findElement(By.xpath("//span[normalize-space()='Lance adicionado com sucesso!']")).getText();
	}
	
	public boolean mensagemdeFalhaDeLance() {
		return driver.getPageSource().contains("Lance invalido!");
	}
	
	public String pegandoNomeDoUsuario() {
		return driver.findElement(By.xpath("//span[@class='font-italic']")).getText();
	}
	
	
	public boolean verificandoValorTabelaDeLances(String dataDoLance, String nomeUsuario, String valor) {
		WebElement ultimaLinhaTabelaLances = this.driver.findElement(By.cssSelector("#lancesDados tbody tr:last-child"));
		WebElement colunaData = ultimaLinhaTabelaLances.findElement(By.cssSelector("td:nth-child(1)"));
		WebElement colunaUsuario = ultimaLinhaTabelaLances.findElement(By.cssSelector("td:nth-child(2)"));
		WebElement colunaValor = ultimaLinhaTabelaLances.findElement(By.cssSelector("td:nth-child(3)"));
				
		return colunaData.getText().equals(dataDoLance) && colunaUsuario.getText().equals(nomeUsuario) && colunaValor.getText().equals(valor);
				
	}
	
	
	

}
