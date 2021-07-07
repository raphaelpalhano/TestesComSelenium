package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class LoginPage extends PageObjectBase{
	private static final String HTTP_LOCALHOST_8090_LOGIN = "http://localhost:8090/login";
	private static final String URLGERAL = "http://localhost:8090";
	
	
	
	 public LoginPage() {
		 super(null);
		 this.driver.navigate().to(HTTP_LOCALHOST_8090_LOGIN);
	 }
	 
	 
	 
	 public void preenchendoUsuarioESenha(String usuario, String senha) {
		 driver.findElement(By.xpath("//input[@placeholder='usuário']")).sendKeys(usuario);
		 driver.findElement(By.xpath("//input[@placeholder='senha']")).sendKeys(senha);
		 
	 }
	 
	 public LeiloesPage efetuarLogin() {
		 driver.findElement(By.xpath("//button[@class='btn btn-primary']")).submit();
		return new LeiloesPage(driver);
	 }

	 	
	
	public boolean verificadorDePagina(String path) {
		return driver.getCurrentUrl().equals(URLGERAL + path);
	}


	public String validandoNomeDeUsuarioLogado() {
		try {
			return driver.findElement(By.xpath("//span[@class='font-italic']")).getText();
		}catch(NoSuchElementException e) {
			return null;
		}
		
	}
	
	
	public void navegaParaPaginaDeLeiloes() {
		driver.navigate().to("http://localhost:8090/leiloes/2");
	}
	
	public boolean contemTexto(String texto) {
		return driver.getPageSource().contains(texto);
	}
}
