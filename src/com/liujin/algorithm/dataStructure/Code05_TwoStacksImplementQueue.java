package com.liujin.algorithm.dataStructure;

import java.util.Stack;

//如何用栈结构实现队列结构
public class Code05_TwoStacksImplementQueue {
    public static class TwoStacksQueue{
        public Stack<Integer> stackPush;
        public Stack<Integer> stackPop;
        public TwoStacksQueue(){
            this.stackPop = new Stack<>();
            this.stackPush = new Stack<>();
        }
        // push栈向pop栈倒入数据
        private void pushToPop(){
            if (stackPop.isEmpty()){
                while (!stackPush.isEmpty()){
                    stackPop.push(stackPush.pop());
                }
            }
        }
        public void add(int pushInt) {
            stackPush.push(pushInt);
            pushToPop();
        }
        public int poll(){
            if (stackPush.isEmpty() && stackPop.isEmpty()){
                throw new RuntimeException("Queue is empty");
            }
            //如果pop栈有数就不倒，没数就倒过去，在返回
            pushToPop();
            return stackPop.pop();
        }
        public int peek(){
            if (stackPop.isEmpty() && stackPush.isEmpty()){
                throw new RuntimeException("Queue is empty");
            }
            return stackPop.peek();
        }
    }
    public static void main(String[] args) {
        TwoStacksQueue test = new TwoStacksQueue();
        test.add(1);
        test.add(2);
        test.add(3);
        System.out.println(test.peek());
        System.out.println(test.poll());
        System.out.println(test.peek());
        System.out.println(test.poll());
        System.out.println(test.peek());
        System.out.println(test.poll());
    }
}
