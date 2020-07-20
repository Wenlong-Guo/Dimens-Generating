package site.wenlong.dimens.languages;

/**
 * 语言国际化-中文
 *
 * @author : 郭文龙
 * @date : 2019/4/30  23:30
 */
public class Chinese implements Text {
    private static Chinese instance;

    static synchronized Chinese getInstance() {
        if (instance == null) {
            instance = new Chinese();
        }
        return instance;
    }

    @Override
    public String getTitleOne() {
        return "设置选项";
    }

    @Override
    public String getCover() {
        return "是否覆盖已有文件";
    }

    @Override
    public String getDecimal() {
        return "保留几位小数的位数";
    }

    @Override
    public String getMinWidth() {
        return "设置所选中的dimens.xml的最小宽度(默认屏幕最小宽度360dp)";
    }

    @Override
    public String getFolder() {
        return "设置限定符名字(默认sw)(注意:命名不影响文件生成的规则)";
    }

    @Override
    public String getBit() {
        return "位";
    }

    @Override
    public String getDp() {
        return "dp";
    }

    @Override
    public String getMore() {
        return "更多";
    }

    @Override
    public String getTitleTwo() {
        return "生成文件模式";
    }

    @Override
    public String getSingle() {
        return "单个生成";
    }

    @Override
    public String getMultiple() {
        return "多个生成";
    }

    @Override
    public String getAdvancedOption() {
        return "扩展选项";
    }

    // 提示语
    @Override
    public String getTipsErrorFile() {
        return "您选择的文件不是dimens.xml,请重新选择";
    }

    @Override
    public String getLayoutTipsErrorFile() {
        return "您选择的文件不是layout文件夹或者xml文件,请重新选择";
    }
    @Override
    public String getGenarate() {
        return "生成Dimens";
    }

    @Override
    public String getTipsErrorNumber() {
        return "请输入正确的数字";
    }

    @Override
    public String getTipsCreateFileFailed() {
        return "文件不存在或者创建文件夹失败";
    }

    @Override
    public String getTipsCreateFileError() {
        return "生成xml文件或文件夹异常,请提交问题到github,感谢";
    }

    @Override
    public String getTipsGenerateSuccess() {
        return "生成成功";
    }

    @Override
    public String getTipsDimensExists() {
        return "已经存在%s文件夹的dimens.xml文件\n请在Dimens Generating Tools的菜单中勾选可以覆盖源文件\n或者备份后删除重新生成";
    }
}