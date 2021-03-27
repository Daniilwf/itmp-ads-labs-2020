import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class D {
	public static void main(String[] args) throws IOException {
		BigInteger[] d = new BigInteger [10];
		sila(d);
		BigInteger[] b = new BigInteger[10];
		sila(b);
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String p = reader.readLine();
		int N = Integer.parseInt(p);
if (N == 1)
	System.out.print(8);
else {
	boolean sila = true;
	for (int i = 0; i < N - 1; i++) {
		if (sila) {
			ahmat(b, d);
			um(d);
			sila = false;
		} else {
			ahmat(d, b);
			um(b);
			sila = true;
		}
	}
	BigInteger sum = BigInteger.valueOf(0);
	if (sila)
		for (int i = 0; i < 10; i++)
			sum = sum.add(d[i]);
	else
		for (int i = 0; i < 10; i++)
			sum = sum.add(b[i]);
	System.out.print(sum.mod(BigInteger.valueOf(1000000000))
			.subtract(BigInteger.valueOf(1)));
}
	}

	public static BigInteger[] um(BigInteger[] d){
		d[0] = d[0].multiply(BigInteger.valueOf(2));
		d[1] = d[1].multiply(BigInteger.valueOf(2));
		d[2] = d[2].multiply(BigInteger.valueOf(2));
		d[3] = d[3].multiply(BigInteger.valueOf(2));
		d[4] = d[4].multiply(BigInteger.valueOf(3));
		d[6] = d[6].multiply(BigInteger.valueOf(3));
		d[7] = d[7].multiply(BigInteger.valueOf(2));
		d[8] = d[8].multiply(BigInteger.valueOf(2));
		d[9] = d[9].multiply(BigInteger.valueOf(2));
		return d;
	}

	public static BigInteger[] ahmat(BigInteger[] b, BigInteger[] d){
		b[0] = d[4].add(d[6]);
		b[1] = d[8].add(d[6]);
		b[2] = d[9].add(d[7]);
		b[3] = d[8].add(d[4]);
		b[4] = d[9].add(d[3]).add(d[0]);
		b[6] = d[7].add(d[0]).add(d[1]);
		b[7] = d[6].add(d[2]);
		b[8] = d[3].add(d[1]);
		b[9] = d[4].add(d[2]);
		return b;
	}
	public static BigInteger[] sila(BigInteger[] d) {
		d[0] = BigInteger.valueOf(0);
		d[1] = BigInteger.valueOf(1);
		d[2] = BigInteger.valueOf(1);
		d[3] = BigInteger.valueOf(1);
		d[4] = BigInteger.valueOf(1);
		d[5] = BigInteger.valueOf(1);
		d[6] = BigInteger.valueOf(1);
		d[7] = BigInteger.valueOf(1);
		d[8] = BigInteger.valueOf(0);
		d[9] = BigInteger.valueOf(1);
		return d;
	}
}