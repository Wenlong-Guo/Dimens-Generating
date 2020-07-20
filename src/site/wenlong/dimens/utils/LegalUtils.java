package site.wenlong.dimens.utils;

import com.intellij.openapi.vfs.VirtualFile;
import site.wenlong.dimens.core.Configuration;

/**
 * 输入数据是否合法的工具类
 *
 * @author : 郭文龙
 * @date : 2019/4/30  23:30
 */
public class LegalUtils {

    public static boolean isNumber(String inputString) {
        try {
            Float.valueOf(inputString);
        } catch (Exception exception) {
            return false;
        }
        return true;
    }

    public static boolean isDimensFile(VirtualFile currentFile) {
        return currentFile != null && currentFile.getName().equals(Configuration.DEFAULT_FILE_NAME);
    }

    public static boolean isLayoutFolder(VirtualFile currentFile) {
        return currentFile != null && currentFile.getName().equals(Configuration.DEFAULT_LAYOUT_FOLDER_NAME);
    }

    public static boolean isLayoutXml(VirtualFile currentFile) {
        return currentFile != null && currentFile.getName().endsWith(Configuration.DEFAULT_LAYOUT_XML_NAME);
    }

    public static float getNumber(String inputString) throws NumberFormatException {
        return Float.valueOf(inputString);
    }


}