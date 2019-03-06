package com.zhihao.datastructure.graph;

import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * @auther: zzh
 * @date: 2019/1/5
 */
public class Graph<E> {

    private int size;

    private  int maxWeight;

    private E[] vertexes;

    private int[][] matrix;

    public Graph(E[] vertexes, int[][] matrix, int maxWeight) {
        this.vertexes = vertexes;
        this.matrix = matrix;
        this.size = matrix.length;
        this.maxWeight = maxWeight;
    }

    public int countInDegree(int index) {
        int edge = 0;
        for (int i=0; i<size; i++) {
            if (matrix[i][index] != 0 && matrix[i][index] != maxWeight) {
                edge++;
            }
        }
        return edge;
    }

    public int countOutDegree(int index) {
        int edge = 0;
        for (int i=0; i<size; i++) {
            if (matrix[index][i] != 0 && matrix[index][i] != maxWeight) {
                edge++;
            }
        }
        return edge;
    }

    public int countInDegree(E vertex) {
        int index = findIndex(vertex);
        if (index == -1)
            throw new NoSuchElementException();
        return countInDegree(index);
    }

    public int countOutDegree(E vertex) {
        int index = findIndex(vertex);
        if (index == -1)
            throw new NoSuchElementException();
        return countOutDegree(index);
    }

    public int lowestCost() {
        return lowestCost(0);
    }

    public int lowestCost(E vertex) {
        int index = findIndex(vertex);
        return lowestCost(index);
    }

    private int lowestCost(int index) {
        int cost = 0;
        final int len = size;
        int[] costArr = new int[len];
        boolean[] isVisited = new boolean[len];
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(index);
        for (int i=0; i<len; i++) {
            costArr[i] = this.maxWeight;
        }
        while(!queue.isEmpty()) {
            int i = queue.pop();
            if (!isVisited[i]) {
                for (int j = 0; j < len; j++) {
                    if (j == index)
                        continue;
                    int weight = matrix[i][j];
                    if (weight != 0 && weight != maxWeight) {
                        if (costArr[j] == maxWeight && !isVisited[j]) {
                            costArr[j] = weight;
                            queue.add(j);
                        } else if (costArr[j] != maxWeight && costArr[j] > weight && !isVisited[j]) {
                            costArr[j] = weight;
                            queue.add(j);
                        }
                    }
                }
                isVisited[i] = true;
            }
        }
        for (int i=0; i<len; i++) {
            System.out.print(costArr[i]+" ");
            if (costArr[i] != 0 && costArr[i] != maxWeight)
                cost += costArr[i];
        }
        System.out.println();
        return cost;
    }

    private int findIndex(E vertex) {
        for (int i=0; i<size; i++) {
            if (vertex == vertexes[i]) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Integer[] vertexes = {1, 2, 3, 4, 5, 6};
        int[][] matrix = new int[6][6];
        int[] arr0 = {0, 3, 7, -1, -1, -1};
        int[] arr1 = {-1, 0, -1, -1, -1, 4};
        int[] arr2 = {-1, -1, 0, -1, 9, -1};
        int[] arr3 = {0, 6, 8, 0, -1, -1};
        int[] arr4 = {2, -1, -1, 5, 0, -1};
        int[] arr5 = {-1, -1, -1, -1, 1, 0};
        matrix[0] = arr0;
        matrix[1] = arr1;
        matrix[2] = arr2;
        matrix[3] = arr3;
        matrix[4] = arr4;
        matrix[5] = arr5;

        Graph graph = new Graph(vertexes, matrix, -1);
        for (int index = 0; index < vertexes.length; index++) {
            System.out.println("顶点" + vertexes[index] + "的入度为" + graph.countInDegree(vertexes[index]));
            System.out.println("顶点" + vertexes[index] + "的出度为" + graph.countOutDegree(vertexes[index]));
            System.out.println("=======================================");
        }
        System.out.println("lowest cost "+graph.lowestCost());
    }
}
