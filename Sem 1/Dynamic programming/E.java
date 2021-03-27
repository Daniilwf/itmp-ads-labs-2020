import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class E {
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String p = reader.readLine();
		String l = reader.readLine();
		System.out.print(Levenstain(p, l));
	}

	public static int Levenstain(String str1, String str2) {
		int[] mas1 = new int[str2.length() + 1];
		int[] mas2 = new int[str2.length() + 1];
		for (int j = 0; j <= str2.length(); j++) {
			mas2[j] = j;
		}
		for (int i = 1; i <= str1.length(); i++) {
			System.arraycopy(mas2, 0, mas1, 0, mas1.length);
			mas2[0] = i;
			for (int j = 1; j <= str2.length(); j++) {
				int price = (str1.charAt(i - 1) != str2.charAt(j - 1)) ? 1 : 0;
				mas2[j] = Math.min(Math.min(mas1[j] + 1, mas2[j - 1] + 1), mas1[j - 1] + price);
			}
		}

		return mas2[mas2.length - 1];
	}
}