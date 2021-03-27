import java.io.*;
 
import static java.lang.Math.max;
import static java.lang.Math.min;
 
public class B {
	private static Pair[] t;
 
	static Pair getPair(Pair a, Pair b) {
		if (a.first > b.first)
			return b;
		if (b.first > a.first)
			return a;
		return new Pair(a.first, a.second + b.second);
	}
 
	static void build(long[] a, long v, long tl, long tr) {
		if (tl == tr)
			t[(int) v] = new Pair(a[(int) tl], 1);
		else {
			long tm = (tl + tr) / 2;
			build(a, v * 2, tl, tm);
			build(a, v * 2 + 1, tm + 1, tr);
			t[(int) v] = getPair(t[(int) (v * 2)], t[(int) (v * 2 + 1)]);
		}
	}
 
	static Pair getMin(long v, long tl, long tr, long l, long r) {
		if (l > r)
			return new Pair(1000000001, 0);
		if (l == tl && r == tr)
			return t[(int) v];
		long tm = (tl + tr) / 2;
		return getPair(
				getMin(v * 2, tl, tm, l, min(r, tm)),
				getMin(v * 2 + 1, tm + 1, tr, max(l, tm + 1), r)
		);
	}
 
	static void update(long v, long tl, long tr, long pos, long new_val) {
		if (tl == tr)
			t[(int) v] = new Pair(new_val, 1);
		else {
			long tm = (tl + tr) / 2;
			if (pos <= tm)
				update(v * 2, tl, tm, pos, new_val);
			else
				update(v * 2 + 1, tm + 1, tr, pos, new_val);
			t[(int) v] = getPair(t[(int) (v * 2)], t[(int) (v * 2 + 1)]);
		}
	}
 
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int prob = 0;
		String p = reader.readLine();
		for (int i = 0; i < p.length(); i++)
			if (Character.isWhitespace(p.charAt(i))) {
				prob = i;
				break;
			}
		int n = Integer.parseInt(p.substring(0, prob));
		int m = Integer.parseInt(p.substring(prob + 1));
		t = new Pair[4*n];
		long[] a = new long[n];
		p = reader.readLine();
		int count = -1;
		prob = 0;
		for (int i = 0; i < p.length(); i++)
			if (Character.isWhitespace(p.charAt(i))) {
				a[++count] = Long.parseLong(p.substring(prob, i));
				prob = i + 1;
			}
		a[++count] = Long.parseLong(p.substring(prob));
		build(a, 1, 0, n - 1);
		for (int i = 0; i < m; i++) {
			p = reader.readLine();
			if (p.charAt(0) ==  '1') {
				for (int j = 2; j < p.length(); j++)
					if (Character.isWhitespace(p.charAt(j))) {
						prob = j;
						break;
					}
				long index = Long.parseLong(p.substring(2, prob));
				long v = Long.parseLong(p.substring(prob + 1));
				update(1, 0, n - 1, index, v);
			}
			else {
				for (int j = 2; j < p.length(); j++)
					if (Character.isWhitespace(p.charAt(j))) {
						prob = j;
						break;
					}
				long l = Long.parseLong(p.substring(2, prob));
				long r = Long.parseLong(p.substring(prob + 1));
				Pair out = getMin(1, 0, n - 1, l, r - 1);
				System.out.print(out.first + " " + out.second + "\n");
			}
		}
	}
 
	private static class Pair {
		private long first;
		private long second;
 
		public Pair(long first, long second) {
			this.first = first;
			this.second = second;
		}
	}
}