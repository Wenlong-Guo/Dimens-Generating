package site.wenlong.dimens.form;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import site.wenlong.dimens.core.Configuration;
import site.wenlong.dimens.languages.LanguagesFactory;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static site.wenlong.dimens.core.Configuration.PLUGINS_NAME;

public class ConverterDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private Configuration configuration = Configuration.getInstance();

    public ConverterDialog(VirtualFile currentFile, Project project) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    public void convertOneLayoutXmlFile(VirtualFile currentFile){
        convert(currentFile);
        Messages.showMessageDialog(LanguagesFactory.createText(configuration.languageIndex).getTipsGenerateSuccess(), PLUGINS_NAME, Messages.getInformationIcon());
    }

    public void convertLayoutFolder(VirtualFile currentFile){
        VirtualFile[] children = currentFile.getChildren();
        for (VirtualFile child : children) {
            convert(child);
        }
        Messages.showMessageDialog(LanguagesFactory.createText(configuration.languageIndex).getTipsGenerateSuccess(), PLUGINS_NAME, Messages.getInformationIcon());
    }

    private void convert(VirtualFile child) {
        try {
            Document coverDocument = new SAXReader().read(new File(Objects.requireNonNull(child.getCanonicalPath())));
            ArrayList<Element> root = new ArrayList<>();
            root.add(coverDocument.getRootElement());
            cycleConvert(root);
            OutputFormat format = new OutputFormat();
            XMLWriter xmlWriter = new XMLWriter(new FileWriter(new File(child.getCanonicalPath())),format);
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
            for (Attribute attribute : element.attributes()) {
                String attributeValue = attribute.getValue();
                System.out.println("name" + attribute.getName());
                System.out.println("value:" + attribute.getValue());
                if (attributeValue.endsWith("dp")) {
                    attribute.setText("@dimen/length_" + attributeValue.substring(0, attributeValue.length() - 2));
                } else if (attributeValue.endsWith("sp")) {
                    attribute.setText("@dimen/font_" + attributeValue.substring(0, attributeValue.length() - 2));
                } else if (attributeValue.endsWith("dip")) {
                    attribute.setText("@dimen/length_" + attributeValue.substring(0, attributeValue.length() - 3));
                } else if (attributeValue.endsWith("px")) {
                    attribute.setText("@dimen/length_" + attributeValue.substring(0, attributeValue.length() - 2));
                }
                cycleConvert(element.elements());
            }
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
