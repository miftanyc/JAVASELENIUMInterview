package importantDemonstration.fileReadWriteMethod;

import pre_prepaired_Method_Storage.ExcelUtility;

public class WriteEmployeeDataToExcel {

    public static void main(String[] args) {
        writeExcelFile();
    }


    public static Object[][] dataProvider(){
        Object[][] employeeData = {
                {"ID", "Name", "Position"},
                {001, "Mifta", "QA Analyst"},
                {002, "Smith", "QA Lead"},
                {003, "Arav", "Project Manager"},
                {004, "Razi", "Developer"},
                {005, "Daniel", "Developer"},
                {006, "Camila", "QA Technician"}};

        return employeeData;
    }

    public static void writeExcelFile(){
        Object[][] data = dataProvider();
        String filepath = "readableFile";
        String filename = "empl Record";

        ExcelUtility.excelFileDataWriter(data, filepath, filename);


    }

}
