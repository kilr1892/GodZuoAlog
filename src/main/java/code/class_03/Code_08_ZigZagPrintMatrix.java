package code.class_03;

public class Code_08_ZigZagPrintMatrix {

	public static void printMatrixZigZag(int[][] matrix) {
		int tX = 0;
		int tY = 0;
		int dX = 0;
		int dY = 0;
		int endX = matrix.length - 1;
		int endY = matrix[0].length - 1;
		boolean fromUp = false;
		while (tX != endX + 1) {
			printLevel(matrix, tX, tY, dX, dY, fromUp);
			tX = tY == endY ? tX + 1 : tX;
			tY = tY == endY ? tY : tY + 1;
			dY = dX == endX ? dY + 1 : dY;
			dX = dX == endX ? dX : dX + 1;
			fromUp = !fromUp;
		}
		System.out.println();
	}

	public static void printLevel(int[][] m, int tR, int tC, int dR, int dC,
			boolean f) {
		if (f) {
			while (tR != dR + 1) {
				System.out.print(m[tR++][tC--] + " ");
			}
		} else {
			while (dR != tR - 1) {
				System.out.print(m[dR--][dC++] + " ");
			}
		}
	}

	public static void main(String[] args) {
		int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
		printMatrixZigZag(matrix);

	}

}
