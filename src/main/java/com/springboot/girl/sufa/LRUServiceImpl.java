package com.springboot.girl.sufa;

/**
 * @Description 用列表实现LRU算法
 * @Author GuanHuizhen
 * @Date 2018/11/12
 */
public class LRUServiceImpl {
    public static MyList myList = new MyList(new Node("a"),new Node("b"));

    public static void main(String args[]) {
        myList.visit("c");
        System.out.println(myList);
        myList.visit("b");
        System.out.println(myList);
        myList.visit("d");
        System.out.println(myList);
        myList.visit("e");
        System.out.println(myList);
        myList.visit("a");
        System.out.println(myList);
        myList.printLRU(2);
    }

}

class MyList {
    Node head;
    Node tail;
    int length ;
    static final int MAX_SIZE = 10;

    public MyList(Node head, Node tail) {
        this.head = head;
        this.tail = tail;
        this.length = 2;
        head.next = tail;
    }

    @Override
    public String toString() {
        return "MyList{" + "head=" + head + '}';
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public Node getTail() {
        return tail;
    }

    public void setTail(Node tail) {
        this.tail = tail;
    }

    public void printLRU(int num) {
        Node cur = head;
        int i = 1;
        while (cur != null) {
            System.out.println(cur.getContent());
            cur = cur.next;
            i++;
            if(i>num) {
                return;
            }
        }
    }

    public Node visit(String content) {
        Node cur = head;
        Node pre = head;
        while (cur!=null) {
            if(cur.getContent().equals(content)) {
                //将结点移动到头结点
                pre.next = cur.next;//前驱指向当前结点的后驱
                cur.next = head;
                head = cur;
                return cur;
            }
            pre = cur;
            cur = cur.next;
        }
        Node newNode = new Node(content);
        if(length == MAX_SIZE) {//删除最后一个结点
            deleteTail();
        }
        newNode.next = head;
        head = newNode;
        length ++;

        return newNode;
    }

    public Node deleteTail() {
        Node cur = head;
        while (cur != null && cur.next != tail) {
            cur = cur.next;
        }
        tail = cur;
        tail.next = null;
        if(length != 0) {
            length --;
        }
        return cur.next;
    }
}

class Node {
    String content;
    Node next;

    public Node(String content) {
        this.content = content;
        this.next = null;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node{" + "content='" + content + '\'' + ", next=" + next + '}';
    }
}
