import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
public class E {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String p = reader.readLine();
        int j = 0;
        int[] a = new int[p.length()/2];
        for (int i = 0; i < p.length(); i++){
            while ((p.charAt(i) != '+') && (p.charAt(i) != '*') && (p.charAt(i) != '-')){
                if (Character.isDigit(p.charAt(i))) {
                    a[j] = Character.getNumericValue(p.charAt(i));
                    j++;
                }
                i++;
            }
            if (p.charAt(i) == '+') {
                a[j - 2] = a[j - 1] + a[j - 2];
                a[j - 1] = 0;
                j--;
            }
            if (p.charAt(i) == '*') {
                a[j - 2] = a[j - 1] * a[j - 2];
                a[j - 1] = 0;
                j--;
            }
            if (p.charAt(i) == '-') {
                a[j - 2] =  a[j - 2] - a[j - 1] ;
                a[j - 1] = 0;
                j--;
            }
        }
        System.out.println(a[0]);
    }
}