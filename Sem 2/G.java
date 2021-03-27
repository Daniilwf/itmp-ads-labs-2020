import java.io.*;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class G {
	private static Node[] t;

	static void build(int v, int tl, int tr) {
		if (tl == tr) {
			t[v] = new Node(0, 0, 0, tl, tr, false);
		} else {
			int tm = (tl + tr) / 2;
			build(v * 2, tl, tm);
			build(v * 2 + 1, tm + 1, tr);
			t[v] = new Node(0, 0, 0, tl, tr, false);
			t[v].set = t[v].data;
		}
	}

	static long get (int v, int l, int r) {
		if (t[v].r < l || t[v].l > r)
			return Long.MAX_VALUE;
		if (t[v].r <= r && t[v].l >= l) {
			push(v);
			return t[v].data;
		}
		push(v);
		return min(get(v * 2, l, r), get(v * 2 + 1, l, r));
	}

	static void updateSet(int v, long value, int l, int r) {
		if (t[v].r < l || t[v].l > r)
			return;
		if (t[v].r <= r && t[v].l >= l) {
			t[v].add = 0;
			pushset(v);

			t[v].set = value;
			t[v].up = true;

			return;
		}
		push(v);
		updateSet(2 * v, value, l, r);
		updateSet(2 * v + 1, value, l, r);
		t[v].data = min(t[v * 2].set + t[v * 2].add, t[v * 2 + 1].set + t[v * 2 + 1].add);
		t[v].set = t[v].data;
	}

	static void updateAdd(int v, long value, int l, int r) {
		if (t[v].r < l || t[v].l > r)
			return;
		if (t[v].r <= r && t[v].l >= l) {
			push(v);
			t[v].add = value;
			return;
		}
		push(v);
		updateAdd(2 * v, value, l, r);
		updateAdd(2 * v + 1, value, l, r);
		t[v].data = min(t[v * 2].set + t[v * 2].add, t[v * 2 + 1].set + t[v * 2 + 1].add);
		t[v].set = t[v].data;
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
		t = new Node[4*n];
		int count;
		build(1, 0, n - 1);
		for (int i = 0; i < m; i++) {
			p = reader.readLine();
			prob = 2;
			if (p.charAt(0) == '1'){
				long value;
				int l = 0;
				int r = 0;
				count = -1;
				for (int j = 2; j < p.length(); j++){
					if (Character.isWhitespace(p.charAt(j))) {
						if (count == 0) {
							r = Integer.parseInt(p.substring(prob, j));
							prob = j + 1;
							count++;
						}
						if (count == -1){
							l = Integer.parseInt(p.substring(prob, j));
							prob = j + 1;
							count++;
						}
					}
				}
				value = Long.parseLong(p.substring(prob));
				updateSet(1, value, l, r - 1);
			}
			else{
				for (int j = 2; j < p.length(); j++)
					if (Character.isWhitespace(p.charAt(j))) {
						prob = j;
						break;
					}
				int l = Integer.parseInt(p.substring(2, prob));
				int r = Integer.parseInt(p.substring(prob + 1));
				System.out.print(get(1,l, r - 1) + "\n");
			}
		}
	}

	static class Node{
		private long data;
		private long add;
		private long set;
		private long l;
		private long r;
		private boolean up;

		public Node(long data, long add, long set, long l, long r, boolean up){
			  this.data = data;
			  this.add = add;
			  this.set = set;
			  this.l = l;
			  this.r = r;
			  this.up = up;
		}
	}

	static void pushset(int v) {
		if (!t[v].up)
			return;

		t[v].data = t[v].set;
		t[v].up = false;

		if (t[v].l == t[v].r)
			return;

		t[2 * v].set = t[v].set;
		t[2 * v + 1].set = t[v].set;

		t[2 * v ].up = true;
		t[2 * v + 1].up = true;

		t[2 * v ].add = 0;
		t[2 * v + 1].add = 0;
	}

	static void push(int v) {
		pushset(v);
		if (t[v].add == 0)
			return;

		t[v].data += t[v].add;
		t[v].set = t[v].data;

		long addi = t[v].add;
		t[v].add = 0;

		if (t[v].l == t[v].r)
			return;

		t[2 * v ].add += addi;
		t[2 * v + 1].add += addi;
	}
}
