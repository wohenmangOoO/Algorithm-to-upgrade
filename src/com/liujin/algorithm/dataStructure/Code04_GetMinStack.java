package com.liujin.algorithm.dataStructure;

import java.util.Stack;

//实现一个特殊的栈，在基本功能基础上，在实习一个返回栈中最小元素的功能，要求push、pop、getMin操作时间复杂度O(1)
public class Code04_GetMinStack {
    public static class MyStack1 {
        private Stack<Integer> stackData;
        private Stack<Integer> stackMin;

        //构造器
        public MyStack1() {
            this.stackData = new Stack<>();
            this.stackMin = new Stack<>();
        }

        public void push(int newNum) {
            if (this.stackMin.isEmpty()) {
                this.stackMin.push(newNum);
            } else if (newNum <= this.getMin()) {
                //<=放进去>不放，以防pop的时候有可能有重复一样所以=要放进去
                this.stackMin.push(newNum);
            }
            this.stackData.push(newNum);
        }

        public int pop() {
            if (this.stackData.isEmpty()) {
                throw new RuntimeException("Your stack is empty.");
            }
            int ans = this.stackData.pop();
            if (ans == this.getMin()) {
                //只有=<弹出去，>没放进去
                this.stackMin.pop();
            }
            return ans;
        }
        public int getMin(){
            if (this.stackMin.isEmpty()) {
                throw new RuntimeException("Your stack is empty.");
            }
            return this.stackMin.peek();
        }

    }
    public static class MyStack2{
        private Stack<Integer> stackData;
        private Stack<Integer> stackMin;
        public MyStack2(){
            this.stackData = new Stack<>();
            this.stackMin = new Stack<>();
        }
        public void push(int newNum){
            if (this.stackMin.isEmpty()){
                this.stackMin.push(newNum);
            }else if (newNum < this.getMin()){
                this.stackMin.push(newNum);
            }else {
                //<自己加,>=加上一个最小数
                int ans = this.stackMin.peek();
                this.stackMin.push(ans);
            }
            this.stackData.push(newNum);
        }
        public int pop(){
            if (this.stackData.isEmpty()){
                throw new RuntimeException("Your stack is empty.");
            }
            //所有都加满了只不过>=加的是上一个最小数，弹也要一起弹
            this.stackMin.pop();
            return this.stackData.pop();
        }
        public int getMin(){
            if (this.stackMin.isEmpty()){
                throw new RuntimeException("Your stack is empty.");
            }
            return this.stackMin.peek();
        }
    }

    public static void main(String[] args) {
        MyStack1 stack1 = new MyStack1();
        stack1.push(3);
        System.out.println(stack1.getMin());
        stack1.push(4);
        System.out.println(stack1.getMin());
        stack1.push(1);
        System.out.println(stack1.getMin());
        System.out.println(stack1.pop());
        System.out.println(stack1.getMin());

        System.out.println("=============");

        MyStack1 stack2 = new MyStack1();
        stack2.push(3);
        System.out.println(stack2.getMin());
        stack2.push(4);
        System.out.println(stack2.getMin());
        stack2.push(1);
        System.out.println(stack2.getMin());
        System.out.println(stack2.pop());
        System.out.println(stack2.getMin());
    }
}
