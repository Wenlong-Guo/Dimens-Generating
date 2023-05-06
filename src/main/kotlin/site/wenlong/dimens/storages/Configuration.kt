package site.wenlong.dimens.storages

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.XmlSerializerUtil

/**
 * 存储数据
 *
 * @author : 郭文龙
 * @Email  : guowenlong20000@sina.com
 * @date   : 2023/5/6  20:24
 */
@State(name = "Configuration", storages = [Storage(value = "\$APP_CONFIG$/Configuration.xml")])
class Configuration : PersistentStateComponent<Configuration> {
    companion object {
        fun getInstance(): Configuration {
            return ApplicationManager.getApplication().getService(Configuration::class.java)
        }
    }

    /**
     * 是否覆盖
     */
    var isOverwrite = false

    /**
     * 是否保留小数点
     */
    var isKeepDecimal = false

    /**
     * 是否设置最小宽度
     */
    var isMinWidth = false

    /**
     * 是否设置重命名文件件中间内容
     */
    var isReName = false

    /**
     * 是否是单个生成
     */
    var isSingle = false

    /**
     * 小数点位数
     */
    var decimalPlaces = 2

    /**
     * 原始宽度
     */
    var originWidth = 360f

    /**
     * 文件夹中间名字
     */
    var rename = "sw"

    /**
     * 单个生成的dimens文件的宽度基准
     */
    var single = "400"

    /**
     * 多个生成的dimens文件的宽度基准集合
     */
    var multi = "300,320,340,360,380,400,420,440,460,480,500"

    /**
     * 语言角标
     */
    var languageIndex = 0

    /**
     * dp dip的前缀
     */
    var dp = "length_"

    /**
     * sp 的前缀
     */
    var sp = "font_"

    override fun getState(): Configuration = this

    override fun loadState(state: Configuration) = XmlSerializerUtil.copyBean(state, this)
}