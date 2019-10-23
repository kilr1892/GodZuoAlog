package code.class_03;

public class Code_06_PrintMatrixSpiralOrder {

	public static void spiralOrderPrint(int[][] matXix) {
		int tX = 0;
		int tY = 0;
		int dX = matXix.length - 1;
		int dY = matXix[0].length - 1;
		while (tX <= dX && tY <= dY) {
			printEdge(matXix, tX++, tY++, dX--, dY--);
		}
	}

	public static void printEdge(int[][] m, int tX, int tY, int dX, int dY) {
		if (tX == dX) {
			for (int i = tY; i <= dY; i++) {
				System.out.print(m[tX][i] + " ");
			}
		} else if (tY == dY) {
			for (int i = tX; i <= dX; i++) {
				System.out.print(m[i][tY] + " ");
			}
		} else {
			int curC = tY;
			int curR = tX;
			while (curC != dY) {
				System.out.print(m[tX][curC] + " ");
				curC++;
			}
			while (curR != dX) {
				System.out.print(m[curR][dY] + " ");
				curR++;
			}
			while (curC != tY) {
				System.out.print(m[dX][curC] + " ");
				curC--;
			}
			while (curR != tX) {
				System.out.print(m[curR][tY] + " ");
				curR--;
			}
		}
	}

	public static void main(String[] args) {
		int[][] matXix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 },
				{ 13, 14, 15, 16 } };
		spiralOrderPrint(matXix);

	}

}
