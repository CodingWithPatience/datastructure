package com.zhihao.datastructure.map;

import com.zhihao.datastructure.set.BSTreeSet;
import com.zhihao.datastructure.set.Set;

import java.util.Random;

/**
 * @auther: zzh
 * @date: 2019/1/30
 */
public class BSTreeMap<K extends Comparable, V> implements Map<K, V>{
    private int size;
    private TreeNode root;
    private BSTreeSet<K> keys;

    public BSTreeMap() {
        keys = new BSTreeSet<>();
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public V remove(K key) {
        TreeNode node = getNode(key);
        if (node != null) {
            V val = node.val;
            root = remove(root, key);
            keys.remove(key);
            return val;
        }
        return null;
    }

    private TreeNode remove(TreeNode node, K key) {
        if (node == null || key == null)
            return null;
        int comRet = key.compareTo(node.key);
        if (comRet > 0)
            node.right = remove(node.right, key);
        else if (comRet < 0)
            node.left = remove(node.left, key);
        else if (node.left != null && node.right != null) {
            TreeNode min = findMin(node.right);
            node.key = min.key;
            node.val = min.val;
            node.right = remove(node.right, node.key);
        }
        else {
            node = (node.left == null) ? node.right : node.left;
            size--;
        }
        return node;
    }

    private TreeNode findMin(TreeNode node) {
        if (node == null)
            return null;
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    @Override
    public boolean containsKey(K key) {
        return keys.contains(key);
    }

    @Override
    public boolean containsValue(V value) {
        return containsValue(root, value);
    }

    private boolean containsValue(TreeNode node, V value) {
        if (node == null)
            return false;
        if (node.val.equals(value))
            return true;
        boolean left = containsValue(node.left, value);
        return left ? true : containsValue(node.right, value);
    }

    @Override
    public V put(K key, V value) {
        TreeNode node = getNode(key);
        if (node != null) {
            V oldVal = node.val;
            node.val = value;
            return oldVal;
        }
        root = put(root, key, value);
        keys.add(key);
        return null;
    }

    private TreeNode put(TreeNode node, K key, V value) {
        if (key == null || value == null)
            throw new IllegalStateException("Key or value can not be null");
        if (node == null) {
            size++;
            return new TreeNode(key, value);
        }
        int comRet = key.compareTo(node.key);
        if (comRet > 0)
            node.right = put(node.right, key, value);
        else if (comRet < 0)
            node.left = put(node.left, key, value);
        else
            node.val = value;
        return node;
    }

    @Override
    public V get(K key) {
        TreeNode node = getNode(key);
        if (node == null)
            return null;
        return node.val;
    }

    private TreeNode getNode(K key) {
        if (key == null)
            throw new IllegalStateException("Key can not be null");
        return getNode(root, key);
    }

    private TreeNode getNode(TreeNode node, K key) {
        if (node == null || key == null)
            return null;
        int comRet = key.compareTo(node.key);
        if (comRet == 0)
            return node;
        else if (comRet > 0)
            return getNode(node.right, key);
        else
            return getNode(node.left, key);
    }

    public Set<K> keySet() {
        return keys;
    }

    private class TreeNode {
        public K key;
        public V val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    private void printTreePre(TreeNode root) {
        if (root!=null) {
            System.out.println("Key:"+root.key+" Value:"+root.val);
            printTreePre(root.left);
            printTreePre(root.right);
        }
    }

    private void printTreeIn(TreeNode root) {
        if (root!=null) {
            printTreeIn(root.left);
            System.out.println("Key:"+root.key+" Value:"+root.val);
            printTreeIn(root.right);
        }
    }

    private void printTreePost(TreeNode root) {
        if (root!=null) {
            printTreePost(root.left);
            printTreePost(root.right);
            System.out.println("Key:"+root.key+" Value:"+root.val);
        }
    }

    public void preOrder() {
        printTreePre(root);
    }

    public void inOrder() {
        printTreeIn(root);
    }
    public void postOrder() {
        printTreePost(root);
    }

    public static void main(String[] args) {
        BSTreeMap<String, String> map = new BSTreeMap<>();
        Random random = new Random();
        for (int i=0; i<10; i++) {
            int r = random.nextInt(10);
            map.put("" + r, "" + r);
        }
        map.inOrder();
        map.put("hello", "world");
        System.out.println(map.size());
        System.out.println(map.isEmpty());
        System.out.println(map.containsKey("hello"));
        System.out.println(map.containsValue("world"));
        System.out.println(map.put("hello", "123"));
        System.out.println(map.put("hello", "456"));
        System.out.println(map.remove("hello"));
        System.out.println(map.get("hello"));
        System.out.println(map.size());
        System.out.println(map.isEmpty());
    }
}
