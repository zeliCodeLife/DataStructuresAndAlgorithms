package com.zero.linkedList;

/**
 * Created by zero on 2020/5/30.
 * desc:链表是有序的列表。
 * 1) 链表是以节点的方式来存储,是链式存储
 * 2) 每个节点包含 data 域， next 域:指向下一个节点.
 * 3) 链表的各个节点不一定是连续存储.
 * 4) 链表分带头节点的链表和没有头节点的链表，根据实际的需求来确定
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //进行测试
        //先创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
        //创建要给链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        //加入
        // singleLinkedList.add(hero1);
        // singleLinkedList.add(hero4);
        // singleLinkedList.add(hero2);
        // singleLinkedList.add(hero3);
        //加入按照编号的顺序
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);
        //显示一把
        singleLinkedList.list();
        //测试修改节点的代码
        HeroNode newHeroNode = new HeroNode(2, "小卢", "玉麒麟~~");
        singleLinkedList.update(newHeroNode);
        System.out.println("修改后的链表情况~~");
        singleLinkedList.list();

        //删除一个节点
        singleLinkedList.del(new HeroNode(1, "", ""));
        singleLinkedList.del(new HeroNode(4, "", ""));
        System.out.println("删除后的链表情况~~");
        singleLinkedList.list();

    }
}
//带头节点带单向链表带实现
class SingleLinkedList{
    //先初始化一个头节点, 头节点不要动, 不存放具体的数据.
    private HeroNode head = new HeroNode(0,"","");
    //添加节点到单向链表
    //思路，当不考虑编号顺序时
    //1. 找到当前链表的最后节点
    //2. 将最后这个节点的 next 指向 新的节点
    public void add(HeroNode heroNode){
        //因为 head 节点不能动，因此我们需要一个辅助遍历 temp
        HeroNode tmp = heroNode;
        while (true){
            //先找到链表带最后
            if (tmp.next==null) {
                  break;
            }
            //如果没有找到最后, 将将 temp 后移
            tmp = tmp.next;
        }
        //当退出 while 循环时，temp 就指向了链表的最后
        //将最后这个节点的 next 指向 新的节点
        tmp.next = heroNode;
    }
    //第二种方式在添加英雄时，根据排名将英雄插入到指定位置 ,这里模仿的是有顺序链表的插入。
    // (如果有这个排名，则添加失败，并给出提示)
    public void addByOrder(HeroNode heroNode) {
        //因为头节点不能动，因此我们仍然通过一个辅助指针(变量)来帮助找到添加的位置
        //因为单向链表，因为我们找的 temp 是位于 添加位置的前一个节点，否则插入不了，插入是调用前面节点.next = node；
        HeroNode tmp = head;
        //flag 标志添加的编号是否存在，默认为 false不存在
        boolean flag = false;
        while(true){
            //说明 temp 已经在链表的最后
            if (tmp.next == null) {
                break;
            }
            //位置找到，就在 temp 的后面插入
            if (tmp.next.no > heroNode.no) {
                  break;
            }  else if (tmp.next.no == heroNode.no){//说明希望添加的 heroNode 的编号已然存在
                flag = true;
            }
            tmp = tmp.next;
        }
        //判断 flag 的值,决定是否添加
        if (flag) { //不能添加，说明编号存在
            System.out.printf("准备插入的英雄的编号 %d 已经存在了, 不能加入\n", heroNode.no);
        }else {
            //插入到链表中, temp 的后面
            heroNode.next = tmp.next;
            tmp.next = heroNode;
        }
    }
    //修改节点的信息, 根据 no 编号来修改，即 no 编号不能改.
    //1. 根据 newHeroNode 的 no 来修改即可
    public void update(HeroNode newHeroNode) {
        //1.链表必须非空
        if (head.next==null) {
            System.out.println("链表为空~");
            return;
        }
        //2。根据no定位
        HeroNode tmp = head.next;
        boolean flag = false;//默认是找不到该节点的。
        while(true){
            if (tmp==null) {
                break; //已经遍历完链表
            }
            if (tmp.no==newHeroNode.no) {
                //找到
                  flag=true;
                  break;
            }
            tmp = tmp.next;
        }
        //3。根据定位的情况修改，找到/没找到
        if (flag) {
            tmp.name = newHeroNode.name;
            tmp.nickname = newHeroNode.nickname;
        }  else {
            System.out.printf("没有找到 编号 %d 的节点，不能修改\n", newHeroNode.no);
        }
    }
    //删除节点
    //思路
    //1. head 不能动，因此我们需要一个 temp 辅助节点找到待删除节点的前一个节点 \
    //2. 说明我们在比较时，是 temp.next.no 和 需要删除的节点的 no 比较
    public  void del(HeroNode node){
        HeroNode tmp = head;
        boolean flag = false; // 标志是否找到待删除节点的
        while(true){
            if (tmp.next == null) { //已经到链表的最后
                break;
            }
            if (tmp.next.no==node.no) {
                //找到的待删除节点的前一个节点 temp
                  flag = true;
                  break;
            }
            //temp 后移，遍历
            tmp = tmp.next;
        }
        //判断 flag
        if (flag) {
            //可以删除
            tmp.next = tmp.next.next;
        }  else{
            System.out.printf("要删除的 %d 节点不存在\n", node.no);
        }
    }
    //显示链表[遍历]
    public void list(){
        //1.判断链表是否为空
        if (head.next==null) {
            System.out.println("链表为空");
            return;
        }
        //2.遍历
        //因为头节点，不能动，因此我们需要一个辅助变量来遍历
        HeroNode temp = head.next;
        while(true){
            if (temp ==null) {//判断是否到链表最后
                  break;
            }
            //输出节点的信息
            System.out.println(temp);
            //将 temp 后移， 一定小心
            temp = temp.next;
        }
    }
}
//定义 HeroNode ， 每个 HeroNode 对象就是一个节点
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next; //指向下一个节点
    //构造器
    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }
    //为了显示方法，我们重新 toString
    @Override
    public String toString() {
        return "HeroNode [no=" + no + ", name=" + name + ", nickname=" + nickname + "]";
    }
}
