package com.liujin.algorithm.xor;


import java.util.HashMap;
import java.util.HashSet;

//一个数组中有一种数出现k次，其他数都出现M次，M > 1,K < M，找到，出现了K次的数，要求额外空间复杂度O(1),时间复杂度O(N)
public class Code04_KM {
    public static HashMap<Integer,Integer> map = new HashMap<>();
    // 请保证arr中，只有一种数出现了K次，其他数都出现了M次
    public static int onlyKTimes(int [] arr, int K, int M){
        //如果map下标图没创建好，就创建一下
        if (map.size() == 0){
            mapCreater(map);
        }
        int [] t = new int[32];
        //把数组中的每一个值已经二进制形式放到t数组中
        // t[0] 0位置的1出现了几个
        // t[i] i位置的1出现了几个
        for (int num : arr) {
            while (num != 0){
                //找出最右侧的1，依次推进放到数组里0000100
                int rightOne = num & (~num + 1);
                //根据map key（二进制1、2、4、8）找出当前下标位置给数组（0、1、2、3）
                t[map.get(rightOne)]++;
                //抵消最右侧的1 0010110 ^ 0000010 = 0010100 相同为0不同为1
                num ^= rightOne;
            }
        }
        int ans = 0;
        for (int i = 0; i < 32; i++){
            //=0当前位置说明没有k !=0 说明一定有k
            // 0 & M = 0有可能k个0根本就进不去
            if (t[i] % M != 0){
                if (t[i] % M == K){
                    //把ans0000 i位置设置为1 ｜只要有一个1就是1
                    ans |= (1 << i);
                }else {
                    return -1;
                }
            }
        }
        //0 & M = 0 k个0返回0是对的，但是如果0的次数!=k就错了应该返回-1
        if (ans == 0){
            int count = 0;
            for (int num : arr) {
                if (num == 0){
                    count++;
                }
            }
            if (count != K) {
                return -1;
            }
        }
        return ans;
    }
    //根据hashmap key位置来找数组下标位置 key二进制位置，value数组下标位置
    public static void mapCreater(HashMap<Integer,Integer> map){
        int value = 1;
        for (int i = 0; i < 32; i++){
            map.put(value, i);
            //二进制一步步推进1、2、4、8、16...
            value <<= 1;
        }
    }
    // 更简洁的写法
    public static int km(int[] arr, int k, int m) {
        int[] help = new int[32];
        for (int num : arr) {
            for (int i = 0; i < 32; i++) {
                help[i] += (num >> i) & 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            help[i] %= m;
            if (help[i] != 0) {
                ans |= 1 << i;
            }
        }
        return ans;
    }
    public static int[] randomArray(int maxKinds, int range, int k, int m){
        int kTimesNum = randomNum(range);
        //以百分之50概率k的范围是否要k 随机的数一定要k < m
        int times = (Math.random() + 0.1) < 0.5 ? k : (int) (Math.random() * (m - 1) + 1);
        int numkinds =(int) (Math.random() * maxKinds) + 2;
        int [] arr = new int[times + (numkinds - 1) * m];
        int index = 0;
        //一共有k = times个数 0到times-1
        for (;index < times; index++){
            arr[index] = kTimesNum;
        }
        numkinds--;
        //为了确保每一个数唯一
        HashSet<Integer> set = new HashSet<>();
        set.add(kTimesNum);
        while (numkinds != 0){
            int curNum = 0;
           do {
               curNum = randomNum(range);
           }while (set.contains(curNum));
           set.add(curNum);
           for (int i = 0; i < m; i++){
               arr[index++] = curNum;
           }
           numkinds--;
        }
        //arr填满了,打乱他们
        for (int i = 0; i < arr.length; i++) {
            int j = (int) (Math.random() * arr.length);
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        return arr;
    }
    public static int test(int [] arr, int k, int m){
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int num : arr) {
            if (map.containsKey(num)){
                map.put(num, map.get(num) + 1);
            }else {
                map.put(num,1);
            }
        }
        for (int num : map.keySet()){
            if (map.get(num) == k){
                return num;
            }
        }
        return -1;
    }
    public static int randomNum(int range){
        return (int) (Math.random() * (range + 1)) - (int)(Math.random() * (range + 1));
    }

    public static void main(String[] args) {
        int testTime = 10000;
        int maxKinds = 6;
        int k = 2;
        int m = 7;
        int range = 10;
        System.out.println("begin");
        for (int i = 0; i < testTime; i++) {
            int [] arr = randomArray(maxKinds, range, k, m);
            if (onlyKTimes(arr, k, m) != test(arr, k, m)){
                System.out.println("错误");
            }
        }
        System.out.println("end");

    }

}
