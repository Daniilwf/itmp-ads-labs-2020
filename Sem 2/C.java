import java.io.*;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class C {
	private static data[] t;

	static data combine(data l, data r) {
		data res = new data(0, 0, 0, 0);
		res.sum = l.sum + r.sum;
		res.pref = max(l.pref, l.sum + r.pref);
		res.suff = max(r.suff, r.sum + l.suff);
		res.ans = max(max(l.ans, r.ans), l.suff + r.pref);
		return res;
	}

	static data makeData(long val) {
		data res = new data(0, 0, 0, 0);
		res.sum = val;
		res.pref = res.suff = res.ans = max(0, val);
		return res;
	}

	static void build(long[] a, int v, int tl, int tr) {
		if (tl == tr)
			t[v] = makeData(a[tl]);
		else {
			int tm = (tl + tr) / 2;
			build(a, v * 2, tl, tm);
			build(a, v * 2 + 1, tm + 1, tr);
			t[v] = combine(t[v * 2], t[v * 2 + 1]);
		}
	}

	static void update(int v, int tl, int tr, int pos, long new_val) {
		if (tl == tr)
			t[v] = makeData(new_val);
		else {
			int tm = (tl + tr) / 2;
			if (pos <= tm)
				update(v * 2, tl, tm, pos, new_val);
			else
				update(v * 2 + 1, tm + 1, tr, pos, new_val);
			t[v] = combine(t[v * 2], t[v * 2 + 1]);
		}
	}

	static data result(int v, int tl, int tr, int l, int r) {
		if (l == tl && tr == r)
			return t[v];
		int tm = (tl + tr) / 2;
		if (r <= tm)
			return result(v * 2, tl, tm, l, r);
		if (l > tm)
			return result(v * 2 + 1, tm + 1, tr, l, r);
		return combine(result(v * 2, tl, tm, l, tm),
				result(v * 2 + 1, tm + 1, tr, tm + 1, r)
		);
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
		t = new data[4 * n];
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
		data temp = result(1,0, n - 1, 0, n - 1);
		System.out.print(temp.ans + "\n");
		for (int i = 0; i < m; i++) {
			p = reader.readLine();
			for (int j = 0; j < p.length(); j++)
				if (Character.isWhitespace(p.charAt(j))) {
					prob = j;
					break;
				}
			int index = Integer.parseInt(p.substring(0, prob));
			long v = Long.parseLong(p.substring(prob + 1));
			update(1, 0, n - 1, index, v);
			temp = result(1,0, n - 1, 0, n - 1);
			System.out.print(temp.ans + "\n");
		}
	}
	static class data {
		public long sum;
		public long pref;
		public long suff;
		public long ans;

		public data(long sum, long pref, long suff, long ans) {
			this.sum = sum;
			this.pref = pref;
			this.suff = suff;
			this.ans = ans;
		}
	}
}
