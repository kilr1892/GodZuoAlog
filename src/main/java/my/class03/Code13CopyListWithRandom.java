package my.class03;

import java.util.HashMap;
import java.util.Map;

/**
 * 复制含有随机指针节点的链表
 * 【题目】 一种特殊的链表节点类描述如下：
 * public class Node { public int value; public Node next; public
 * Node rand;
 * public Node(int data) { this.value = data; }
 * }
 * Node类中的value是节点值，next指针和正常单链表中next指针的意义
 * 一 样，都指向下一个节点，rand指针是Node类中新增的指针，这个指
 * 针可 能指向链表中的任意一个节点，也可能指向null。 给定一个由
 * Node节点类型组成的无环单链表的头节点head，请实现一个 函数完成
 * 这个链表中所有结构的复制，并返回复制的新链表的头节点。 进阶：
 * 不使用额外的数据结构，只用有限几个变量，且在时间复杂度为 O(N)
 * 内完成原问题要实现的函数。
 *
 * @author RichardLee
 * @version v1.0
 */
public class Code13CopyListWithRandom {

    public static class Node {
        public int value;
        public Node next;
        public Node rand;

        public Node(int data) {
            this.value = data;
        }
    }

    public static void main(String[] args) {
        Node head = null;
        Node res1 = null;
        Node res2 = null;
        printRandLinkedList(head);
//        res1 = copyListWithRand1(head);
//        printRandLinkedList(res1);
        res2 = copyListWithRand2(head);
        printRandLinkedList(res2);
        printRandLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);

        head.rand = head.next.next.next.next.next; // 1 -> 6
        head.next.rand = head.next.next.next.next.next; // 2 -> 6
        head.next.next.rand = head.next.next.next.next; // 3 -> 5
        head.next.next.next.rand = head.next.next; // 4 -> 3
        head.next.next.next.next.rand = null; // 5 -> null
        head.next.next.next.next.next.rand = head.next.next.next; // 6 -> 4

        System.out.println("head=====");
        printRandLinkedList(head);
//        res1 = copyListWithRand1(head);
//        printRandLinkedList(res1);
        System.out.println("res2==========");
        res2 = copyListWithRand2(head);
        printRandLinkedList(res2);
        System.out.println("head===========");
        printRandLinkedList(head);
        System.out.println("=========================");

    }

    private static Node copyListWithRand1(Node head) {
        if (head == null) {
            return null;
        }
        Node first = head;
        // 这个问题在于, 如何把ran对应的那个复制出来的Node, 很快的找到
        // 就是一个由已知node找对应的Node的问题
        // 用Map<Node,Node>就最合适了
        Map<Node, Node> oldToNewMap = new HashMap<>();
        while (head != null) {
            oldToNewMap.put(head, new Node(head.value));
            head = head.next;
        }
        head = first;
        while (head != null) {
            oldToNewMap.get(head).next = oldToNewMap.get(head.next);
            oldToNewMap.get(head).rand = oldToNewMap.get(head.rand);
            head = head.next;
        }
        return oldToNewMap.get(first);
    }

    private static Node copyListWithRand2(Node head) {
        if (head == null) {
            return null;
        }
        Node originalNextNode;
        Node newHead;
        Node firstHead = head;
        // 将1 1' 2 2' ... 依次相连
        while (head != null) {
            originalNextNode = head.next;
            newHead = new Node(head.value);
            head.next = newHead;
            newHead.next = originalNextNode;
            head = originalNextNode;
        }
        head = firstHead;
        newHead = head.next;
        // 将ran依次相连
        while (head != null) {
            newHead.rand = head.rand;
            head = newHead.next;
            if (head != null)
            newHead = head.next;
        }
        newHead = firstHead.next;
        head = firstHead;
        // 记录copy的第一个节点
        Node res = newHead;
        // 两组node分开
        while (newHead != null) {
            head.next = newHead.next;
            if (newHead.next != null) {
                newHead.next = newHead.next.next;
            }
            newHead = newHead.next;
            head = head.next;
        }

        return res;
    }

    public static void printRandLinkedList(Node head) {
        Node cur = head;
        System.out.print("order: ");
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.next;
        }
        System.out.println();
        cur = head;
        System.out.print("rand:  ");
        while (cur != null) {
            System.out.print(cur.rand == null ? "- " : cur.rand.value + " ");
            cur = cur.next;
        }
        System.out.println();
    }
}
