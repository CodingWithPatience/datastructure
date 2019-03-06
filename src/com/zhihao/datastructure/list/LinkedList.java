package com.zhihao.datastructure.list;

/**
 * @auther: zzh
 * @date: 2019/1/28
 */
public class LinkedList<E> {

    private int size;
    private Node head;
    private Node tail;
    private Node dummyHead;

    public LinkedList() {
        dummyHead = new Node(null);
        size = 0;
    }

    public E remove(int index) {
        if (index >= size || index < 0)
            throw new IllegalStateException("Invalid index");
        if (isEmpty())
            throw new IllegalStateException("LinkedList is empty");
        Node prev = dummyHead;
        for (int i=0; i<index; i++)
            prev = prev.next;
        Node delNode = prev.next;
        E e = delNode.e;
        if (delNode == tail && size > 1) {
            tail = prev;
        }
        if (delNode == head) {
            head = delNode.next;
            dummyHead.next = head;
        }
        size--;
        return e;
    }

    public boolean contains(E e) {
        Node node = head;
        while (node != null) {
            if (node.e.equals(e))
                return true;
        }
        return false;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size-1);
    }

    public E get(int index) {
        if (index >= size || index < 0)
            throw new IllegalStateException("Invalid index");
        if (isEmpty())
            throw new IllegalStateException("LinkedList is empty");
        Node node = head;
        for (int i=0; i<index; i++)
            node = node.next;
        return node.e;
    }

    public E getFirst() {
        return get(0);
    }

    public void add(int index, E e) {
        if (index > size || index < 0)
            throw new IllegalStateException("Invalid index");
        Node prev = dummyHead;
        for (int i=0; i<index; i++)
            prev = prev.next;
        Node node = new Node(e);
        node.next = prev.next;
        prev.next = node;
        if (index == size)
            tail = node;
        if (index == 0) {
            head = node;
            dummyHead.next = head;
        }
        size++;
    }

    public void addFirst(E e) {
        add(0, e);
    }

    public void addLast(E e) {
        if (tail == null) {
            tail = new Node(e);
            head = tail;
            dummyHead.next = head;
            size++;
            return;
        }
        Node node = new Node(e);
        tail.next = node;
        tail = node;
        size++;
    }

    public void add(E e) {
        addFirst(e);
    }

    public Node getHead() {
        return head;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LinkedList: head [");
        Node node = head;
        while (node != null) {
            sb.append(node.e+"->");
            node = node.next;
        }
        sb.append("NULL] tail");
        return sb.toString();
    }

    private class Node {
        public E e;
        public Node next;

        public Node(E e) {
            this(e, null);
        }

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i=0; i<10; i++) {
            list.add(i);
            System.out.println(list);
        }
    }
}
