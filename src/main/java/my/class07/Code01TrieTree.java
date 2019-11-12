package my.class07;

/**
 * 前缀树.
 *
 * @author RichardLee
 * @version v1.0
 */
public class Code01TrieTree {

    public static class TrieNode {
        // 记录该字母有多少来过的, 就是把信息放在边上
        // 放在节点上就不好弄了
        private int path;
        // 记录有多少是以该词为结尾的
        private int end;
        // 存放下一节点的
        private TrieNode[] nexts;

        public TrieNode() {
            // 初始化
            path = 0;
            end = 0;
            // 26个字母
            nexts = new TrieNode[26];
        }
    }

    public static class Trie {
        // 单例只有一个root
        private TrieNode root;

        public Trie() {
            // 初始化
            root = new TrieNode();
        }

        public void insert(String word) {
            if (word == null) {
                return;
            }
            char[] chs = word.toCharArray();
            // 只有一个root~
            TrieNode node = root;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                // 用于选择哪个索引来存
                index = chs[i] - 'a';
                if (node.nexts[index] == null) {
                    // 该索引无值, new一个
                    node.nexts[index] = new TrieNode();
                }
                // 换下节点
                node = node.nexts[index];
                // 说明这个字母有人用了
                node.path++;
            }
            // 结束了, end+1
            node.end++;
        }

        public int search(String word) {
            if (word == null) {
                return 0;
            }
            char[] chs = word.toCharArray();
            TrieNode node = root;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = chs[i] - 'a';
                if (node.nexts[index] == null) {
                    // 中间有一个对不上, 就返回0
                    return 0;
                }
                node = node.nexts[index];
            }
            // 返回有几个是以此为结尾的
            return node.end;
        }

        public void delete(String word) {
            // 先看看是不是有存在这个词
            if (search(word) != 0) {
                // 存在的就去删除啦~
                char[] chs = word.toCharArray();
                TrieNode node = root;
                int index = 0;
                for (int i = 0; i < chs.length; i++) {
                    index = chs[i] - 'a';
                    // node.nexts[index].path -1 后的值是0, 说明之后的node都只有一个, 那么可以直接抹去后面的
                    if (--node.nexts[index].path == 0) {
                        node.nexts[index] = null;
                        return;
                    }
                    node = node.nexts[index];
                }
                node.end--;
            }
        }

        public int prefixNumber(String pre) {
            if (pre == null) {
                return 0;
            }
            char[] chs = pre.toCharArray();
            TrieNode node = root;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = chs[i] - 'a';
                if (node.nexts[index] == null) {
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.path;
        }
    }
}
