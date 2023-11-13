package pre_prepaired_Method_Storage;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;


public class ExcelUtility {

    public FileInputStream fi;
    public FileOutputStream fo;
    public XSSFWorkbook workbook;
    public XSSFSheet sheet;
    public XSSFRow row;
    public XSSFCell cell;
    public CellStyle style;
    String path;

    public ExcelUtility(String path) {
        this.path=path;
    }


    public static Object[][] excelFileDataReader(String filepath, String sheetname) {
        File file = new File(filepath);

        FileInputStream fis = null;
        XSSFWorkbook workbook = null;

        try {
            fis = new FileInputStream(file);
            workbook = new XSSFWorkbook(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        XSSFSheet sheet = workbook.getSheet(sheetname);
        int rows = sheet.getLastRowNum();
        int cols = sheet.getRow(0).getLastCellNum();

        Object[][] data = new Object[rows][cols];

        for (int r = 0; r < rows; r++) {
            XSSFRow row = sheet.getRow(r + 1);
            for (int c = 0; c < cols; c++) {
                XSSFCell cell = row.getCell(c);
                CellType cellType = cell.getCellType();
                switch (cellType) {
                    case STRING:
                        data[r][c] = cell.getStringCellValue();
                        break;
                    case NUMERIC:
                        double nuumericValue = cell.getNumericCellValue();
                        data[r][c] = Integer.toString((int)nuumericValue);
                        //by default Numeric store Data as double. we type-cast to int. which then converted to get String Value by using Integer Wrapper Class
                        break;
                    case BOOLEAN:
                        data[r][c] = cell.getBooleanCellValue();
                        break;
                }
            }
        }
        return data;
    }


    public static void excelFileDataWriter(Object[][] data, String filepath, String filename){
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();

        int rows = data.length;
        int cols = data[0].length;

        for(int r=0; r<rows; r++){
            XSSFRow row = sheet.createRow(r);
            for(int c=0; c<cols; c++){
                XSSFCell cell = row.createCell(c);
                Object value = data[r][c];

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

        String fileLocation = filepath+"/"+filename+".xlsx";
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(fileLocation);
            workbook.write(fos);
        } catch (IOException e) {
            System.out.println("<<<<<<<<<<<<<< !!!!!Excel File Failed To Create!!!!! >>>>>>>>>>>>>");
            throw new RuntimeException(e);

        }

        try {
            workbook.close();
            System.out.println("<<<<<<<<<<<<<< Excel File Successfully Created >>>>>>>>>>>>>");
        } catch (IOException e) {
            System.out.println("<<<<<<<<<<<<<< !!!!!Workbook Failed To Close !!!!! >>>>>>>>>>>>>");
            throw new RuntimeException(e);

        }

    }



    public static void readExcelFileUsingForLoop_Println(String sheetname){
        File file = new File("./readableFile/Country City And Its Population.xlsx");
        FileInputStream fis = null;
        XSSFWorkbook workbook = null;

        try {
            fis = new FileInputStream(file);
            workbook = new XSSFWorkbook(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        XSSFSheet sheet = workbook.getSheet(sheetname);
        //Get The Number of Row
        int rows = sheet.getLastRowNum();
        //Get the Number of Col
        int cols = sheet.getRow(0).getLastCellNum();

        //For Loop for Row
        for(int r=0; r<rows; r++){
            XSSFRow row = sheet.getRow(r + 1); //because row 0 is index of table
            //Get Column for that row
            for(int c=0; c<cols; c++){
                XSSFCell cell = row.getCell(c);
                switch(cell.getCellType()){
                    case STRING:
                        System.out.print(cell.getStringCellValue());
                        break;

                    case NUMERIC:
                        System.out.print(cell.getNumericCellValue());
                        break;

                    case BOOLEAN:
                        System.out.print(cell.getBooleanCellValue());
                        break;
                }
                System.out.print("   |   ");
            }
            System.out.println("\n----------------------------------------");
        }

    }



    public static void readExcelFileUsingIteratorMethod_Println(String sheetname){
        File file = new File("./readableFile/Country City And Its Population.xlsx");
        FileInputStream fis = null;
        XSSFWorkbook workbook = null;
        try {
            fis = new FileInputStream(file);
            workbook = new XSSFWorkbook(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        XSSFSheet sheet = workbook.getSheet(sheetname);

        Iterator<Row> rowItr = sheet.iterator();
        while(rowItr.hasNext()){
            Row row = rowItr.next();
            Iterator<Cell> cellItr = row.cellIterator();
            while(cellItr.hasNext()){
                Cell cell = cellItr.next();

                CellType cellType = cell.getCellType();
                switch (cellType){
                    case STRING:
                        System.out.print(cell.getStringCellValue());
                        break;
                    case NUMERIC:
                        System.out.print(cell.getNumericCellValue());
                        break;
                    case BOOLEAN:
                        System.out.print(cell.getBooleanCellValue());
                        break;
                }
                System.out.print("        |       ");
            }
            System.out.println("\n=========================================================");

        }

    }



    public int getRowCount(String sheetName) throws IOException {
        fi=new FileInputStream(path);
        workbook=new XSSFWorkbook(fi);
        sheet=workbook.getSheet(sheetName);
        int rowcount=sheet.getLastRowNum();
        workbook.close();
        fi.close();
        return rowcount;
    }



    public int getCellCount(String sheetName,int rownum) throws IOException {
        fi=new FileInputStream(path);
        workbook=new XSSFWorkbook(fi);
        sheet=workbook.getSheet(sheetName);
        row=sheet.getRow(rownum);
        int cellcount=row.getLastCellNum();
        workbook.close();
        fi.close();
        return cellcount;
    }



    public String getCellData(String sheetName,int rownum,int colnum) throws IOException {
        fi=new FileInputStream(path);
        workbook=new XSSFWorkbook(fi);
        sheet=workbook.getSheet(sheetName);
        row=sheet.getRow(rownum);
        cell=row.getCell(colnum);

        DataFormatter formatter = new DataFormatter();
        String data;
        try{
            data = formatter.formatCellValue(cell); //Returns the formatted value of a cell as a String regardless of the cell type.
        }
        catch(Exception e)
        {
            data="";
        }
        workbook.close();
        fi.close();
        return data;
    }



    public void setCellData(String sheetName,int rownum,int colnum,String data) throws IOException {
        File xlfile=new File(path);
        if(!xlfile.exists())    // If file not exists then create new file
        {
            workbook=new XSSFWorkbook();
            fo=new FileOutputStream(path);
            workbook.write(fo);
        }

        fi=new FileInputStream(path);
        workbook=new XSSFWorkbook(fi);

        if(workbook.getSheetIndex(sheetName)==-1) // If sheet not exists then create new Sheet
            workbook.createSheet(sheetName);

        sheet=workbook.getSheet(sheetName);

        if(sheet.getRow(rownum)==null)   // If row not exists then create new Row
            sheet.createRow(rownum);
        row=sheet.getRow(rownum);

        cell=row.createCell(colnum);
        cell.setCellValue(data);
        fo=new FileOutputStream(path);
        workbook.write(fo);
        workbook.close();
        fi.close();
        fo.close();
    }



    public void fillGreenColor(String sheetName,int rownum,int colnum) throws IOException {
        fi=new FileInputStream(path);
        workbook=new XSSFWorkbook(fi);
        sheet=workbook.getSheet(sheetName);

        row=sheet.getRow(rownum);
        cell=row.getCell(colnum);

        style=workbook.createCellStyle();

        style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(style);
        workbook.write(fo);
        workbook.close();
        fi.close();
        fo.close();
    }



    public void fillRedColor(String sheetName,int rownum,int colnum) throws IOException {
        fi=new FileInputStream(path);
        workbook=new XSSFWorkbook(fi);
        sheet=workbook.getSheet(sheetName);
        row=sheet.getRow(rownum);
        cell=row.getCell(colnum);

        style=workbook.createCellStyle();

        style.setFillForegroundColor(IndexedColors.RED.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(style);
        workbook.write(fo);
        workbook.close();
        fi.close();
        fo.close();
    }

}
