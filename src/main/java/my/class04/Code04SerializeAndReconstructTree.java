package my.class04;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 介绍二叉树的序列化和反序列化
 *
 * @author RichardLee
 * @version v1.0
 */
public class Code04SerializeAndReconstructTree {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static void main(String[] args) {
        Node head = null;
        printTree(head);

        String pre = serialByPre(head);
        System.out.println("serialize tree by pre-order: " + pre);
        head = reconByPreString(pre);
        System.out.print("reconstruct tree by pre-order, ");
        printTree(head);

        String level = serialByLevel(head);
        System.out.println("serialize tree by level: " + level);
        head = reconByLevelString(level);
        System.out.print("reconstruct tree by level, ");
        printTree(head);

        System.out.println("====================================");

        head = new Node(1);
        printTree(head);

        pre = serialByPre(head);
        System.out.println("serialize tree by pre-order: " + pre);
        head = reconByPreString(pre);
        System.out.print("reconstruct tree by pre-order, ");
        printTree(head);

        level = serialByLevel(head);
        System.out.println("serialize tree by level: " + level);
        head = reconByLevelString(level);
        System.out.print("reconstruct tree by level, ");
        printTree(head);

        System.out.println("====================================");

        head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.right.right = new Node(5);
        printTree(head);

        pre = serialByPre(head);
        System.out.println("serialize tree by pre-order: " + pre);
        head = reconByPreString(pre);
        System.out.print("reconstruct tree by pre-order, ");
        printTree(head);

        level = serialByLevel(head);
        System.out.println("serialize tree by level: " + level);
        head = reconByLevelString(level);
        System.out.print("reconstruct tree by level, ");
        printTree(head);

        System.out.println("====================================");

        head = new Node(100);
        head.left = new Node(21);
        head.left.left = new Node(37);
        head.right = new Node(-42);
        head.right.left = new Node(0);
        head.right.right = new Node(666);
        printTree(head);

        pre = serialByPre(head);
        System.out.println("serialize tree by pre-order: " + pre);
        head = reconByPreString(pre);
        System.out.print("reconstruct tree by pre-order, ");
        printTree(head);

        level = serialByLevel(head);
        System.out.println("serialize tree by level: " + level);
        head = reconByLevelString(level);
        System.out.print("reconstruct tree by level, ");
        printTree(head);

        System.out.println("====================================");

    }

    private static String serialByPre(Node head) {
        if (head == null) {
            return "#!";
        }
        // 就是前序遍历的时候, 本来是打印的, 改为string
        String res = head.value + "!";
        res += serialByPre(head.left);
        res += serialByPre(head.right);
        return res;
    }

    private static Node reconByPreString(String preStr) {
        // string里的数先存入队列中, 方便使用而已, 也可不存
        String[] values = preStr.split("!");
        Queue<String> queue = new LinkedList<>();
        for (String value : values) {
            queue.offer(value);
        }
        return reconPreOrder(queue);
    }

    private static Node reconPreOrder(Queue<String> queue) {
        // 取一个数
        String poll = queue.poll();
        // # 号占位符, 则返回null
        if (("#").equals(poll) || poll == null) {
            return null;
        }
        // 把先序遍历中的打印换为 new Node
        // 其余一样, 递归调用
        Node node = new Node(Integer.parseInt(poll));
        node.left = reconPreOrder(queue);
        node.right = reconPreOrder(queue);
        return node;
    }

    private static Node reconByLevelString(String levelStr) {
        // 本质上就是用了两个容器, 一个装原来的string(便于加左右孩), 一个队列装指针的node(便于知道指针轮到了哪个)
        // 用于装原来的, 可以放到队列里, 稍微方便点
        String[] values = levelStr.split("!");
        // 用于values的指针
        int index = 0;
        // 生成头节点
        Node head = generateNodeByString(values[index++]);
        // 队列
        Queue<Node> queue = new LinkedList<>();

        // 先加一个到队列里, 为的是给while循环一个初始值
        if (head != null) {
            queue.offer(head);
        }
        // node吧 为了复用写在这的
        Node node = null;

        while (!queue.isEmpty()) {
            // 当队列有值时
            // 队列是为了用于确认到了哪个node加左右孩子了
            node = queue.poll();
            node.left = generateNodeByString(values[index++]);
            node.right = generateNodeByString(values[index++]);

            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return head;

    }

    private static Node generateNodeByString(String value) {
        if ("#".equals(value)) {
            return null;
        }
        return new Node(Integer.parseInt(value));
    }

    private static String serialByLevel(Node head) {
        if (head == null) {
            return "#!";
        }

        String res = head.value + "!";
        Queue<Node> queue = new LinkedList<>();
        queue.offer(head);

        while (!queue.isEmpty()) {
            head = queue.poll();
            if (head.left != null) {
                res += head.left.value + "!";
                queue.offer(head.left);
            } else {
                res += "#!";
            }

            if (head.right != null) {
                res += head.right.value + "!";
                queue.offer(head.right);
            } else {
                res += "#!";
            }
        }
        return res;
    }

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
}
