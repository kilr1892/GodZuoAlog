package my.class08;

import java.util.HashSet;

/**
 * .
 *
 * @author RichardLee
 * @version v1.0
 */
public class Code04PrintAllPermutations {
    public static void printAllPermutations1(String str) {
        char[] chs = str.toCharArray();
        process1(chs, 0);
    }

    public static void process1(char[] chs, int i) {
        if (i == chs.length) {
            // i 是代表换到第几个数了
            System.out.println(String.valueOf(chs));
        }
        for (int j = i; j < chs.length; j++) {
            // 为了把所有的数交换
            swap(chs, i, j);
            process1(chs, i + 1);
        }
    }

    public static void process2(char[] chs, int i) {
        if (i == chs.length) {
            System.out.println(String.valueOf(chs));
        }
        HashSet<Character> set = new HashSet<>();
        for (int j = i; j < chs.length; j++) {
            if (!set.contains(chs[j])) {
                set.add(chs[j]);
                swap(chs, i, j);
                process2(chs, i + 1);
            }
        }
    }

    public static void swap(char[] str, int i, int j) {
        char c = str[i];
        str[i] = str[j];
        str[j] = c;
    }

    public static void main(String[] args) {
        String test1 = "abc";
        printAllPermutations1(test1);
    }

}
