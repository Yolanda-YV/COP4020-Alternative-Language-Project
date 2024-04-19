import java.util.regex.*;
import java.io.File; // Files class
import java.io.FileNotFoundException; // FileNotFoundException class for errors
import java.util.Scanner; // Scanner class for reading files
import java.util.ArrayList; // ArrayList class for a resizable array

public class Main {
    public static void main(String[] args) {
        ArrayList<Cell> cells = new ArrayList<Cell>();
        try {
            // Putting file into File class type, csv file is in the same directory as the Main.java
            File cellFile = new File("cells.csv");
            Scanner cellScanner = new Scanner(cellFile);
            // cellScanner.hasNextLine() ORIGINAL CONDITION
            while (cellScanner.hasNextLine()) {
                String data = cellScanner.nextLine();
                ArrayList<String> dataSplit = readLine(data);
                Cell cell = createCell(dataSplit);
                cells.add(cell);
            }
            cellScanner.close();
            System.out.println("Number of cells: "+cells.size());
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
        Pattern pattern = Pattern.compile("\"([^\"]*)\"|([^,]+)|(,{1})(?=(?:,{1}))|,$");
        Matcher matcher = pattern.matcher(line);
        // find gets the nexts expression that matches pattern (returns true if found, false if not)
        while (matcher.find()) {
            // group gets the matched expression
            String column = matcher.group(1) != null ? matcher.group(1) : matcher.group(2) != null ? matcher.group(2) : "";
            //System.out.println(matcher.group(1) + " " + matcher.group(2) + " " + matcher.group(3));
            columns.add(column);
        }
        return columns;
    }

    // Method to create new cell object and set its attributes
    // Takes in a string array of values from a row in the CSV file
    // Returns a cell object
    public static Cell createCell(ArrayList<String> data) {
        Cell cell = new Cell();
        for (int i = 0; i < data.size(); i++) {
            // Setting the fields of the cell object
            String column = data.get(i);
            switch (i) {
                case 0:
                    cell.setOem(column);
                    break;
                case 1:
                    cell.setModel(column);
                    break;
                case 2:
                    cell.setLaunch_announced(column);
                    break;
                case 3:
                    cell.setLaunch_status(column);
                    break;
                case 4:
                    cell.setBody_dimensions(column);
                    break;
                case 5:
                    cell.setBody_weight(column);
                    break;
                case 6:
                    cell.setBody_sim(column);
                    break;
                case 7:
                    cell.setDisplay_type(column);
                    break;
                case 8:
                    cell.setDisplay_size(column);
                    break;
                case 9:
                    cell.setDisplay_resolution(column);
                    break;
                case 10:
                    cell.setFeatures_sensors(column);
                    break;
                case 11:
                    cell.setPlatform_os(column);
                    break;
            }
        }

        return cell;
    }
}