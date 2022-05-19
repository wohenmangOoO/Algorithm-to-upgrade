package com.liujin.algorithm.binarySearch;
//二分查找 局部最小值问题
//arr[0] < arr[1]
//arr[N-1] < arr[N-2]
// >[i]<
public class Code04_BSAwesome {
    public static int getLessIndex(int [] arr){
        if (arr == null || arr.length == 0){
            return -1; // no exist
        }
        if (arr.length == 1 || arr[0] < arr[1]){
            return 0;
        }
        if (arr[arr.length-2] > arr[arr.length - 1]){
            return arr.length - 1;
        }
        int left = 1;
        int right = arr.length - 2;
        int mid = 0;
        while (left < right) {
            mid  = left + ((right - left) >> 1);
            if (arr[mid] > arr[mid + 1]){
                left = mid + 1;
            }else if (arr[mid] > arr[mid - 1]){
                right = mid - 1;
            }else {
                return mid;
            }

        }
        return left;
    }
}
