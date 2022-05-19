package com.liujin.algorithm.xor;
//一个数组中有一种数出现了奇数次，其他都出现了偶数次，怎么找到并打印这种数
public class Code01_EvenTimeOddTimes {
    //arr中，只有一种数，出现奇数次
    public static void printOddTimesNum(int [] arr){
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        System.out.println(eor);
    }
}
