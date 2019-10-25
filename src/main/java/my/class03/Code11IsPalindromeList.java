package my.class03;

import java.util.Stack;

/**
 * 判断一个链表是否为回文结构
 * 【题目】 给定一个链表的头节点head，请判断该链表是否为回
 * 文结构。 例如： 1->2->1，返回true。 1->2->2->1，返回true。
 * 15->6->15，返回true。 1->2->3，返回false。
 * 进阶： 如果链表长度为N，时间复杂度达到O(N)，额外空间复杂
 * 度达到O(1)。
 *
 * @author RichardLee
 * @version v1.0
 */
public class Code11IsPalindromeList {
    public static void printLinkedList(Node node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    // 需要 n 额外空间
    private static boolean isPalindrome1(Node head) {
        if (head == null) {
            return true;
        }
        Node tmp = head;
        Stack<Integer> stack = new Stack<>();
        while (head != null) {
            stack.push(head.value);
            head = head.next;
        }

        while (tmp != null) {
            if (tmp.value != stack.pop()) {
                return false;
            }
            tmp = tmp.next;
        }

        return true;

    }

    // 需要 n/2 额外空间
    private static boolean isPalindrome2(Node head) {
        // 一半的空间  应该是用一个栈, 存最后一半的数
        // 之后弹栈来判断
        if (head == null) {
            return true;
        }
        // 1. 准备快慢两个指针
        Node low = head;
        Node fast = head;
        // 2. 分奇偶要微调
        //    使得当快指针到最后一个时
        //      慢指针到中间(奇数)
        //      到中间前一个数(偶数)
        while (fast.next != null) {
            if (fast.next.next == null) {
                // 偶数
                fast = fast.next;
            } else {
                fast = fast.next.next;
                low = low.next;
            }
            // 这个循环好了后
            // 快指针就在最后一个位置了
            // 慢指针在  中间(奇数) 中间前一个(偶数)
        }
        // 慢指针后一个开始压栈
        Stack<Node> nodes = new Stack<>();
        while (low != null) {
            nodes.push(low);
            low = low.next;
        }
        // 判断下
        while (!nodes.isEmpty()) {
            if (nodes.pop().value != head.value) {
                return false;
            }
            head = head.next;
        }

        return true;
    }

    // 不需要额外空间
    private static boolean isPalindrome3(Node head) {
        if (head == null) {
            return true;
        }
        // 1. 准备快慢两个指针
        Node low = head;
        Node fast = head;
        // 2. 分奇偶要微调
        //    使得当快指针到最后一个时
        //      慢指针到中间(奇数)
        //      到中间前一个数(偶数)
        while (fast.next != null) {
            if (fast.next.next == null) {
                // 偶数
                fast = fast.next;
            } else {
                fast = fast.next.next;
                low = low.next;
            }
            // 这个循环好了后
            // 快指针就在最后一个位置了
            // 慢指针在  中间(奇数) 中间前一个(偶数)
        }
        // 3. 快指针到中间部分, 链表反转
        // 这里让慢指针之后的链表反转
        Node reverse = reverse(low.next);
        // 这个 haha 是为了最后还原用的, 所以先记录了
        Node haha = reverse;
        low.next = null;

        // 4. 遍历, 看是否相同
        try {
            while (reverse != null && head != null) {

                if (head.value != reverse.value) {
                    return false;
                }
                head = head.next;
                reverse = reverse.next;
            }
            return true;
        }finally {
//            assert reverse != null;
            low.next = reverse(haha);
        }
        // 5. 恢复为原样

    }

    public static Node reverse(Node head) {
        Node pre = null;
        Node next = null;

        // 循环条件等下写
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }

        public static void main(String[] args) {
            Node head = null;
            printLinkedList(head);
            System.out.print(isPalindrome1(head) + " | ");
            System.out.print(isPalindrome2(head) + " | ");
            System.out.println(isPalindrome3(head) + " | ");
            printLinkedList(head);
            System.out.println("=========================");

            head = new Node(1);
            printLinkedList(head);
            System.out.print(isPalindrome1(head) + " | ");
            System.out.print(isPalindrome2(head) + " | ");
            System.out.println(isPalindrome3(head) + " | ");
            printLinkedList(head);
            System.out.println("=========================");

            head = new Node(1);
            head.next = new Node(2);
            printLinkedList(head);
            System.out.print(isPalindrome1(head) + " | ");
            System.out.print(isPalindrome2(head) + " | ");
            System.out.println(isPalindrome3(head) + " | ");
            printLinkedList(head);
            System.out.println("=========================");

            head = new Node(1);
            head.next = new Node(1);
            printLinkedList(head);
            System.out.print(isPalindrome1(head) + " | ");
            System.out.print(isPalindrome2(head) + " | ");
            System.out.println(isPalindrome3(head) + " | ");
            printLinkedList(head);
            System.out.println("=========================");

            head = new Node(1);
            head.next = new Node(2);
            head.next.next = new Node(3);
            printLinkedList(head);
            System.out.print(isPalindrome1(head) + " | ");
            System.out.print(isPalindrome2(head) + " | ");
            System.out.println(isPalindrome3(head) + " | ");
            printLinkedList(head);
            System.out.println("=========================");

            head = new Node(1);
            head.next = new Node(2);
            head.next.next = new Node(1);
            printLinkedList(head);
            System.out.print(isPalindrome1(head) + " | ");
            System.out.print(isPalindrome2(head) + " | ");
            System.out.println(isPalindrome3(head) + " | ");
            printLinkedList(head);
            System.out.println("=========================");

            head = new Node(1);
            head.next = new Node(2);
            head.next.next = new Node(3);
            head.next.next.next = new Node(1);
            printLinkedList(head);
            System.out.print(isPalindrome1(head) + " | ");
            System.out.print(isPalindrome2(head) + " | ");
            System.out.println(isPalindrome3(head) + " | ");
            printLinkedList(head);
            System.out.println("=========================");

            head = new Node(1);
            head.next = new Node(2);
            head.next.next = new Node(2);
            head.next.next.next = new Node(1);
            printLinkedList(head);
            System.out.print(isPalindrome1(head) + " | ");
            System.out.print(isPalindrome2(head) + " | ");
            System.out.println(isPalindrome3(head) + " | ");
            printLinkedList(head);
            System.out.println("=========================");

            head = new Node(1);
            head.next = new Node(2);
            head.next.next = new Node(3);
            head.next.next.next = new Node(2);
            head.next.next.next.next = new Node(1);
            printLinkedList(head);
            System.out.print(isPalindrome1(head) + " | ");
            System.out.print(isPalindrome2(head) + " | ");
            System.out.println(isPalindrome3(head) + " | ");
            printLinkedList(head);
            System.out.println("=========================");

        }
    }
}
