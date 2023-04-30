package br.ce.orlando.page;

import static br.ce.orlando.core.DriverFactory.getDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import br.ce.orlando.core.BasePage;

public class FilePO extends BasePage {
	
		public void acessarTelaInicial() {
			getDriver().get("https://the-internet.herokuapp.com/upload");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		public void sendUploadFiles() {
			WebElement inputFile = getDriver().findElement(By.id("file-upload"));
			String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\Geralt.png";
			inputFile.sendKeys(filePath);
		}

		/**
		 * Você pode específicar um arquivo desejado para realizar upload do arquivo,
		 * partindo do diretório do projeto.
		 * 
		 * @param arquivoEspecifico localização do arquivo
		 */
		public void sendUploadFiles(String arquivoEspecifico) {
			WebElement inputFile = getDriver().findElement(By.id("file-upload"));
			String filePath = System.getProperty("user.dir") + arquivoEspecifico;
			inputFile.sendKeys(filePath);
		}
		
	}

