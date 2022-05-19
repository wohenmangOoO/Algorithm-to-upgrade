package com.liujin.algorithm.xor;
//一个数组中有两种数出现了奇数次，其他都出现了偶数次，怎么找到并打印这两种数
public class Code03_PrintOddTimesNum {
    //arr中，两种数出现了奇数次
    public static void printOddTimesNum(int [] arr){
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            //eor = a ^ b
            eor ^= arr[i];
        }
        //提取出一个数最右侧1 a != b 一定会有1
        int rightOne = eor & (-eor);
        int onlyOne = 0;//eor'
        for (int i = 0; i < arr.length; i++) {
            //只有最右侧位置是1才能进，0不能进
            if ((arr[i] & onlyOne) != 0 ){
                //a = a
                onlyOne ^= arr[i];
            }
        }
        //b = b
        eor = eor ^ onlyOne;
    }
}
