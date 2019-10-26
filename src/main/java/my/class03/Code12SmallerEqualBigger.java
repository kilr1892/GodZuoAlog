package my.class03;

/**
 * 将单向链表按某值划分成左边小、中间相等、右边大的形式
 * 【题目】 给定一个单向链表的头节点head，节点的值类型是整型，再给定一个
 * 整 数pivot。实现一个调整链表的函数，将链表调整为左部分都是值小于 pivot
 * 的节点，中间部分都是值等于pivot的节点，右部分都是值大于 pivot的节点。
 * 除这个要求外，对调整后的节点顺序没有更多的要求。 例如：链表9->0->4->5-
 * >1，pivot=3。 调整后链表可以是1->0->4->9->5，也可以是0->1->9->5->4。总
 * 之，满 足左部分都是小于3的节点，中间部分都是等于3的节点（本例中这个部
 * 分为空），右部分都是大于3的节点即可。对某部分内部的节点顺序不做 要求。
 * <p>
 * 进阶： 在原问题的要求之上再增加如下两个要求。
 * 在左、中、右三个部分的内部也做顺序要求，要求每部分里的节点从左 到右的
 * 顺序与原链表中节点的先后次序一致。 例如：链表9->0->4->5->1，pivot=3。
 * 调整后的链表是0->1->9->4->5。 在满足原问题要求的同时，左部分节点从左到
 * 右为0、1。在原链表中也 是先出现0，后出现1；中间部分在本例中为空，不再
 * 讨论；右部分节点 从左到右为9、4、5。在原链表中也是先出现9，然后出现4，
 * 最后出现5。
 * 如果链表长度为N，时间复杂度请达到O(N)，额外空间复杂度请达到O(1)。
 *
 * @author RichardLee
 * @version v1.0
 */
public class Code12SmallerEqualBigger {
    public static void printLinkedList(Node node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head1 = new Node(7);
        head1.next = new Node(9);
        head1.next.next = new Node(1);
        head1.next.next.next = new Node(8);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(2);
        head1.next.next.next.next.next.next = new Node(5);
        printLinkedList(head1);
//        head1 = listPartition1(head1, 4);
        head1 = listPartition2(head1, 3);
        printLinkedList(head1);

    }

    private static Node listPartition1(Node head, int pivot) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        int i = 0;
        while (head != null) {
            i++;
            head = head.next;
        }
        Node[] nodeArr = new Node[i];
        i = 0;
        // 这里把node存入arr里
        while (cur != null) {
            nodeArr[i] = cur;
            i++;
            cur = cur.next;
        }

        Node node = arrPartition(nodeArr, pivot);
        return node;
    }

    private static Node arrPartition(Node[] nodeArr, int pivot) {
        int smaller = -1;
        int bigger = nodeArr.length;
        int index = 0;

        while ( index != bigger) {
            if (nodeArr[index].value < pivot) {
                // 小于
                smaller++;
                index++;
            } else if (nodeArr[index].value > pivot) {
                bigger--;
                swap(nodeArr, index, bigger);
            } else {
                // 相等
                index++;
            }
        }
        Node node = nodeArr[0];
        nodeArr[nodeArr.length - 1].next = null;
        Node res = node;
        for (int i = 1; i < nodeArr.length; i++) {
            node.next = nodeArr[i];
            node = node.next;
        }
        return res;
    }

    public static void swap(Node[] nodeArr, int a, int b) {
        Node tmp = nodeArr[a];
        nodeArr[a] = nodeArr[b];
        nodeArr[b] = tmp;
    }

    private static Node listPartition2(Node head, int partitionNum) {
        // 最简单的做法, 用一个数组来装, 装好了再遍历为node
        // 分为大等小三个节点
        Node small = null;
        Node equal = null;
        Node bigger = null;

        Node smallHeadSave = null;
        Node equalHeadSave = null;
        Node biggerHaedSave = null;

        while (head != null) {

            if (head.value < partitionNum) {
                // 小于
                if (small == null) {
                    small = head;
                    smallHeadSave = head;
                } else {
                    small.next = head;
                    small = small.next;
                }
            } else if (head.value > partitionNum) {
                if (bigger == null) {
                    bigger = head;
                    biggerHaedSave = head;
                } else {
                    bigger.next = head;
                    bigger = bigger.next;
                }
            } else {
                if (equal == null) {
                    equal = head;
                    equalHeadSave = head;
                } else {
                    equal.next = head;
                    equal = equal.next;
                }
            }
            head = head.next;
        }
        if (small != null) {
            small.next = null;
        }
        if (equal != null) {
            equal.next = null;
        }
        if (bigger != null) {
            bigger.next = null;
        }
        // 拼接三段
        if (smallHeadSave != null && small != null) {
            // small有值
            if (equalHeadSave != null && equal != null) {
                // eq有值
                small.next = equalHeadSave;
                equal.next = biggerHaedSave;
            } else {
                // eq无值
                small.next = biggerHaedSave;
            }
            return smallHeadSave;
        } else if (equalHeadSave != null && equal != null) {
            // small无值, eq有值
            equal.next = biggerHaedSave;
            return equalHeadSave;
        } else {
            // small无值, eq无值
            return biggerHaedSave;
        }
    }

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

}
