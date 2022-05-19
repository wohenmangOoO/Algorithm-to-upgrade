package com.liujin.algorithm.simpleSorting;

import java.util.Arrays;

//插入排序
public class Code03_InsertionSort {
    public static void insertionSort(int [] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        //0...1做到有序
        //0...2做到有序
        //0...i做到有序
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0 && arr[j] > arr[j+1]; j--){
                swap(arr, j, j+1);
            }
        }
    }
    public static void swap(int [] arr, int a, int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
    //for test
    public static int [] generateRandomArray(int maxSize, int maxValue){
        int [] arr = new int[(int) (Math.random() * (maxSize + 1))];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1) - (int)(Math.random() * maxValue));
        }
        return arr;
    }
    //for test
    public static int [] copyArray(int [] arr){
        int [] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }
    //fot test
    public static void printArray(int [] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "");
        }
        System.out.println();
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
    public static void compartor(int [] arr){
        Arrays.sort(arr);
    }

    public static void main(String[] args) {
        int maxsize = 100;
        int maxValue = 100;
        int testTime = 500000;
        boolean succeed = true;
        System.out.println("begin！");
        for (int i = 0; i < testTime; i++) {
            int [] arr = generateRandomArray(maxsize, maxValue);
            int [] arr1 = copyArray(arr);
            int [] arr2 = copyArray(arr);
            compartor(arr1);
            insertionSort(arr2);
            if (!isEqual(arr1,arr2)){
                succeed = false;
                printArray(arr);
                printArray(arr1);
                printArray(arr1);
                break;
            }
        }
        System.out.println(succeed ? "Nice！" : "Fucking fucked!");
    }
}
