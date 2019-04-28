package site.wenlong.dimens.zold;

import java.text.DecimalFormat;

public class Utils {
    public static String trimDecimal(double originNumber, int length) {
        String format;
        StringBuilder sb = new StringBuilder("0.");
        for (int l = 0; l < length; l++) {
            sb.append("#");
        }
        format = sb.toString();
        DecimalFormat df = new DecimalFormat(format);
        return df.format(originNumber);
    }

    public static void main(String[] args) {
        System.out.print(trimDecimal(100f / 3, 2));
    }
}
