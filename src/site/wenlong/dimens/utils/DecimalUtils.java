package site.wenlong.dimens.utils;

import java.text.DecimalFormat;

/**
 * 小数点格式化工具类
 *
 * @author : 郭文龙
 * @date : 2019/4/30  23:30
 */
public class DecimalUtils {
    public static String formatDecimal(double originNumber, int length) {
        StringBuilder sb = new StringBuilder("0.");
        for (int l = 0; l < length; l++) {
            sb.append("#");
        }
        DecimalFormat df = new DecimalFormat(sb.toString());
        return df.format(originNumber);
    }
}