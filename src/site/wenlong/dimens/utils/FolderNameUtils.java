package site.wenlong.dimens.utils;

/**
 * 文件夹名称工具类
 *
 * @author : 郭文龙
 * @date : 2019-05-01  00:04
 */
public class FolderNameUtils {
    public static String splicingFolderName(String qualifierName, float toDimension) {
        return "values-" +
                qualifierName +
                (int) toDimension +
                "dp";
    }
}