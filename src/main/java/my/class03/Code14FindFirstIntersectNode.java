package my.class03;

/**
 * 两个单链表相交的一系列问题
 * 【题目】 在本题中，单链表可能有环，也可能无环。给定两个
 * 单链表的头节点 head1和head2，这两个链表可能相交，也可能
 * 不相交。请实现一个函数， 如果两个链表相交，请返回相交的
 * 第一个节点；如果不相交，返回null 即可。 要求：如果链表1
 * 的长度为N，链表2的长度为M，时间复杂度请达到 O(N+M)，额外
 * 空间复杂度请达到O(1)。
 *
 * @author RichardLee
 * @version v1.0
 */
public class Code14FindFirstIntersectNode {
    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->null
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);

        // 0->9->8->6->7->null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

        // 1->2->3->4->5->6->7->4...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println(getIntersectNode(head1, head2).value);

        // 0->9->8->6->4->5->6..
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

    }

    public static Node getIntersectNode(Node head1, Node head2) {

        if (head1 == null || head2 == null) {
            return null;
        }
        // 1. 确定有环无环 得到
        // head1 head2 loop1 loop2
        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);
        // 再分多种情况来叙述
        if (loop1 == null && loop2 == null) {
            // 1. 无环 noloop
            return noLoop(head1, head2);
        }
        // 2. 有环 (有环还分好几种情况)
        if (loop1 != null && loop2 != null) {
            return bothLoop(head1, head2, loop1, loop2);
        }
        return null;
    }

    private static Node bothLoop(Node head1, Node head2, Node loop1, Node loop2) {
        Node cur1 = null;
        Node cur2 = null;
        // loop1 和 loop2 的位置, 可以判断环的状态
        // 2.2 两条链在入环之前相交
        if (loop1 == loop2) {
            cur1 = head1;
            cur2 = head2;
            Node saveLoop1Next = loop1.next;
            // 先把两条链切成单链
            loop1.next = null;
            // 调用 noLoop
            Node node = noLoop(head1, head2);
            // 恢复
            loop1.next = saveLoop1Next;
            return node;
        } else {
            cur1 = loop1.next;
            cur2 = loop2;
            while (cur1 != cur2) {
                if (cur1 == loop1) {
                    // 2.1 环不相交
                    return null;
                }
                cur1 = cur1.next;
            }
            // 2.3 两条链分别入环
            return loop1;
        }
    }

    private static Node noLoop(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        // 感觉HashSet是万能的...
        // 不过这里用计数法, 减去长的多余的, 然后两者再一起走
        int linkDifferenceValue = 0;
        Node cur1 = head1;
        Node cur2 = head2;
        // 算一下差值
        while (cur1.next != null) {
            linkDifferenceValue++;
            cur1 = cur1.next;
        }
        while (cur2.next != null) {
            linkDifferenceValue--;
            cur2 = cur2.next;
        }

        if (cur1 != cur2) {
            // 提前判断下, 说明两条链不相交, 那么不用在后面判断了
            return null;
        }
        // 下面一定是有交点的情况
        // 判断下差值谁大谁小
        // cur1 现在是长链, cur2 是短链
        cur1 = linkDifferenceValue > 0 ? head1 : head2;
        cur2 = cur1 == head1 ? head2 : head1;
        linkDifferenceValue = Math.abs(linkDifferenceValue);
        for (int i = 0; i < linkDifferenceValue; i++) {
            cur1 = cur1.next;
        }
        while (cur1 != cur2) {
            // 两者相等跳出
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        // 返回相等的值
        return cur1;
    }

    private static Node getLoopNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        // 确定有无环
        // 1. 可以用hashset  2. 可以用一种算法
        // 这里先用第二种
        Node n1 = head.next;
        Node n2 = head.next.next;

        // 这种算法为:
        // 快指针一次2步 慢指针一次1步
        // 先找到快慢指针相遇的点
        // 之后快指针回到头节点, 这次快指针一次走一步
        // 两者再次相遇的地点为入环第一个节点
        while (n2 != n1) {
            if (n2.next == null || n2.next.next == null) {
                return null;
            }
            n1 = n1.next;
            n2 = n2.next.next;
        }
        n2 = head;
        while (n2 != n1) {
            n2 = n2.next;
            n1 = n1.next;
        }

        return n2;
    }

    public static Node getLoopNodeHashMap(Node head) {
        // 这个用 hashmap 试试
        return null;
    }

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }
}
