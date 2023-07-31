package learn;

import java.util.ArrayList;
import java.util.List;

public class Trie {

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        insert(word, 1);
    }
    public void insert(String word, int v) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (node.children[c - 'a'] == null)
                node.children[c - 'a'] = new TrieNode();
            node = node.children[c - 'a'];
        }
        node.value = v;
    }

    public int search(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (node.children[c - 'a'] == null)
                return -1;
            node = node.children[c - 'a'];
        }
        return node.value;
    }

    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            if (node.children[c - 'a'] == null)
                return false;
            node = node.children[c - 'a'];
        }
        return true;
    }

    public List<String> searchPrefix(String prefix) {
        List<String> result = new ArrayList<>();
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            if (node.children[c - 'a'] == null)
                return result;
            node = node.children[c - 'a'];
        }
        dfs(node, prefix, result);
        return result;
    }

    private void dfs(TrieNode node, String prefix, List<String> result) {
        if (node.value != -1) {
            result.add(prefix);
        }
        for (int i = 0; i < 26; i++) {
            if (node.children[i] != null) {
                dfs(node.children[i], prefix + (char)('a' + i), result);
            }
        }
    }


    class TrieNode {
        TrieNode[] children;
        int value;

        public TrieNode() {
            children = new TrieNode[26];
            value = -1;
        }
    }
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple", 1);
        trie.insert("app", 1);
        trie.insert("app", 2);
        trie.insert("ack", 1);
        trie.insert("bool", 1);
        System.out.println(trie.search("apple"));
        System.out.println(trie.search("app"));
        System.out.println(trie.search("a"));
        System.out.println(trie.search("b"));

        System.out.println(trie.startsWith("app"));
        System.out.println(trie.startsWith("a"));

        System.out.println(trie.searchPrefix("app"));
        System.out.println(trie.searchPrefix("a"));
    }


}
