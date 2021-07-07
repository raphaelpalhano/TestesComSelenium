package pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class LeiloesPage extends PageObjectBase {
	private static final String URL_GERAL = "http://localhost:8090/";
	

	public LeiloesPage(WebDriver driver) {
		super(driver);
		
	}

	

	public boolean verificandoPaginaInicial() {
		return driver.getPageSource().contains("Leilões cadastrados");
		
	}
	
	public FormularioLeilaoPage carregandoFormulario() {
		this.driver.navigate().to(URL_GERAL + "leiloes/new");
		return new FormularioLeilaoPage(driver);
	}

	public boolean isLeilaoCadastradoContem(String nomeLeilao, String valor, String data) {
		WebElement ultimaLinhaDaTabela = this.driver.findElement(By.cssSelector("#tabela-leiloes tbody tr:last-child"));
		WebElement colunaNome = ultimaLinhaDaTabela.findElement(By.cssSelector("td:nth-child(1)"));
		WebElement colunaDataAbertura = ultimaLinhaDaTabela.findElement(By.cssSelector("td:nth-child(2)"));
		WebElement colunaValorInicial = ultimaLinhaDaTabela.findElement(By.cssSelector("td:nth-child(3)"));
	
		return colunaNome.getText().equals(nomeLeilao) 
				&& colunaDataAbertura.getText().equals(data) 
				&& colunaValorInicial.getText().equals(valor);
		
	}
	


	public LanceleilaoPage acessandoPaginaDarLance( String valor) {
		driver.navigate().to(URL_GERAL + "leiloes" + valor);
		return new LanceleilaoPage(driver);
	}
	

	

}
 