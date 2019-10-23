package my.class03;

/**
 * .
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
            printMatrix(matrix, tX++, tY++, dX--, dY--);
        }

    }

    public static void printMatrix(int[][] matrix, int tX, int tY, int dX, int dY) {
        if (tX == dX) {
            for (int i = tY; i <= dY; i++) {
                System.out.print(matrix[tX][i] + " ");
            }
        } else if (tY == dY) {
            for (int i = tX; i <= dX; i++) {
                System.out.print(matrix[i][tY] + " ");
            }
        } else {
            int indexX = tX;
            int indexY = tY;
            while (indexY < dY) {
                System.out.print(matrix[indexX][indexY] + " , ");
                indexY++;
            }
            while (indexX < dX) {
                System.out.print(matrix[indexX][indexY] + " , ");
                indexX++;
            }
            while (indexY > tX) {
                System.out.print(matrix[indexX][indexY] + " , ");
                indexY--;
            }
            while (indexX > tY) {
                System.out.print(matrix[indexX][indexY] + " , ");
                indexX--;
            }
        }
    }

}
