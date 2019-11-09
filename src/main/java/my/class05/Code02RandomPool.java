package my.class05;

import java.util.HashMap;
import java.util.Random;

/**
 * 设计RandomPool结构
 * 【题目】 设计一种结构，在该结构中有如下三个功能：
 * insert(key)：将某个key加入到该结构，做到不重复加入。
 * delete(key)：将原本在结构中的某个key移除。
 * getRandom()：等概率随机返回结构中的任何一个key。
 * 【要求】 Insert、delete和getRandom方法的时间复杂度都是
 * O(1)
 *
 * @author RichardLee
 * @version v1.0
 */
public class Code02RandomPool {

    public static void main(String[] args) {
        Pool<String> pool = new Pool<>();
        pool.insert("zuo");
        pool.insert("cheng");
        pool.insert("yun");
        pool.delete("zuo");
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());

    }

    public static class Pool<K> {
        public HashMap<K, Integer> keyIndexMap;
        public HashMap<Integer, K> indexKeyMap;
        public int size;

        public Pool() {
            keyIndexMap = new HashMap<>();
            indexKeyMap = new HashMap<>();
            size = 0;
        }

        public void insert(K key) {
            if (!keyIndexMap.containsKey(key)) {
                keyIndexMap.put(key, size);
                indexKeyMap.put(size, key);
                size++;
            }
        }

        public void delete(K key) {
            // 删除一个 key, 需要在第一个 map 里删除, 也要在第二个 map 里删除
            // 删除出了空洞, 容易造成随机 get 的性能浪费, 因此解决方案是
            // 把最后的那个 key 和删除的 key 交换

            if (keyIndexMap.containsKey(key)) {
                size--;
                // 找出 key 对应的 value
                Integer deleteIndex = keyIndexMap.get(key);
                // 找出最后一个 key
                K lastKey = indexKeyMap.get(size);
                // 把第一个 map 里的换了
                keyIndexMap.put(lastKey, deleteIndex);
                keyIndexMap.remove(key);
                // 把第二个 map 里的换了
                indexKeyMap.put(deleteIndex, lastKey);
                // 删除第一个 key 和 第二个 index
                indexKeyMap.remove(size);
                // 改变大小

            }
        }

        public K getRandom() {
            if (size == 0) {
                return null;
            }
            int randomIndex = new Random().nextInt(size);
            return indexKeyMap.get(randomIndex);
        }
    }

}
