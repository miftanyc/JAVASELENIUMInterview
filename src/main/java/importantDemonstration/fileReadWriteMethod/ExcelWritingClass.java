package importantDemonstration.fileReadWriteMethod;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelWritingClass {

    public static void main(String[] args) {
        excelFileWriter();
    }
    
    public static void excelFileWriter(){
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("employee");
        
        Object[][] employeeData = {
                {"ID", "Name", "Position"},
                {001, "Mifta", "QA Analyst"},
                {002, "Smith", "QA Lead"},
                {003, "Arav", "Project Manager"},
                {004, "Razi", "Developer"},
                {005, "Daniel", "Developer"},
                {006, "Camila", "QA Technician"}};
        
        int rows = employeeData.length;
        int cols = employeeData[0].length;
        
        
        for(int r=0; r<rows; r++){
            XSSFRow row = sheet.createRow(r);
            for(int c=0; c<cols; c++){
                XSSFCell cell = row.createCell(c);
                Object value = employeeData[r][c];

                if(value instanceof String){
                    cell.setCellValue((String) value);
                }
                if(value instanceof Integer){
                    cell.setCellValue((Integer)value);
                }
                if(value instanceof Boolean){
                    cell.setCellValue((Boolean) value);
                }

            }

        }

        String filepath = "./readableFile/employee Information.xlsx";
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(filepath);
            workbook.write(fos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            workbook.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
