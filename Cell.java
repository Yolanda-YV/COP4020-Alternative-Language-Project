import java.util.regex.*;

public class Cell {
    // Fields or columns from the CSV file cells, will be initialized by setter methods
    String oem;
    String model;
    Integer launch_announced;
    String launch_status;
    String body_dimensions;
    Float body_weight;
    String body_sim;
    String display_type;
    Float display_size;
    String display_resolution;
    String features_sensors;
    String platform_os;
    
    // Setter methods for the fields
    public void setOem(String oem) {
        this.oem = oem;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public void setLaunch_announced(String launch_announced) {
        // Finding year in the launch_announced string using pattern matching (4 sequential numbers)
        Pattern pattern = Pattern.compile("[0-9]{4}");
        Matcher matcher = pattern.matcher(launch_announced);
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
            this.launch_status = null;
        }
    }
    public void setBody_dimensions(String body_dimensions) {
        this.body_dimensions = body_dimensions;
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
        if (body_sim.toLowerCase().equals("no") || body_sim.toLowerCase().equals("yes")) {
            this.body_sim = null;
        } else {
            this.body_sim = body_sim;
        }
    }
    public void setDisplay_type(String display_type) {
        this.display_type = display_type;
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
        this.display_resolution = display_resolution;
    }
    public void setFeatures_sensors(String features_sensors) {
        if (features_sensors.toLowerCase().equals("12") || features_sensors.toLowerCase().equals("20.1")) {
            this.features_sensors = null;
        } else {
            this.features_sensors = features_sensors;
        }
    }
}
