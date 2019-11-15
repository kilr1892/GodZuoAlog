package my.class08;

/**
 * 打印一个字符串的全部排列，要求不要出现重复的排列
 *
 * @author RichardLee
 * @version v1.0
 */
public class Code03PrintAllSubsquences {
    public static void main(String[] args) {
        String test = "abc";
        printAllSub(test.toCharArray(),0,"");
    }

    private static void printAllSub(char[] str, int i, String res) {
        if (i == str.length) {
            System.out.println(res);
            return;
        }
         printAllSub(str, i + 1, res);
        printAllSub(str, i + 1, res + str[i]);
    }

    public static void printAllSub2(char[] str, int i, String res) {
        if (i == str.length) {
            System.out.println(res);
            return;
        }
        printAllSub2(str, i + 1, res);
        printAllSub2(str, i + 1, res + str[i]);
    }
}
