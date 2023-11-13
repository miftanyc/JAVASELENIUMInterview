package importantDemonstration.fileReadWriteMethod;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pre_prepaired_Method_Storage.ExcelUtility;

public class ExcelReadAndDataProvide {


    @Test(enabled = true ,dataProvider = "SupplyExcelData")
    public static void dataPrinter(String email, String password, String runMode){
        if(runMode.contains("Y")){
            System.out.print("Email: "+email);
            System.out.print("       |       ");
            System.out.print("Password: "+password);
            System.out.println("\n===========================================================================");
        }

    }

    @DataProvider(name = "SupplyExcelData")
    public static Object[][] provideExcelData(){
        String filepath = "./readableFile/Email And Password Provider.xlsx";
        String sheetname = "credentials";
        Object[][] data = ExcelUtility.excelFileDataReader(filepath, sheetname);
        return data;
    }

    @Test(enabled = false ,dataProvider = "provideyourdata")
    public static void readData(String firstname, String lastname){
        System.out.print("FirstName:" + firstname);
        System.out.print("       |       ");
        System.out.print("LastName: "+lastname);
        System.out.println("\n===========================================================================");

    }
    @DataProvider(name="provideyourdata")
    public static String[][] dataprov(){
        String[][] firstLast= {{"mifta", "chowdhury"},{"shofiq", "chowdhury"}};
        return firstLast;

    }
}
