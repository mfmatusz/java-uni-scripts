package lab1.ex1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Stack;

/**
 * Lab 1 / Exercise 1 — XML Tag Validator
 *
 * Validates nesting and closure of a predefined set of tags in an XML file.
 * Only tags listed in the companion .txt config file are checked.
 *
 * Usage: java lab1.ex1.XMLValidator [xml-file] [tags-config-file]
 * Defaults: laboratories/lab1/ex1/test1.xml  laboratories/lab1/ex1/test1.txt
 */
public class XMLValidator {

    /**
     * Validates XML tag nesting for the allowed tags defined in the config file.
     *
     * @return null if valid, or an error message describing the first problem found
     */
    public static String validate(String xmlFilePath, String tagsConfigPath) {
        Properties allowedTags = new Properties();
        try {
            allowedTags.load(new FileReader(tagsConfigPath));
        } catch (IOException e) {
            return "Cannot read tag config: " + e.getMessage();
        }

        Stack<String> tagStack = new Stack<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(xmlFilePath))) {
            reader.readLine(); // skip XML declaration line

            String line;
            while ((line = reader.readLine()) != null) {
                LinkedList<Integer> openBrackets = new LinkedList<>();
                LinkedList<Integer> closeBrackets = new LinkedList<>();

                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == '<') openBrackets.add(i);
                    if (line.charAt(i) == '>') closeBrackets.add(i);
                }

                if (openBrackets.isEmpty() || closeBrackets.isEmpty()) {
                    continue;
                }

                while (!openBrackets.isEmpty() && !closeBrackets.isEmpty()) {
                    String tagName = line.substring(openBrackets.removeFirst() + 1, closeBrackets.removeFirst());

                    if (tagName.charAt(0) == '/') {
                        String name = tagName.substring(1);
                        if (!allowedTags.containsKey(name)) {
                            continue;
                        }
                        if (tagStack.isEmpty()) {
                            return "Nesting error: unexpected closing tag <" + tagName + ">";
                        }
                        String expected = tagStack.pop();
                        if (!expected.equals(name)) {
                            return "Nesting error: expected </" + expected + ">, found <" + tagName + ">";
                        }
                    } else {
                        if (!allowedTags.containsKey(tagName)) {
                            continue;
                        }
                        tagStack.push(tagName);
                    }
                }
            }
        } catch (IOException e) {
            return "Cannot read XML file: " + e.getMessage();
        }

        if (!tagStack.isEmpty()) {
            return "Unclosed tags remaining: " + tagStack;
        }
        return null; // valid
    }

    public static void main(String[] args) {
        String xmlPath  = args.length > 0 ? args[0] : "laboratories/lab1/ex1/test1.xml";
        String tagsPath = args.length > 1 ? args[1] : "laboratories/lab1/ex1/test1.txt";

        String error = validate(xmlPath, tagsPath);
        if (error == null) {
            System.out.println("Valid: all allowed tags are correctly nested and closed.");
        } else {
            System.out.println("Invalid: " + error);
        }
    }
}
