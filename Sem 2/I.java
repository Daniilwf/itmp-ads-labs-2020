import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
public class I {
	private static Matrix2x2[] t;
 
	public static Matrix2x2 multiply (Matrix2x2 a, Matrix2x2 b, int r){
		Matrix2x2 c = new Matrix2x2(0, 0, 0, 0);
		c.a11 = (a.a11 * b.a11 + a.a12 * b.a21) % r;
		c.a12 = (a.a11 * b.a12 + a.a12 * b.a22) % r;
		c.a21 = (a.a21 * b.a11 + a.a22 * b.a21) % r;
		c.a22 = (a.a21 * b.a12 + a.a22 * b.a22) % r;
		return c;
	}
 
	static void build(Matrix2x2[] a,int v, int tl, int tr, int r) {
		if (tl == tr) {
			t[v] = a[tl];
		} else {
			int tm = (tl + tr)/2;
			build(a, v * 2, tl, tm, r);
			build(a, v * 2 + 1, tm + 1, tr, r);
			t[v] = multiply(t[v * 2], t[v * 2 + 1], r);
		}
	}
 
	static Matrix2x2 get(int v, int tl, int tr, int l, int r, int br) {
		if (tr < l || tl > r) return new Matrix2x2(1, 0, 0, 1);
		if (tr <= r && tl >= l) return t[v];
		int tm = (tl + tr)/2;
		Matrix2x2 a = get(2 * v, tl, tm, l, r, br);
		Matrix2x2 b = get(2 * v + 1, tm + 1, tr, l, r, br);
		return multiply(a, b, br);
	}
 
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int prob = 0;
		String p = reader.readLine();
		int num = p.length();
		int count = -1;
		int r = 0;
		int n = 0;
		int m = 0;
		for (int j = 0; j < num; j++){
			if (Character.isWhitespace(p.charAt(j))) {
				if (count == 0) {
					n = Integer.parseInt(p.substring(prob, j));
					prob = j + 1;
					count++;
				}
				if (count == -1){
					r = Integer.parseInt(p.substring(prob, j));
					prob = j + 1;
					count++;
				}
			}
		}
		m = Integer.parseInt(p.substring(prob));
		Matrix2x2[] a = new Matrix2x2[n];
		for (int i = 0; i < n; i++) {
			p = reader.readLine();
			int length = p.length();
			for (int j = 0; j < length; j++)
				if (Character.isWhitespace(p.charAt(j))) {
					prob = j;
					break;
				}
			a[i] = new Matrix2x2(0, 0, 0, 0);
			a[i].a11 = Long.parseLong(p.substring(0, prob));
			a[i].a12 = Long.parseLong(p.substring(prob + 1));
			p = reader.readLine();
			length = p.length();
			for (int j = 0; j < length; j++)
				if (Character.isWhitespace(p.charAt(j))) {
					prob = j;
					break;
				}
			a[i].a21 = Long.parseLong(p.substring(0, prob));
			a[i].a22 = Long.parseLong(p.substring(prob + 1));
			p = reader.readLine();
		}
		t = new Matrix2x2[4*n];
		build(a, 1, 0, n - 1, r);
		for (int i = 0; i < m; i++) {
			p = reader.readLine();
			int length = p.length();
			for (int j = 0; j < length; j++)
				if (Character.isWhitespace(p.charAt(j))) {
					prob = j;
					break;
				}
			int temp1 = Integer.parseInt(p.substring(0, prob));
			int temp2 = Integer.parseInt(p.substring(prob + 1));
			Matrix2x2 result = get(1, 0, n - 1, temp1 - 1, temp2 - 1, r);
			System.out.print(result.a11 + " " + result.a12 + "\n" + result.a21 + " " + result.a22 + "\n" + "\n");
		}
		/*Matrix2x2[][] res= new Matrix2x2[n][n];
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++){
				res[i][j] = multiply(t[i], t[j], r);
			}
		}
		for (int i = 0; i < m; i++){
			p = reader.readLine();
			int length = p.length();
			for (int j = 0; j < length; j++)
				if (Character.isWhitespace(p.charAt(j))) {
					prob = j;
					break;
				}
			int temp1 = Integer.parseInt(p.substring(0, prob));
			int temp2 = Integer.parseInt(p.substring(prob + 1));
			Matrix2x2 result = res[temp1 - 1][temp2 - 1];
			System.out.print(result.a11 + " " + result.a12 + "\n" + result.a21 + " " + result.a22 + "\n" + "\n");
		}*/
	}
	static class Matrix2x2{
		private long a11;
		private long a12;
		private long a21;
		private long a22;
 
		public Matrix2x2(long a11, long a12, long a21, long a22){
			this.a11 = a11;
			this.a12 = a12;
			this.a21 = a21;
			this.a22 = a22;
		}
	}
}