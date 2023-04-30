package br.ce.orlando.test;

import java.io.IOException;

import org.junit.Test;

import br.ce.orlando.core.BaseTest;
import br.ce.orlando.core.PlanilhaTestes;
import br.ce.orlando.page.FilePO;

public class UploadFileTest extends BaseTest {
	
	FilePO filePO = new FilePO();
	
	@Test
	public void deveRealizarUploadFileTest() {
		filePO.acessarTelaInicial();
		filePO.sendUploadFiles();
		
		try {
			PlanilhaTestes.escreverResultadoTeste("ProjetoSI - Casos de teste - Funcionais Automatizados - E2E.xlsx", "UploadFileTest.java", false);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
}
