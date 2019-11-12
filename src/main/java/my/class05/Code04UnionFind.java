package my.class05;

import java.util.HashMap;
import java.util.List;

/**
 * .
 *
 * @author RichardLee
 * @version v1.0
 */
public class Code04UnionFind {
    public static class Node {
        // whatever you like
    }
    public static class UnionFindSet {
        // key-value : child-father
        public HashMap<Node, Node> fatherMap;
        public HashMap<Node, Integer> sizeMap;

        // 构造方法
        public UnionFindSet(List<Node> nodes) {
            makeSets(nodes);
        }

        public void makeSets(List<Node> nodes) {
            // 初始化
            fatherMap = new HashMap<>();
            sizeMap = new HashMap<>();
            // 存为父节点
            for (Node node : nodes) {
                fatherMap.put(node, node);
                // 各自的结合元素个数为1
                sizeMap.put(node, 1);
            }
        }
        // 找到代表节点
        private Node findHead(Node node) {
            Node father = fatherMap.get(node);
            if (father != node) {
                // 两个node相等, 说明已经到了代表节点
                father = findHead(node);
            }
            return father;
        }

        public boolean isSameSet(Node a, Node b) {
            return findHead(a) == findHead(b);
        }

        // 合并两个, 并赋予一个为代表节点
        public void union(Node a, Node b) {
            if (a == null || b == null) {
                return;
            }
            // 找到代表节点
            Node aHead = findHead(a);
            Node bHead = findHead(b);
            if (aHead != bHead) {
                // 说明是两个代表
                int aSetSize = sizeMap.get(aHead);
                int bSetSize = sizeMap.get(bHead);
                if (aSetSize <= bSetSize) {
                    fatherMap.put(aHead, bHead);
                    sizeMap.put(bHead, aSetSize + bSetSize);
                } else {
                    fatherMap.put(bHead, aHead);
                    sizeMap.put(aHead, aSetSize + bSetSize);
                }
            }
        }
    }
}
