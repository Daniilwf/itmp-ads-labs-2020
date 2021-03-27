import java.util.Scanner;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
 
 
public class F {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        int N =scan.nextInt();
        int K = scan.nextInt();
        int[] a = new int[N];
        for (int i=0; i < N; i++){
            a[i] = scan.nextInt();
        }
        for (int i=1; i <= K; i++){
            int x = scan.nextInt();
            if (x<=a[0]) {System.out.println(a[0]); continue;}
            if (x >= a[N-1]) {System.out.println(a[N-1]); continue;}
            int firstindex=0;
            int lastindex=N-1;
            int midindex=0;
            while (firstindex < lastindex){
                midindex=(firstindex+lastindex)/2;
                if (a[midindex] == x) {
                    System.out.println(x);
                    break;
                }
                else{
                    if (a[midindex]>x)
                    lastindex=midindex;
                else
                    firstindex=midindex+1;
 
            }}
            if (a[midindex] == x)
                continue;
            else {
                if ((x - a[firstindex-1]) > (a[firstindex] - x))
                    System.out.println(a[firstindex]);
                else
                    System.out.println(a[firstindex-1]);
        	}
	}
    }
}
 