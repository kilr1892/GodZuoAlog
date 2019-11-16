package my.class08;

/**
 * 母牛每年生一只母牛，新出生的母牛成长三年后也能每年生一只
 * 母牛，假设不会死。求N年后，母牛的数量。
 *
 * @author RichardLee
 * @version v1.0
 */
public class Code05Cow {
    public static int cowNumber1(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1 || n == 2 || n == 3) {
            return n;
        }
        return cowNumber1(n - 1) + cowNumber1(n - 3);
    }
}
