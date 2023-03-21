//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package lab5.ex1;

public class Coder {
    public Coder() {
    }

    static String code(String var0) {
        StringBuilder var1 = new StringBuilder();

        for (int var2 = 0; var2 < var0.length(); ++var2) {
            var1.append((char) (var0.charAt(var2) + 1)); // Zaszyfruj każdy znak w łańcuchu var0 poprzez inkrementację jego wartości o 1 i dodaj go do obiektu StringBuilder var1.
        }

        return var1.toString(); // Zwróć zaszyfrowany łańcuch znaków.
    }
}