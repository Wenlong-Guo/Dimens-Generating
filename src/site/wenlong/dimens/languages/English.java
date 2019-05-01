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
    public String getGenarate() {
        return genarate;
    }

    @Override
    public String getTipsErrorNumber() {
        return tipsErrorNumber;
    }
}