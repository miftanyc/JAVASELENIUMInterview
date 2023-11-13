package importantDemonstration.fileReadWriteMethod;

import org.testng.annotations.Test;

import java.io.*;
import java.util.Scanner;

public class O04_ReadTextFile {

    //Approach 1: BufferedReader - FileReader Approach
    @Test(enabled = false)
    public static void bufferedReaderFileReaderApproach() throws IOException {

        FileReader fr = new FileReader(System.getProperty("user.dir")+"/readableFile/Professional Summary.txt");

        BufferedReader br = new BufferedReader(fr);
        String line;
        while((line=br.readLine())!=null){
            System.out.println(line);
        }
        br.close();
    }


    //Approach2: File Class and Scanner Class Approach
    @Test(enabled = true)
    public static void fileAndScannerApproach() throws FileNotFoundException {
        File file = new File(System.getProperty("user.dir")+"/readableFile/Professional Summary.txt");
        Scanner scanner = new Scanner(file);

        while(scanner.hasNextLine()){
            System.out.println(scanner.nextLine());
        }

    }

}
