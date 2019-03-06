package com.zhihao.datastructure.tree;

/**
 * @auther: zzh
 * @date: 2018/12/31
 */
public class AVLTree<K extends Comparable, V> {

    private AVLNode root;

    private int size;

    public AVLTree() {
        this.root = null;
        this.size = 0;
    }

    public V insert(K key, V val) {
        if (key == null)
            throw new IllegalStateException("Key or can not be null");
        AVLNode node = getNode(key);
        V oldVal = null;
        if (node != null)
            oldVal = node.val;
        root = insert(key, val, root);
        return oldVal;
    }

    public V remove(K key) {
        if (key == null)
            throw new IllegalStateException("Key can not be null");
        AVLNode node = getNode(key);
        if (node != null) {
            V oldVal = node.val;
            root = remove(key, root);
            return oldVal;
        }
        return null;
    }

    public boolean containsKey(K key) {
        if (key == null)
            throw new IllegalStateException("Key can not be null");
        if (getNode(key) != null)
            return true;
        return false;
    }

    public boolean containsValue(V val) {
        if (val == null)
            throw new IllegalStateException("Value can not be null");
        if (getNode(root, val) != null)
            return true;
        return false;
    }

    public V getValue(K key) {
        if (key == null)
            throw new IllegalStateException("Key can not be null");
        AVLNode node = getNode(key);
        if (node != null)
            return node.val;
        return null;
    }

    public AVLNode getRoot() {
        return root;
    }

    public void printTreePre() {
        printTreePre(root);
    }

    public void printTreeIn() {
        printTreeIn(root);
    }

    public void printTreePost() {
        printTreePost(root);
    }

    private void printTreePre(AVLNode root) {
        if (root!=null) {
            System.out.println("Key:"+root.key+" Value:"+root.val);
            printTreePre(root.left);
            printTreePre(root.right);
        }
    }

    private void printTreeIn(AVLNode root) {
        if (root!=null) {
            printTreeIn(root.left);
            System.out.println("Key:"+root.key+" Value:"+root.val);
            printTreeIn(root.right);
        }
    }

    private void printTreePost(AVLNode root) {
        if (root!=null) {
            printTreePost(root.left);
            printTreePost(root.right);
            System.out.println("Key:"+root.key+" Value:"+root.val);
        }
    }

    private AVLNode getNode(K key) {
        if (key == null)
            return null;
        return getNode(root, key);
    }

    private AVLNode getNode(AVLNode node, K key) {
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

    private AVLNode getNode(AVLNode node, V val) {
        if (node == null || val == null)
            return null;
        if (node.val.equals(val))
            return node;
        AVLNode left = getNode(node.left, val);
        if (left == null)
            return getNode(node.right, val);
        return left;
    }

    private AVLNode remove(K key, AVLNode root) {
        if (root == null || key == null)
            return null;
        int comResult = key.compareTo(root.key);
        if (comResult > 0) {
            root.right = remove(key, root.right);
        }
        else if (comResult < 0) {
            root.left = remove(key, root.left);
        }
        else if (root.left!=null && root.right!=null) {
            AVLNode min = findMin(root.right);
            root.key = min.key;
            root.val = min.val;
            root.right = remove(root.key, root.right);
        }
        else {
            root = root.left == null ? root.right : root.left;
            size--;
        }
        return balance(root);
    }

    private AVLNode findMin(AVLNode node) {
        if (node == null)
            return null;
        while (node.left!=null) {
            node = node.left;
        }
        return node;
    }

    private AVLNode insert(K key, V val, AVLNode root) {
        if (root == null) {
            size++;
            return new AVLNode(key, val);
        }
        int comResult = key.compareTo(root.key);
        if (comResult > 0) {
            root.right = insert(key, val, root.right);
        }
        else if (comResult < 0) {
            root.left = insert(key, val, root.left);
        }
        else {
            root.val = val;
        }
        root = balance(root);
        return root;
    }

    private AVLNode balance(AVLNode root) {
        if (root == null)
            return null;
        if (height(root.left) - height(root.right) > 1) {
            if (height(root.left.left) >= height(root.left.right)) {
                root = rotateLeftChild(root);
            }
            else {
                root = doubleRotateLeftChild(root);
            }
        }
        else {
            if (height(root.right) - height(root.left) > 1) {
                if (height(root.right.right) >= height(root.right.left)) {
                    root = rotateRightChild(root);
                } else {
                    root = doubleRotateRightChild(root);
                }
            }
        }
        root.height = Math.max(height(root.left), height(root.right))+1;
        return root;
    }

    private AVLNode doubleRotateRightChild(AVLNode root) {
        if (root == null)
            return null;
        root.right = rotateLeftChild(root.right);
        return rotateRightChild(root);
    }

    private AVLNode rotateRightChild(AVLNode root) {
        if (root == null)
            return null;
        AVLNode newRoot = root.right;
        root.right = newRoot.left;
        newRoot.left = root;
        root.height = Math.max(height(root.left), height(root.right))+1;
        newRoot.height = Math.max(height(newRoot.left), height(newRoot.right))+1;
        return newRoot;
    }

    private AVLNode doubleRotateLeftChild(AVLNode root) {
        if (root == null)
            return null;
        root.left = rotateRightChild(root.left);
        return rotateLeftChild(root);
    }

    private AVLNode rotateLeftChild(AVLNode root) {
        if (root == null)
            return null;
        AVLNode newRoot = root.left;
        root.left = newRoot.right;
        newRoot.right = root;
        root.height = Math.max(height(root.left), height(root.right))+1;
        newRoot.height = Math.max(height(newRoot.left), height(newRoot.right))+1;
        return newRoot;
    }

    private int height(AVLNode node) {
        return node == null ? 0 : node.height;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private class AVLNode {
        int height;
        K key;
        V val;
        AVLNode left;
        AVLNode right;

        public AVLNode(K key, V val) {
            this(key, val, null, null);
        }

        public AVLNode(K key, V val, AVLNode left, AVLNode right) {
            this.key = key;
            this.val = val;
            this.left = left;
            this.right = right;
            this.height = 1;
        }
    }

    public static void main(String[] args) {
        Integer[] arr = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        AVLTree<Integer, String> tree = new AVLTree<>();
        for (Integer num : arr) {
            tree.insert(num, num+"");
        }
        System.out.println(tree.size());
        tree.printTreePre();
        System.out.println("================");
        tree.printTreeIn();
        System.out.println("================");
        tree.printTreePost();
        System.out.println("================");
        tree.remove(8);
        tree.printTreeIn();
        System.out.println("================");
        tree.remove(3);
        tree.printTreeIn();
        System.out.println("================");
        System.out.println(tree.size());
    }
}
