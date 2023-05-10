package site.wenlong.dimens.languages

/**
 * 语言工厂类
 *
 * @author : 郭文龙
 * @Email  : guowenlong20000@sina.com
 * @date   : 2023/5/7  0:55
 */
object LanguagesFactory {
    fun createText(languagesEnum: Int): Text {
        return when (languagesEnum) {
            1 -> Chinese
            0 -> English
            else -> English
        }
    }
}