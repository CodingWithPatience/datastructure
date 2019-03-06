package com.zhihao.datastructure.stack;

import com.zhihao.datastructure.list.LinkedList;

/**
 * @auther: zzh
 * @date: 2019/1/28
 */
public class LinkedListStack<E> implements Stack<E> {

    private LinkedList<E> list;

    public LinkedListStack() {
        list = new LinkedList<>();
    }

    @Override
    public E pop() {
        if (isEmpty())
            return null;
        return list.removeFirst();
    }

    @Override
    public void push(E e) {
        list.addFirst(e);
    }

    @Override
    public E peek() {
        return list.getFirst();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LinkedListStack: top [");
        sb.append(list.toString()).append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        LinkedListStack<Integer> stack = new LinkedListStack<>();
        for (int i=0; i<10; i++) {
            stack.push(i);
            System.out.println(stack);
        }
        for (int i=0; i<10; i++) {
            stack.pop();
            System.out.println(stack);
        }
    }
}
