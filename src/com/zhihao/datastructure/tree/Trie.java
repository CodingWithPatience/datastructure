package com.zhihao.datastructure.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * @auther: zzh
 * @date: 2019/1/31
 */
public class Trie {

    private int size;
    private Node root;

    public Trie() {
        root = new Node();
        size = 0;
    }

    public void insert(String word) {
        Node cur = root;
        for (int i=0; i<word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null)
                cur.next.put(c, new Node());
            cur = cur.next.get(c);
        }
        if (!cur.isWord) {
            cur.isWord = true;
            size++;
        }
    }

    public boolean search(String word) {
        Node cur = root;
        for (int i=0; i<word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null)
                return false;
            cur = cur.next.get(c);
        }
        return cur.isWord;
    }

    public boolean startsWith(String prefix) {
        Node cur = root;
        for (int i=0; i<prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (cur.next.get(c) == null)
                return false;
            cur = cur.next.get(c);
        }
        return true;
    }

    public int size() {
        return size;
    }

    private class Node {
        public boolean isWord;
        public Map<Character, Node> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new HashMap<>();
        }

        public Node() {
            this(false);
        }
    }
}
