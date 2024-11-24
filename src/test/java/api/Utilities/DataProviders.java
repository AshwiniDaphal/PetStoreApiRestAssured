package api.Utilities;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.testng.annotations.DataProvider;
public class DataProviders {

	@DataProvider(name = "Data" , parallel = false)
	public String[][] getAllData() throws IOException {
		String path = System.getProperty("user.dir")+"/src/test/resources/TestData/Testdata.xlsx";
		ExcelReader ex = new ExcelReader(path);

		int rownum = ex.getRowCount("Sheet1");
		System.out.println("rownum"+rownum);
		int colcount = ex.getCellCount("Sheet1", 1);
		System.out.println("colcount"+colcount);
		String apiExceldata[][] = new String[rownum][colcount];
		for (int i = 1; i <=rownum; i++) {
			for (int j = 0; j < colcount; j++) {
				apiExceldata[i - 1][j] = ex.getCellData("Sheet1", i, j);
				//apiExceldata[i][j] = ex.getCellData("Sheet1", i, j);
				
			}
		}

		ex.close();

		return apiExceldata;
	}

	@DataProvider(name = "UserName")
	public String[] getUserName() throws IOException {
		String path = System.getProperty("user.dir")+"/src/test/resources/TestData/Testdata.xlsx";
		ExcelReader ex1 = new ExcelReader(path);

		int rownum = ex1.getRowCount("Sheet1");

		String apiExceldata[] = new String[rownum];
		for (int i = 1; i <=rownum; i++) {

			apiExceldata[i - 1] = ex1.getCellData("Sheet1", i, 1);
		}

		ex1.close();
		return apiExceldata;
	}
	
	

}
