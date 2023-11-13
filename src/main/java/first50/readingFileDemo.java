package first50;

import java.io.*;

public class readingFileDemo {
    public static void main(String[] args) throws IOException {
        readingFile();

    }
    public static void readingFile() throws IOException {
        FileReader fr = new FileReader("./readableFile/Professional Summary.txt");
        BufferedReader br = new BufferedReader(fr);

        String line;

        while((line=br.readLine())!=null){
            System.out.println(line);
        }
    }
}
