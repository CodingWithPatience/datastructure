package com.zhihao.datastructure.tree;

/**
 * @auther: zzh
 * @date: 2019/2/3
 */
public class RBTree<K extends Comparable, V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private RBTNode root;

    private int size;

    public RBTree() {
        this.root = null;
        this.size = 0;
    }

    public V insert(K key, V val) {
        if (key == null)
            throw new IllegalStateException("Key can not be null");
        RBTNode node = getNode(key);
        V oldVal = null;
        if (node != null)
            oldVal = node.val;
        root = insert(key, val, root);
        if (root.color == RED)
            root.height++;
        root.color = BLACK;
        return oldVal;
    }

//    public V remove(K key) {
//        if (key == null)
//            throw new IllegalStateException("Key can not be null");
//        RBTNode node = getNode(key);
//        if (node != null) {
//            V oldVal = node.val;
//            root = remove(key, root);
//            if (root.color == RED) {
//                root.color = BLACK;
//                root.height++;
//            }
//            root.parent = null;
//            return oldVal;
//        }
//        return null;
//    }

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
        RBTNode node = getNode(key);
        if (node != null)
            return node.val;
        return null;
    }

    public RBTNode getRoot() {
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

    private void printTreePre(RBTNode root) {
        if (root!=null) {
            System.out.print("Key:"+root.key+" Value:"+root.val+
                " Color:"+(root.color?"red":"black")+" Height:"+root.height);
            if (root.parent!=null)
                System.out.println(" Parent:"+root.parent.val);
            else
                System.out.println(" Parent:NULL");
            printTreePre(root.left);
            printTreePre(root.right);
        }
    }

    private void printTreeIn(RBTNode root) {
        if (root!=null) {
            printTreeIn(root.left);
            System.out.print("Key:"+root.key+" Value:"+root.val+
                " Color:"+(root.color?"red":"black")+" Height:"+root.height);
            if (root.parent!=null)
                System.out.println(" Parent:"+root.parent.val);
            else
                System.out.println(" Parent:NULL");
            printTreeIn(root.right);
        }
    }

    private void printTreePost(RBTNode root) {
        if (root!=null) {
            printTreePost(root.left);
            printTreePost(root.right);
            System.out.print("Key:"+root.key+" Value:"+root.val+
                    " Color:"+(root.color?"red":"black")+" Height:"+root.height);
            if (root.parent!=null)
                System.out.println(" Parent:"+root.parent.val);
            else
                System.out.println(" Parent:NULL");
        }
    }

    private RBTNode getNode(K key) {
        if (key == null)
            return null;
        return getNode(root, key);
    }

    private RBTNode getNode(RBTNode node, K key) {
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

    private RBTNode getNode(RBTNode node, V val) {
        if (node == null || val == null)
            return null;
        if (node.val.equals(val))
            return node;
        RBTNode left = getNode(node.left, val);
        if (left == null)
            return getNode(node.right, val);
        return left;
    }

//    private RBTNode remove(K key, RBTNode root) {
//        if (root == null || key == null)
//            return null;
//        int comResult = key.compareTo(root.key);
//        if (comResult > 0) {
//            RBTNode right = remove(key, root.right);
//            root.right = right;
//            if (right != null)
//                right.parent = root;
//        }
//        else if (comResult < 0) {
//            RBTNode left = remove(key, root.left);
//            root.left = left;
//            if (left != null)
//                left.parent = root;
//        }
//        else if (root.left!=null && root.right!=null) {
//            RBTNode min = findMin(root.right);
//            root.key = min.key;
//            root.val = min.val;
//            root.right = remove(root.key, root.right);
//        }
//        else {
//            root = root.left == null ? root.right : root.left;
//            size--;
//        }
//        if (root != null) {
//            int height;
//            if (root.left != null)
//                height = height(root.left);
//            else
//                height = height(root.right);
//            if (root.color == RED)
//                root.height = height;
//            else
//                root.height = height+1;
//
//            if (root.left == null && root.right != null &&
//                    root.color == BLACK && root.right.color == BLACK) {
//                root = rightToLeft(root);
//            }
//        }
//        root = keep(root);
//        root = balance(root);
//        return keep(root);
//    }

    private RBTNode rightToLeft(RBTNode parent) {
        if (parent == null || parent.right == null)
            return null;
        RBTNode right = parent.right;
        parent.right = right.left;
        parent.height = 1;
        right.height = 2;
        right.left = parent;
        parent.parent = right;
        return right;
    }

    private boolean isLeft(RBTNode root) {
        if (root.parent != null && root.parent.left == root)
            return true;
        return false;
    }

    private RBTNode balance(RBTNode root) {
        if (root != null && root.left != null && root.right != null) {
            if (height(root.left) > height(root.right))
                return rotateLeft(root);
            else if (height(root.left) < height(root.right))
                return rotateRight(root);
        }
        return root;
    }

    private RBTNode rotateLeft(RBTNode root) {
        if (root == null)
            return null;
        RBTNode left = root.left;
        if (left.right != null)
            left.right.parent = root;
        root.left = left.right;
        left.parent = root.parent;
        left.right = root;
        root.parent = left;

        if (root.color == BLACK && left.color == BLACK) {
            changeColor(root, RED);
        }
        if (root.color == BLACK)
            root.height = height(root.right)+1;
        else
            root.height = height(root.right);

        if (left.color == BLACK)
            left.height = height(root)+1;
        else
            left.height = height(root);
        return left;
    }

    private void changeColor(RBTNode root, boolean color) {
        if (root == null)
            return;
        if (color == RED && root.color == BLACK) {
            root.color = color;
            root.height = height(root) - 1;
        }
    }

    private RBTNode rotateRight(RBTNode root) {
        if (root == null)
            return null;
        RBTNode right = root.right;
        if (right.left != null)
            right.left.parent = root;
        root.right = right.left;
        right.parent = root.parent;
        right.left = root;
        root.parent = right;

        if (root.color == BLACK && right.color == BLACK)
            changeColor(root, RED);
        if (root.color == BLACK) {
            root.height = height(root.left) + 1;
        }
        else
            root.height = height(root.left);

        if (right.color == BLACK)
            right.height = height(root)+1;
        else
            right.height = height(root);
        return right;
    }

    private RBTNode rightSibling(RBTNode root) {
        if (root == null || root.parent == null)
            return null;
        return root.parent.right;
    }

    private RBTNode findMin(RBTNode node) {
        if (node == null)
            return null;
        while (node.left!=null) {
            node = node.left;
        }
        return node;
    }

    private RBTNode insert(K key, V val, RBTNode root) {
        if (root == null) {
            size++;
            return new RBTNode(key, val);
        }
        int comResult = key.compareTo(root.key);
        if (comResult > 0) {
            RBTNode right = insert(key, val, root.right);
            root.right = right;
            int height;
            if (root.left != null)
                height = height(root.left);
            else
                height = height(root.right);
            if (root.color == BLACK)
                root.height = height+1;
            else
                root.height = height;
            right.parent = root;
        }
        else if (comResult < 0) {
            RBTNode left = insert(key, val, root.left);
            root.left = left;
            int height;
            if (root.left != null)
                height = height(root.left);
            else
                height = height(root.right);
            if (root.color == BLACK)
                root.height = height+1;
            else
                root.height = height;

            left.parent = root;
        }
        else {
            root.val = val;
        }
        return keep(root);
    }

    private int height(RBTNode node) {
        return node == null ? 0 : node.height;
    }

    private RBTNode flipColors(RBTNode root) {
        root.color = RED;
        root.left.color = BLACK;
        root.left.height++;
        root.right.color = BLACK;
        root.right.height++;
        root.height = root.left.height;
        return root;
    }

    private boolean isRed(RBTNode node) {
        if (node == null)
            return false;
        return node.color;
    }

    private RBTNode rightRotation(RBTNode root) {
        if (root == null)
            return null;
        RBTNode retNode = root.left;
        if (retNode.right != null)
            retNode.right.parent = root;
        root.left = retNode.right;
        root.height = height(retNode.right);
        retNode.right = root;
        retNode.parent = root.parent;

        retNode.height++;
        retNode.color = root.color;
        root.color = RED;
        root.parent = retNode;
        return retNode;
    }

    private RBTNode leftRotation(RBTNode root) {
        if (root == null)
            return null;
        RBTNode right = root.right;
        if (right.left != null)
            right.left.parent = root;
        root.right = right.left;
        root.height = height(right.left);
        right.left = root;
        right.parent = root.parent;

        if (root.color == BLACK) {
            right.height++;
        }
        right.color = root.color;
        root.color = RED;
        root.parent = right;
        return right;
    }

    private RBTNode keep(RBTNode root) {
        if (root == null)
            return null;
        if (isRed(root.right) && !isRed(root.left))
            root = leftRotation(root);
        if (isRed(root.left) && isRed(root.left.left))
            root = rightRotation(root);
        if (isRed(root.left) && isRed(root.right))
            root = flipColors(root);
        return root;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private class RBTNode {
        boolean color;
        int height;
        K key;
        V val;
        RBTNode parent;
        RBTNode left;
        RBTNode right;

        public RBTNode(RBTNode node) {
            this.key = node.key;
            this.val = node.val;
            this.height = node.height;
            this.color = node.color;
            this.parent = node.parent;
            this.left = node.left;
            this.right = node.right;
        }

        public RBTNode(K key, V val) {
            this(key, val, null);
        }

        public RBTNode(K key, V val, RBTNode parent) {
            this(key, val, parent, null, null);
        }

        public RBTNode(K key, V val, RBTNode parent, RBTNode left, RBTNode right) {
            this.key = key;
            this.val = val;
            this.parent = parent;
            this.left = left;
            this.right = right;
            this.color = true;
            this.height = 0;
        }
    }

    public static void main(String[] args) {
        Integer[] arr = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};//,4,5,6,7,8,9,10,11};
        RBTree<Integer, String> tree = new RBTree<>();
        for (Integer num : arr) {
            tree.insert(num, num+"");
        }
        System.out.println(tree.size());
        tree.printTreePre();
        System.out.println("================");
//        tree.remove(8);
//        tree.printTreePre();
//        System.out.println("================");
//        tree.remove(12);
//        tree.printTreePre();
//        System.out.println("================");
//        tree.remove(4);
//        tree.printTreePre();
//        System.out.println("================");
//        tree.remove(5);
//        tree.printTreePre();
//        System.out.println("================");
//        tree.remove(1);
//        tree.printTreePre();
//        System.out.println("================");
//        tree.remove(10);
//        tree.printTreePre();
    }
}
