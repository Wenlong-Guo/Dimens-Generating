package site.wenlong.dimens.update

/**
 * 版本
 *
 * @author : 郭文龙
 * @Email  : guowenlong20000@sina.com
 * @date   : 2023/5/20  22:07
 */
enum class VersionEnum(val version: String, val content: String) {
    V310("3.1.0", "1.Replace Java with Kotlin language.<br/>2.Build the project using Gradle.<br/>3.Add update notes feature."),
    UNKNOWN("0.0.0", "")
}