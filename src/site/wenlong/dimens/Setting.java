package site.wenlong.dimens;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.components.JBCheckBox;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static site.wenlong.dimens.Config.PLUGINS_NAME;

/**
 * TODO UI 颜色还需要调整一下下
 */
public class Setting extends JFrame {
    JPanel mRoot;
    private JBCheckBox mCb_isCovered;
    private JBCheckBox mCb_isCurrentDimens;
    private JTextField et_originDimens;
    private JButton btn_generateSingle;
    private JTextField et_multiDimens;
    private JTextField et_targetDimens;
    private JButton btn_generateMulti;
    private boolean isCurrentDimens;
    private boolean isCoverEnable;
    private float mTargetDimens;
    private List<Float> multiDemens = new ArrayList<>();

    public Setting(VirtualFile currentFile, Project project) throws HeadlessException {
        setContentPane(mRoot);
        initData(currentFile, project);
    }

    private void initData(VirtualFile currentFile, Project project) {
        mCb_isCurrentDimens.addChangeListener(e -> {
            JBCheckBox checkbox = (JBCheckBox) e.getSource();
            isCurrentDimens = checkbox.isSelected();
        });
        mCb_isCovered.addChangeListener(e -> {
            JBCheckBox checkbox = (JBCheckBox) e.getSource();
            isCoverEnable = checkbox.isSelected();
            Config.IS_COVER_FILE = isCoverEnable;
        });
        btn_generateSingle.addActionListener(e -> {
            if (isCurrentOriginDimensFail()) return;
            if (isTargetDimensFail()) return;
            generateDimens(currentFile, mTargetDimens);
            dispose();
            Objects.requireNonNull(project.getProjectFile()).getFileSystem().refresh(true);
            Messages.showMessageDialog("生成成功", PLUGINS_NAME, Messages.getInformationIcon());
        });
        btn_generateMulti.addActionListener(e -> {
            if (isCurrentOriginDimensFail()) return;
            if (isMultiDimensFail()) return;
            for (Float targetDimens : multiDemens) {
                generateDimens(currentFile, targetDimens);
            }
            dispose();
            restoreDefaultSetting();
            Objects.requireNonNull(project.getProjectFile()).getFileSystem().refresh(true);
            Messages.showMessageDialog("生成成功", PLUGINS_NAME, Messages.getInformationIcon());
        });
    }

    private boolean isTargetDimensFail() {
        try {
            mTargetDimens = Float.valueOf(et_targetDimens.getText());
        } catch (Exception exception) {
            Messages.showMessageDialog("请输入正确的数字", PLUGINS_NAME, Messages.getInformationIcon());
            return true;
        }
        return false;
    }

    private boolean isMultiDimensFail() {
        String text = et_multiDimens.getText();
        String[] split = text.split(",");
        for (String num : split) {
            try {
                Float targetDimens = Float.valueOf(num);
                multiDemens.add(targetDimens);
            } catch (Exception e) {
                Messages.showMessageDialog("请输入正确的数字", PLUGINS_NAME, Messages.getInformationIcon());
                return true;
            }
        }
        return false;
    }

    private boolean isCurrentOriginDimensFail() {
        if (isCurrentDimens) {
            try {
                Config.DEFAULT_ORIGIN_DIMENS = Float.valueOf(et_originDimens.getText());
            } catch (Exception exception) {
                Messages.showMessageDialog("请输入正确的数字", PLUGINS_NAME, Messages.getInformationIcon());
                return true;
            }
        }
        return false;
    }

    private boolean generateDimens(VirtualFile currentFile, float targetDimens) {
        Document document;
        try {
            document = getDocument(currentFile);
        } catch (DocumentException e) {
            Messages.showMessageDialog("加载xml文件异常,请提交问题到github,感谢", PLUGINS_NAME, Messages.getInformationIcon());
            return false;
        }
        //TODO 读取配置
        Document converedDocument = converDimension(document, new DimensionEntity(Config.DEFAULT_ORIGIN_DIMENS, targetDimens));
        try {
            return createDimensFile(converedDocument, currentFile.getParent().getParent().getCanonicalPath(), new DimensionEntity(Config.DEFAULT_ORIGIN_DIMENS, targetDimens), Config.DEFAULT_FILE_NAME, Config.IS_COVER_FILE);
        } catch (IOException e) {
            Messages.showMessageDialog("生成xml文件或文件夹异常,请提交问题到github,感谢", PLUGINS_NAME, Messages.getInformationIcon());
            return false;
        }
        //todo reload项目
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
                    "请在Dimens Generating Tools的菜单中勾选可以覆盖源文件" + "\n" +
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

    private void restoreDefaultSetting() {
        Config.DEFAULT_ORIGIN_DIMENS = 360f;
        Config.IS_COVER_FILE = true;
    }
}
