import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
 
import static java.lang.StrictMath.abs;
import static java.lang.StrictMath.sqrt;
 
public class I {
    public static void main(String[] args) throws IOException {
        double x=0;
        double low = 0;
        double mid = 50000;
        double high = 100000;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String p = reader.readLine();
        double C = Double.parseDouble(p);
        while (abs(C-(mid*mid-sqrt(mid)))>0.000001) {
            mid = (low + high) / 2;
            if (mid*mid-sqrt(mid) < C) {
                low = mid + 1;
            } else if (mid*mid-sqrt(mid) > C) {
                high = mid - 1;
            } else if (mid*mid-sqrt(mid) == C) {
                x = mid;
                break;
            }
        }
 
        x=mid;
        DecimalFormat df = new DecimalFormat("###");
        double y = Double.parseDouble(df.format(x))-1;
        double z = y + 2;
        while (z-y>= 0.000001) {
            double mid1 = (z+y) / 2;
            if (mid1*mid1+sqrt(mid1) < C) {
                y = mid1;
            } else if (mid1*mid1+sqrt(mid1) > C) {
                z = mid1;
            } else if (mid1*mid1+sqrt(mid1) == C) {
                y = mid1;
                break;
            }
        }
        String formattedDouble = new DecimalFormat("#0.000000").format(y+0.0000001);
     //   y = Double.parseDouble(df.format(y));
      //  if ((y*y+sqrt(y))==C)
        System.out.println(formattedDouble);
        //else{
        //        if ((y*y+sqrt(y))>C)
        //            y -= 0.000001;
       //         else y += 0.000001;
        //    System.out.println(df1.format(y));
       // }
    }
}