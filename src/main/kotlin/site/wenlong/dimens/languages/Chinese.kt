package site.wenlong.dimens.languages

/**
 * 中文
 *
 * @author : 郭文龙
 * @Email  : guowenlong20000@sina.com
 * @date   : 2023/5/6  23:05
 */
object Chinese : Text {
    override val titleOne: String = "设置选项"

    override val cover: String = "是否覆盖已有文件"

    override val decimal: String = "保留几位小数的位数"

    override val minWidth: String = "设置所选中的dimens.xml的最小宽度(默认屏幕最小宽度360dp)"

    override val folder: String = "设置限定符名字(默认sw)(注意:命名不影响文件生成的规则)"

    override val bit: String = "位"

    override val more: String = "更多"

    override val titleTwo: String = "生成文件模式"

    override val single: String = "单个生成"

    override val multi: String = "多个生成"

    override val genarate: String = "生成Dimens"

    override val advancedOption: String = "扩展选项"

    override val tipsErrorFile: String = "您选择的文件不是dimens.xml,请重新选择"

    override val layoutTipsErrorFile: String = "您选择的文件不是layout文件夹或者xml文件,请重新选择"

    override val tipsErrorNumber: String = "请输入正确的数字"

    override val tipsCreateFileFailed: String = "文件不存在或者创建文件夹失败"

    override val tipsCreateFileError: String = "生成xml文件或文件夹异常,请提交问题到github,感谢"

    override val tipsGenerateSuccess: String = "生成成功"

    override val tipsConvertSuccess: String = "转换成功"

    override val tipsConvertFail: String = "转换失败"

    override val tipsDimensExists: String =
        "已经存在%s文件夹的dimens.xml文件\n请在Dimens Generating Tools的菜单中勾选可以覆盖源文件\n或者备份后删除重新生成"

    override val tipsGenerateFail: String = "生成失败"

    override val tipsInputEmpty: String = "不允许输入内容为空"

    override val tipsInputZero: String = "不允许输入数字为0"

    override val tipsConvertTitle: String = "转换设置"

    override val tipsConvertTip1: String = "小贴士:1.转换需要一点点时间\n      2.px不会转换"

    override val tipsConvertDP: String = "dp转换的前缀"

    override val tipsConvertSP: String = "sp转换的前缀"

    override val tipsConvertEP1: String = "(例如:length_ ,生成后为length_1)"

    override val tipsConvertEP2: String = "(例如:font_ ,生成后为font_1)"

    override val tipsConvertButton: String = "转换"

    override val language: String = "语言"

    override val converting: String = "转换中"

    override val exceptionOfFileError: String = "文件内部错误"
}