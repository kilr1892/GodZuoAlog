package my.class07;

import java.util.PriorityQueue;

/**
 * 一块金条切成两半，是需要花费和长度数值一样的铜板的。比如
 * 长度为20的 金条，不管切成长度多大的两半，都要花费20个铜
 * 板。一群人想整分整块金 条，怎么分最省铜板？
 * 例如,给定数组{10,20,30}，代表一共三个人，整块金条长度为
 * 10+20+30=60. 金条要分成10,20,30三个部分。 如果， 先把长
 * 度60的金条分成10和50，花费60 再把长度50的金条分成20和30，
 * 花费50 一共花费110铜板。
 * 但是如果， 先把长度60的金条分成30和30，花费60 再把长度30
 * 金条分成10和20，花费30 一共花费90铜板。
 * 输入一个数组，返回分割的最小代价。
 *
 * @author RichardLee
 * @version v1.0
 */
public class Code02Less_Money {
    public static int lessMoney(int[] arr) {
        // 存到一个优先级队列中
        PriorityQueue<Integer> pQ = new PriorityQueue<>();
        for (int i : arr) {
            pQ.offer(i);
        }
        // 最少的钱
        int sum = 0;
        int cur = 0;
        while (pQ.size() > 1) {
            // 如果队列里有两个数
            // 算出最小的两个数相加的值
            cur = pQ.poll() + pQ.poll();
            // 花费的钱
            sum += cur;
            // 再将值加入到队列中
            pQ.offer(cur);
        }
        return sum;
    }
}
