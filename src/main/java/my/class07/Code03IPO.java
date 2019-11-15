package my.class07;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 输入： 参数1，正数数组costs 参数2，正数数组profits 参数3，
 * 正数k 参数4，正数m
 * costs[i]表示i号项目的花费 profits[i]表示i号项目在扣除花
 * 费之后还能挣到的钱(利润) k表示你不能并行、只能串行的最多
 * 做k个项目 m表示你初始的资金
 * 说明：你每做完一个项目，马上获得的收益，可以支持你去做下
 * 一个 项目。
 * 输出： 你最后获得的最大钱数。
 *
 * @author RichardLee
 * @version v1.0
 */
public class Code03IPO {
    public static class Node {
        /** 利润 */
        private int p;
        /** 花费 */
        private int c;

        public Node(int p, int c) {
            this.p = p;
            this.c = c;
        }
    }

    public static class MinCostComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o1.p - o2.p;
        }
    }

    public static class MaxProfitComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o2.c - o1.c;
        }
    }

    public static int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        // 先把东西做成Node吧
        Node[] nodes = new Node[profits.length];
        for (int i = 0; i < profits.length; i++) {
            nodes[i] = new Node(profits[i], capital[i]);
        }
        // 花费最少的
        PriorityQueue<Node> minCostQ = new PriorityQueue<>(new MinCostComparator());
        // 能做的活
        PriorityQueue<Node> maxProfitQ = new PriorityQueue<>(new MaxProfitComparator());
        // 把值存进来吧
        for (int i = 0; i < nodes.length; i++) {
            minCostQ.add(nodes[i]);
        }
        // 最多做k个项目
        for (int i = 0; i < k; i++) {
            // 当前有w的钱
            // 当前的小根堆里的, 能不能做, 能做就加进来
            while (!minCostQ.isEmpty() && minCostQ.peek().c <= w) {
                maxProfitQ.add(minCostQ.poll());
            }
            if (maxProfitQ.isEmpty()) {
                // 能做的活一个都没有, 直接返回
                return w;
            }
            // 做能做的, 利润最大的那个
            w += maxProfitQ.poll().p;
        }
        return w;
    }

}
