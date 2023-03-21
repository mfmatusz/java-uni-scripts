package lab2.ex2;

import java.util.ArrayList;
import java.util.List;

/**
 * Lab 2 / Exercise 2 — Trie-based Contact Search
 *
 * Stores contact names in a Trie for efficient prefix-based lookup.
 */
public class Contact {

    private final TrieNode root = new TrieNode();

    /** Adds a contact name to the trie. */
    public void add(String contact) {
        root.insert(contact);
    }

    /** Returns all stored names that start with the given prefix. */
    public List<String> find(String prefix) {
        TrieNode current = root;
        for (char ch : prefix.toCharArray()) {
            current = current.getChildren().get(ch);
            if (current == null) return List.of();
        }
        List<String> results = new ArrayList<>();
        collectAll(current, prefix, results);
        return results;
    }

    private void collectAll(TrieNode node, String prefix, List<String> results) {
        if (node.isEndOfWord()) {
            results.add(prefix);
        }
        for (var entry : node.getChildren().entrySet()) {
            collectAll(entry.getValue(), prefix + entry.getKey(), results);
        }
    }
}
