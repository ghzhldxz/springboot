package com.springboot.girl.other;

import java.util.Random;

/**
 * @Description 跳表
 * @Author teacher wang
 * @Date 2018/12/18
 */
public class SkipList {
    private static final int MAX_LEVEL = 16;

    private int levelCount = 1;
    // 带头链表
    private Node head = new Node();

    private Random r = new Random();

    public Node find(int value) {
        Node p = head;
        for (int i = levelCount - 1; i >= 0; --i) {
            while (p.forwards[i] != null && p.forwards[i].data < value) {
                p = p.forwards[i];
            }
        }

        if (p.forwards[0] != null && p.forwards[0].data == value) {
            return p.forwards[0];
        } else {
            return null;
        }
    }

    public void insert(int value) {
        int level = randomLevel();
        Node newNode = new Node();
        newNode.data = value;
        newNode.maxLevel = level;
        Node update[] = new Node[level];
        for (int i = 0; i < level; ++i) {
            update[i] = head;
        }

        // record every level largest value which smaller than insert value in update[]
        Node p = head;
        for (int i = level - 1; i >= 0; --i) {
            //从最高层的头结点开始，寻找前驱，1、当发现第i层p没有next结点时，直接将p放入前驱数组update[i]；2、或者有next结点，但是next结点对应的data>value时，也将p放入update[i]数组；
            //3、最后如果p有next结点，且data<value时，将p赋值为p.next，继续遍历，直到找到满足上述两种情况的结点为止（最后一个结点，或者data值更大的结点）
            while (p.forwards[i] != null && p.forwards[i].data < value) {
                p = p.forwards[i];
            }
            //收集每一导待插入位置的前驱结点
            update[i] = p;// use update save node in search path
        }

        // in search path node next node become new node forwords(next)
        for (int i = 0; i < level; ++i) {
            //先将新结点的next，即下一个结点指向收集到的前缀的next结点
            newNode.forwards[i] = update[i].forwards[i];
            //再将前面收集确认的前缀的next结点指向新结点。这就类似于链表插入结点时的操作，只是这里加个循环，一层层的做插入
            update[i].forwards[i] = newNode;
        }

        // update node hight
        if (levelCount < level) levelCount = level;
    }

    public void delete(int value) {
        Node[] update = new Node[levelCount];
        Node p = head;
        for (int i = levelCount - 1; i >= 0; --i) {
            while (p.forwards[i] != null && p.forwards[i].data < value) {
                p = p.forwards[i];
            }
            update[i] = p;
        }

        if (p.forwards[0] != null && p.forwards[0].data == value) {
            for (int i = levelCount - 1; i >= 0; --i) {
                if (update[i].forwards[i] != null && update[i].forwards[i].data == value) {
                    update[i].forwards[i] = update[i].forwards[i].forwards[i];
                }
            }
        }
    }

    // 随机 level 次，如果是奇数层数 +1，防止伪随机
    private int randomLevel() {
        int level = 1;
        for (int i = 1; i < MAX_LEVEL; ++i) {
            if (r.nextInt() % 2 == 1) {
                level++;
            }
        }

        return level;
    }

    public void printAll() {
        Node p = head;
        while (p.forwards[0] != null) {
            System.out.print(p.forwards[0] + " ");
            p = p.forwards[0];
        }
        System.out.println();
    }

    public class Node {
        private int data = -1;
        private Node forwards[] = new Node[MAX_LEVEL];
        private int maxLevel = 0;

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("{ data: ");
            builder.append(data);
            builder.append("; levels: ");
            builder.append(maxLevel);
            builder.append(" }");

            return builder.toString();
        }
    }

    public static void main(String[] args) {
        SkipList skipList = new SkipList();
        skipList.insert(7);
        skipList.insert(1);
        skipList.insert(6);
    }
}
