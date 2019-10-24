package my.class03;

/**
 * “之”字形打印矩阵
 * 【题目】 给定一个矩阵matrix，按照“之”字形的方式打印这
 * 个矩阵，例如： 1 2 3 4 5 6 7 8 9 10 11 12
 * “之”字形打印的结果为：1，2，5，9，6，3，4，7，10，11，8，12
 * 【要求】 额外空间复杂度为O(1)。
 *
 * @author RichardLee
 * @version v1.0
 */
public class Code08ZigZagPrintMatrix {
    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        printMatrixZigZag(matrix);
    }

    public static void printMatrixZigZag(int[][] matrix) {
        int tX = 0;
        int tY = 0;
        int dX = 0;
        int dY = 0;
        int endX = matrix.length - 1;
        int endY = matrix[0].length - 1;
        boolean toUp = true;

        while (tX <= endX && dY <= endY) {
            printLevel(matrix, tX, tY, dX, dY, toUp);
            // 分几种情况
//            if (tY != endY) {
//                // 第一种 ty 还没到边界
//                tY++;
//            } else {
//                // 第二种 ty 到了边界
//                tX++;
//            }
//
//            if (dX != endX) {
//                // 第三种 dx 还没到边界
//                dX++;
//            }else {
//                // 第四种 dx 到了边界
//                dY++;
//            }
            // 其实就是上面的简写
            tX = tY != endY ? tX : tX + 1;
            tY = tY != endY ? tY + 1 : tY;
            dY = dX != endX ? dY : dY + 1;
            dX = dX != endX ? dX + 1 : dX;

            // 改变下flag
            toUp = !toUp;
        }
    }

    public static void printLevel(int[][] matrix, int tX, int tY, int dX, int dY, boolean toUp) {
        // 打印的方法
        // 1. txty dxdy为两个斜线的坐标
        // 2. fromUp为判断是从上到下打印还是从下到上

        // 还可以改写  参考示例的  不用多出变量
        int currentX;
        int currentY;
        if (!toUp) {
            // false为向下打印
            // 指向第一个
            currentX = tX;
            currentY = tY;
            while (currentX != dX) {
                System.out.print(matrix[currentX++][currentY--] + " ");
            }
        } else {
            // 向上
            // 指向最后一个
            currentX = dX;
            currentY = dY;
            while (currentX != tX) {
                System.out.print(matrix[currentX--][currentY++] + " ");
            }
        }
        System.out.print(matrix[currentX][currentY] + " ");
    }
}
