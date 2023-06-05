package file;


import java.io.*;
import java.util.Date;
import java.util.Scanner;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class MyFileWriter {

        public static void main(String[] args) throws IOException {
            MyFileWriter.readDataFromFile();
            MyFileWriter fileWriter = new MyFileWriter();


            MyFileWriter fileWriter1 = new MyFileWriter();
            fileWriter1.appendData("");
        }

        public static void appendData(String data) throws IOException {
            File myFile = new File("TransactionHistory.txt");
            FileWriter fileWriter = null;
            try {
                fileWriter = new FileWriter(myFile, true);  // Set the second parameter to true for append mode
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String dateTime = dateFormat.format(new Date());
            String line = dateTime + " - " + data;
            printWriter.println(line);  // Write the transaction data
            printWriter.close();
            bufferedWriter.close();
            fileWriter.close();
        }


        public static void readDataFromFile() {
            File myFile = new File("TransactionHistory.txt");
            try {
                Scanner myReader = new Scanner(myFile);
                while (myReader.hasNext()) {
                    String dataInFile = myReader.nextLine();
                    System.out.println(dataInFile);
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("File does not exist");
            }
        }


}

