import java.util.regex.*;
import java.io.File; // Files class
import java.io.FileNotFoundException; // FileNotFoundException class for errors
import java.util.Scanner; // Scanner class for reading files

public class Main {
    public static void main(String[] args) {
        try {
            // Putting file into File class type, csv file is in the same directory as the Main.java
            File cellFile = new File("cells.csv");
            Scanner cellScanner = new Scanner(cellFile);
            while (cellScanner.hasNextLine()) {
                String data = cellScanner.nextLine();
                String[] dataSplit = data.split(",");
                System.out.println(dataSplit[0]);
            }
            cellScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }
}