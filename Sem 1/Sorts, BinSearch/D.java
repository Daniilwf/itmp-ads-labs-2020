import java.io.BufferedReader;
import java.util.Scanner;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
 
 
public class zD {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String p = reader.readLine();
        int N = Integer.parseInt(p);
        int[] a = new int[N];
        int j=0;
        int max1=0;
        int maxp=0;
        for (int i=0; i < N; i++){
            String o = reader.readLine();
            if (o.length() == 1) {
                for (int k = 0; k < j; k++)
                    if (max1 < a[k]) {
                        max1 = a[k];
                        maxp = k;
                    }
                System.out.println(max1);
                a[maxp] = 0;
                max1 = 0;
            } else
                 {
                    int y = Integer.parseInt((o.substring(2)));;
                    a[j]=y;
                    j ++;
                }
            }
        }
    }