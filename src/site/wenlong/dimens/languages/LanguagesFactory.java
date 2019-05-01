package site.wenlong.dimens.languages;

/**
 * 多语言工厂类
 *
 * @author : 郭文龙
 * @date : 2019-05-01  01:01
 */
public class LanguagesFactory {
    public static Text createText(int languagesEnum) {
        Text text = null;
        switch (languagesEnum) {
            case 1:
                text = Chinese.getInstance();
                break;
            case 0:
                text = English.getInstance();
                break;
            default:
                text = English.getInstance();
                break;
        }
        return text;
    }
}