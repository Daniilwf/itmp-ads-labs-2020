import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class G {
	public static int[][] dp;
	public static int[][] p;
	public static String s;

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		s = reader.readLine();
		int n = s.length();
		dp = new int[n][n];
		p = new int[n][n];
		if (n > 0) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (j < i) {
						dp[i][j] = 0;
						continue;
					}
					if (i == j)
						dp[i][j] = 1;
				}
			}
			for (int right = 0; right < n; right++) {
				for (int left = right; left >= 0; left--) {
					if (left == right) {
						dp[left][right] = 1;
					} else {
						int best = 100000;
						int mink = -1;
						if ((s.charAt(left) == '(' && s.charAt(right) == ')')
								|| (s.charAt(left) == '[' && s.charAt(right) == ']')
								|| (s.charAt(left) == '{' && s.charAt(right) == '}'))
							best = dp[left + 1][right - 1];
						for (int k = left; k < right; k++) {
							if (best > dp[left][k] + dp[k + 1][right]) {
								best = dp[left][k] + dp[k + 1][right];
								mink = k;
							}
						}
						dp[left][right] = best;
						p[left][right] = mink;
					}
				}
			}
			ans(0, n - 1);
		} else System.out.print("");
	}

	static void ans(int left, int right) {
		if (dp[left][right] == right - left + 1)
			return;
		if (dp[left][right] == 0) {
			System.out.print(s.substring(left, right + 1));
			return;
		}
		if (p[left][right] == -1) {
			System.out.print(s.charAt(left));
			ans(left + 1, right - 1);
			System.out.print(s.charAt(right));
			return;
		}
		ans(left, p[left][right]);
		ans(p[left][right] + 1, right);
	}

}
 