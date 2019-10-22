package my.class01;

import java.util.Arrays;

/**
 * 记录递归的debug心得.
 * <p>
 * 先找到递归出错的那个递归条件赋予的值
 * <p>
 * 之后从那一步开始, 一步一步往下debug
 * <p>
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 *
 * @author RichardLee
 * @version v1.0
 */
public class Code04QuickSort {
    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int l, int r) {
        // 先是左边的, 0到num的左边, 一直分解, 直到两者相等
        // 再是右边的, num的右边到arr.length-1,一直分解, 直到两者相等
        // 左右分解的lr不同, 因此无法单纯的用lr的值来判断
        // 但有一点是不变的, 就是l<r这点
        // 因此用这个
//        也可以改为
//        if (l >= r) {
//            return;
//        }
        if (l < r) {
            int num = (int) (Math.random() * (r - l + 1)) + l;
            int[] partition = partition(arr, l, r, arr[num]);
            quickSort(arr, l, partition[0] - 1);
            quickSort(arr, partition[1] + 1, r);
        }

    }

    /**
     * 递归式以后都要用lr 因为是lr上来继续递归
     *
     * @param arr arr
     * @param l   需要排序的区间 左标
     * @param r   需要排序的区间 右标
     * @param num 基准值
     * @return 等于num的区间
     */
    public static int[] partition(int[] arr, int l, int r, int num) {

        // less 和 more 为小于大于指针
        // 在指针范围内的是小于和大于的
        int less = l - 1;
        int more = r + 1;

        // i 是arr的未排序, 不知与num值大还是小的指针,
        int i = l;

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

    private static void myArraySwap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // for test
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
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

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            quickSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = generateRandomArray(maxSize, maxValue);
//        int[] arr = new int[]{4, 2, 6, 5, 4};
        printArray(arr);
        quickSort(arr);
        printArray(arr);

    }
}
