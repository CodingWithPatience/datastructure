package com.zhihao.datastructure.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @auther: zzh
 * @date: 2018/12/28
 */
public class DoubleLinkedList<E> {

    private int size;

    private Node<E> head;

    private Node<E> tail;

    private int modCount;

    public DoubleLinkedList() {
        this.size = 0;
        this.modCount = 0;
    }

    public void add(E element) {
        addLast(element);
    }

    public E remove() {
        return removeFirst();
    }

    public E remove(int index) {
        if (isEmpty())
            throw new NoSuchElementException();
        checkIndex(index);
        if (index >= size/2) {
            Node<E> node = tail;
            for (int i=size-1; i>index; i--) {
                node = node.prev;
            }
            final Node<E> prev = node.prev;
            final Node<E> next = node.next;
            final E element = node.item;
            if (prev==null) {
                head = next;
            }
            else {
                prev.next = next;
            }
            if (next==null) {
                tail = prev;
            }
            else {
                next.prev = prev;
            }
            node.item = null;
            modCount++;
            size--;
            return element;
        }
        else {
            Node<E> node = head;
            for (int i=0; i<index; i++) {
                node = node.next;
            }
            final Node<E> prev = node.prev;
            final Node<E> next = node.next;
            final E element = node.item;
            if (prev==null) {
                head = next;
            }
            else {
                prev.next = next;
            }
            if (next==null) {
                tail = prev;
            }
            else {
                next.prev = prev;
            }
            node.item = null;
            modCount++;
            size--;
            return element;
        }
    }

    public E removeLast() {
        final Node<E> last = tail;
        if (last == null)
            throw new NoSuchElementException();
        return unLinkLast(last);
    }

    private E unLinkLast(Node<E> last) {
        Node<E> prev = last.prev;
        final E element = last.item;
        prev.next = null;
        tail = prev;
        last.prev = null;
        last.item = null;
        if (prev == null)
            head = null;
        else
            prev.next = null;
        size--;
        modCount++;
        return element;
    }

    public void addLast(E element) {
        if (isEmpty()) {
            head = new Node<>(element,null, null);
            tail = head;
            size++;
            modCount++;
            return;
        }
        final Node<E> t = tail;
        Node<E> node = new Node<>(element, t, null);
        tail = node;
        t.next = tail;
        size++;
        modCount++;
    }

    public void addFirst(E element) {
        linkFirst(element);
    }

    private void linkFirst(E first) {
        if (isEmpty()) {
            head = new Node<>(first,null, null);
            tail = head;
            size++;
            modCount++;
            return;
        }
        final Node<E> h = head;
        Node<E> newNode = new Node<>(first,null, h);
        head = newNode;
        h.prev = head;
        size++;
        modCount++;
    }

    public E getFirst() {
        final Node<E> first = head;
        if (first == null) {
            throw new NoSuchElementException();
        }
        return first.item;
    }

    public E getLast() {
        final Node<E> last = tail;
        if (last == null) {
            throw new NoSuchElementException();
        }
        return last.item;
    }

    public boolean contains(E element) {
        if (isEmpty())
            return false;
        final Node first = head;
        Node node = first;
        for (int i=0; i<size && node!=null; i++, node = node.next) {
            if (element.equals(node.item)) {
                return true;
            }
        }
        return false;
    }

    public E get(int index) {
        checkIndex(index);
        if (index >= size/2) {
            Node<E> node = tail;
            for (int i=size-1; i>index; i--) {
                node = node.prev;
            }
            return node.item;
        }
        else {
            Node<E> node = head;
            for (int i=0; i<index; i++) {
                node = node.next;
            }
            return node.item;
        }
    }

    public E set(int index, E value) {
        checkIndex(index);
        if (index >= size/2) {
            Node<E> node = tail;
            for (int i=size-1; i>index; i--) {
                node = node.prev;
            }
            E oldValue = node.item;
            node.item = value;
            modCount++;
            return oldValue;
        }
        else {
            Node<E> node = head;
            for (int i=0; i<index; i++) {
                node = node.next;
            }
            E oldValue = node.item;
            node.item = value;
            modCount++;
            return oldValue;
        }
    }



    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return head.item;
    }

    public E pop() {
        if (isEmpty())
            throw new NoSuchElementException();
        return removeFirst();
    }

    public E poll() {
        final Node<E> first = head;
        if (head == null)
            return null;
        return unLinkFirst(first);
    }

    private E removeFirst() {
        final Node<E> first = head;
        if (first == null)
            throw new NoSuchElementException();
        return unLinkFirst(first);
    }

    private E unLinkFirst(Node<E> h) {
        final Node<E> next = h.next;
        final E element = h.item;
        h.item = null;
        h.next = null;
        head = next;
        if (next == null) {
            tail = null;
        }
        else {
            next.prev = null;
        }
        size--;
        modCount++;
        return element;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    final private void checkIndex(int index) {
        if (index < 0 || index > size-1)
            throw new ArrayIndexOutOfBoundsException();
    }

    public Iterator<E> iterator() {
        return new Iter();
    }

    private static class Node<E> {
        E item;
        Node<E> prev;
        Node<E> next;

        public Node(E item, Node<E> prev, Node<E> next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    private class Iter implements Iterator<E> {

        int cursor = 0;

        int lastRet = -1;

        int expectModCount = DoubleLinkedList.this.modCount;

        @Override
        public boolean hasNext() {
            return cursor < DoubleLinkedList.this.size;
        }

        @Override
        public E next() {
            checkModCount();
            try {
                int i = cursor;
                E element = DoubleLinkedList.this.get(i);
                lastRet = i;
                cursor = i+1;
                return element;
            } catch (IndexOutOfBoundsException e) {
                checkModCount();
                throw new NoSuchElementException();
            }
        }

        @Override
        public void remove() {
            checkModCount();
            if (lastRet < 0)
                throw new IllegalStateException();
            try {
                DoubleLinkedList.this.remove(lastRet);
                expectModCount = DoubleLinkedList.this.modCount;
                if (lastRet < cursor)
                    cursor--;
                lastRet = -1;
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new ConcurrentModificationException();
            }
        }

        final private void checkModCount() {
            if (expectModCount != DoubleLinkedList.this.modCount)
                throw new ConcurrentModificationException();
        }
    }

    public static void main(String[] args) {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        for (int i=0; i<10; i++) {
            list.add(i);
        }
        for (int i=0; i<10; i++) {
            Integer num = list.get(i);
            System.out.print(num+" ");
        }
        System.out.println();
        list.set(1,5);
        while (!list.isEmpty()) {
            Integer num = list.pop();
            System.out.print(num+" ");
        }
        System.out.println();
        for (int i=20; i>0; i--) {
            list.add(i);
        }
        list.addLast(0);
        list.addFirst(0);
        list.remove();
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer num = iterator.next();
            System.out.print(num+" ");
            iterator.remove();
        }
        System.out.println();
        while (!list.isEmpty()) {
            Integer num = list.poll();
            System.out.print(num+" ");
        }
    }
}
