package br.ce.orlando.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

public class LendoXLSX {
	int B = 0;
	private static final String fileName = "C:/teste/teste.xlsx";

	@Test
	public void deveLer() {

		try {
			FileInputStream arquivo = new FileInputStream(new File(LendoXLSX.fileName));

			// cria um workbook = planilha toda com todas as abas
			@SuppressWarnings("resource")
			XSSFWorkbook workbook = new XSSFWorkbook(arquivo);

			// recuperamos apenas a primeira aba ou primeira planilha
			XSSFSheet sheet = workbook.getSheetAt(0);

			// retorna todas as linhas da planilha 0 (aba 1)
			Iterator<Row> rowIterator = sheet.iterator();

			// varre todas as linhas da planilha 0
			while (rowIterator.hasNext()) {
				// recebe cada linha da planilha
				Row row = rowIterator.next();

				// pegamos todas as celulas desta linha
				Iterator<Cell> cellIterator = row.iterator();

				// varremos todas as celulas da linha atual
				while (cellIterator.hasNext()) {

					// criamos uma celula
					Cell cell = cellIterator.next();

					switch (cell.getColumnIndex()) {

					case 0:
						@SuppressWarnings("unused") String numeroObtido = cell.getStringCellValue();
						System.out.println("TIPO STRING: " + cell.getStringCellValue());
						
						break;
					}

				}
			}

		} catch (FileNotFoundException ex) {
			Logger.getLogger(LendoXLSX.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(LendoXLSX.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
