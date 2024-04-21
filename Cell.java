import java.util.regex.*;
import java.util.ArrayList;
import java.util.Scanner; // for reading user input

public class Cell {
    // Fields or columns from the CSV file cells, will be initialized by setter methods
    private String oem;
    private String model;
    private Integer launch_announced;
    private String launch_status;
    private String body_dimensions;
    private Float body_weight;
    private String body_sim;
    private String display_type;
    private Float display_size;
    private String display_resolution;
    private String features_sensors;
    private String platform_os;
    
    /* Setter methods for the fields 
     * Data will be transformed during the setter methods
     * Ex: the launch_announced field will be transformed from a string to an integer and will set only the year
     * Invalid data will be set to null
     * Ex: if launch_announced does not have a year, it will be set to null
     * If a field/column has no invalid data, it will be set as is (If data is missing or has "-" setter methods will set it to null)
     * Ex: if platform_os is missing, setPlatform_os will set it to null
    */
    public void setOem(String oem) {
        switch (oem) {
            case "-":
            case "":
                this.oem = null;
                break;
            default:
                this.oem = oem;
                break;
        }
    }
    public void setModel(String model) {
        switch (model) {
            case "-":
            case "":
                this.model = null;
                break;
            default:
                this.model = model;
                break;
        }
    }
    public void setLaunch_announced(String launch_announced) {
        // Finding year in the launch_announced string using pattern matching (4 sequential numbers)
        Pattern pattern = Pattern.compile("[0-9]{4}");
        Matcher matcher = pattern.matcher(launch_announced);
        // If found, store the year as an integer. Otherwise, set to null (invalid, missing data, or "-")
        if (matcher.find()) {
            this.launch_announced = Integer.parseInt(matcher.group());
        } else {
            this.launch_announced = null;
        }
    }
    public void setLaunch_status(String launch_status) {
        // Finding the year in launch_status string using pattern matching (4 sequential numbers)
        Pattern pattern = Pattern.compile("[0-9]{4}");
        Matcher matcher = pattern.matcher(launch_status);
        // will be kept as a string because it can be a year or a status
        if (matcher.find()) {
            // If found, store the year only
            this.launch_status = matcher.group();
        } else if (launch_status.toLowerCase().equals("cancelled") || (launch_status.toLowerCase().equals("discontinued"))) {
            // If cancelled or discontinued, store the status as is
            this.launch_status = launch_status;
        } else {
            // If not a year or status, set to null (invalid, missing data, or "-")
            this.launch_status = null;
        }
    }
    public void setBody_dimensions(String body_dimensions) {
        switch (body_dimensions) {
            case "-":
            case "":
                this.body_dimensions = null;
                break;
            default:
                this.body_dimensions = body_dimensions;
                break;
        }
    }
    public void setBody_weight(String body_weight) {
        // Finding the weight in grams
        // weight in grams, so looking for at least 1 number followed by a whitespace then a "g"
        Pattern pattern = Pattern.compile("[0-9]+\\s*g");
        Matcher matcher = pattern.matcher(body_weight);
        if (matcher.find()) {
            // Getting the number from the string
            String weightInGrams = matcher.group();
            // Removing the whitespace and "g"
            weightInGrams = weightInGrams.substring(0, weightInGrams.length() - 2);
            // Converting the string to a float
            this.body_weight = Float.parseFloat(weightInGrams);
        } else {
            this.body_weight = null;
        }
    }
    public void setBody_sim(String body_sim) {
        switch (body_sim.toLowerCase()) {
            case "-":
            case "":
            case "no":
            case "yes":
                this.body_sim = null;
                break;
            default:
                this.body_sim = body_sim;
                break;
        }
    }
    public void setDisplay_type(String display_type) {
        switch (display_type) {
            case "-":
            case "":
                this.display_type = null;
                break;
            default:
                this.display_type = display_type;
                break;
        }
    }
    public void setDisplay_size(String display_size) {
        // Finding the display size in inches
        // size in inches, so looking for at least 1 number followed by a whitespace then a "inches"
        Pattern pattern = Pattern.compile("[0-9]+.+[0-9]\\s*inches");
        Matcher matcher = pattern.matcher(display_size);
        if (matcher.find()) {
            // Getting the number from the string
            String sizeInInch = matcher.group();
            // Removing the whitespace and "inches"
            sizeInInch = sizeInInch.substring(0, sizeInInch.length() - 7);
            // Converting the string to a float
            this.display_size = Float.parseFloat(sizeInInch);
        } else {
            this.display_size = null;
        }
    }
    public void setDisplay_resolution(String display_resolution) {
        switch (display_resolution) {
            case "-":
            case "":
                this.display_resolution = null;
                break;
            default:
                this.display_resolution = display_resolution;
                break;
        }
    }
    public void setFeatures_sensors(String features_sensors) {
        if (features_sensors.toLowerCase().equals("12") || features_sensors.toLowerCase().equals("20.1")) {
            this.features_sensors = null;
        } else {
            this.features_sensors = features_sensors;
        }
        switch (features_sensors.toLowerCase()) {
            case "-":
            case "":
            case "12":
            case "20.1":
                this.features_sensors = null;
                break;
            default:
                this.features_sensors = features_sensors;
                break;
        }
    }
    public void setPlatform_os(String platform_os) {
        Pattern pattern = Pattern.compile("([^,]+)");
        Matcher matcher = pattern.matcher(platform_os);
        if (matcher.find()) {
            this.platform_os = matcher.group();
        } else {
            this.platform_os = null;
        }
    }

