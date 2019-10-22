package my.class01;

import java.util.Arrays;

/**
 * .
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 *
 * @author RichardLee
 * @version v1.0
 */
public class Code03HeapSort {

    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int index = arr.length - 1;
        heapInsert(arr, index);
        // 把数组转为大根堆
        // 一个一个传入, 并在传入的时候调整
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }
        // 移除位在第一个数据的根节点，并做最大堆调整的递归运算(heapify)
        int i = arr.length - 1;
        while (i > 0) {
            // 把最大的数移到数组从后面的位置, 依次减少
            swap(arr, i, 0);
            // 调整移动后的数组
            heapify(arr, 0, --i);
        }

    }

    /**
     * 将一个数组, 一个一个数存入时, 按照大根堆规则存入
     *
     * @param arr   arr
     * @param index 存入的位置
     */
    public static void heapInsert(int[] arr, int index) {

        // index从0开始依次向后排
        if (arr[index] > arr[(index - 1) / 2]) {
            // 在子节点大于父节点的情况下
            // 交换父子位置
            swap(arr, index, (index - 1) / 2);
            // 变换该数的下标
            index = (index - 1) / 2;
            // 继续插入
            heapInsert(arr, index);
        }
    }

    /**
     * 当移除一个数时, 堆堆的末端子节点作调整，使得子节点永远小于父节点
     *
     * @param arr           arr
     * @param index         记录从0开始的节点下标
     * @param rightBoundary 需要调整位置的范围
     */
    public static void heapify(int[] arr, int index, int rightBoundary) {
        // 左孩索引
        int childLeft = index * 2 + 1;
        // 右孩索引
        int childRight = index * 2 + 2;
        // 左孩右孩和自己, 哪个值大, maxIndex就设置为其索引号
        int maxIndex = 0;
        if (childLeft <= rightBoundary && childRight <= rightBoundary) {
            // 在左右孩都不越界的情况下
            // 判断左右孩哪个大
            maxIndex = arr[childLeft] > arr[childRight] ? childLeft : childRight;
        } else if (childLeft <= rightBoundary) {
            maxIndex = childLeft;
        } else {
            return;
        }
        // 判断自己和大孩哪个大
        if (arr[index] < arr[maxIndex]) {
            // 如果自己小于孩子
            // 交换位置
            swap(arr, index, maxIndex);
            // 修改自己的索引
            index = maxIndex;
            // 递归调用
            heapify(arr, index, rightBoundary);
        }
    }


    public static void swap(int[] arr, int i, int j) {
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
            heapSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = generateRandomArray(maxSize, maxValue);
//        int[] arr = new int[]{3, 1, 2, 4, 5};
        printArray(arr);
        heapSort(arr);
        printArray(arr);
    }

}
