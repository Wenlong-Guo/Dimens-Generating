package site.wenlong.dimens.languages

/**
 * 英语
 *
 * @author : 郭文龙
 * @Email  : guowenlong20000@sina.com
 * @date   : 2023/5/6  23:14
 */
object English : Text {
    override val titleOne = "Setting Options"

    override val cover = "Overwrite existing files"

    override val decimal = "Keep the decimal point"

    override val minWidth = "Set the minimum width of the selected dimens.xml(Default 360dp)"

    override val folder =
        "Set the qualifier name (default sw) (Note: no matter how you name it will not change the rules for file generation)"

    override val bit = "bit"

    override val more = "more"

    override val titleTwo = "Generation Mode"

    override val single = "Single"

    override val multiple = "Multiple"

    override val advancedOption = "advanced option"

    override val genarate = "Genarate dimens"

    override val tipsErrorFile = "The file you selected is not dimens.xml, please re-select"

    override val layoutTipsErrorFile =
        "The file you selected is not layout folder or an xml file, please re-select"

    override val tipsErrorNumber = "Please enter the correct number"

    override val tipsCreateFileFailed: String = "File does not exist or folder creation failed"

    override val tipsCreateFileError: String =
        "Generate xml file or folder exception, please submit a question to github, thanks";

    override val tipsGenerateSuccess: String = "Generated successfully"

    override val tipsConvertSuccess: String = "Converted successfully"

    override val tipsConvertFail: String = "Converter fail"

    override val tipsDimensExists: String =
        "The dimens.xml file of the %s folder already exists.\n Please check the Dimens Generating Tools menu to overwrite the source file \n or delete and regenerate after backup."

    override val tipsInputEmpty: String = "Do not allow input to be empty"

    override val tipsInputZero: String = "Do not allow input numbers to be 0"

    override val tipsConvertTitle: String = "Convert Option"

    override val tipsConvertTip1: String = "Tips:need a little time and px will not be convert"

    override val tipsConvertDP: String = "dp conversion prefix"

    override val tipsConvertSP: String = "sp conversion prefix"

    override val tipsConvertEP1: String = "(For example: length_ ,generation is length_1)"

    override val tipsConvertEP2: String = "(For example: font_ ,generation is font_1)"

    override val tipsConvertButton: String = "Convert"

    override val language: String = "Language"

    override val converting: String = "Converting..."

    override val exceptionOfFileError: String = "File Error"
}