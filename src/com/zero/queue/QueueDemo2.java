package com.zero.queue;


import java.util.Scanner;

/**
 * Created by zero on 2020/5/29.
 * desc:对前面的数组模拟队列的优化，充分利用数组. 因此将数组看做是一个环形的。
 */
public class QueueDemo2 {
    public static void main(String[] args) {
        CircleArray queue = new CircleArray(3);
        char key = ' '; //接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        //输出一个菜单
        while (loop) {
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
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
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

class CircleArray {
    private int maxSize; //数组的最大容量
    // front 变量的含义做一个调整: front 就指向队列的第一个元素, 也就是说 arr[front] 就是队列的第一个元素
    // front 的初始值 = 0
    // 队列头
    private int front;
    // rear 变量的含义做一个调整:rear 指向队列的最后一个元素的后一个位置. 因为希望空出一个空间做为约定.
    // rear 的初始值 = 0
    // 队列尾
    private int rear;
    private int[] arr;//该数据用于存放数据模拟队列

    public CircleArray(int arrMaxSize) {
        this.maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = 0;// 指向队列头部，因为是环形队列所以必须指向一个位置，默认的时候队列是空的front=rear=数组的下标0
        rear = 0;// 指向队列尾
    }

    //判断队列是否满了
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return rear == front;
    }

    //添加数据到队列
    public void addQueue(int n) {
        //先判断是不是满了
        if (isFull()) {
            System.out.println("队列满了！");
            return;
        }
        //往空位赋值
        arr[rear] = n;
        //将 rear 后移, 这里必须考虑取模，不能是简单的加一了，要考虑从头开始的情况
        rear = (rear + 1) % maxSize;


    }

    //出队列
    public int getQueue() {
        if (isEmpty()) {
            // 通过抛出异常
            throw new RuntimeException("队列空，不能取数据");
        }

        //front++;循环队列，简单的++不再满足需求，需要考虑取模
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;

    }

    //展示队列的所有数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空");
            return;
        }
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    //显示队列的头数据， 注意不是取出数据
    public int headQueue() {
        // 判断
        if (isEmpty()) {
            throw new RuntimeException("队列空的，没有数据~~");
        }
        return arr[front];
    }

    // 求出当前队列有效数据的个数
    public int size() {
        // rear = 2
        // front = 1
        // maxSize = 3
        return (rear + maxSize - front) % maxSize;
    }
}