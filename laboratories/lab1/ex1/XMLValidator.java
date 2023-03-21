import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Stack;

public class XMLValidator {

    public static void main(String[] args) {
        String xmlFilePath = "C:\\Users\\kamil\\IdeaProjects\\APRO2\\Laby\\Lab1\\src\\test1fail.xml";

        Properties allowedTags = new Properties();
        try {
            allowedTags.load(new FileReader("C:\\Users\\kamil\\IdeaProjects\\APRO2\\Laby\\Lab1\\src\\test1.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stack<String> tagStack = new Stack<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(xmlFilePath))) {
            String line;
            reader.readLine();

            while ((line = reader.readLine()) != null) {

                LinkedList<Integer> open = new LinkedList<Integer>();
                LinkedList<Integer> close = new LinkedList<Integer>();

                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == '<') {
                        open.add(i);
                    }
                    if (line.charAt(i) == '>') {
                        close.add(i);
                    }
                }
                if (open.isEmpty() || close.isEmpty()){
                    continue;
                }

                while (!open.isEmpty() || !close.isEmpty() ) {
                    String tagName = line.substring(open.removeFirst() + 1, close.removeFirst());

                    if (tagName.charAt(0) == '/') {
                        if (!allowedTags.contains(tagName.substring(1))) {
                            continue;
                        }

                        if (tagStack.isEmpty()) {
                            System.out.println("Błąd zagnieżdżenia: napotkano nieoczekiwany znacznik zamykający");
                            return;
                        }

                        String expectedTagName = tagStack.pop();
                        if (!expectedTagName.equals(tagName.substring(1))) {
                            System.out.println("Błąd zagnieżdżenia: oczekiwano " + expectedTagName + ", ale napotkano " + tagName);
                            return;
                        }
                    } else {
                        if (!allowedTags.contains(tagName)) {
                            continue;
                        }

                        tagStack.push(tagName);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!tagStack.isEmpty()) {
            System.out.println("Nie domknięto wszystkich znaczników");
        } else {
            System.out.println("Poprawne zagnieżdżenie i domknięcie znaczników");
        }
    }
}
