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
            this.platform_os = platform_os;
        } else {
            this.platform_os = null;
        }
    }
}
