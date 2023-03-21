package lab5.ex1;

import java.time.Year;

/**
 * Lab 5 / Exercise 1 — Reverse Engineering: Password Checker
 *
 * Originally provided as a compiled .class file to decompile and analyse.
 *
 * The expected password format is: {@code <encoded>_<year>}
 *   - Part 1 must equal Coder.encode("CB42")  → "DC53"
 *   - Part 2 must equal the current calendar year (e.g. "2026")
 *
 * Example valid password for 2026: {@code DC53_2026}
 *
 * Usage: java lab5.ex1.PasswordChecker DC53_2026
 */
public class PasswordChecker {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Wrong password!");
            return;
        }

        String[] parts = args[0].split("_");
        if (parts.length < 2 || parts[0].length() != 4 || parts[1].length() != 4) {
            System.out.println("Wrong password!");
            return;
        }

        String expectedCode = Coder.encode("CB42");
        int currentYear = Year.now().getValue();

        if (parts[0].equals(expectedCode) && Integer.parseInt(parts[1]) == currentYear) {
            System.out.println("Good guess");
        } else {
            System.out.println("Wrong password!");
        }
    }
}
