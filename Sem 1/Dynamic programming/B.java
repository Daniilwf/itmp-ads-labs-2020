import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class B {
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String p = reader.readLine();
		int prob = 0;
		for (int i = 0; i < p.length(); i++){
			if (Character.isWhitespace(p.charAt(i))){
				prob = i;
				break;
			}
		}
		int n = Integer.parseInt(p.substring(0, prob));
		int m = Integer.parseInt(p.substring(prob + 1));
		int[][] price = new int[n][m];
		String[][] put = new String[n][m];
		/*for (int i = 0; i < n; i++){
			int tempcount = 0;
			prob = 0;
			p = reader.readLine();
			for (int j = 0; j < p.length(); j++) {
				if (Character.isWhitespace(p.charAt(j))) {
					a[i][tempcount] = Integer.parseInt(p.substring(prob, j));
					prob = j + 1;
					tempcount++;
				}
			}
			a[i][tempcount] = Integer.parseInt(p.substring(prob));
		}*/
		for (int i = 0; i < n; i++){
			int tempcount = 0;
			prob = 0;
			p = reader.readLine();
			for (int j = 0; j < p.length(); j++){
				if (Character.isWhitespace(p.charAt(j))) {
					if ((i==0) && (tempcount == 0)) {
						price[0][0] = Integer.parseInt(p.substring(prob, j));
						put[0][0] = "";
						prob = j + 1;
						tempcount++;
						continue;
					}
					int udobstvo= Integer.parseInt(p.substring(prob, j));
					if (i == 0) {
						if (tempcount != 0) {
							price[i][tempcount] = price[i][tempcount - 1] + udobstvo;
							put[i][tempcount] = (put[i][tempcount - 1]) + ("R");
						}
					}
					else{
						if (tempcount != 0) {
							if (price[i][tempcount - 1] > price[i - 1][tempcount]) {
								price[i][tempcount] = price[i][tempcount - 1] + udobstvo;
								put[i][tempcount] = (put[i][tempcount - 1]) + ("R");
							} else{
								price[i][tempcount] = price[i - 1][tempcount] + udobstvo;
								put[i][tempcount] = (put[i - 1][tempcount]) + ("D");
							}
						}
						else {
							price[i][tempcount] = price[i - 1][tempcount] + udobstvo;
							put[i][tempcount] = (put[i - 1][tempcount]) + ("D");
						}
						put[i-1][tempcount] = "";
					}

					prob = j + 1;
					tempcount++;
				}
			}
			if (i == 0) {
				price[i][tempcount] = price[i][tempcount - 1] + Integer.parseInt(p.substring(prob));
				put[i][tempcount] = (put[i][tempcount - 1]) + ("R");
			}
			else {
				if (price[i][tempcount - 1] > price[i - 1][tempcount]) {
					price[i][tempcount] = price[i][tempcount - 1] + Integer.parseInt(p.substring(prob));
					put[i][tempcount] = (put[i][tempcount - 1]) + ("R");
				} else{
					price[i][tempcount] = price[i - 1][tempcount] + Integer.parseInt(p.substring(prob));
					put[i][tempcount] = (put[i - 1][tempcount]) + ("D");
				}
			}
		}
		/*price[0][0] = a[0][0];
		for (int i = 0; i < n; i++){
			for (int j = 0; j < m; j++) {
				put[i][j] = new StringBuilder();
				if (i==0) {
					if (j != 0) {
						price[i][j] = price[i][j - 1] + a[i][j];
						put[i][j].append(put[i][j - 1])
								.append("R");
					}
				}
				else{
					if (j == 0){
						price[i][j] = price[i-1][j] + a[i][j];
						put[i][j].append(put[i-1][j])
								.append("D");
					}
					else{
						if (price[i-1][j] > price[i][j-1]){
							price[i][j] = price[i-1][j] + a[i][j];
							put[i][j].append(put[i - 1][j])
									.append("D");
						}
						else{
							price[i][j] = price[i][j - 1] + a[i][j];
							put[i][j].append(put[i][j - 1])
									.append("R");
						}
					}
				}
			}
		}*/
		System.out.println(price[n-1][m-1]);
		System.out.println(put[n-1][m-1]);
	}
}