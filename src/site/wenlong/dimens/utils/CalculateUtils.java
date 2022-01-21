package site.wenlong.dimens.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * 计算工具类
 *
 * @author : 郭文龙
 * @date : 2019/4/30  23:30
 */
public class CalculateUtils {
    public static String targetDimension(float dimens, int length, float scale) {
        return formatDecimal(dimens / scale, length);
    }

    public static float scale(float originDimension, float toDimension) {
        return originDimension / toDimension;
    }

    public static String formatDecimal(double originNumber, int length) {
        StringBuilder sb = new StringBuilder("0.");
        for (int l = 0; l < length; l++) {
            sb.append("#");
        }
        String decimal = new DecimalFormat(sb.toString()).format(originNumber);
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.getDefault());
        numberFormat.setGroupingUsed(false);
        return numberFormat.format(Double.valueOf(decimal));
    }
}