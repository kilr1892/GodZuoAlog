package my.class04;

/**
 * 判断一棵二叉树是否是平衡二叉树
 *
 * @author RichardLee
 * @version v1.0
 */
public class Code06IsBalancedTree {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }
    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        System.out.println(isBalance(head));

    }

    public static class ReturnData {
        private boolean isB;
        private int h;

        public ReturnData(boolean isB, int h) {
            this.isB = isB;
            this.h = h;
        }
    }

    private static boolean isBalance(Node head) {
        ReturnData balanceProcess = isBalanceProcess(head);
        return balanceProcess.isB;
    }

    private static ReturnData isBalanceProcess(Node head) {
        // 这是给一个 base case 默认为 true
        if (head == null) {
            return new ReturnData(true, 0);
        }
        // 假设左子树能返回一个判断
        ReturnData leafData = isBalanceProcess(head.left);
        // 先进行左子树平衡性判断
        if (!leafData.isB) {
            // 如果不平衡, 返回 false, 根据递归, 一路返回 false
            return new ReturnData(false, 0);
        }
        // 假设右子树能返回一个判断
        ReturnData rightData = isBalanceProcess(head.right);
        // 再对右子树进行平衡性判断
        if (!rightData.isB) {
            // 如果不平衡, 返回 false, 根据递归, 一路返回 false
            return new ReturnData(false, 0);
        }
        // 如果左右两子树都平衡, 则进行高度差判断, 看根树是否平衡
        if (Math.abs(leafData.h - rightData.h) > 1) {
            // 若大于1 则不平衡
            return new ReturnData(false, 0);
        }
        // 如果上述 if 都通过了, 则说明根树是平衡的
        // 放回 true, 并对该根树的层数取两子树最大值 + 1
        return new ReturnData(true, Math.max(leafData.h, rightData.h) + 1);

    }

}
