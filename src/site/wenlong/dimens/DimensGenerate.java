package site.wenlong.dimens;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static site.wenlong.dimens.Config.PLUGINS_NAME;

public class DimensGenerate extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent event) {
        VirtualFile currentFile = event.getData(PlatformDataKeys.VIRTUAL_FILE);

        // TODO: 2018/10/28 0028  弹窗提示
        if (!isCurrentFilePassable(currentFile, Config.DEFAULT_FILE_NAME)) {
            Messages.showMessageDialog("您选择的文件不是dimens.xml,请重新生成", PLUGINS_NAME, Messages.getInformationIcon());
            return;
        }
        Document document;
        try {
            document = getDocument(currentFile);
        } catch (DocumentException e) {
            Messages.showMessageDialog("加载xml文件异常,请提交问题到github,感谢", PLUGINS_NAME, Messages.getInformationIcon());
            return;
        }
        //TODO 读取配置
        Document converedDocument = converDimension(document, new DimensionEntity(440F));
        try {
            createDimensFile(converedDocument, currentFile.getParent().getParent().getCanonicalPath(), new DimensionEntity(440), Config.DEFAULT_FILE_NAME, Config.IS_COVER_FILE);
        } catch (IOException e) {
            Messages.showMessageDialog("生成xml文件或文件夹异常,请提交问题到github,感谢", PLUGINS_NAME, Messages.getInformationIcon());
        }
        //todo reload项目
    }

    private boolean isCurrentFilePassable(VirtualFile currentFile, String defaultFileName) {
        return currentFile != null && currentFile.getName().equals(defaultFileName);
    }

    private Document getDocument(VirtualFile currentFile) throws DocumentException {
        SAXReader saxReader = new SAXReader();
        File file = new File(Objects.requireNonNull(currentFile.getCanonicalPath()));
        return saxReader.read(file);
    }

    private Document converDimension(Document document, DimensionEntity dimensionEntity) {
        Document coverDocument = document;
        Element rootElement = coverDocument.getRootElement();
        List<Element> elements = rootElement.elements();
        Element subElement;
        String subElementValueString;
        for (int i = 0; i < elements.size(); i++) {
            subElement = elements.get(i);
            subElementValueString = (String) subElement.getData();
            float subElementValue;
            if (subElementValueString.endsWith("dp")) {
                subElementValue = Float.valueOf(subElementValueString.substring(0, subElementValueString.length() - 2));
                subElement.setText(dimensionEntity.calculateDimension(subElementValue) + "dp");
            } else if (subElementValueString.endsWith("sp")) {
                subElementValue = Float.valueOf(subElementValueString.substring(0, subElementValueString.length() - 2));
                subElement.setText(dimensionEntity.calculateDimension(subElementValue) + "sp");
            }
        }
        return coverDocument;
    }

    private boolean createDimensFile(Document document, String parentFolderUrl, DimensionEntity dimensionEntity, String defaultFileName, boolean isCover) throws IOException {
        String dimensFolderUrl = parentFolderUrl + "/" + dimensionEntity.getFolderName();
        File parentFolder = new File(dimensFolderUrl);
        if (!parentFolder.exists() && !parentFolder.mkdir()) {
            Messages.showMessageDialog("文件不存在或者创建文件夹失败", PLUGINS_NAME, Messages.getInformationIcon());
            return false;
        }
        File dimensFile = new File(parentFolder + "/" + defaultFileName);
        if (!isCover && dimensFile.exists()) {
            Messages.showMessageDialog("已经存在" + dimensionEntity.getFolderName() + "文件夹的" + defaultFileName + "文件" + "\n" +
                    "请在Tools -> Dimens Generating Tools的菜单中勾选可以覆盖源文件" + "\n" +
                    "或者备份后删除重新生成", PLUGINS_NAME, Messages.getInformationIcon());
            return false;
        }
        dimensFile.delete();
        FileOutputStream fileOutputStream = new FileOutputStream(dimensFile);
        XMLWriter xmlWriter = new XMLWriter(fileOutputStream);
        xmlWriter.write(document);
        xmlWriter.flush();
        xmlWriter.close();
        return true;
    }
}