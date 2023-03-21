package lab2.ex2;

import java.util.ArrayList;
import java.util.List;

public class Contact {
    private TrieNode root;

    public Contact() {
        root = new TrieNode();
    }

    public void add(String contact) {
        root.insert(contact);
    }

    public List<String> find(String prefix) {
        List<String> contacts = new ArrayList<>();
        TrieNode current = root;
        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            TrieNode node = current.children.get(ch);
            if (node == null) {
                return contacts;
            }
            current = node;
        }
        findContacts(current, prefix, contacts);
        return contacts;
    }

    private void findContacts(TrieNode node, String prefix, List<String> contacts) {
        if (node.isEndOfWord) {
            contacts.add(prefix);
        }
        for (Character ch : node.children.keySet()) {
            findContacts(node.children.get(ch), prefix + ch, contacts);
        }
    }
}


