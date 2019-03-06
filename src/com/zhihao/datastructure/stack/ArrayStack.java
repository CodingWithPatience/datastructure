package com.zhihao.datastructure.stack;

import com.zhihao.datastructure.array.Array;

/**
 * @auther: zzh
 * @date: 2019/1/28
 */
public class ArrayStack<E> implements Stack<E> {

    private Array<E> data;

    public ArrayStack(int capacity) {
        data = new Array<>(capacity);
    }
    public ArrayStack() {
        this(10);
    }

    @Override
    public E pop() {
        if (isEmpty())
            return null;
        return data.removeLast();
    }

    @Override
    public void push(E e) {
        data.addList(e);
    }

    @Override
    public E peek() {
        if (isEmpty())
            return null;
        return data.getLast();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("ArrayStack: size=%d, capacity=%d\n", data.size(), data.getCapacity()));
        sb.append("[");
        for (int i=0; i<data.size(); i++) {
            sb.append(data.get(i));
            if (i != data.size()-1)
                sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        ArrayStack<Integer> stack = new ArrayStack<>();
        for (int i=0; i<10; i++) {
            stack.push(i);
            System.out.println(stack);
        }
        System.out.println(stack.peek());
        for (int i=0; i<10; i++) {
            System.out.println(stack);
            stack.pop();
        }
        System.out.println(stack);
    }
}
