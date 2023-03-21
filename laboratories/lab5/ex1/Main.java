//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package lab5.ex1;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class Main {
    public Main() {
    }

    public static void main(String[] var0) {
        if (var0.length != 1) {
            System.out.println("Wrong password!"); // Wyświetl komunikat "Wrong password!", jeśli ilość argumentów var0 jest różna od 1.
        } else {
            String var1 = var0[0]; // Przypisz pierwszy argument var0 do zmiennej var1.
            String[] var2 = var1.split("_"); // Podziel zmienną var1 na części oddzielone znakiem podkreślenia "_" i zapisz je w tablicy var2.
            Date var3 = Date.from(Instant.now()); // Utwórz obiekt Date reprezentujący aktualną datę i czas.
            SimpleDateFormat var4 = new SimpleDateFormat("yyyy"); // Utwórz obiekt SimpleDateFormat z formatem "yyyy" (tylko rok).
            String var5 = var4.format(var3); // Sformatuj obecną datę do postaci łańcucha znaków zgodnej z formatem "yyyy" i przypisz do zmiennej var5.
            int var6 = Integer.parseInt(var5); // Skonwertuj zmienną var5 na liczbę całkowitą i przypisz do zmiennej var6.
            if (var2[0].length() == 4 && var2[1].length() == 4) { // Sprawdź, czy pierwsza część hasła ma dokładnie 4 znaki, a druga część również ma dokładnie 4 znaki.
                if (var2[0].equals(Coder.code("CB42")) && Integer.parseInt(var2[1]) == var6) { // Sprawdź, czy pierwsza część hasła jest równa zaszyfrowanemu łańcuchowi "CB42" oraz czy druga część hasła jest równa aktualnemu roku.
                    System.out.println("Good guess"); // Wyświetl komunikat "Good guess", jeśli oba warunki są spełnione.
                } else {
                    System.out.println("Wrong password!"); // Wyświetl komunikat "Wrong password!", jeśli któreś z tych dwóch warunków nie jest spełnione.
                }
            } else {
                System.out.println("Wrong password!"); // Wyświetl komunikat "Wrong password!", jeśli pierwsza lub druga część hasła nie ma dokładnie 4 znaków.
            }
        }
    }
}
