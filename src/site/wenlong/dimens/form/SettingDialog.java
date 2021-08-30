package site.wenlong.dimens.form;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import site.wenlong.dimens.core.Configuration;
import site.wenlong.dimens.exception.*;
import site.wenlong.dimens.languages.LanguagesFactory;
import site.wenlong.dimens.languages.Text;
import site.wenlong.dimens.tools.InputTools;
import site.wenlong.dimens.utils.CalculateUtils;
import site.wenlong.dimens.utils.FolderNameUtils;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static site.wenlong.dimens.core.Configuration.DEFAULT_FILE_NAME;
import static site.wenlong.dimens.core.Configuration.PLUGINS_NAME;


/**
 * 插件面板
 * // TODO: 2019/5/5 提示国际化
 *
 * @author : 郭文龙
 * @date : 2019/4/30  23:30
 */
public class SettingDialog extends JFrame {
    private final Configuration configuration = Configuration.getInstance();
    private Text mText = LanguagesFactory.createText(configuration.languageIndex);

    private JPanel mRoot;

    private JComboBox comboBox;

    private JLabel titleOne;
    private JCheckBox cbCover;
    private JCheckBox cbDecimal;
    private JCheckBox cbMinWidth;
    private JCheckBox cbFolder;

    private JLabel titleTwo;
    private JRadioButton rbSingle;
    private JRadioButton rbMultiple;

    private JButton btnMore;
    private JButton btnSingleOption;
    private JButton btnMultipleOption;

    private JButton btnGenerate;

    private JTextField etDecimal;
    private JTextField etSingle;
    private JTextField etMultiple;
    private JTextField etMinWidth;
    private JTextField etFolder;
    private JLabel tvBit;
    private JLabel tvDp;
    private JLabel tvDpSingle;
    private JLabel tvDpMultiple;

    public SettingDialog(VirtualFile currentFile, Project project) throws HeadlessException {
        setContentPane(mRoot);
        initJComboBox();
        initJCheckBox();
        initEditText();
        initJRadioButton();
        initSaveConfiguration();
        reStoreConfiguration();
        initData(currentFile, project);

    }

