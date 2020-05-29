package com.zero.queue;


import java.util.Scanner;

/**
 * Created by zero on 2020/5/29.
 * desc:对前面的数组模拟队列的优化，充分利用数组. 因此将数组看做是一个环形的。
 *
 */
public class QueueDemo2 {
    public static void main(String[] args) {
        ArrayQueue2 queue = new ArrayQueue2(3);
        char key = ' '; //接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        //输出一个菜单
        while(loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看队列头的数据");
            key = scanner.next().charAt(0);//接收一个字符
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("输出一个数");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g': //取出数据
                    try {
                        int res = queue.getQueue(); System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                           // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h': //查看队列头的数据
                    try {
                        int res = queue.headQueue();
                        System.out.printf("队列头的数据是%d\n", res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e': //退出
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
    }
}

class ArrayQueue2{
    private int maxSize; //数组的最大容量
    private int front; //队列头
    private int rear;//队列尾
    private int[] arr;//该数据用于存放数据模拟队列

    public ArrayQueue2(int arrMaxSize){
        this.maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = -1;// 指向队列头部，分析出 front 是指向队列头的前一个位置.
        rear = -1;// 指向队列尾，指向队列尾的数据(即就是队列最后一个数据)
    }
    //判断队列是否满了
    public boolean isFull(){
        return rear == maxSize-1;
    }

    //判断队列是否为空
    public boolean isEmpty(){
        return rear == front;
    }
    //添加数据到队列
    public void addQueue(int n){
        //先判断是不是满了
        if (isFull()) {
            System.out.println("队列满了！");
            return;
        }
        rear++;
        arr[rear] = n;

    }

    //出队列
    public int getQueue(){
        if (isEmpty()) {
            // 通过抛出异常
            throw new RuntimeException("队列空，不能取数据");
        }
        front++;
        return arr[front];

    }

    //展示队列的所有数据
    public void showQueue(){
        if (isEmpty()) {
            System.out.println("队列为空");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n", i, arr[i]);
        }
    }
    //显示队列的头数据， 注意不是取出数据
    public int headQueue(){
        // 判断
        if (isEmpty()) {
            throw new RuntimeException("队列空的，没有数据~~");
        }
        return arr[front+1];
    }
}