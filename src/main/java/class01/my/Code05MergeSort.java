package class01.my;

import java.util.Arrays;

/**
 * 归并排序.
 *
 * 再把lr递归时候的赋值研究下吧
 *
 *
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 *
 * @author RichardLee
 * @version v1.0
 */
public class Code05MergeSort {
    public static void mergeSort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    private static void mergeSort(int[] arr, int l, int r) {
        // 分到最小只有一个的时候返回
        if (l == r) {
            return;
        }
        // 假设为[1,2,3,4]
        int mid = (l + r) / 2;
        // 先执行这(数组左边)到最后一个
        mergeSort(arr, l, mid);
        // 再以上一条最后一个的条件执行这条, 分数组的右边
        mergeSort(arr, mid + 1, r);
        // 再执行这条, 合并, 之后读取栈中上一条左边的, 带入l r 等, 执行右边的, 能合并的就继续合并, 合并不了后再分
        merge(arr, l, mid, mid + 1, r);
    }

    private static void merge(int[] arr, int l, int mid, int mid2, int r) {
        // merge 过程就是一个比较的过程, 并把比较的结果放在一个数组里, 最后复制该辅助数组
        // 用一个辅助int[]来装
        int[] helpArr = new int[(r - l)+1];
        int p1 = l;
        int p2 = mid2;
        int i = 0;
        // 比较大小了
        while (p1 <= mid && p2 <= r) {
            helpArr[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) {
            helpArr[i++] = arr[p1++];
        }
        while (p2 <= r) {
            helpArr[i++] = arr[p2++];
        }

        if (helpArr.length >= 0) System.arraycopy(helpArr, 0, arr, 0 + l, helpArr.length);
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
//        int testTime = 500000;
//        int maxSize = 100;
//        int maxValue = 100;
//        boolean succeed = true;
//        for (int i = 0; i < testTime; i++) {
//            int[] arr1 = generateRandomArray(maxSize, maxValue);
//            int[] arr2 = copyArray(arr1);
//            mergeSort(arr1);
//            comparator(arr2);
//            if (!isEqual(arr1, arr2)) {
//                succeed = false;
//                printArray(arr1);
//                printArray(arr2);
//                break;
//            }
//        }
//        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

//        int[] arr = generateRandomArray(maxSize, maxValue);
        int[] arr = new int[]{3, 1, 4, 2};
        printArray(arr);
        mergeSort(arr);
        printArray(arr);

    }
}
