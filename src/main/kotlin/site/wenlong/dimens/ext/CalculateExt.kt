package site.wenlong.dimens.ext

import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

/**
 * 计算
 *
 * @author : 郭文龙
 * @Email  : guowenlong20000@sina.com
 * @date   : 2023/5/10  20:34
 */
fun targetDimension(dimens: Float, length: Int, scale: Float): String? {
    println("dimens: ${dimens}")
    println("length: ${length}")
    println("scale: ${scale}")

    return formatDecimal((dimens / scale).toDouble(), length)
}

fun scale(originDimension: Float, toDimension: Float): Float {
    return originDimension / toDimension
}

fun formatDecimal(originNumber: Double, length: Int): String? {
    val sb = StringBuilder("0.")
    for (l in 0 until length) {
        sb.append("#")
    }
    val decimal = DecimalFormat(sb.toString()).format(originNumber)
    val numberFormat = NumberFormat.getNumberInstance(Locale.getDefault())
    numberFormat.isGroupingUsed = false
    return numberFormat.format(java.lang.Double.valueOf(decimal))
}