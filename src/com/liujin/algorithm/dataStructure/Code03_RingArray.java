package com.liujin.algorithm.dataStructure;
//用数组实现栈和队列
public class Code03_RingArray {

    public static class MyStack{
        private int [] arr;
        private int pushi;//end
        private int popi;//begin
        //靠size来解耦，该不该继续
        private int size;
        private final int limit;
        //构造器
        public MyStack(int limit) {
            arr = new int[limit];
            pushi = 0;
            popi = 0;
            size = 0;
            this.limit = limit;
        }
        //如果现在下标是i，返回下一个位置
        private  int nextIndex(int i){
            return i < limit-1 ? i+1 : 0;
        }
        //如果下标是i，返回上一个位置
        private int previousIndex(int i){
            return i > 0 ? i-1 : limit-1;
        }
        public void push(int value){
            if (size == limit){
                throw new RuntimeException("栈满了，不能再加了");
            }
            size++;
            arr[pushi] = value;
            //如果没到底就+1，如果到底回到0位置
            pushi = nextIndex(pushi);
        }

        public int pop(){
            if (size == 0){
                throw new RuntimeException("栈空了，不能再拿了");
            }
            //后进先出，,此时说明有，从end开始拿
            size--;
            int ans = arr[pushi];
            //如果没到底就-1，如果到底回到数组尾位置
            pushi = previousIndex(pushi);
            return ans;
        }
    }
    public static class MyQueue{
        private int [] arr;
        private int pushi;
        private int polli;
        private int size;
        //限制长度不可修改
        private final int limit;
        //构造器
        public MyQueue(int limit) {
            pushi = 0;
            polli = 0;
            size = 0;
            this.limit = limit;
        }
        //如果现在下标是i，返回下一个位置
        private int nextIndex(int i){
            return i < limit-1 ? i+1 : 0;
        }
        public void push(int value){
            if (size == limit){
                throw new RuntimeException("队列满了，不能再加了");
            }
            size++;
            arr[pushi] = value;
            //如果没到底就+1，如果到底回到0位置
            pushi = nextIndex(pushi);
        }
        public int poll(){
            if (size == 0){
                throw new RuntimeException("队列空了，不能再拿了");
            }
            size--;
            int ans = arr[polli];
            //如果没到底就+1，如果到底回到0位置
            polli = nextIndex(polli);
            return ans;
        }
    }

}
