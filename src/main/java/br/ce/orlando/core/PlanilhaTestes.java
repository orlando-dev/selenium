package br.ce.orlando.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class PlanilhaTestes {
    
    @SuppressWarnings("resource")
	public static void escreverResultadoTeste(String nomePlanilha, String nomeClasseTeste, boolean passou) throws IOException {
        // Carrega o arquivo de planilha a partir da pasta target
        File file = new File("src/main/resources/" + nomePlanilha);

        // Cria um objeto FileInputStream para ler a planilha
        FileInputStream inputStream = new FileInputStream(file);
        
        // Cria um objeto XSSFWorkbook para representar a planilha
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        
        // Seleciona a primeira aba da planilha
        XSSFSheet sheet = workbook.getSheetAt(0);
        
        // Procura a coluna "NOME DO TESTE AUTOMATIZADO" para encontrar a linha do teste
        int nomeColuna = -1;
        for (Cell celula : sheet.getRow(0)) {
            if (celula.getStringCellValue().equals("NOME DO TESTE AUTOMATIZADO")) {
                nomeColuna = celula.getColumnIndex();
                break;
            }
        }
        
        if (nomeColuna == -1) {
            throw new IllegalArgumentException("Coluna 'NOME DO TESTE AUTOMATIZADO' não encontrada na planilha.");
        }
        
        // Procura a coluna "RESULTADO OBTIDO" para atualizar o resultado do teste
        int resultadoColuna = -1;
        for (Cell celula : sheet.getRow(0)) {
            if (celula.getStringCellValue().equals("RESULTADO OBTIDO")) {
                resultadoColuna = celula.getColumnIndex();
                break;
            }
        }
        
        if (resultadoColuna == -1) {
            throw new IllegalArgumentException("Coluna 'RESULTADO OBTIDO' não encontrada na planilha.");
        }
        
        // Procura o nome da classe de teste na coluna "NOME DO TESTE AUTOMATIZADO"
        for (Row linha : sheet) {
            Cell cell = linha.getCell(nomeColuna);
            if (cell != null && cell.getStringCellValue().equals(nomeClasseTeste)) {
                // Atualiza o resultado na coluna "RESULTADO OBTIDO"
                XSSFCell resultCell = (XSSFCell) linha.getCell(resultadoColuna, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                resultCell.setCellValue(passou ? "OK" : "ERRO");
                break;
            }
        }
        
        // Fecha o arquivo de entrada
        inputStream.close();
        
        // Cria um objeto FileOutputStream para gravar a planilha atualizada
        FileOutputStream outputStream = new FileOutputStream(file);
        workbook.write(outputStream);
        outputStream.close();
        
        // Fecha o objeto XSSFWorkbook
        workbook.close();
    }
}
