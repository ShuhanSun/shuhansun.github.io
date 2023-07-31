package app;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.Format;

public class Num {

    public static void main(String[] args) {
        d2(3.1);
        d2(3.10);
        d2(3.00);
        d2(3.101);
        d2(3.123456);
        d2(3.0012);
        d2(0.0012);
        d2(123.0012);
        d2(0.001);
        d2(3);
    }

    private static void d2(double d) {
//        BigDecimal b = new BigDecimal(String.valueOf(d));
        String r = new DecimalFormat("0.00").format(d);
        System.out.println(r);
    }
}
