package lab5.ex1;

/**
 * Lab 5 / Exercise 1 — Reverse Engineering: Caesar Cipher
 *
 * Originally provided as a compiled .class file to decompile and analyse.
 * Encodes a string by shifting each character's ASCII value up by 1.
 */
class Coder {

    static String encode(String input) {
        StringBuilder sb = new StringBuilder();
        for (char ch : input.toCharArray()) {
            sb.append((char) (ch + 1));
        }
        return sb.toString();
    }
}
