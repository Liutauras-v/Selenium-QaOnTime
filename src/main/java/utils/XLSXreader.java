package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLSXreader {

	private Map<String, List<String>> map = new HashMap<String, List<String>>();

	public XLSXreader(String filePath) {
		File myFile = new File(filePath);
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(myFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		XSSFWorkbook myWorkBook = null;
		try {
			myWorkBook = new XSSFWorkbook(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}

		XSSFSheet mySheet = myWorkBook.getSheetAt(0);

		Iterator<Row> rowIterator = mySheet.iterator();
		boolean isHead = true;
		List<String> headRow = null;
		List<String> rowList = null;
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			rowList = new ArrayList<String>();

			Iterator<Cell> cellIterator = row.cellIterator();

			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				rowList.add(cell.getStringCellValue());
			}
			if (isHead) {
				for (int i = 0; i < rowList.size(); i++) {
					map.put(rowList.get(i), new ArrayList<String>());
				}
				headRow = rowList;
				isHead = false;
			} else {

				for (int i = 0; i < headRow.size(); i++) {
					List<String> tempr = map.get(headRow.get(i));
					tempr.add(rowList.get(i));
					map.put(headRow.get(i), tempr);
				}

			}
		}

	}

	public int keySize() {
		return map.size();
	}

	public int size() {
		int count = 0;
		for (Entry<String, List<String>> entry : map.entrySet()) {
			List<String> v = entry.getValue();
			count = v.size();
		}
		return count;
	}

	public boolean containsKey(String key) {
		return map.containsKey(key);
	}

	public boolean containsValue(String value) {

		for (Entry<String, List<String>> entry : map.entrySet()) {
			String k = entry.getKey();
			if(map.get(k).contains(value)) {
				return true;
			}
		}

		return false;
	}

	public boolean containsValueByKey(String value, String key) {

		try {
			if (map.get(key).contains(value)) {
				return true;
			}
		} catch (NullPointerException e) {
			return false;
		}
		return false;
	}

	@Override
	public String toString() {
		String sb = "";
		for (Entry<String, List<String>> entry : map.entrySet()) {
			String k = entry.getKey();
			List<String> v = entry.getValue();
			sb += "{" + k + ":" + v + "}, ";
		}
		if (sb != null && sb.length() > 0) {
			sb = sb.substring(0, sb.length() - 2);
		}
		return sb;
	}

	public String getItem(String key, int index) {
		return map.get(key).get(index);
	}
}
