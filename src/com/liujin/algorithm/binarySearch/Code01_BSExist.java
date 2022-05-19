package com.liujin.algorithm.binarySearch;

import java.util.Arrays;

//Binary Search二分查找 是否存在
public class Code01_BSExist {
    public static boolean bsExist(int [] arr, int num){
        if (arr == null || arr.length == 0){
            return false;
        }
        int mid = 0;
        int L = 0;
        int R = arr.length - 1;
        while (L < R){//L..R 至少两个数的时候
            //mid =（L + R）/ 2 不安全内存溢出 >>带符号左移
            mid = L + ((R - L) >> 1);
            if (arr[mid] == num){
                return true;
            }else if (arr[mid] > num){
                R = mid - 1;
            }else {
                L = mid + 1;
            }
        }
        return arr[L] == num;

    }
    //for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }
    //for test
    public static boolean isEquals(int [] arr, int num){
        for (int cur : arr) {
            if (cur == num){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int maxSize = 100;
        int maxValue = 100;
        int testtime = 500000;
        boolean succeed = true;
        System.out.println("begin");
        for (int i = 0; i < testtime; i++) {
            int [] arr = generateRandomArray(maxSize, maxValue);
            Arrays.sort(arr);
            int num = (int) ((Math.random() * (maxValue + 1)) - (int)(Math.random() * maxValue));
            if (bsExist(arr, num) != isEquals(arr, num)){
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }

}
