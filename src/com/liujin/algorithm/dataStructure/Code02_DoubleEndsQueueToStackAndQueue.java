package com.liujin.algorithm.dataStructure;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

//双向链表实现队列和栈
public class Code02_DoubleEndsQueueToStackAndQueue {
    public static class Node<T> {
        public T value;
        public Node<T> last;
        public Node<T> next;

        public Node(T data) {
            value = data;
        }
    }

    public static class DoubleEndsQueue<T> {
        public Node<T> head;
        public Node<T> tail;

        public void addFromBottom(T value) {
            Node<T> cur = new Node<T>(value);
            if (head == null) {
                head = cur;
                tail = cur;
            } else {
                cur.last = tail;
                tail.next = cur;
                tail = cur;
            }
        }

        public void addFromHead(T value) {
            Node<T> cur = new Node<>(value);
            if (head == null) {
                head = cur;
                tail = cur;
            } else {
                head.last = cur;
                cur.next = head;
                head = cur;
            }
        }

        public T popFromHead() {
            if (head == null) {
                return null;
            }
            Node<T> cur = head;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                head = head.next;
                cur.next = null;
                head.last = null;
            }
            return cur.value;
        }

        public T popFromBottom() {
            if (head == null) {
                return null;
            }
            Node<T> cur = tail;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                tail = tail.last;
                tail.next = null;
                cur.last = null;
            }
            return cur.value;
        }

        public boolean isEmpty() {
            return head == null;
        }
    }

    public static class DoubleEndsStack<T> {
        public Node<T> head;
        public Node<T> tail;

        public void push(T value) {
            Node<T> cur = new Node<>(value);
            if (head == null) {
                head = cur;
                tail = cur;
            } else {
                tail.next = cur;
                cur.last = tail;
                tail = cur;
            }
        }

        public T pop() {
            if (head == null) {
                return null;
            }
            Node<T> cur = tail;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                tail = tail.last;
                tail.next = null;
                cur.last = null;

            }
            return cur.value;

        }

        public boolean isEmpty() {
            return head == null;
        }
    }

    public static class MyQueue<T> {
        public DoubleEndsQueue<T> queue = new DoubleEndsQueue<T>();

        public void push(T value) {
            queue.addFromHead(value);
        }

        public T poll() {
            return queue.popFromBottom();
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }
    }

    public static class MyStack<T> {
        public DoubleEndsStack<T> stack = new DoubleEndsStack<>();

        public void push(T value) {
            stack.push(value);
        }

        public T pop() {
            return stack.pop();
        }

        public boolean isEmpty() {
            return stack.isEmpty();
        }
    }

    public static boolean isEqual(Integer o1, Integer o2) {
        if (o1 == null && o2 != null) {
            return false;
        }
        if (o1 != null && o2 == null) {
            return false;
        }
        if (o1 == null && o2 == null) {
            return true;
        }
        return o1.equals(o2);
    }

    public static void main(String[] args) {
        int oneTestDataNum = 100;
        int value = 10000;
        int testTimes = 100000;
        System.out.println("begin");
        for (int i = 0; i < testTimes; i++) {
            MyStack<Integer> myStack = new MyStack<>();
            MyQueue<Integer> myQueue = new MyQueue<>();
            Stack<Integer> stack = new Stack<>();
            Queue<Integer> queue = new LinkedList<>();
            for (int j = 0; j < oneTestDataNum; j++) {
                int nums = (int) (Math.random() * value);
                if (stack.isEmpty()){
                    myStack.push(nums);
                    stack.push(nums);
                }else {
                    if (Math.random() < 0.5){
                        myStack.push(nums);
                        stack.push(nums);
                    }else {
                        if (!isEqual(myStack.pop(), stack.pop())) {
                            System.out.println("Stack oops!");
                        }
                    }
                }
                int numq = (int) (Math.random() * value);
                if (queue.isEmpty()){
                    myQueue.push(numq);
                    queue.offer(numq);
                }else {
                    if (Math.random() < 0.5){
                        myQueue.push(numq);
                        queue.offer(numq);
                    }else {
                        if (!isEqual(myQueue.poll(), queue.poll())){
                            System.out.println("Queue oops!");
                        }
                    }
                }
            }
        }
        System.out.println("finish!");
    }
}