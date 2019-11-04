package my.class04;

import java.util.Stack;

/**
 * 实现二叉树的先序、中序、后序遍历，包括递归方式和非递归
 * 方式
 *
 * @author RichardLee
 * @version v1.0
 */
public class Code01PreInPosTraversal {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }
    
    public static void main(String[] args) {
//        Node head = new Node(5);
//        head.left = new Node(3);
//        head.right = new Node(8);
//        head.left.left = new Node(2);
//        head.left.right = new Node(4);
//        head.left.left.left = new Node(1);
//        head.right.left = new Node(7);
//        head.right.left.left = new Node(6);
//        head.right.right = new Node(10);
//        head.right.right.left = new Node(9);
//        head.right.right.right = new Node(11);
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.left.right.left = new Node(7);
        head.left.right.right = new Node(8);



        // recursive
        System.out.println("==============recursive==============");
        System.out.print("pre-order: ");
        preOrderRecur(head);
        System.out.println();
        System.out.print("in-order: ");
        inOrderRecur(head);
        System.out.println();
        System.out.print("pos-order: ");
        posOrderRecur(head);
        System.out.println();

        // unrecursive
        System.out.println("============unrecursive=============");
        System.out.print("pre-order: ");
        preOrderUnRecur(head);
        System.out.print("in-order: ");
        inOrderUnRecur(head);
        System.out.print("pos-order: ");
        posOrderUnRecur1(head);
//        System.out.print("pos-order: ");

//        posOrderUnRecur2(head);

    }

    private static void posOrderUnRecur1(Node head) {
        // 后续 左 - 右 - 中
        // 相当于 前序(中 左 右)改改成为(中 右 左)存到一个辅助栈中, 再取出的过程
        if (head != null) {
            Stack<Node> nodeStack = new Stack<>();
            Stack<Node> helpStack = new Stack<>();
            nodeStack.push(head);
            while (!nodeStack.isEmpty()) {
                head = nodeStack.pop();
                helpStack.push(head);
                if (head.left != null) {
                    nodeStack.push(head.left);
                }
                if (head.right != null) {
                    nodeStack.push(head.right);
                }
            }

            while (!helpStack.isEmpty()) {
                System.out.print(helpStack.pop().value +" ");
            }
        }
        System.out.println();
    }


    private static void inOrderUnRecur(Node head) {
        // 中序 左 - 中 - 右
        if (head != null) {
            Stack<Node> nodeStack = new Stack<>();
            while (!nodeStack.isEmpty() || head != null) {
                if (head != null) {
                    nodeStack.push(head);
                    head = head.left;
                } else {
                    head = nodeStack.pop();
                    System.out.print(head.value + " ");
                    head = head.right;
                }
            }
        }
        System.out.println();
    }

    private static void preOrderUnRecur(Node head) {
        // 前序 中 - 左 - 右

        if (head != null) {
            Stack<Node> nodeStack = new Stack<>();
            nodeStack.push(head);
            while (!nodeStack.isEmpty()) {
                head = nodeStack.pop();
                System.out.print(head.value + " ");
                if (head.right != null) {
                    nodeStack.push(head.right);
                }
                if (head.left != null) {
                    nodeStack.push(head.left);
                }
            }
        }
        System.out.println();
    }

    private static void posOrderRecur(Node head) {
        if (head == null) {
            return;
        }
        posOrderRecur(head.left);
        posOrderRecur(head.right);
        System.out.print(head.value +" ");
    }

    private static void inOrderRecur(Node head) {
        if (head == null) {
            return;
        }
        inOrderRecur(head.left);
        System.out.print(head.value + " ");
        inOrderRecur(head.right);
    }

    private static void preOrderRecur(Node head) {
        if (head == null) {
            return;
        }
        System.out.print(head.value +" ");
        preOrderRecur(head.left);
        preOrderRecur(head.right);
    }
}