    private void initEditText() {
        etDecimal.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                configuration.mBit = Integer.parseInt(etDecimal.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                configuration.mBit = Integer.parseInt(etDecimal.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
        etMinWidth.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                configuration.mOriginWidth = Float.parseFloat(etMinWidth.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                configuration.mOriginWidth = Float.parseFloat(etMinWidth.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
        etFolder.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                configuration.mRename = String.valueOf(etFolder.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                configuration.mRename = String.valueOf(etFolder.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
        etSingle.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                configuration.mSingle = String.valueOf(etSingle.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                configuration.mSingle = String.valueOf(etSingle.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
        etMultiple.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                configuration.mMulitple = String.valueOf(etMultiple.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                configuration.mMulitple = String.valueOf(etMultiple.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
    }

    private void initData(VirtualFile currentFile, Project project) {
        btnGenerate.addActionListener(a -> {
            int decimal;
            float minWidth;
            String folderName = InputTools.getString(etFolder.getText(), configuration.isReName, configuration.mRename);
            float target = 0.0f;
            ArrayList<Float> targets = new ArrayList<>();
            boolean isSingle = configuration.isSingle;
            try {
                decimal = InputTools.getInt(etDecimal.getText(), configuration.isKeepPoint, configuration.mBit);
                minWidth = InputTools.getFloat(etMinWidth.getText(), configuration.isMinWidth, configuration.mOriginWidth);
                if (isSingle) {
                    target = InputTools.getFloat(etSingle.getText());
                } else {
                    targets = InputTools.getMultipleNumber(etMultiple.getText());
                }
                new SAXReader().read(new File(Objects.requireNonNull(currentFile.getCanonicalPath())));
            } catch (NumberFormatException e) {
                Messages.showMessageDialog(mText.getTipsErrorNumber(), PLUGINS_NAME, Messages.getInformationIcon());
                return;
            } catch (DocumentException e) {
                Messages.showMessageDialog(mText.getTipsCreateFileError(), PLUGINS_NAME, Messages.getInformationIcon());
                return;
            } catch (InputZeroException e) {
                Messages.showMessageDialog(mText.getTipsInputZero(), PLUGINS_NAME, Messages.getInformationIcon());
                return;
            } catch (InputEmptyException e) {
                Messages.showMessageDialog(mText.getTipsInputEmpty(), PLUGINS_NAME, Messages.getInformationIcon());
                return;
            }
            try {
                if (isSingle) {
                    generateSingle(currentFile, configuration.isCover, decimal, minWidth, folderName, target);
                } else {
                    generateMultiple(currentFile, configuration.isCover, decimal, minWidth, folderName, targets);
                }
            } catch (CreateFileException e) {
                Messages.showMessageDialog(mText.getTipsCreateFileFailed(), PLUGINS_NAME, Messages.getInformationIcon());
                return;
            } catch (FileExistsException e) {
                Messages.showMessageDialog(e.getMessage(), PLUGINS_NAME, Messages.getInformationIcon());
                return;
            } catch (IOException e) {
                Messages.showMessageDialog(mText.getTipsCreateFileError(), PLUGINS_NAME, Messages.getInformationIcon());
                return;
            }
            dispose();
            Objects.requireNonNull(project.getProjectFile()).getFileSystem().refresh(true);
            Messages.showMessageDialog(mText.getTipsGenerateSuccess(), PLUGINS_NAME, Messages.getInformationIcon());
        });
    }

    private void generateSingle(VirtualFile currentFile, boolean isCover, int decimal, float originDimens, String qualifierName, float targetDimens) throws IOException, CreateFileException, FileExistsException {
        try {
            Document document = new SAXReader().read(new File(Objects.requireNonNull(currentFile.getCanonicalPath())));
            Document converedDocument = converDimension(document, decimal, targetDimens, originDimens);
            createDimensFile(converedDocument, currentFile.getParent().getParent().getCanonicalPath(), FolderNameUtils.splicingFolderName(qualifierName, targetDimens), isCover);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    private void generateMultiple(VirtualFile currentFile, boolean isCover, int decimal, float minWidth, String folderName, ArrayList<Float> targets) throws IOException, CreateFileException, FileExistsException {
        for (float target : targets) {
            generateSingle(currentFile, isCover, decimal, minWidth, folderName, target);
        }
    }

    private Document converDimension(Document document, int decimal, float targetDimens, float originDimens) {
        float scale = originDimens / targetDimens;
        Element rootElement = document.getRootElement();
        List<Element> elements = rootElement.elements();
        Element subElement;
        String subElementValueString;
        for (Element element : elements) {
            subElement = element;
            subElementValueString = (String) subElement.getData();
            float subElementValue;
            if (subElementValueString.endsWith("dp")) {
                subElementValue = Float.valueOf(subElementValueString.substring(0, subElementValueString.length() - 2));
                subElement.setText(CalculateUtils.targetDimension(subElementValue, decimal, scale) + "dp");
            } else if (subElementValueString.endsWith("sp")) {
                subElementValue = Float.valueOf(subElementValueString.substring(0, subElementValueString.length() - 2));
                subElement.setText(CalculateUtils.targetDimension(subElementValue, decimal, scale) + "sp");
            } else if (subElementValueString.endsWith("dip")) {
                subElementValue = Float.valueOf(subElementValueString.substring(0, subElementValueString.length() - 3));
                subElement.setText(CalculateUtils.targetDimension(subElementValue, decimal, scale) + "dip");
            }
        }
        return document;
    }

    private void createDimensFile(Document document, String parentFolderUrl, String customFolderName, boolean isCover) throws IOException, CreateFileException, FileExistsException {
        String dimensFolderUrl = parentFolderUrl + "/" + customFolderName;
        File parentFolder = new File(dimensFolderUrl);
        if (!parentFolder.exists() && !parentFolder.mkdir()) {
            throw new CreateFileException();
        }
        File dimensFile = new File(parentFolder + "/" + DEFAULT_FILE_NAME);
        if (!isCover && dimensFile.exists()) {
            throw new FileExistsException(String.format(mText.getTipsDimensExists(), customFolderName));
        }
        if (!dimensFile.delete()) {
            System.out.println("删除文件失败失败:"+dimensFile.getName());
        }
        FileOutputStream fileOutputStream = new FileOutputStream(dimensFile);
        XMLWriter xmlWriter = new XMLWriter(fileOutputStream);
        xmlWriter.write(document);
        xmlWriter.flush();
        xmlWriter.close();
    }

    private void initJRadioButton() {
        ButtonGroup bg = new ButtonGroup();// 初始化按钮组
        bg.add(rbSingle);
        bg.add(rbMultiple);
        rbSingle.setSelected(configuration.isSingle);
        rbMultiple.setSelected(!configuration.isSingle);
        rbSingle.addChangeListener(e -> configuration.isSingle = rbSingle.isSelected());
    }

    private void initJCheckBox() {
        cbCover.addChangeListener(e -> {
            JCheckBox jCheckBox = (JCheckBox) e.getSource();
            configuration.isCover = jCheckBox.isSelected();
        });
        cbDecimal.addChangeListener(e -> {
            JCheckBox jCheckBox = (JCheckBox) e.getSource();
            configuration.isKeepPoint = jCheckBox.isSelected();
        });
        cbMinWidth.addChangeListener(e -> {
            JCheckBox jCheckBox = (JCheckBox) e.getSource();
            configuration.isMinWidth = jCheckBox.isSelected();
        });
        cbFolder.addChangeListener(e -> {
            JCheckBox jCheckBox = (JCheckBox) e.getSource();
            configuration.isReName = jCheckBox.isSelected();
        });
    }

    private void initJComboBox() {
        comboBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                configuration.languageIndex = comboBox.getSelectedIndex();
                updateLanguagesUI();
            }
        });
    }

    private void initSaveConfiguration() {
        addWindowStateListener(i -> configuration.getState());
    }

    private void reStoreConfiguration() {
        comboBox.setSelectedIndex(configuration.languageIndex);
        cbCover.setSelected(configuration.isCover);
        cbDecimal.setSelected(configuration.isKeepPoint);
        cbMinWidth.setSelected(configuration.isMinWidth);
        cbFolder.setSelected(configuration.isReName);
        etDecimal.setText(String.valueOf(configuration.mBit));
        etMinWidth.setText(String.valueOf(configuration.mOriginWidth));
        etFolder.setText(configuration.mRename);
        etSingle.setText(configuration.mSingle);
        etMultiple.setText(configuration.mMulitple);
        updateLanguagesUI();
    }

    private void updateLanguagesUI() {
        mText = LanguagesFactory.createText(configuration.languageIndex);
        titleOne.setText(mText.getTitleOne());
        cbCover.setText(mText.getCover());
        cbDecimal.setText(mText.getDecimal());
        cbMinWidth.setText(mText.getMinWidth());
        cbFolder.setText(mText.getFolder());

        titleTwo.setText(mText.getTitleTwo());
        rbSingle.setText(mText.getSingle());
        rbMultiple.setText(mText.getMultiple());

        btnMore.setText(mText.getMore());
        btnSingleOption.setText(mText.getAdvancedOption());
        btnMultipleOption.setText(mText.getAdvancedOption());

        btnGenerate.setText(mText.getGenarate());

    }
}