package com.zhihao.datastructure;

import com.zhihao.datastructure.array.Array;

/**
 * @auther: zzh
 * @date: 2019/1/28
 */
public class Main {
    public static void main(String[] args) {
        Array<Integer> array = new Array<>();
        for (int i=0; i<10; i++) {
            array.addList(i);
        }
        array.addFirst(10);
        array.add(5, 0);
        array.remove(3);
        array.remove();
        System.out.println(array);
    }
}
