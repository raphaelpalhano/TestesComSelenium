package com.raphaelpalhano.github.leilao.login;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import pages.LoginPage;

public class LoginTest {
	LoginPage loginPage; 
	
	
	@BeforeEach
	public void setup() {
		loginPage = new LoginPage();
		
	}
	
	
	@AfterEach
	public void saindoDoDriver() {
		loginPage.fechar();
	}
	
	//VALIDANDO LOGIN
	@Test
	public void validandoOLoginDoUsuario() {
		loginPage.preenchendoUsuarioESenha("fulano", "pass");
		loginPage.efetuarLogin();
		assertTrue(loginPage.verificadorDePagina("/leiloes"));
		assertEquals("fulano", loginPage.validandoNomeDeUsuarioLogado());
	}
	
	
	
	//VALIDANDO CONDIÇÃO DE FALHA NO LOGIN
	@Test
	public void testandoUsuarioInvalidoLogin() {
		loginPage.preenchendoUsuarioESenha("bento", "coca321");
		loginPage.efetuarLogin();
		
		//validação
		assertTrue(loginPage.verificadorDePagina("/login?error"));
		assertTrue(loginPage.contemTexto( "Usuário e senha inválidos."));
		assertNull(loginPage.validandoNomeDeUsuarioLogado());
		
	}
	
	//VALIDANDO AUTORIZAÇÃO DO USUÁRIO
	
	@Test
	public void usuarioSemLoginAcessandoService() {
		loginPage.navegaParaPaginaDeLeiloes();
		
		assertTrue(loginPage.verificadorDePagina("/login"));
		assertFalse(loginPage.contemTexto("Dados do Leilão"));
	}

	


}
