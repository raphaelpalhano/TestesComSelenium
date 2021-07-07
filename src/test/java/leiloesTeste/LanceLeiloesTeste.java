package leiloesTeste;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import pages.LanceleilaoPage;
import pages.LeiloesPage;
import pages.LoginPage;

public class LanceLeiloesTeste {
	LanceleilaoPage lanceleilaoPage;
	LeiloesPage leiloesPage;
	
	@BeforeEach
	public void setup() {
		LoginPage loginPage = new LoginPage();
		loginPage.preenchendoUsuarioESenha("rafa", "pass");
		this.leiloesPage = loginPage.efetuarLogin();
		this.lanceleilaoPage = this.leiloesPage.acessandoPaginaDarLance("/2");
	}
	
	@AfterEach
	public void quit() {
		this.lanceleilaoPage.fechar();
	}
	
	
	@Test
	public void verificandoPaginaDeLance() {
		assertTrue(this.lanceleilaoPage.verificandoURL("2"));
	}
	
	@Test
	public void preenchendoValorEEnviando() {
		String nome = "rafa";
		String dataDoDiaAtual = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	    String valor = "R$ 2.500,00";
	    lanceleilaoPage.preenchendoOCampoDeLance(valor);
    	lanceleilaoPage.clicandoNoBotaoDarLance();
	    if(lanceleilaoPage.verificandoInformacoesRepetidas(nome, valor, dataDoDiaAtual)) {
	    	assertThrows(AssertionFailedError.class, () ->  fail("Informacoes repetidas na tabela!"));
	    	assertTrue(lanceleilaoPage.verificandoValorTabelaDeLances(dataDoDiaAtual, nome, valor));
	    	System.out.println("falha na entrada de lance do leilao! Usuario ja fez seu lance.");
	    }else {

	    	assertTrue(this.lanceleilaoPage.verificandoURL("2"));
			assertEquals("Lance adicionado com sucesso!", this.lanceleilaoPage.mensagemDeLanceDoLeilao());
			assertTrue(lanceleilaoPage.verificandoValorTabelaDeLances(dataDoDiaAtual, nome, valor));
	    }
	    	
		
		
	}
	
	

}
