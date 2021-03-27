import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Math.max;

public class A {
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String p = reader.readLine();
		int prob = 0;
		for (int i = 0; i < p.length(); i++)
			if (Character.isWhitespace(p.charAt(i))){
				prob = i;
				break;
			}
		int n = Integer.parseInt(p.substring(0,prob));
		int k = Integer.parseInt(p.substring(prob+1));
		int[] price = new int[n];
		int[] ahmat = new int[n];
		StringBuilder[] put = new StringBuilder[n];
		int[] count = new int[n];
		price[0] = 0;
		price[n-1] = 0;
		prob = 0;
		ahmat[0] = 0;
		put[0] = new StringBuilder();
		put[0].append("1").append(" ");
		p = reader.readLine();
		int tempcount = 0;
		int ind;
		for (int i = 0; i < p.length(); i++) {
			if (Character.isWhitespace(p.charAt(i))) {
				int max = Integer.MIN_VALUE;
				ind = 0;
				tempcount++;
				price[tempcount] = Integer.parseInt(p.substring(prob, i));
				prob = i + 1;
				for (int j = tempcount - 1; j >= tempcount - k; j--) {
					if (j >= 0) {
						if (ahmat[j] > max) {
							max = ahmat[j];
							ind = j;
						}
					} else
						break;
				}
				ahmat[tempcount] = max + price[tempcount];
				put[tempcount] = new StringBuilder();
				put[tempcount].append(put[ind]).append(tempcount+1).append(" ");
				count[tempcount] = count[ind] + 1;
			}
		}
		int max = Integer.MIN_VALUE;
		ind = 0;
		price[n-2] = Integer.parseInt(p.substring(prob));
		for (int j = n-2 - 1; j >= n-2 - k; j--) {
			if (j >= 0) {
				if (ahmat[j] > max) {
					max = ahmat[j];
					ind = j;
				}
			} else
				break;
		}
		ahmat[n-2] = max + price[n-2];
		put[n-2] = new StringBuilder();
		put[n-2].replace(0, put[n-2].length(), String.valueOf(put[ind])).append(n-2+1).append(" ");
		count[n-2] = count[ind] + 1;
		max = Integer.MIN_VALUE;
		ind = 0;
		for (int j = n-1 - 1; j >= n-1 - k; j--) {
			if (j >= 0) {
				if (ahmat[j] > max) {
					max = ahmat[j];
					ind = j;
				}
			} else
				break;
		}
		ahmat[n-1] = max;
		put[n-1] = new StringBuilder();
		put[n-1].replace(0, put[n-1].length(), String.valueOf(put[ind])).append(n-1+1).append(" ");
		count[n-1] = count[ind] + 1;
		/*for (int i = 1; i < n; i++)
			for (int j = i-1; j>=i-k; j--){
				if (j>=0)
				max = max(ahmat[j], max);
			}
			ahmat[i] = max + price[i];
*/
		System.out.println(ahmat[n-1]);
		System.out.println(count[n-1]);
		System.out.println(put[n-1]);
	}
}