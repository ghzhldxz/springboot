package com.springboot.girl.other;

import io.swagger.models.auth.In;

import java.util.*;

/**
 * @Description 列表算法练习
 * @Author GuanHuizhen
 * @Date 2018/10/19
 */
public class ListExercise {
    /**
     * 报数问题T_T 哭吧
     */
    public static void printNumberBy3() {
        List<Integer> numbers = new ArrayList<Integer>();
        Iterator iterator = numbers.iterator();
        for(int i=0;i<10;i++) {
            numbers.add(i+1);
        }
        int size = 3;
        int i = 0;
        int number = 0;
        List<Integer> del = new ArrayList<>();
        while (iterator.hasNext()) {
            if(numbers.size()==1) {
                System.out.println("最后一个成员："+numbers.get(0));
                return;
            }
            if(i>=numbers.size()) {
                i = i % numbers.size();
                if( numbers.size() >=size) {
                    int delCount = 0;
                    for (int index : del) {
                        numbers.remove(index - delCount);
                        delCount++;
                    }
                    // i = (i+1) % 3;
                    del.clear();
                } else {
                   // System.out.println(i);
                }
            }
            if((number + 1) % size == 0) {
                number = 0;
                System.out.println("退出成员："+numbers.get(i));
                if(numbers.size() < size) {
                   numbers.remove(i);
                   continue;
                } else {
                    del.add(i) ;
                }
            } else {
                System.out.println(numbers.get(i));
                number ++ ;
            }
            i++;
        }
    }

    public static int bsearch2(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid =  low + ((high - low) >> 1);
            if (a[mid] >= value) {
                if ((mid == n - 1) || (a[mid - 1] != value)) {
                    return mid;
                }
                else {
                    high = mid - 1;
                }
            } else if (a[mid] < value) {
                low = mid + 1;
            } else {

            }
        }
        return -1;
    }


    public static int bsearch(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] >= value) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        if (low < n && a[low]==value) {
            return low;
        }
        else {
            return -1;
        }
    }

    public static int binaryCycleSearch(int[] nums,int target) {
        //二分法将数组分成两部分，要区分出哪部分是有序的
        //当nums[mid] > nums[mid-1] 时，前半部分有序
        //当nums[mid] < nums[mid + 1]时，后半部分有序
        int low = 0;
        int high = nums.length - 1;
        while (high>=low) {

            int mid = low + ((high-low) >> 1);
            if(nums[mid] == target) {
                return mid;
            }
            if(high == low) {
                return -1;
            }
            if(mid == 0) {
                low = low = mid + 1;
                continue;
            } else if(mid == high) {
                high = mid -1;
                continue;
            }
            if(nums[mid] >=nums[low]) {
                //target落在前半部分有序队列中
                if(nums[mid] > target && (nums[mid-1] >= target && nums[low] <= target)) {
                    high = mid - 1;
                }
                //target未落在有序队列中（后半部分）
                else {
                    low = mid + 1;
                }
            } else if(nums[mid] < nums[high] ){
                //target落在后半部分有序队列中
                if(nums[mid] < target && (nums[high] >=target && nums[mid+1] <= target)) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

        }

        return -1;

    }



    public static void main(String[] args) {
        int[] nums = {2,3,4,5,1};
        ListExercise.binaryCycleSearch(nums,1);

        int[] num = new int[]{0,0,1,1,1,1,1};
        ListExercise.bsearch2(num,num.length,1);


        List<String> test = Arrays.asList("33","3","1","9","3","5");
        Collections.sort(test);

        ListExercise.printNumberBy3();
        ListNode listNode = new ListNode(0);
        ListNode tempNode = listNode;

        ListNode listNode2 = new ListNode(0);
        ListNode tempNode2 = listNode2;
        for(int i=1;i<6;i++) {
            ListNode otherNode = new ListNode(i*i);
            tempNode.next = otherNode;
            tempNode = otherNode;
        }

        for(int j=1;j<7;j++) {
            ListNode otherNode = new ListNode(j*3);
            tempNode2.next = otherNode;
            tempNode2 = otherNode;
        }

       /* System.out.println(listNode.toString());
        System.out.println(listNode2.toString());
        //listNode = Solution.reverseList(listNode);
        //listNode = Solution.swapPairs(listNode);
        listNode = Solution.addTwoNumbers(listNode,listNode2);
        System.out.println(listNode.toString());*/

       /* ListNode cycleList = new ListNode(1);
        ListNode node2 = new ListNode(2);
        cycleList.next = node2;
        node2.next = cycleList;
        //System.out.println(Solution.hasCycle(cycleList));
        System.out.println(Solution.detectCycle(cycleList).val);
*/
       System.out.println("l1="+listNode);
       System.out.println("l2="+listNode2);
       ListNode ret = Solution.mergeList2(listNode,listNode2);
       System.out.println("l1="+listNode);
       System.out.println("l2="+listNode2);
       System.out.println(ret);

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
     * 合并两个有序列表
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode mergeList(ListNode l1,ListNode l2) {
        ListNode newList = new ListNode(0);
        ListNode cur = newList;
        while (l1 != null && l2 != null) {
            if(l1.val>l2.val) {
                cur.next = l2;
                cur = l2;
                l2 = l2.next;
            } else if(l1.val < l2.val) {
                cur.next = l1;
                cur = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                cur = l2;
                l2 = l2.next;//此句代码不能放到后面l1 = l1.next处，因为下面一行代码会改面l2.next的指向，so.....
                cur.next = l1;
                cur = l1;
                l1 = l1.next;
            }
            cur.next = null;
        }
        if(l1 == null){
            cur.next = l2;
        }
        if(l2 == null){
            cur.next = l1;
        }
        return newList.next;
    }

    /**
     * 合并两个有序列表--失败
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode mergeList2(ListNode l1,ListNode l2) {
        if(l1 == null){
            return l2;
        }
        if(l2 == null){
            return l1;
        }
        ListNode cur = l2;
        ListNode pre = l2;
        ListNode temp = null;
        while (l1 != null && cur != null) {
            if(l1.val>=cur.val) {
                pre = cur;
                cur = cur.next;
            } else {//在cur的前面插入
                pre.next = l1;
                pre = l1;
                temp = l1.next;
                l1.next = cur;
                l1 = temp;
            }
        }
        if(cur == null) {
            pre.next = l1;
        }
        return l2;
    }


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

    /**
     * 判断单向链表中是否有环(方法二）
     * @param head
     * @return
     */
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
