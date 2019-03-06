package com.zhihao.datastructure.tree;

/**
 * @auther: zzh
 * @date: 2018/12/25
 */
public class BSTree<T extends Comparable> {

    private TreeNode root;

    private int size;

    public BSTree() {
        root = null;
        size = 0;
    }

    public BSTree(TreeNode root) {
        this.root = root;
        size = 0;
    }

    public boolean insert(T data) {
        if (data == null)
            return false;
        if (root == null) {
            root = new TreeNode(data);
            size++;
            return true;
        }
        TreeNode node = root;
        TreeNode parent = root;
        int child = 0;              // 0:left; 1:right
        while (node!=null) {
            parent = node;
            int result = data.compareTo(node.val);
            if (result > 0) {
                node = node.right;
                child = 1;
            }
            else if (result < 0) {
                node = node.left;
                child = 0;
            }
            else
                return false;
        }

        if (child == 0)
            parent.left = new TreeNode(data);
        else
            parent.right = new TreeNode(data);
        size++;
        return true;
    }

    public boolean contains(T data) {
        if (isEmpty())
            return false;

        TreeNode node = root;
        while (node!=null) {
            int result = data.compareTo(node.val);
            if (result > 0) {
                node = node.right;
            }
            else if (result < 0) {
                node = node.left;
            }
            else
                return true;
        }
        return false;
    }

    public void remove(T data) {
        if (isEmpty())
            return;

        root = remove(data, root);
    }

    private TreeNode remove(T data, TreeNode node) {
        if (node==null)
            return null;
        int result = data.compareTo(node.val);
        if (result > 0) {
            node.right = remove(data, node.right);
        }
        else if (result < 0) {
            node.left = remove(data, node.left);
        }
        else if (node.left!=null && node.right!=null) {
            node.val = findMin(node.right).val;
            node.right = remove(node.val, node.right);
        }
        else {
            node = (node.left == null) ? node.right : node.left;
            size--;
        }
        return node;
    }

    public T findMin() {
        if (isEmpty())
            return null;
        return findMin(root).val;
    }

    public T findMax() {
        if (isEmpty())
            return null;
        return findMax(root).val;
    }

    private TreeNode findMin(TreeNode node) {
        if (node == null)
            return null;

        TreeNode retNode = node;
        while (node!=null) {
            retNode = node;
            node = node.left;
        }
        return retNode;
    }
    private TreeNode findMax(TreeNode node) {
        if (node == null)
            return null;

        TreeNode retNode = node;
        while (node!=null) {
            retNode = node;
            node = node.right;
        }
        return retNode;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void makeEmpty() {
        root = null;
        size = 0;
    }

    public TreeNode getRoot() {
        return this.root;
    }

    public int size() {
        return size;
    }

    public void preOrder() {
       preOrder(root);
    }

    private void preOrder(TreeNode root) {
        if (root != null) {
            System.out.println("TreeNode: "+root.val);
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(TreeNode root) {
        if (root != null) {
            preOrder(root.left);
            System.out.println("TreeNode: "+root.val);
            preOrder(root.right);
        }
    }

    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(TreeNode root) {
        if (root != null) {
            preOrder(root.left);
            preOrder(root.right);
            System.out.println("TreeNode: "+root.val);
        }
    }

    private class TreeNode {
        T val;
        TreeNode left;
        TreeNode right;
        TreeNode(T val) {
            this.val = val;
        }
    }
}
