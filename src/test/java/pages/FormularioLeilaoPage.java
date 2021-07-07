package pages;

import java.util.function.BooleanSupplier;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FormularioLeilaoPage extends PageObjectBase {
	
	private static final String URL_GERAL = "http://localhost:8090/";

	public FormularioLeilaoPage(WebDriver driver) {
		super(driver);
	}

	
	public LeiloesPage preencherFormularioLeilao(String nomeLeilao, String valor, String abertura) {
		driver.findElement(By.id("nome")).sendKeys(nomeLeilao);
		driver.findElement(By.id("valorInicial")).sendKeys(valor);
		driver.findElement(By.id("dataAbertura")).sendKeys(abertura);
		driver.findElement(By.id("button-submit")).submit();
		
		return new LeiloesPage(driver);
		
		
	}

	public boolean isPaginaAtualEIgual() {
		return driver.getCurrentUrl().equals(URL_GERAL + "leiloes");
	}

	public boolean isMensagemDeErrorApareceuNaTela() {
		String pageSource = driver.getPageSource();
		return pageSource.contains("não deve estar em branco") 
				&& pageSource.contains("minimo 3 caracteres") 
				&& pageSource.contains("deve ser um valor maior de 0.1")
				&& pageSource.contains("deve ser uma data no formato dd/MM/yyyy");
	}

	

}
