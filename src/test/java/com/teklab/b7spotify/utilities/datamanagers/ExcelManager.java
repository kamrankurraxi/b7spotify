package com.teklab.b7spotify.utilities.datamanagers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelManager {              //parse means which page u want to read
	public ArrayList<Map<String, String>> parse(int i) {// i is the natural sheet number staring from1
		try {
			FileInputStream file = new FileInputStream(new File("src/test/resources/testData/tekLabData.xlsx"));
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			// get first/desired sheet, we did a trick to make the sheet at i-1
			XSSFSheet sheet = workbook.getSheetAt(i - 1);
			// create an rowItet to iterate all the rows in this sheet ,skipping empty rows
			Iterator<Row> rowIter = sheet.rowIterator();
			// create the Header Map
			List<String> headers = new ArrayList<String>();
			// we only did the if once , so we could get the header row content
			if (rowIter.hasNext()) {
				// store the current row element to 'row'. Right now it is header ro
				Row row = rowIter.next();
				// put cell values in to 'headers'container
				for (Cell cell : row) {
					headers.add(cell.getStringCellValue());
					// headers.add(new DataFormatter().formatCellValue(cell));	

				}
			}
			// this is a content container
			ArrayList<Map<String, String>> contents = new ArrayList<Map<String, String>>();
			// the cursor already move to content row when we star the whilp loop,
			// so we are storing all content row with header as a pair
			while (rowIter.hasNext()) {
				// store the current row element to 'row'
				Row row = rowIter.next();
				Map<String, String> values = new HashMap<String, String>();
				for (Cell cell : row) {
					// TODO safeguard for header resolving, cell column index
					// might be out of bound of header array
					// cell.setCellType(Cell.CELL_TYPE_STRING);
					values.put(headers.get(cell.getColumnIndex()), cell.getStringCellValue());
					// values.put(headers.get(cell.getColumIndex()) , new
					// Dataformatter.formatCellValue(cell));

				}
				contents.add(values);
			}

			file.close();
			workbook.close();
			return contents;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public ArrayList<Map<String, String>> getdata(ArrayList<Map<String, String>> contents, String column,
			String value) {
		ArrayList<Map<String, String>> result = new ArrayList<Map<String, String>>();
		for (Map<String, String> element : contents) {
			// find the row taht is our target
			if (element.get(column).equals(value)) {// map.get("key")--->get the value
				// once we found it , we put it in the result map
				result.add(element);

			}
		}
		return result;
	}
}
