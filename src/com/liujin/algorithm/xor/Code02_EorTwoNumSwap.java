package com.liujin.algorithm.xor;
//如何不用额外变量交换两数
public class Code02_EorTwoNumSwap {
    //i和j不能是同一个位置，否则出错
    public static void swap(int [] arr,int i, int j){
        arr[i] = arr[i] ^ arr[j];//i = i ^ j j = j
        arr[j] = arr[j] ^ arr[i];//i = i ^ j j = j ^ j ^ i(一抵消j = i)
        arr[i] = arr[j] ^ arr[i];//j = i i = j ^ i ^ i(一抵消i = j)
    }
}
