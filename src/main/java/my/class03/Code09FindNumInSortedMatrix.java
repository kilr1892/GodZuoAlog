package my.class03;

/**
 * 在行列都排好序的矩阵中找数
 * 【题目】 给定一个有N*M的整型矩阵matrix和一个整数K，
 * matrix的每一行和每一 列都是排好序的。实现一个函数，判断K
 * 是否在matrix中。 例如： 0 1 2 5 2 3 4 7 4
 * 4 4 8 5 7 7 9 如果K为7，返回true；如果K为6，返
 * 回false。
 * 【要求】 时间复杂度为O(N+M)，额外空间复杂度为O(1)。
 *
 * @author RichardLee
 * @version v1.0
 */
public class Code09FindNumInSortedMatrix {
    public static boolean isContains(int[][] matrix, int target) {
        // 从右往左, 遇到小的向下走一个
        // 再判断, 继续以上过程
        int curX = 0;
        int curY = matrix[0].length - 1;
        while (curX != matrix.length) {
            int curNum = matrix[curX][curY];
            if (curNum > target) {
                curY--;
            } else if (curNum < target) {
                curX++;
            } else {
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        int[][] matrix = new int[][] { { 0, 1, 2, 3, 4, 5, 6 },// 0
                { 10, 12, 13, 15, 16, 17, 18 },// 1
                { 23, 24, 25, 26, 27, 28, 29 },// 2
                { 44, 45, 46, 47, 48, 49, 50 },// 3
                { 65, 66, 67, 68, 69, 70, 71 },// 4
                { 96, 97, 98, 99, 100, 111, 122 },// 5
                { 166, 176, 186, 187, 190, 195, 200 },// 6
                { 233, 243, 321, 341, 356, 370, 380 } // 7
        };
        int K = 2323;
        System.out.println(isContains(matrix, K));
    }
}
