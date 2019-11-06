package my.class04;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 判断一棵二叉树是否是平衡二叉树
 *
 * @author RichardLee
 * @version v1.0
 */
public class Code07IsBSTAndCBT {
    // for test -- print tree
    public static void printTree(Node head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    public static void main(String[] args) {
//        Node head = new Node(4);
//        head.left = new Node(2);
//        head.right = new Node(6);
//        head.left.left = new Node(1);
//        head.left.right = new Node(3);
//        head.right.left = new Node(5);

        Node head = new Node(3);
        head.left = new Node(2);
        head.right = new Node(5);
//        head.right.right = new Node(7);
        head.left.left = new Node(1);
        head.left.right = new Node(4);
        printTree(head);
        System.out.println(isBST(head));
        System.out.println(isCBT(head));

    }

    // 判断是否为完全二叉树
    // 就是从左往右排, 依次排满
    private static boolean isCBT(Node head) {
        if (head == null) {
            return true;
        }
        // 可能性, 从节点的状态开始考虑
        // 节点的状态分为, 1) 左右孩双全 2) 有左无右 3) 有右无左 4) 无左无右
        // 找出 false 条件: 情况3 或出现2的情况后, 继续右边还有节点
        // 其余都为 true
        // 用的是层级遍历, 要用一个队列来存
        Queue<Node> queue = new LinkedList<>();
        queue.offer(head);
        Node l = null;
        Node r = null;
        // leaf 为出现2 4的情况后, 开始判断叶节点阶段
        boolean leaf = false;

        while (!queue.isEmpty()) {
            head = queue.poll();
            l = head.left;
            r = head.right;
            // 判断出现2的情况开始后, 下一层是否全为叶节点 以及 3的情况
            if ((leaf && (l != null || r != null)) || (head.left == null && head.right != null)) {
                return false;
            }
            // 在队列中加入当前节点的下一层左右孩
            if (l != null) {
                queue.offer(l);
            }
            if (r != null) {
                queue.offer(r);
            }
            // 第四种情况
            if (l == null || r == null) {
                leaf = true;
            }
        }

        return true;
    }

    // 二叉查找树
    // 二叉查找树（Binary Search Tree，BST）是一种特殊的二叉树，
    // 一棵二叉搜索树（BST）是一棵二叉树，其中，每个节点的值都要
    // 大于其左子树中任意节点的值而小于右子树中任意节点的值。
    // 感觉这个定义有些奇怪 = = 好吧  不管了
    // 用中序遍历可以做的, 其排序好的值是升序即可
    private static boolean isBST(Node root) {
        return helper(root, null, null);
    }

    private static boolean helper(Node node, Integer lower, Integer upper) {
        if (node == null) {
            return true;
        }
        int val = node.value;
        if (lower != null && val <= lower) {
            return false;
        }
        if (upper != null && val >= upper) {
            return false;
        }
        if (!helper(node.right, val, upper)) {
            return false;
        }
        if (!helper(node.left, lower, val)) {
            return false;
        }
        return true;
    }
}

class Node {
    public int value;
    public Node left;
    public Node right;

    public Node(int data) {
        this.value = data;
    }
}