    /* Getter methods for the fields */
    public String getOem() {
        return oem;
    }
    public String getModel() {
        return model;
    }
    public Integer getLaunch_announced() {
        return launch_announced;
    }
    public String getLaunch_status() {
        return launch_status;
    }
    public String getBody_dimensions() {
        return body_dimensions;
    }
    public Float getBody_weight() {
        return body_weight;
    }
    public String getBody_sim() {
        return body_sim;
    }
    public String getDisplay_type() {
        return display_type;
    }
    public Float getDisplay_size() {
        return display_size;
    }
    public String getDisplay_resolution() {
        return display_resolution;
    }
    public String getFeatures_sensors() {
        return features_sensors;
    }
    public String getPlatform_os() {
        return platform_os;
    }

    /* Other methods */
    // Helper method
    // Takes the cell object and the name of the attribute/column and returns the value of the attribute/column as a string
    private static String getValue(Cell cell, String attribute) {
        switch (attribute) {
            case "oem":
                return String.valueOf(cell.getOem());
            case "model":
                return String.valueOf(cell.getModel());
            case "launch_announced":
                return String.valueOf(cell.getLaunch_announced());
            case "launch_status":
                return String.valueOf(cell.getLaunch_status());
            case "body_dimensions":
                return String.valueOf(cell.getBody_dimensions());
            case "body_weight":
                return String.valueOf(cell.getBody_weight());
            case "body_sim":
                return String.valueOf(cell.getBody_sim());
            case "display_type":
                return String.valueOf(cell.getDisplay_type());
            case "display_size":
                return String.valueOf(cell.getDisplay_size());
            case "display_resolution":
                return String.valueOf(cell.getDisplay_resolution());
            case "features_sensors":
                return String.valueOf(cell.getFeatures_sensors());
            case "platform_os":
                return String.valueOf(cell.getPlatform_os());
            default:
                System.err.println("Invalid attribute/column name");
                return null;
        }
    }
    // Takes in a Cell object
    // returns the number of feature sensors as an integer
    public static int getNumberOfFeatureSensors(Cell cell) {
        String features = cell.getFeatures_sensors();
        if (features == null) {
            // if null, there are no features
            return 0;
        }
        // Counting the number of commas in the string
        int count = 0;
        for (int i = 0; i < features.length(); i++) {
            if (features.charAt(i) == ',') {
                count++;
            }
        }
        // Adding 1 to the count because the number of commas is the number of features - 1
        return count + 1;
    }
    // Takes in an array list of cell objects and a column name
    // returns the mode of the values in the column as a string
    public static String calculateMode(ArrayList<Cell> cells, String column) {
        ArrayList<String> uniqueValues = getUniqueValues(cells, column);
        int maxCount = 0; //highest frequency of a value
        String mode = null;
        for (int i = 0; i < uniqueValues.size(); i++) {
            int count = 0;
            for (int j = 0; j < cells.size(); j++) {
                if (getValue(cells.get(j), column).equals(uniqueValues.get(i))) {
                    count++;
                }
            }
            if (count > maxCount) {
                // if count for the current value is higher than the max count, update the max count and mode
                maxCount = count;
                mode = uniqueValues.get(i);
            }
        }
        return mode;
    }
    // Takes in an array list of cell objects and calculates the average weight of the cells in grams, 
    // returns a double
    public static double calculateAverageWeight(ArrayList<Cell> cells) {
        double totalWeight = 0;
        int totalCells = cells.size();
        for (int i = 0; i < totalCells; i++) {
            if (cells.get(i).getBody_weight() != null) {
                // Invalid values were already set to null and will not be included in the calculation
                totalWeight += cells.get(i).getBody_weight();
            }
        }
        return totalWeight / totalCells;
    }
    // Takes in an array list of cell objects and calculates the average display size of the cells in inches, 
    // returns a double
    public static double calculateAverageDisplaySize(ArrayList<Cell> cells) {
        double totalSize = 0;
        int totalCells = cells.size();
        for (int i = 0; i < totalCells; i++) {
            if (cells.get(i).getDisplay_size() != null) {
                totalSize += cells.get(i).getDisplay_size();
            }
        }
        return totalSize / totalCells;
    }
    // Takes in an array list of cell objects, the attribute/column name, and the value to search for
    // returns an array list of cells from the original array list that have the same value for the attribute/column
    public static ArrayList<Cell> searchByAttribute(ArrayList<Cell> cells, String attribute, String value) {
        ArrayList<Cell> searchResults = new ArrayList<Cell>();
        for (int i = 0; i < cells.size(); i++) {
            if (getValue(cells.get(i), attribute).equals(value)) {
                searchResults.add(cells.get(i));
            }
        }
        return searchResults;
    }
    // Takes in an array list of cell objects, the attribute/column name, and the value to filter by (For numerical values)
    // returns an array list of cells from the original array list that have a value greater than the filter value for the attribute/column
    public static ArrayList<Cell> getGreaterThan(ArrayList<Cell> cells, String attribute, double filterValue) {
        ArrayList<Cell> filterResults = new ArrayList<Cell>();
        for (int i = 0; i < cells.size(); i++) {
            double value = Double.valueOf(getValue(cells.get(i), attribute));
            if (value > filterValue) {
                filterResults.add(cells.get(i));
            }
        }
        return filterResults;
    }
    // Takes in an array list of cell objects and the attribute/column name, 
    // returns an array list of unique values for the attribute/column
    public static ArrayList<String> getUniqueValues(ArrayList<Cell> cells, String attribute) {
        ArrayList<String> uniqueValues = new ArrayList<String>();
        for (int i = 0; i < cells.size(); i++) {
            String value = getValue(cells.get(i), attribute);
            if (!uniqueValues.contains(value)) {
                uniqueValues.add(value);
            }
        }
        return uniqueValues;
    }
    // Takes in an array list of cell objects and either:
    //  - the index of a cell to delete 
    //  - the attribute name and value of cells to delete
    // Overloading deleteCell method to have 2 different ways to delete cells
    // Returns the updated array list of cell objects
    public static ArrayList<Cell> deleteCell(ArrayList<Cell> cells, int index) {
        if (index >= 0 && index < cells.size()) {
            cells.remove(index);
        } else {
            System.err.println("Invalid index");
        }
        return cells;
    }
    public static ArrayList<Cell> deleteCell(ArrayList<Cell> cells, String attribute, String value) {
        for (int i = 0; i < cells.size(); i++) {
            if (getValue(cells.get(i), attribute).equals(value)) {
                cells.remove(i);
                i--; // Decrement i because the size of the array list has decreased
            }
        }
        return cells;
    }
    // Takes in an array list of cell objects and asks for user input to add a new cell object
    // Returns the updated array list of cell objects
    public static ArrayList<Cell> addCell(ArrayList<Cell> cells) {
        // Uses scanner class to get user input, uses next line to get a string value
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter the cell data below, invalid values will be set to null");
        System.out.print("\tOEM: ");
        String oem = scanner.nextLine();
        System.out.print("\tModel: ");
        String model = scanner.nextLine();
        System.out.print("\tLaunch Announced: ");
        String launch_announced = scanner.nextLine();
        System.out.print("\tLaunch Status: ");
        String launch_status = scanner.nextLine();
        System.out.print("\tBody Dimensions: ");
        String body_dimensions = scanner.nextLine();
        System.out.print("\tBody Weight: ");
        String body_weight = scanner.nextLine();
        System.out.print("\tBody SIM: ");
        String body_sim = scanner.nextLine();
        System.out.print("\tDisplay Type: ");
        String display_type = scanner.nextLine();
        System.out.print("\tDisplay Size: ");
        String display_size = scanner.nextLine();
        System.out.print("\tDisplay Resolution: ");
        String display_resolution = scanner.nextLine();
        System.out.print("\tFeatures Sensors: ");
        String features_sensors = scanner.nextLine();
        System.out.print("\tPlatform OS: ");
        String platform_os = scanner.nextLine();
        scanner.close(); // Closing the scanner
        // Creating a new cell object and setting its attributes
        Cell cell = new Cell();
        cell.setOem(oem);
        cell.setModel(model);
        cell.setLaunch_announced(launch_announced);
        cell.setLaunch_status(launch_status);
        cell.setBody_dimensions(body_dimensions);
        cell.setBody_weight(body_weight);
        cell.setBody_sim(body_sim);
        cell.setDisplay_type(display_type);
        cell.setDisplay_size(display_size);
        cell.setDisplay_resolution(display_resolution);
        cell.setFeatures_sensors(features_sensors);
        cell.setPlatform_os(platform_os);
        // Adding the new cell object to the array list
        cells.add(cell);
        return cells;
    }
}
