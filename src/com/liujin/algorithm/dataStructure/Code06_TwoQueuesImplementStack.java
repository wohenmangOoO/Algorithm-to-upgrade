package com.liujin.algorithm.dataStructure;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

//如何用队列结构实现栈结构
public class Code06_TwoQueuesImplementStack {
    public static class TwoQueuesStack<T>{
        public Queue<T> queue;
        public Queue<T> help;
        public TwoQueuesStack(){
            this.help = new LinkedList<>();
            this.queue = new LinkedList<>();
        }
        public void push(T value){
            queue.offer(value);
        }
        public T pop(){
            if (queue.isEmpty()){
                throw new RuntimeException("Stack is empty");
            }
            while (queue.size() < 1){
                //只留一个返回，其他都放help里跳转（因为栈是后进先出）
                help.offer(queue.poll());
            }
            T ans = queue.poll();
            //把queue重置（两个队列交换queue里面现在有值，重复操作）
            Queue<T> temp = queue;
            queue = help;
            help = temp;
            return ans;
        }
        public T peek(){
            if (queue.isEmpty()){
                throw new RuntimeException("Stack is empty");
            }
            while (queue.size() < 1){
                help.offer(queue.poll());
            }
            //把当前值取出来在放回去
            T ans = queue.poll();
            help.offer(ans);
            //重置 交换操作
            Queue<T> temp = queue;
            queue = help;
            help = temp;
            return ans;
        }
        public boolean isEmpty(){
            return queue.isEmpty();
        }
    }

    public static void main(String[] args) {
        TwoQueuesStack<Integer> MyStack = new TwoQueuesStack();
        Stack<Integer> test = new Stack<>();
        int testTime = 100000;
        int max = 1000;
        System.out.println("begin");
        for (int i = 0; i < testTime; i++) {
            if (Math.random() < 0.25){
                if (MyStack.isEmpty()){
                    //如果mystack为null，test不为null就报错
                    if (!test.isEmpty()){
                        System.out.println("Oops");
                    }
                    int num = (int) (Math.random() * (max + 1));
                    test.push(num);
                    MyStack.push(num);
                }else if (Math.random() < 0.5){
                    if (!test.pop().equals(MyStack.pop())){
                        System.out.println("Oops");
                    }
                    int num = (int) (Math.random() * (max + 1));
                    test.push(num);
                    MyStack.push(num);
                }else if (Math.random() < 0.75){
                    if (!test.peek().equals(MyStack.peek())){
                        System.out.println("Oops");
                    }
                }else {
                    if (MyStack.isEmpty() != test.isEmpty()) {
                        System.out.println("Oops");
                    }
                }
            }
        }
        System.out.println("test finish!");
    }
}

