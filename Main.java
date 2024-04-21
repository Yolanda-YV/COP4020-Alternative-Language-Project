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
            // Printing the number of cells and the information of each cell
            System.out.println("Number of total cells: "+cells.size());
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } 

        // Finding company with highest average weight of phone body
        double highestAvgWeight = 0;
        String highestAvgWeightCompany = "";
        // Getting unique values of oem (companies)
        ArrayList<String> companies = Cell.getUniqueValues(cells, "oem");
        for (int i = 0; i < companies.size(); i++) {
            // Getting all cells of a company from cells
            ArrayList<Cell> companyCells = Cell.searchByAttribute(cells, "oem", companies.get(i));
            // Getting average weight of all cells of a company
            double avgWeight = Cell.calculateAverageWeight(companyCells);
            if (avgWeight > highestAvgWeight) {
                highestAvgWeight = avgWeight;
                highestAvgWeightCompany = companies.get(i);
            }
        }
        System.out.println("Company with highest average weight of phone body: " + highestAvgWeightCompany + " with an average of " + highestAvgWeight + " grams");
    
        // Finding phones with different announcement and release years
        ArrayList<Cell> diffYears = new ArrayList<Cell>();
        for (int i = 0; i < cells.size(); i++) {
            Cell cell = cells.get(i);
            String launchAnnounced = String.valueOf(cell.getLaunch_announced());
            String launchStatus = String.valueOf(cell.getLaunch_status());
            if (!launchAnnounced.equals(launchStatus) && launchStatus.length()==4) {
                diffYears.add(cell);
            }
        }
        // Printing cell information including oem and models
        System.err.println("Phones with different announcement and release years:");
        printCells(diffYears);
    }

    // Method to read a line from a CSV file and split it into columns
    public static ArrayList<String> readLine(String line) {
        // Finding 4 pattern matches:
        // 1. double quotes on the outside
        // 2. no double quotes on the outside, no commas inside, but has at least one character inside
        // 3. a comma followed by a comma (missing value)
        // 4. a comma followed by the end of the line (missing value)
        ArrayList<String> columns = new ArrayList<String>();
        Pattern pattern = Pattern.compile("\"([^\"]*)\"|([^,]+)|(,{1})(?=(?:,{1}))|,$");
        Matcher matcher = pattern.matcher(line);
        // find gets the nexts expression that matches pattern (returns true if found, false if not)
        while (matcher.find()) {
            // group gets the matched expression
            String column = matcher.group(1) != null ? matcher.group(1) : matcher.group(2) != null ? matcher.group(2) : "";
            columns.add(column);
        }
        return columns;
    }

    // Method to create new cell object and set its attributes, given a line of valid data
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

    // Method for printing the information of a cell object
    public static void printCell(Cell cell) {
        System.out.println("\tOEM: " + cell.getOem());
        System.out.println("\tModel: " + cell.getModel());
        System.out.println("\tLaunch Announced: " + cell.getLaunch_announced());
        System.out.println("\tLaunch Status: " + cell.getLaunch_status());
        System.out.println("\tBody Dimensions: " + cell.getBody_dimensions());
        System.out.println("\tBody Weight (grams): " + cell.getBody_weight());
        System.out.println("\tBody SIM: " + cell.getBody_sim());
        System.out.println("\tDisplay Type: " + cell.getDisplay_type());
        System.out.println("\tDisplay Size (inches): " + cell.getDisplay_size());
        System.out.println("\tDisplay Resolution: " + cell.getDisplay_resolution());
        System.out.println("\tFeatures Sensors: " + cell.getFeatures_sensors());
        System.out.println("\tPlatform OS: " + cell.getPlatform_os());
    }

    // Method for printing the information of multiple cell objcts in an array list
    public static void printCells(ArrayList<Cell> cells) {
        for (int i = 0; i < cells.size(); i++) {
            System.out.println("Cell " + (i + 1) + ":");
            printCell(cells.get(i));
        }
    }
}