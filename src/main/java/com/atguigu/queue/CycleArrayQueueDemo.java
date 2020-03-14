package com.atguigu.queue;

import java.util.Scanner;

public class CycleArrayQueueDemo {
    public static void main(String[] args) {
        CycleArrayQueue queue = new CycleArrayQueue(4);//设置4 ，实际的元素为3，有一个空间作为约定

        Scanner scanner = new Scanner(System.in);
        char key = ' ';//用户输入的菜单命令

        boolean flag = true;
        while (flag) {
            System.out.println("s(show):展现循环队列");
            System.out.println("a(add):向循环队列中添加元素");
            System.out.println("g(get):从循环队列中取出一个元素");
            System.out.println("h(head):取出循环队列头部元素");
            System.out.println("e(exit):退出");

            key = scanner.next().charAt(0);

            switch (key) {
                case 's':
                    queue.show();
                    break;
                case 'a':
                    if (!queue.isFull()) {
                        System.out.println("请输入添加的元素值:");
                        int data = scanner.nextInt();
                        queue.addQueue(data);
                    }else{
                        System.out.println("循环队列已满，不能添加");
                    }
                    break;
                case 'g':
                    try {
                        int data = queue.getQueue();
                        System.out.println("从循环队列中取出的数据是: " + data);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int data = queue.headQueue();
                        System.out.println("循环队列中头部数据是:" + data);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();//关闭资源
                    flag = false;
                    break;
                default:
                    System.out.println("请输入正确的命令");
                    break;
            }

        }
        System.out.println("程序已正常退出");
    }
}

class CycleArrayQueue{
    //front 变量的含义做一个调整: front就指向队列的第一个元素，也就是说arr[front] 就是队列的第一个元素
    //front的初始值为0
    private int front;
    //rear变量的含义做一个调整: rear指向队列的最后一个元素的后一个位置，因为希望空出一个空间做为约定
    //rear的初始值为0
    private int rear;
    private int maxSize; //队列的最大容量
    private int[] arr;   //存储队列数据的容器

    public CycleArrayQueue(int maxArraySize) {
        this.maxSize = maxArraySize;//循环队列的最大容量
        this.arr = new int[maxSize];//存放队列的数组
    }

    //打印循环链表中的所有内容
    //打印从:[front,rear)之间的元素
    public void show() {
        //判断是否为空
        if(isEmpty()){
            System.out.println("循环队列没有数据");
            return;
        }
        //循环队列中 有效元素的个数
        //
        int total = size();
        for (int i=0; i<total; i++) {
            System.out.printf("arr[%d]=%d\n",(front+i)%maxSize,arr[(front+i)%maxSize]);
        }
    }

    public int size(){
        return (rear - front + maxSize)%maxSize;
    }

    //判断环形队列是否为空
    public boolean isEmpty() {
        return front == rear;
    }

    //判断环形队列是否已满
    public boolean isFull(){
        return (rear+1)%maxSize == front;//
    }

    //向环形队列中添加元素
    public void addQueue(int num) {
        if (isFull()) {
            System.out.println("环形队列已满，不能添加,可以尝试先从队列中取出数据!");
            return;
        }
        arr[rear]=num;
        rear = (rear+1)%maxSize;
    }

    //向环形队列中取出一个元素
    public int getQueue(){
        if (isEmpty()) {
            throw new RuntimeException("环形队列已空，不能取数据，可以尝试先从队列中添加数据");
        }
        //步骤:
        //1.首先保存front对应位置的值到一个临时变量中
        //2.将front后移
        //3.将临时变量返回
        int temp = arr[front];
        front = (front+1)%maxSize;//考虑取模
        return temp;
    }

    //查看环形队列的头部元素
    public int headQueue(){
        if (isEmpty()) {
            throw new RuntimeException("环形队列已空，不能获得其头部元素，可以尝试先从队列中添加元素");
        }
        return arr[front];
    }
}
