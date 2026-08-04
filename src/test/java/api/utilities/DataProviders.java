package api.utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {

    @DataProvider(name = "Data")
    public String[][] getAllData() throws IOException {

        String path = System.getProperty("user.dir") + "/TestData/API_Automation_TestData.xlsx";
        ExcelUtility xlUtil = new ExcelUtility(path);

        int totalRows = xlUtil.getRowCount("Sheet1");
        int totalCols = xlUtil.getCellCount("Sheet1", 1);

        String[][] apidata = new String[totalRows][totalCols];
        for (int i = 1; i <= totalRows; i++) {
            for (int j = 0; j < totalCols; j++) {
                apidata[i - 1][j] = xlUtil.getCellData("Sheet1", i, j);

            }
        }

        return apidata;
    }
    @DataProvider(name="UserNames")
    public String[] getUserNames() throws IOException {

        String path = System.getProperty("user.dir") + "/TestData/API_Automation_TestData.xlsx";
        ExcelUtility xlUtil = new ExcelUtility(path);

        int rownum = xlUtil.getRowCount("Sheet1");

        String apidata[] = new String[rownum];

        for (int i = 1; i <= rownum; i++) {
            apidata[i - 1] = xlUtil.getCellData("Sheet1", i, 1);
        }

        return apidata;
    }

}