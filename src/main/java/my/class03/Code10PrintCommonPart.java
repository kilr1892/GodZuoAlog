package my.class03;

/**
 * 打印两个有序链表的公共部分
 * 【题目】 给定两个有序链表的头指针head1和head2，打印两个
 * 链表的公共部分。
 *
 * @author RichardLee
 * @version v1.0
 */
public class Code10PrintCommonPart {
    public static void main(String[] args) {
        Node node1 = new Node(2);
        node1.next = new Node(3);
        node1.next.next = new Node(5);
        node1.next.next.next = new Node(6);

        Node node2 = new Node(1);
        node2.next = new Node(2);
        node2.next.next = new Node(5);
        node2.next.next.next = new Node(7);
        node2.next.next.next.next = new Node(8);

        printLinkedList(node1);
        printLinkedList(node2);
        printCommonPart(node1, node2);
    }

    public static void printLinkedList(Node node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void printCommonPart(Node node1, Node node2) {

        while (node1 != null && node2 != null) {
            System.out.print("Common Part: ");
            if (node1.value < node2.value) {
                // 3.小的指针走, 直到两者相等
                node1 = node1.next;
            } else if (node1.value > node2.value) {
                node2 = node2.next;
            } else {
                // 4.当两者相等, 则
                // 两个指针都走一步, 重复 2 3 两步
                System.out.print(node1.value + " ");
                node1 = node1.next;
                node2 = node2.next;
            }
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
