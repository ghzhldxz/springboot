package com.springboot.girl.other;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description 列表算法练习
 * @Author GuanHuizhen
 * @Date 2018/10/19
 */
public class ListExercise {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(7);
        ListNode tempNode = listNode;

        ListNode listNode2 = new ListNode(5);
        ListNode tempNode2 = listNode2;
        for(int i=1;i<3;i++) {
            ListNode otherNode = new ListNode(i);
            tempNode.next = otherNode;
            tempNode = otherNode;
        }

        for(int j=3;j<7;j++) {
            ListNode otherNode = new ListNode(j);
            tempNode2.next = otherNode;
            tempNode2 = otherNode;
        }

       /* System.out.println(listNode.toString());
        System.out.println(listNode2.toString());
        //listNode = Solution.reverseList(listNode);
        //listNode = Solution.swapPairs(listNode);
        listNode = Solution.addTwoNumbers(listNode,listNode2);
        System.out.println(listNode.toString());*/

        ListNode cycleList = new ListNode(1);
        ListNode node2 = new ListNode(2);
        cycleList.next = node2;
        node2.next = cycleList;
        //System.out.println(Solution.hasCycle(cycleList));
        System.out.println(Solution.detectCycle(cycleList).val);


    }

}

/**
 * Definition for singly-linked list.
 */

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return "ListNode{" + "val=" + val + ", next=" + next + '}';
    }
}

class Solution {
    /**
     * 将链表进行反转
     * @param head
     * @return
     */
    public static ListNode reverseList(ListNode head) {
       /* ListNode cur = head;
        ListNode next = head.next;
        while (next!=null) {
            ListNode tail = next.next;
            next.next = cur;
            cur = next;
            next = tail;
        }
        head.next = null;
        return cur;*/
       ListNode cur = head;
       ListNode prev = null;
       ListNode next = null;
       while (cur != null) {
           next= cur.next;
           cur.next = prev;
           prev = cur;
           cur = next;
       }
       return prev;
    }

    /**
     * 将列表中的数值做【两两交换】
     * 1跟2 换  3跟4换  ......
     * @param head
     * @return
     */
    public static ListNode swapPairs(ListNode head) {
        if(head == null || head.next==null) {
            return head;
        }
        ListNode cur = head;
        ListNode temp = head.next;
        ListNode a = null;
        ListNode b = null;
        while(cur != null && cur.next != null) {
            b = cur.next.next;
            cur.next.next = cur;
            if(a != null) {
                a.next = cur.next;
            }
            a = cur;
            cur = b;

        }
        a.next = cur;
        return temp;
    }

    /**
     * 将两个列表中的数值两两相加
     * l1:5->3->7
     * l2:2->8->1
     * re:7->1->9    十进制为917
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1 == null && l2 == null) {
            return null;
        }
        if(l1==null) {
            return l2;
        }else if(l2 == null) {
            return l1;
        }
        ListNode p = l1,q = l2;
        ListNode cur = null,head = null;

        int carry = 0;
        while(p!=null || q!=null) {
            int pVal = p==null? 0: p.val;
            int qVal = q==null? 0: q.val;
            int sum = carry + pVal + qVal;
            carry = sum / 10;
            if(head == null) {
                cur = new ListNode(sum % 10) ;
                head = cur;
            }  else {
                cur.next = new ListNode(sum % 10) ;
                cur = cur.next;
            }
            if(p != null) p = p.next;
            if(q != null) q = q.next;

        }
        //最后一次进位
        if(carry > 0) {
            cur.next = new ListNode(carry) ;
        }
        return head;
    }

    /**
     * 判断单向链表中是否有环
     * @param head
     * @return
     */
    public static boolean hasCycle(ListNode head) {
        if(head == null || head.next == null) {
            return false;
        }
        ListNode fast = head;
        ListNode slow = head;
        while(fast !=null && fast.next != null && slow.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow) {
                return true;
            }
        }
        return false;
    }

    public static ListNode detectCycle(ListNode head) {
        if(head == null || head.next == null) {
            return null;
        }
        ListNode cur = head;
        Set<ListNode> nodeSet = new HashSet<>();
        while (cur != null) {
            boolean ret = nodeSet.add(cur);
            if(!ret) {
                return cur;
            }
            cur = cur.next;
        }
        return null;
    }
}
