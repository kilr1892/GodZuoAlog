package class01.my.class01;

/**
 * .
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 *
 * @author RichardLee
 * @version v1.0
 */
public class Code08NetherlandsFlag {
    /**
     * 问题二（荷兰国旗问题）
     * 给定一个数组arr，和一个数num，请把小于num的数放在数组的
     * 左边，等于num的数放在数组的中间，大于num的数放在数组的
     * 右边。
     *
     * @param arr 给定的arr
     * @param l   要排序的左下标
     * @param r   要排序的右下标
     * @param num   num, 基本值
     * @return int[2] 等于num的的索引
     */
    public static int[] partition(int[] arr, int l, int r, int num) {
        // less 和 more 为小于大于指针
        // 在指针范围内的是小于和大于的
        int less = l - 1;
        int more = r + 1;
        // i 是arr的未排序, 不知与num值大还是小的指针,
        int i = 0;

        while (i < more) {
            // 当 i 还没遇到到more指针
            if (arr[i] < num) {
                // 当小于时
                // 先让less + 1
                // 再让i处和less处的值交换
                // 最好i++
                myArraySwap(arr, i++, ++less);
            } else if (arr[i] > num) {
                // 先more--
                // 再将more和i处的值交换, 因为换来的值还未判断排序过
                // 所以i不用改变位置
                myArraySwap(arr, i, --more);
            } else {
                // 相等
                i++;
            }
        }
        // 返回值
        return new int[]{less + 1, more - 1};
    }

    private static void myArraySwap(int[] arr, int i, int less) {
        int tmp = arr[i];
        arr[i] = arr[less];
        arr[less] = tmp;
    }

    // for test
    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // for test
    public static int[] generateArray() {
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 3);
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
//        int[] test = generateArray();
        int[] test = new int[]{4, 2, 6, 5, 4};
        printArray(test);
        int[] res = partition(test, 0, test.length - 1, 1);
//        int[] res = partition(test);
        printArray(test);
        System.out.println(res[0]);
        System.out.println(res[1]);

    }
}
