import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class D {
	private static int[] t;
	private static int x;

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int prob = 0;
		String p = reader.readLine();
		int num = p.length();
		for (int i = 0; i < num; i++)
			if (Character.isWhitespace(p.charAt(i))) {
				prob = i;
				break;
			}
		int n = Integer.parseInt(p.substring(0, prob));
		int m = Integer.parseInt(p.substring(prob + 1));
		x = 1;
		while (x < n)
			x *= 2;
		int[] a = new int[x];
		x = x * 2 - 1;
		p = reader.readLine();
		int count = -1;
		prob = 0;
		num = p.length();
		for (int i = 0; i < num; i++)
			if (Character.isWhitespace(p.charAt(i))) {
				a[++count] = Integer.parseInt(p.substring(prob, i));
				prob = i + 1;
			}
		a[++count] = Integer.parseInt(p.substring(prob));
		t = new int[x];
		build(0, a);
		for (int i = 0; i < m; i++) {
			p = reader.readLine();
			if (p.charAt(0) == '1') {
				int index = Integer.parseInt(p.substring(2));
				if (t[x/2 + index] == 1)
					t[x/2 + index] = 0;
				else
					t[x/2 + index] = 1;
				update(x/2 + index, 0);
			} else {
				int k = Integer.parseInt(p.substring(2));
				System.out.print(find_kth(0, k + 1) - x/2 + "\n");
			}
		}
	}

	static int update(int v, int count) {
		if (count > 0){
			t[v] = t[2*v + 1] + t[2*v + 2];
			if (v == 0)
				return t[v];
			update((v-1)/2, count + 1);
		}
		else {
			if (t.length == 1)
				return t[v];
			return update((v-1)/2, count + 1);
		}
		return v;
	}

	static int build(int v, int[] a) {
		if (v > x/2 - 1) {
			t[v] = a[v - x/2];
			return t[v];
		}
		else {
			return t[v] = build(v * 2 + 1, a)
					+ build(v * 2 + 2, a);
		}
	}

	static int find_kth (int v, int k) {
		if (v > x/2 - 1)
			return v;
		if (t[v*2 + 1] >= k)
			return find_kth (v*2 + 1, k);
		else
			return find_kth (v*2 + 2, k - t[v*2 + 1]);
	}
}
