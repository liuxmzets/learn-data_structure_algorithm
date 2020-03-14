package com.atguigu.linkedlist;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4,"林冲","豹子头");

        singleLinkedList.addSort(hero4);
        singleLinkedList.addSort(hero1);
        singleLinkedList.addSort(hero3);
        singleLinkedList.addSort(hero2);

        singleLinkedList.show();
        //修改
        HeroNode updateNode = new HeroNode(4, "小林", "豹子头~~");

        System.out.println("修改后");
        singleLinkedList.update(updateNode);
        singleLinkedList.show();

        //获得链表的实际长度
        //System.out.println(getSingleListNodesLength(singleLinkedList.getHead()));

        //删除
//        singleLinkedList.del(100);

//        System.out.println(singleLinkedList.getHead());

        System.out.println(getReverseK(4, singleLinkedList.getHead()));
    }


    //查找单链表中的倒数第k个结点 k:从1 2 3 4
    public static HeroNode getReverseK(int index,HeroNode head){
        //思路
        //1.先将head指向的链表反转
        //2.再遍历获得第k个位置的结点

        //考虑空链表
        if(head.next == null){
            return null;
        }

        //定义一个辅助变量指向head
        HeroNode temp = head.next;
        head.next = null;

        while(true){
            //a.将temp的节点赋值给 head.next
            HeroNode leave = temp.next;
            temp.next = head.next;
            head.next = temp;
            if(leave == null){
                break;
            }
            temp = leave;
        }

        HeroNode check = head;
        for (int j = 1; j <= index; j++) {
            check = check.next;
        }
        return check;
    }


    /**
     * 获取单链表有效结点的个数，如果单链表带头结点，不统计头结点
     * @param head
     * @return
     */
    public static int getSingleListNodesLength(HeroNode head){
        if(head.next == null){
            return 0;
        }
        //定义一个辅助变量
        HeroNode temp = head.next;
        int num = 0;
        while(temp != null){
            num++;
            temp=temp.next;
        }
        return num;
    }
}


class SingleLinkedList{
    //定义头节点
    private HeroNode head = new HeroNode(-1,"","");

    public HeroNode getHead() {
        return head;
    }

    //删除节点
    //思路
    //1.head 不能动，因此我们需要一个temp辅助节点 找到待删除结点的前一个节点
    //2.说明我们在比较时，是temp.next.no 和需要删除的节点的no比较
    public void del(int no){
        //1.找到待删除结点的前驱节点
        HeroNode temp = head;
        boolean flag = false;
        while(temp.next != null){
            if (temp.next.id == no) {
                //找到
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;
        }else{
            System.out.printf("单链表中不存在要删除的id为%d的英雄\n",no);
        }
    }

    //效果单链表中的一个节点
    public void update(HeroNode newHeroNode) {

        //定义一个辅助遍历 指向单链表的第一个元素
        HeroNode temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if(temp.id == newHeroNode.id){//找到了
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickName = newHeroNode.nickName;
        }else{
            System.out.printf("不存在编号为%d的英雄",newHeroNode.id);
        }
    }

    //添加节点到单链表中
    public void add(HeroNode data) {
        /*
        1.找到单链表的最后一个节点
        2.将最后一个节点的next指向新节点
         */
        //定义一个临时遍历
        HeroNode temp = head;

        while(temp.next != null){
            temp = temp.next;
        }
        temp.next = data;
    }


    //按照编号id添加节点到单链表中
    //如果id相同则提示：已经存在该编号的英雄
    public void addSort(HeroNode data){
        //定义一个辅助变量 temp,初始化指向head
        HeroNode temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            //找到合适的插入位置
            if(temp.next.id>data.id){
                break;
            } else if (temp.next.id == data.id) {
                flag = true;
            }
            temp = temp.next;
        }

        if (!flag) {
            data.next = temp.next;
            temp.next = data;
        }else{
            System.out.printf("准备插入的英雄的编号%d已经存在，不能加入\n",data.id);
        }
    }

    //单链表是否为空
    public boolean isEmpty(){
        return head.next==null;
    }

    //遍历list
    public void show() {
        if (isEmpty()) {
            System.out.println("单链表没有数据");
            return;
        }
        //将单链表的第一个元素赋值给temp
        HeroNode temp = head.next;
        while (temp!=null){
            //打印当前节点
            System.out.println(temp);
            temp = temp.next;
        }
    }
}

//定义英雄节点
class HeroNode{
    public int id;//编号
    public String name;//英雄名称
    public String nickName;//英雄昵称
    public HeroNode next;//指向下一个节点

    //构造函数，构造一个英雄
    public HeroNode(int id, String name, String nickName) {
        this.id = id;
        this.name = name;
        this.nickName = nickName;
    }

    //打印英雄节点

    @Override
    public String toString() {
        return "HeroNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
