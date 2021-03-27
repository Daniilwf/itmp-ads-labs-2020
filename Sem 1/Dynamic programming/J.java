import java.io.*;

public class J {
	public static int n;
	public static int m;
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String p = reader.readLine();
		int vojd = 0;
		for (int i = 0; i < p.length(); i++){
			if (Character.isWhitespace(p.charAt(i))) {
				vojd = i;
				break;
			}
		}
		n = Integer.parseInt(p.substring(0, vojd));
		m = Integer.parseInt(p.substring(vojd+1));
		if (n > m) {
			int temp = m;
			m = n;
			n = temp;
		}
		int length = 1 << n;
		int[][] a = new int[m][length];
		int[][] dp = new int[length][length];
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				if (transition(i, j)) {
					dp[i][j] = 1;
				} else {
					dp[i][j] = 0;
				}
			}
		}
		for (int i = 0; i < length; i++) {
			a[0][i] = 1;
		}
		for (int k = 1; k < m; k++) {
			for (int i = 0; i < length; i++) {
				for (int j = 0; j < length; j++) {
					a[k][i] = a[k][i] + a[k - 1][j] * dp[j][i];
				}
			}
		}
		long result = 0;
		for (int i = 0; i < length; i++) {
			result += a[m - 1][i];
		}
		System.out.print(result);
	}

	static boolean transition(int x, int y) {
		int first, second, third, fourth;
		for (int i = 0; i < n - 1; i++) {
			if ((x & (1 << i)) == 0)
				first = 0;
			else
				first = 1;
			if ((x & (1 << i + 1)) == 0)
				second = 0;
			else
				second = 1;
			if ((y & (1 << i)) == 0)
				third = 0;
			else
				third = 1;
			if ((y & (1 << i + 1)) == 0)
				fourth = 0;
			else
				fourth = 1;
			if ((first == second) && (second == third) && (third == fourth)) {
				return false;
			}
		}
		return true;
	}
}