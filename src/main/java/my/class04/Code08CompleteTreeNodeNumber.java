package my.class04;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 已知一棵完全二叉树, 求其节点的个数.
 * 要求: 时间复杂度低于O(N), N为这个树的节点个数
 *
 * @author RichardLee
 * @version v1.0
 */
public class Code08CompleteTreeNodeNumber {
    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        System.out.println(nodeNum(head));

    }

    private static int nodeNum(Node head) {
        if (head == null) {
            return 0;
        }
        // 1. 前提是要知道一个性质, 满二叉树的节点是 2^n - 1
        // 2. 判断左或右子树是否为满二叉树, 有一个是, 另一个就不是
        // 3. 遍历另一个就行了

        // 先找左节点或右节点吧最右的那个节点是不是满的
        // 通过右节点和层数

        // TODO 这里其实求一个level就行了, 不满的 level 是满的 - 1
        // TODO 还有2的 n 次方可以用 << 来
        Node l = head.left;
        Node r = head.right;
        int lLevel = getLevel(l);
        boolean lIsFull = isFull(l, lLevel);
        int rLevel = getLevel(r);
        int res = 0;
        if (lIsFull) {
            // 左边是满的
            res = (int) Math.pow(2, lLevel);
            // 加上右边遍历下
            res += traverse(r);
        } else {
            // 右边是满的
            res = (int) Math.pow(2, rLevel);
            res += traverse(l);
        }
        return res;
    }

    private static int traverse(Node head) {
        int i = 0;
        Queue<Node> nodeQueue = new LinkedList<>();
        nodeQueue.offer(head);
        while (!nodeQueue.isEmpty()) {
            head = nodeQueue.poll();
            if (head.left != null) {
                nodeQueue.offer(head.left);
            } else if (head.right != null) {
                nodeQueue.offer(head.right);
            }
            i++;
        }
        return i;
    }

    private static boolean isFull(Node l, int lLevel) {
        int i = 1;
        while (l.right != null) {
            l = l.right;
            i++;
        }
        if (i == lLevel) {
            return true;
        }
        return false;
    }

    private static int getLevel(Node l) {
        int i = 1;
        while (l.left != null) {
            l = l.left;
            i++;
        }
        return i;
    }

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

}
