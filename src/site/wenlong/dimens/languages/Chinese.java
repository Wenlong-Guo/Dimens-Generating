package site.wenlong.dimens.languages;

/**
 * 语言国际化-中文
 *
 * @author : 郭文龙
 * @date : 2019/4/30  23:30
 */
public class Chinese implements Text {
    private String titleOne = "设置选项";

    private String cover = "是否覆盖已有文件";
    private String decimal = "保留几位小数的位数";
    private String minWidth = "设置所选中的dimens.xml的最小宽度(默认屏幕最小宽度360dp)";
    private String folder = "设置限定符名字(默认sw)(注意:命名不影响文件生成的规则)";

    private String bit = "位";
    private String dp = "dp";
    private String more = "更多";

    private String titleTwo = "生成文件模式";

    private String single = "单个生成";
    private String multiple = "多个生成";

    private String advancedOption = "扩展选项";
    private String genarate = "生成Dimens";
    // 提示语
    private String tipsErrorFile = "您选择的文件不是dimens.xml,请重新选择";
    private String tipsErrorNumber = "请输入正确的数字";

    private static Chinese instance;

    public static synchronized Chinese getInstance() {
        if (instance == null) {
            instance = new Chinese();
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