package com.liujin.algorithm.simpleSorting;

import java.util.Arrays;

//选择排序
public class Code01_SelectionSort {
    public static void selectionSort(int [] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        // 0 ~ N-1  找到最小值，在哪，放到0位置上
        // 1 ~ n-1  找到最小值，在哪，放到1 位置上
        // 2 ~ n-1  找到最小值，在哪，放到2 位置上
        for (int i = 0; i < arr.length; i++) {
            int minindex = i;
            for (int j = i + 1; j < arr.length; j++){// i ~ N-1 上找最小值的下标
                minindex = arr[minindex] < arr[j] ? minindex : j;
            }
            swap(arr, i, minindex);
        }
    }

    public static void swap(int [] arr, int a, int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    //fot test
    public static int [] generateRandomArray(int maxSize, int maxValue){
        int [] arr = new int[(int) (Math.random() * (maxSize + 1))];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((Math.random() * (maxValue + 1)) - (Math.random() * maxValue));
        }
        return arr;
    }
    //for test
    public static boolean isEqual(int [] arr1, int [] arr2){

        if (arr1 == null && arr2 != null || arr1 != null && arr2 == null){
            return false;
        }
        if (arr1 == null && arr2 == null){
            return true;
        }
        if (arr1.length != arr2.length){
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]){
                return false;
            }
        }
        return true;
    }
    public static int [] copyArray(int [] arr){
        int [] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }
    public static void comparator(int [] arr){
        Arrays.sort(arr);
    }
    public static void printArray(int [] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int maxSize = 10;
        int maxValue = 100;
        int testTime = 500000;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int [] arr = generateRandomArray(maxSize,maxValue);
            int [] arr1 = copyArray(arr);
            int [] arr2 = copyArray(arr);
            comparator(arr1);
            selectionSort(arr2);
            if (!isEqual(arr1,arr2)){
                succeed = false;
                printArray(arr);
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

    }
}
