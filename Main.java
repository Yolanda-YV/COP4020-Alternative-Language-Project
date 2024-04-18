import java.util.regex.*;
import java.io.File; // Files class
import java.io.FileNotFoundException; // FileNotFoundException class for errors
import java.util.Scanner; // Scanner class for reading files
import java.util.ArrayList; // ArrayList class for a resizable array

public class Main {
    public static void main(String[] args) {
        
        try {
            // Putting file into File class type, csv file is in the same directory as the Main.java
            File cellFile = new File("cells.csv");
            Scanner cellScanner = new Scanner(cellFile);
            while (cellScanner.hasNextLine()) {
                String data = cellScanner.nextLine();
                ArrayList<String> dataSplit = readLine(data);
                System.out.println(dataSplit.size() + " columns");
                for (int i = 0; i < dataSplit.size(); i++) {
                    System.out.print(dataSplit.get(i) + "; ");
                }
                System.out.println("");
            }
            cellScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } 
    }

    // Method to read a line from a CSV file and split it into columns
    public static ArrayList<String> readLine(String line) {
        // Finding 2 pattern matches: column with no outter "" or internal commas, column with outter "" and internal commas
        // 1. double quotes on the outside, inside
        // 2. no double quotes on the outside, no commas inside, but has at least one character inside
        ArrayList<String> columns = new ArrayList<String>();
        Pattern pattern = Pattern.compile("\"([^\"]*)\"|([^,]+)");
        Matcher matcher = pattern.matcher(line);
        // find gets the nexts expression that matches pattern (returns true if found, false if not)
        while (matcher.find()) {
            // group gets the matched expression
            String column = matcher.group(1) != null ? matcher.group(1) : matcher.group(2);
            columns.add(column);
        }
        return columns;
    }
}