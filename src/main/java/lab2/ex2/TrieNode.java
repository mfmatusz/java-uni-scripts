package lab2.ex2;

import java.util.HashMap;
import java.util.Map;

/**
 * A single node in a Trie data structure.
 * Each node stores its children and whether it marks the end of an inserted word.
 */
class TrieNode {

    private final Map<Character, TrieNode> children = new HashMap<>();
    private boolean isEndOfWord;

    /** Inserts a word starting from this node. */
    public void insert(String word) {
        TrieNode current = this;
        for (char ch : word.toCharArray()) {
            current.children.computeIfAbsent(ch, k -> new TrieNode());
            current = current.children.get(ch);
        }
        current.isEndOfWord = true;
    }

    public Map<Character, TrieNode> getChildren() {
        return children;
    }

    public boolean isEndOfWord() {
        return isEndOfWord;
    }
}
