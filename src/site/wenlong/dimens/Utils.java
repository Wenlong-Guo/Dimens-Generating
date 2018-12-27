package site.wenlong.dimens;

import java.text.DecimalFormat;

public class Utils {
    public static String trimDecimal(double originNumber, int length) {
        String format;
        StringBuffer sb = new StringBuffer("0.");
        for (int l = 0; l < length; l++) {
            sb.append("#");
        }
        format = sb.toString();
        DecimalFormat df = new DecimalFormat(format);
        return df.format(originNumber);
    }
}
