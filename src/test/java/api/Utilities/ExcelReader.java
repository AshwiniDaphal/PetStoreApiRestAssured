package api.Utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	public FileInputStream fis;
	public FileOutputStream fos;
	public XSSFWorkbook workBook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell column;
	public XSSFCellStyle style;

	public String path=System.getProperty("user.dir")+"/src/test/resources/TestData/TestData.xlsx";

	public ExcelReader(String path) {
		this.path = path;
		try {
			this.fis = new FileInputStream(this.path);

			this.workBook = new XSSFWorkbook(this.fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getRowCount(String Sheet) throws IOException
	{
		sheet = workBook.getSheet(Sheet);
		return sheet.getLastRowNum();
	}

	public int getCellCount(String Sheet, int rownum) throws IOException
	{
		sheet = workBook.getSheet(Sheet);
		row = sheet.getRow(rownum);
		int cellcount = row.getLastCellNum();

		return cellcount;
	}

	public String getCellData(String Sheet, int rownum, int colnum) throws IOException {

		sheet = workBook.getSheet(Sheet);
		row = sheet.getRow(rownum);
		column = row.getCell(colnum);
		
		if(column==null)
			return "";

		CellType type=column.getCellType();

		switch(type)
		{
		case FORMULA:
			return column.getStringCellValue();
		case NUMERIC:
			try {
				return String.valueOf((int)column.getNumericCellValue());
			} catch (Exception e) {
				return "";
			} 
		case BOOLEAN:
			try {
				return String.valueOf(column.getBooleanCellValue());
			} catch (Exception e) {
				return "";
			} 
		case STRING:
			return column.getStringCellValue();
		case BLANK:
			return "";
		}
		return column.getStringCellValue();

	}


	public void close()
	{
		try {
			workBook.close();
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//	public void setCellData(String Test, int rownum, int colnum, String data) throws IOException {
	//		File xlfile = new File(path);
	//		if (!xlfile.exists()) {
	//			workBook = new XSSFWorkbook();
	//			fos = new FileOutputStream(path);
	//			workBook.write(fos);
	//		}
	//		fis = new FileInputStream(path);
	//		workBook = new XSSFWorkbook(fis);
	//
	//		if (workBook.getSheetIndex(Test) == -1)
	//			workBook.createSheet(Test);
	//		sheet = workBook.getSheet(Test);
	//		if (sheet.getRow(rownum) == null);
	//		sheet.createRow(rownum);
	//		row = sheet.getRow(rownum);
	//
	//		column = row.createCell(colnum);
	//		column.setCellValue(data);
	//		fos = new FileOutputStream(path);
	//		workBook.write(fos);
	//		workBook.close();
	//		fis.close();
	//		fos.close();
	//	}


}
