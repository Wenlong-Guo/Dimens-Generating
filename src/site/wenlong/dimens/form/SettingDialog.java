package site.wenlong.dimens.form;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import site.wenlong.dimens.core.Configuration;
import site.wenlong.dimens.languages.LanguagesFactory;
import site.wenlong.dimens.languages.Text;
import site.wenlong.dimens.utils.LegalUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;

import static site.wenlong.dimens.zold.Config.PLUGINS_NAME;

/**
 * 插件面板
 *
 * @author : 郭文龙
 * @date : 2019/4/30  23:30
 */
public class SettingDialog extends JFrame {
    private Configuration configuration = Configuration.getInstance();
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
        initJRadioButton();
        initSaveConfiguration();
        initJButton(currentFile, project);
        reStoreConfiguration();

    }

    private void initJButton(VirtualFile currentFile, Project project) {
        int decimal = configuration.mBit;
        if (configuration.isKeepPoint) {
            String decimalBit = etDecimal.getText();
            if (LegalUtils.isNumber(decimalBit)) {
                decimal = Integer.valueOf(decimalBit);
            } else {
                Messages.showMessageDialog(mText.getTipsErrorNumber(), PLUGINS_NAME, Messages.getInformationIcon());
                return;
            }
        }
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