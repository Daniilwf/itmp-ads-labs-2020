import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
 
public class B {
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String p = reader.readLine();
		int j = 0;
		StringBuilder st = new StringBuilder();
		for (int i = 0; i < p.length(); i++) {
			while (!Character.isWhitespace(p.charAt(i))){
				st.append(p.charAt(i));
				i++;
			}
			j=i;
			break;
		}
		String h = st.toString();
		int N = Integer.parseInt(h);
		ArrayList<Integer> mas = new ArrayList<>(N);
		for (int i = j; i < p.length(); i++)
			if (Character.isDigit(p.charAt(i)))
				mas.add(Character.getNumericValue(p.charAt(i)));
		int gcount=0;
		int count=1;
		for (int i=0; i<mas.size()-1; i++)
			if (mas.get(i) == mas.get(i+1)) {
				count++;
				continue;
			} else {
				if (count>2) {
					for (j = 0; j < count; j++)
						mas.remove(i - count+1);
					gcount += count;
					i = i - count -2;
					if (i<0)
						i=-1;
				}
				count=1;
			}
		if (count>2)
			gcount += count;
		System.out.println(gcount);
	}
}