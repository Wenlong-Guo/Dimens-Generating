package site.wenlong.dimens.form;

import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.jetbrains.annotations.NotNull;
import site.wenlong.dimens.core.Configuration;
import site.wenlong.dimens.languages.LanguagesFactory;
import site.wenlong.dimens.languages.Text;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static site.wenlong.dimens.core.Configuration.PLUGINS_NAME;

public class ConverterDialog extends JDialog {
    private JPanel mRoot;
    private JLabel titleLabel;
    private JButton convertButton;
    private JTextField etLength;
    private JTextField etFont;
    private JComboBox comboBox;
    private JLabel tipsLabel;
    private JLabel dpLabel;
    private JLabel spLabel;
    private JLabel epLabel1;
    private JLabel epLabel2;
    private final Configuration configuration = Configuration.getInstance();
    private Text mText = LanguagesFactory.createText(configuration.languageIndex);
    private boolean isFolder = false;

    public ConverterDialog(VirtualFile currentFile, Project project, boolean isFolder) {
        setContentPane(mRoot);
        this.isFolder = isFolder;
        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        mRoot.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        initJComboBox();
        initEditText();
        initSaveConfiguration();
        reStoreConfiguration();
        initData(currentFile, project);
    }

    private void initData(VirtualFile currentFile, Project project) {
        convertButton.addActionListener(a -> {
            if (isFolder) {
                convertLayoutFolder(currentFile, project);
            } else {
                convertOneLayoutXmlFile(currentFile, project);
            }
            dispose();
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

    private void initEditText() {
        etFont.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                configuration.mSP = String.valueOf(etFont.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                configuration.mSP = String.valueOf(etFont.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
        etLength.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                configuration.mDP = String.valueOf(etLength.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                configuration.mDP = String.valueOf(etLength.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
    }

    private void initSaveConfiguration() {
        addWindowStateListener(i -> configuration.getState());
    }

    private void reStoreConfiguration() {
        comboBox.setSelectedIndex(configuration.languageIndex);
        etFont.setText(configuration.mSP);
        etLength.setText(configuration.mDP);
        updateLanguagesUI();
    }

    private void updateLanguagesUI() {
        mText = LanguagesFactory.createText(configuration.languageIndex);
        titleLabel.setText(mText.getTipsConvertTitle());
        tipsLabel.setText(mText.getTipsConvertTip1());
        dpLabel.setText(mText.getTipsConvertDP());
        spLabel.setText(mText.getTipsConvertSP());
        epLabel1.setText(mText.getTipsConvertEP1());
        epLabel2.setText(mText.getTipsConvertEP2());
        convertButton.setText(mText.getTipsConvertButton());
    }

    public void convertOneLayoutXmlFile(VirtualFile currentFile, Project project) {
        ProgressManager.getInstance().run(new Task.Backgroundable(project, "转换中") {
            @Override
            public void run(@NotNull ProgressIndicator progressIndicator) {
                convert(currentFile);
            }
        });
        Messages.showMessageDialog(LanguagesFactory.createText(configuration.languageIndex).getTipsGenerateSuccess(), PLUGINS_NAME, Messages.getInformationIcon());
    }

    public void convertLayoutFolder(VirtualFile currentFile, Project project) {
        ProgressManager.getInstance().run(new Task.Backgroundable(project, "转换中") {
            @Override
            public void run(@NotNull ProgressIndicator progressIndicator) {
                VirtualFile[] children = currentFile.getChildren();
                for (VirtualFile child : children) {
                    convert(child);
                }
            }
        });
        Messages.showMessageDialog(LanguagesFactory.createText(configuration.languageIndex).getTipsGenerateSuccess(), PLUGINS_NAME, Messages.getInformationIcon());
    }

    private void convert(VirtualFile child) {
        try {
            Document coverDocument = new SAXReader().read(new File(Objects.requireNonNull(child.getCanonicalPath())));
            ArrayList<Element> root = new ArrayList<>();
            root.add(coverDocument.getRootElement());
            cycleConvert(root);
            OutputFormat format = new OutputFormat();
            XMLWriter xmlWriter = new XMLWriter(new FileWriter(new File(child.getCanonicalPath())), format);
            xmlWriter.write(coverDocument);
            xmlWriter.flush();
            xmlWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cycleConvert(List<Element> elements) {
        if (elements == null || elements.size() == 0) return;
        for (Element element : elements) {
            System.out.println("外层name\n" + element.getName());
            for (Attribute attribute : element.attributes()) {
                String attributeValue = attribute.getValue();
                System.out.println("name:" + attribute.getName());
                System.out.println("value:" + attribute.getValue());
                if (attributeValue.endsWith("dp")) {
                    attribute.setText("@dimen/" + configuration.mDP + attributeValue.substring(0, attributeValue.length() - 2));
                } else if (attributeValue.endsWith("sp")) {
                    attribute.setText("@dimen/" + configuration.mSP + attributeValue.substring(0, attributeValue.length() - 2));
                } else if (attributeValue.endsWith("dip")) {
                    attribute.setText("@dimen/" + configuration.mDP + attributeValue.substring(0, attributeValue.length() - 3));
                } else if (attributeValue.endsWith("px")) {
                    //todo 暂时不转换
//                    attribute.setText("@dimen/length_" + attributeValue.substring(0, attributeValue.length() - 2));
                }
            }
            cycleConvert(element.elements());
        }
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
