package site.wenlong.dimens.languages;

/**
 * 语言国际化-英文
 *
 * @author : 郭文龙
 * @date : 2019/4/30  23:30
 */
public class English implements Text {
    private String titleOne = "Setting Options";
    private String cover = "Overwrite existing files";
    private String decimal = "Keep the decimal point";
    private String minWidth = "Set the minimum width of the selected dimens.xml(Default 360dp)";
    private String folder = "Set the qualifier name (default sw) (Note: no matter how you name it will not change the rules for file generation)";

    private String bit = "bit";
    private String dp = "dp";
    private String more = "more";

    private String titleTwo = "Generation Mode";

    private String single = "Single";
    private String multiple = "Multiple";

    private String advancedOption = "advanced option";
    private String genarate = "Genarate dimens";

    //提示语
    private String tipsErrorFile = "The file you selected is not dimens.xml, please re-select";
    private String layoutTipsErrorFile = "The file you selected is not layout folder or an xml file, please re-select";
    private String tipsErrorNumber = "Please enter the correct number";

    private static English instance;

    public static synchronized English getInstance() {
        if (instance == null) {
            instance = new English();
        }
        return instance;
    }

    @Override
    public String getTitleOne() {
        return titleOne;
    }

    @Override
    public String getCover() {
        return cover;
    }

    @Override
    public String getDecimal() {
        return decimal;
    }

    @Override
    public String getMinWidth() {
        return minWidth;
    }

    @Override
    public String getFolder() {
        return folder;
    }

    @Override
    public String getBit() {
        return bit;
    }

    @Override
    public String getDp() {
        return dp;
    }

    @Override
    public String getMore() {
        return more;
    }

    @Override
    public String getTitleTwo() {
        return titleTwo;
    }

    @Override
    public String getSingle() {
        return single;
    }

    @Override
    public String getMultiple() {
        return multiple;
    }

    @Override
    public String getAdvancedOption() {
        return advancedOption;
    }

    public String getTipsErrorFile() {
        return tipsErrorFile;
    }

    @Override
    public String getLayoutTipsErrorFile() {
        return layoutTipsErrorFile;
    }

    @Override
    public String getGenarate() {
        return genarate;
    }

    @Override
    public String getTipsErrorNumber() {
        return tipsErrorNumber;
    }

    @Override
    public String getTipsCreateFileFailed() {
        return "File does not exist or folder creation failed";
    }

    @Override
    public String getTipsCreateFileError() {
        return "Generate xml file or folder exception, please submit a question to github, thanks";
    }

    @Override
    public String getTipsGenerateSuccess() {
        return "Generated successfully";
    }

    @Override
    public String getTipsConvertSuccess() {
        return "Converted successfully";
    }

    @Override
    public String getTipsDimensExists() {
        return "The dimens.xml file of the %s folder already exists.\n Please check the Dimens Generating Tools menu to overwrite the source file \n or delete and regenerate after backup.";
    }

    @Override
    public String getTipsInputEmpty() {
        return "Do not allow input to be empty";
    }

    @Override
    public String getTipsInputZero() {
        return "Do not allow input numbers to be 0";
    }

    @Override
    public String getTipsConvertTitle() {
        return "Convert Option";
    }

    @Override
    public String getTipsConvertTip1() {
        return "Tips:need a little time\n      2.px will not convert";
    }

    @Override
    public String getTipsConvertDP() {
        return "dp conversion prefix";
    }

    @Override
    public String getTipsConvertSP() {
        return "sp conversion prefix";
    }

    @Override
    public String getTipsConvertEP1() {
        return "(For example: length_ ,generation is length_1)";
    }

    @Override
    public String getTipsConvertEP2() {
        return "(For example: font_ ,generation is font_1)";
    }

    @Override
    public String getTipsConvertButton() {
        return "Convert";
    }
}