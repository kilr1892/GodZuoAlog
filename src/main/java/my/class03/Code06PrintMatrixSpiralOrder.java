package my.class03;

/**
 * 转圈打印矩阵
 * 【题目】 给定一个整型矩阵matrix，请按照转圈的方式打印它。
 * 例如： 1 2 3 4 5 6 7 8 9 10 11 12 13 14
 * 15 16 打印结果为：1，2，3，4，8，12，16，15，14，13，9，
 * 5，6，7，11， 10
 * 【要求】 额外空间复杂度为O(1)。
 *
 * @author RichardLee
 * @version v1.0
 */
public class Code06PrintMatrixSpiralOrder {

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3}, {5, 6, 7}, {9, 10, 12},
                {13, 14, 15}};
        spiralOrderPrint(matrix);

    }

    public static void spiralOrderPrint(int[][] matrix) {

        // 第一个坐标
        int tX = 0;
        int tY = 0;
        // 行数
        int dX = matrix.length - 1;
        // 列数
        int dY = matrix[0].length - 1;
        while (tX <= dX && tY <= dY) {
            // 打印好后, 将边框的两个坐标依次修改
            printMatrix(matrix, tX++, tY++, dX--, dY--);
        }

    }

    public static void printMatrix(int[][] matrix, int tX, int tY, int dX, int dY) {
        if (tX == dX) {
            // 这个是在只有一列的情况下的打印
            for (int i = tY; i <= dY; i++) {
                System.out.print(matrix[tX][i] + " ");
            }
        } else if (tY == dY) {
            // 这个是在只有一行情况下的打印
            for (int i = tX; i <= dX; i++) {
                System.out.print(matrix[i][tY] + " ");
            }
        } else {
            // 这个是打印外边框
            // 当前的xy坐标
            int indexX = tX;
            int indexY = tY;
            // 最上面行, 打印到倒数第二个数
            while (indexY < dY) {
                System.out.print(matrix[indexX][indexY] + " , ");
                indexY++;
            }
            // 最右边列, 打印到倒数第二个数
            while (indexX < dX) {
                System.out.print(matrix[indexX][indexY] + " , ");
                indexX++;
            }
            // 最下面行, 从右往左打印到第二个数
            while (indexY > tX) {
                System.out.print(matrix[indexX][indexY] + " , ");
                indexY--;
            }
            // 最左边列, 从下往上打印到第二个数
            while (indexX > tY) {
                System.out.print(matrix[indexX][indexY] + " , ");
                indexX--;
            }
        }
    }

}
