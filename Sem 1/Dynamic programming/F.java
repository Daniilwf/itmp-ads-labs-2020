import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class F {
	public static void main (String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String p = reader.readLine();
		int n = Integer.parseInt(p);
		int[][] a = new int[n + 1][n + 2];
		for (int i = 0; i < n + 1; i++) {
			for (int j = 0; j < n + 2; j++)
				a[i][j] = 3000000;

		}
		a[0][0] = 0;
		int[] P = new int[n + 1];
		int count = 0;
		for (int i = 1; i <= n; i++) {
			p = reader.readLine();
			P[i] = Integer.parseInt(p);
			if (P[i] > 100) {
				count++;
				for (int j = 0; j <= count; j++)
					if (j == 0)
						a[i][j] = a[i - 1][j + 1];
					else
						a[i][j] = Math.min(a[i - 1][j - 1] + P[i], a[i - 1][j + 1]);
			} else
				for (int j = 0; j <= count; j++)
					a[i][j] = Math.min(a[i - 1][j] + P[i], a[i - 1][j + 1]);
		}
		int min = Integer.MAX_VALUE;
		int k1 = 0;
		for (int i = 0; i <= n; i++) {
			if (a[n][i] <= min) {
				min = a[n][i];
				k1 = i;
			}
		}
		StringBuilder put = new StringBuilder();
		int i = n;
		int temp = min;
		int k2 = 0;
		int ind = k1;
		if (n > 0)
			while (i != 0) {
				if (P[i] == 0) {
					i--;
					continue;
				}
				if (P[i] > 100) {
					if (temp == a[i - 1][ind + 1]) {
						StringBuilder x = new StringBuilder();
						x.append(i);
						put.append(x.reverse())
								.append("\n");
						k2++;
						ind = ind + 1;
					} else {
						temp = temp - P[i];
						ind = ind - 1;
					}
				} else {
					if (temp == a[i - 1][ind + 1]) {
						StringBuilder x = new StringBuilder();
						x.append(i);
						put.append(x.reverse())
								.append("\n");
						k2++;
						ind = ind + 1;
					} else {
						temp = temp - P[i];
					}
				}
				i--;
			}
		System.out.println(min);
		System.out.println(k1 + " " + (k2));
		if (put.length() > 0)
			System.out.println(put.reverse()
					.substring(1, put.length()));
	}
}
 