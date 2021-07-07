package leiloesTeste;


import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import pages.FormularioLeilaoPage;
import pages.LeiloesPage;
import pages.LoginPage;

public class LeiloesTeste {
	private LeiloesPage leiloesPage; 
	FormularioLeilaoPage paginaDeFormulario;
	
	@BeforeEach
	public void setup() {
		LoginPage fazendoLogin = new LoginPage();
		fazendoLogin.preenchendoUsuarioESenha("fulano", "pass");
		this.leiloesPage = fazendoLogin.efetuarLogin();
		
	}
	
	@AfterEach
	public void saindoDoDriver() {
		this.leiloesPage.fechar();
	}
	
	
	@Test
	public void deveriaCadastrarLeilao() {
		
		assertTrue(leiloesPage.verificandoPaginaInicial());
		String dataDoDiaAtual = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		String nomeLeilao = "Leiloeiro do dia: " + dataDoDiaAtual ;
		String valor = "600.00";
		
		if(leiloesPage.verificandoInformacoesRepetidas(nomeLeilao, valor, dataDoDiaAtual)) {
			assertThrows(AssertionFailedError.class, () ->  fail("Informacoes repetidas na tabela!"));
			System.out.println("Valores de coluna repetidos!");
			
		}else {
			this.paginaDeFormulario = leiloesPage.carregandoFormulario();
			
			this.leiloesPage = paginaDeFormulario.preencherFormularioLeilao(nomeLeilao, valor, dataDoDiaAtual );
			
			assertTrue(leiloesPage.isLeilaoCadastradoContem(nomeLeilao, valor, dataDoDiaAtual ));
			
		}
	}
	
	
	@Test
	public void verificandoFormulario() {
		this.paginaDeFormulario = leiloesPage.carregandoFormulario();
		this.leiloesPage = paginaDeFormulario.preencherFormularioLeilao("", "", "");
		assertTrue(this.paginaDeFormulario.isPaginaAtualEIgual());
		assertTrue(this.paginaDeFormulario.isMensagemDeErrorApareceuNaTela());
	}
	
	
		
	
}
	
