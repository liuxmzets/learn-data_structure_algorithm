package com.atguigu.queue;

import java.util.Scanner;

public class ArrayQueueDemo {

    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue(3);
        char key = ' ';//接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        //输出一个菜单
        while (loop) {
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列取出数据");
            System.out.println("h(head):查看队列头的数据");

            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    arrayQueue.listQueue();
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                case 'a':
                    if(!arrayQueue.isFull()){
                        System.out.println("请输入添加到队列的数值:");
                        int num = scanner.nextInt();
                        arrayQueue.addQueue(num);
                    }else{
                        System.out.println("队列已满，不能添加数据");
                    }
                    break;
                case 'g':
                    try{
                        int data = arrayQueue.getQueue();
                        System.out.println("从队列中取出的元素为:" + data);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int headData = arrayQueue.headQueue();
                        System.out.println("队列头的元素是:" + headData);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    System.out.println("输入的菜单有误，请重新输入");
                    break;
            }
        }

        System.out.println("查询队列已退出");
    }
}

//使用数组模拟队列--编写一个ArrayQueue类
class ArrayQueue{
    private int front;   //队列头
    private int rear;    //队列尾
    private int maxSize; //表示数组的最大容量
    private int[] arr;   //该数据用于存放数据，模拟队列


    public ArrayQueue(int arrMaxSize) {
        this.maxSize = arrMaxSize;
        arr = new int[maxSize];
        this.front = -1;//指向队列头部，分析出front是指向队列头的前一个位置
        this.rear = -1; //指向队列尾，指向队列尾的数据(即就是队列最后一个数据)
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return front == rear;
    }

    //判断队列是否已满
    public boolean isFull(){
        return rear == maxSize - 1;
    }

    //向队列中添加一个元素
    public void addQueue(int num) {
        if(isFull()){
//            throw new RuntimeException("队列已满~~");
            System.out.println("队列已满，不能添加!");
            return;
        }
        rear++;//让rear 后移
        arr[rear] = num;
    }

    //从队列中取出一个元素
    public int getQueue(){
        if (isEmpty()) {
            throw new RuntimeException("队列是空的，不能取数据");
        }
        front++;
        return arr[front];
    }

    //从队列中取出第一个元素
    public int headQueue(){
        if (isEmpty()) {
            throw new RuntimeException("队列是空的~~");
        }
        return arr[front + 1];
    }

    //打印队列
    public void listQueue(){
        if (isEmpty()) {
            System.out.println("队列是空的，没有数据");
            return;
        }
        for (int i = 0; i < maxSize; i++) {
            System.out.printf("arr[%d]=%d\n", i, arr[i]);
        }
    }
}
