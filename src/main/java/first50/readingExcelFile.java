package first50;

import pre_prepaired_Method_Storage.ExcelUtility;
import pre_prepaired_Method_Storage.TestDataProvider;

public class readingExcelFile {

    public static void main(String[] args) {
        writeExcelFile();
    }


    public static void writeExcelFile(){
        Object[][] data = TestDataProvider.employeeDataProvider();
        String filepath = "readableFile";
        String filename = "new Data";

        ExcelUtility.excelFileDataWriter(data, filepath, filename);

    }


}
