import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.max;

public class C {
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String p = reader.readLine();
		int N = Integer.parseInt(p);
		p = reader.readLine();
		int[] mas = new int[N];
		zapolnit(mas, N, p);
		List<Integer> answer = findLIS(mas);
		System.out.println(answer.size());
		for (int i = answer.size()-1; i>=0; i--)
			System.out.print(answer.get(i) + " ");
		reader.close();
	}
	public static int[] zapolnit(int[] mas, int N, String p){
		int tempcount = -1;
		int prob = 0;
		for (int i = 0; i < p.length(); i++)
			if (Character.isWhitespace(p.charAt(i))) {
				tempcount++;
				mas[tempcount] = Integer.parseInt(p.substring(prob, i));
				prob = i+1;
			}
		mas[N-1] = Integer.parseInt(p.substring(prob));
			return mas;
	}
	public static List<Integer> findLIS(int[] a){
		int n = a.length;
		int[] d = new int[n+1];
		int[] pos = new int[n+1];
		int[] prev = new int[n];
		int length = 0;
		pos[0] = -1;
		d[0] = Integer.MIN_VALUE;
		for (int i = 1; i <= n; i++)
			d[i] = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++){
			int j = binSearch(d, a[i]);
			if ((d[j - 1] < a[i]) && (a[i] < d[j])){
				d[j] = a[i];
				pos[j] = i;
				prev[i] = pos[j - 1];
				length = max(length, j);
			}
		}
		List<Integer> answer = new ArrayList<>();
		int p = pos[length];
		while (p != -1){
			answer.add(a[p]);
			p = prev[p];
		}
		return answer;
	}
	public static int binSearch(int[] a, int key){
		int l = -1;
		int r = a.length;
		while (l < r -1){
			int m = (l+r)/2;
			if (a[m] < key)
				l = m;
			else
				r = m;
		}
		return r;
	}
	public static List<Integer> AfindLIS(int[] a){
		int n = a.length;
		int[] d = new int[n];
		int[] prev = new int[n];

		for (int i = 0; i < n; i++){
			d[i] = 1;
			prev[i] = -1;
			for (int j = 0; j < i; j++){
				if ((a[j] < a[i]) && (d[j] + 1 > d[i])) {
					d[i] = d[j] + 1;
					prev[i] = j;
			}
			}
		}
		int pos = 0;
		int length = d[0];
		for (int i = 0; i < n; i++)
			if (d[i] > length){
				pos = i;
				length = d[i];
			}
		List<Integer> answer = new ArrayList<>();
		while (pos != -1){
			answer.add(a[pos]);
			pos = prev[pos];
		}
		return answer;
	}
}