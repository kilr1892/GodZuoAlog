package class01.my.class01;

import java.util.Arrays;

/**
 * 找出一组有序数相连两数的最大差值
 * 桶排序, 用空桶的目的是保证差值不在桶内,
 * 因为至少有一个空桶, 空桶两边非空桶的差值
 * 至少比一个桶的范围大
 * <p>
 * 思路:
 * 1. 先遍历, 找出最大最小, 确定桶的个数
 * 2. 遍历数组, 确定没个数停留的桶索引
 * 3. 在桶里, 只记录桶的最大值和最小值
 * 4. 非空桶两两比较, 确定最大差值
 *
 * @author RichardLee
 * @version v1.0
 */
public class Code11MaxGap {
    public static int maxGap(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        // 1. 先遍历, 找出最大最小, 确定桶的个数
        int len = nums.length;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            min = Math.min(min, nums[i]);
            max = Math.max(max, nums[i]);
        }

        if (max == min) {
            return 0;
        }

        // 记录桶是否为空
        // len+1 保证了空桶一定存在
        boolean[] hasNum = new boolean[len + 1];
        int[] maxs = new int[len + 1];
        int[] mins = new int[len + 1];
        // 用来记录桶的值
        int bid = 0;
        for (int i = 0; i < nums.length; i++) {
            // 2. 遍历数组, 确定数应该分配去的桶索引(index)
            // 这个算法, 保证了最小的在第一个桶, 最大的在最后一个桶
            bid = (nums[i] - min) * len / (max - min);
            // 3. 在桶里, 只记录桶的最大值和最小值
            // 桶里有没有值, 有的话比较, 没有的话直接存
            mins[bid] = hasNum[bid] ? Math.min(nums[i], mins[bid]) : nums[i];
            maxs[bid] = hasNum[bid] ? Math.max(nums[i], maxs[bid]) : nums[i];
            hasNum[bid] = true;
        }

        // 4. 非空桶两两比较, 确定最大差值
        int res = Integer.MIN_VALUE;
        int lastMax = maxs[0];
        for (int i = 0; i < len; i++) {
            if (hasNum[i + 1]) {
                // 如果下一个不是空桶
                res = Math.max(res, mins[i + 1] - lastMax);
                // 记录这个桶的值
                lastMax = maxs[i + 1];
            }
            // 如果是空桶, 跳过, 最大值还是上一个不是空桶的值
        }

        return res;
    }

    // for test
    public static int comparator(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        Arrays.sort(nums);
        int gap = Integer.MIN_VALUE;
        for (int i = 1; i < nums.length; i++) {
            gap = Math.max(nums[i] - nums[i - 1], gap);
        }
        return gap;
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
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            if (maxGap(arr1) != comparator(arr2)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
//        int[] arr = new int[]{10, 13, 15, 20};
//        int i = maxGap(arr);
//        System.out.println(i);
    }

}
