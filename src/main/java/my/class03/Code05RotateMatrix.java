package my.class03;

import java.util.Arrays;

/**
 * 旋转正方形矩阵
 * 【题目】 给定一个整型正方形矩阵matrix，请把该矩阵调整成
 * 顺时针旋转90度的样子。
 * 【要求】 额外空间复杂度为O(1)。
 *
 * @author RichardLee
 * @version v1.0
 */
public class Code05RotateMatrix {

    public static void main(String[] args) {
//        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12},
//                {13, 14, 15, 16}};
        int[][] matrix = {{1, 2, 3}, {5, 6, 7}, {9, 10, 11}};
        printMatrix(matrix);
        System.out.println("=========");
        rotate(matrix);
        printMatrix(matrix);

    }

    // 这个旋转一定是矩形
    // 大圈中, 先把第一个的位置找到了
    // 剩下的就是+1的迭代
    // 大圈进小圈, 依次迭代
    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }

    public static void rotate(int[][] matrix) {
        int tX = 0;
        int tY = 0;
        int dX = matrix.length - 1;
        int dY = matrix[0].length - 1;

        while (tX <= dX && tY <= dY) {
            rotateEdge(matrix, tX++, tY++, dX--, dY--);
        }

    }

    public static void rotateEdge(int[][] matrix, int tX, int tY, int dX, int dY) {
        int curr1X = tX;
        int curr1Y = tY;
        int curr2X = tX;
        int curr2Y = dY;
        int curr3X = dX;
        int curr3Y = dY;
        int curr4X = dX;
        int curr4Y = tY;

        if (tX == dX && tY == dY) {

        } else {
            while (curr1Y < dX) {
                // 交换
                int tmp = matrix[curr1X][curr1Y];
                matrix[curr1X][curr1Y] = matrix[curr4X][curr4Y];
                matrix[curr4X][curr4Y] = matrix[curr3X][curr3Y];
                matrix[curr3X][curr3Y] = matrix[curr2X][curr2Y];
                matrix[curr2X][curr2Y] = tmp;
                curr1Y++;
                curr2X++;
                curr3Y--;
                curr4X--;
            }
        }


    }
}
