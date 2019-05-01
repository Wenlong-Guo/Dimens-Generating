package site.wenlong.dimens.utils;

/**
 * 计算工具类
 *
 * @author : 郭文龙
 * @date : 2019/4/30  23:30
 */
public class CalculateUtils {
    public String targetDimension(float dimens, int length, float scale) {
        return DecimalUtils.formatDecimal(dimens / scale, length);
    }

    public float scaleDimension(float originDimension, float toDimension) {
        return originDimension / toDimension;
    }
}