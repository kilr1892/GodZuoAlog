package my.class05;

public class Code03Islands {

	public static int countIslands(int[][] m) {
		if (m == null) {
			return 0;
		}
		// x 的值
		int x = m.length;
		// y 的值
		int y = m[0].length;
		// 岛的个数
		int res = 0;
		// 判断二维矩阵中的每个元素
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				if (m[i][j] == 1) {
					// 为1 说明是岛
					res++;
					// 感染函数, 会将所有相连的感染为2
					infect(m, i, j, x, y);
				}
			}
		}
		return res;
	}

	public static void infect(int[][] m, int i, int j, int x, int y) {
		if (i < 0 || i >= x || j < 0 || j >= y || m[i][j] != 1) {
			return;
		}
		m[i][j] = 2;
		infect(m, i + 1, j, x, y);
		infect(m, i - 1, j, x, y);
		infect(m, i, j + 1, x, y);
		infect(m, i, j - 1, x, y);
	}

	public static void main(String[] args) {
		int[][] m1 = {  { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
				        { 0, 1, 1, 1, 0, 1, 1, 1, 0 }, 
				        { 0, 1, 1, 1, 0, 0, 0, 1, 0 },
				        { 0, 1, 1, 0, 0, 0, 0, 0, 0 }, 
				        { 0, 0, 0, 0, 0, 1, 1, 0, 0 }, 
				        { 0, 0, 0, 0, 1, 1, 1, 0, 0 },
				        { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, };
		System.out.println(countIslands(m1));

		int[][] m2 = {  { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
						{ 0, 1, 1, 1, 1, 1, 1, 1, 0 }, 
						{ 0, 1, 1, 1, 0, 0, 0, 1, 0 },
						{ 0, 1, 1, 0, 0, 0, 1, 1, 0 }, 
						{ 0, 0, 0, 0, 0, 1, 1, 0, 0 }, 
						{ 0, 0, 0, 0, 1, 1, 1, 0, 0 },
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, };
		System.out.println(countIslands(m2));

	}

}
