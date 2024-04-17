import java.util.regex.*;

public class Main {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("[0-9]+\\s*g");
        Matcher matcher = pattern.matcher("125 g 123 kg");
        if (matcher.find()) {
            System.out.println("Match found: " + matcher.group());
            String weightInGrams = matcher.group();
            // Removing the whitespace and "g" from the string
            weightInGrams = weightInGrams.substring(0, weightInGrams.length() - 2);
            // Converting the string to a float
            System.out.println("Match found: " + weightInGrams);
        } else {
            System.out.println("Match not found");
        }
    }
}