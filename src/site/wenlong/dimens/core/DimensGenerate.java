package site.wenlong.dimens.core;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import site.wenlong.dimens.form.SettingDialog;
import site.wenlong.dimens.languages.LanguagesFactory;
import site.wenlong.dimens.utils.LegalUtils;

import static site.wenlong.dimens.core.Configuration.PLUGINS_NAME;


/**
 * Dimens生成插件的Action
 *
 * @author : 郭文龙
 * @date : 2019/4/30  23:30
 */
public class DimensGenerate extends AnAction {
    private final Configuration mConfiguration = Configuration.getInstance();

    @Override
    public void actionPerformed(AnActionEvent event) {
        VirtualFile currentFile = event.getData(PlatformDataKeys.VIRTUAL_FILE);
        if (LegalUtils.isDimensFile(currentFile)) {
            Project project = event.getData(PlatformDataKeys.PROJECT);
            SettingDialog setting = new SettingDialog(currentFile, project);
            setting.setSize(1000, 450);
            setting.setLocationRelativeTo(null);
            setting.setVisible(true);
        } else {
            Messages.showMessageDialog(LanguagesFactory.createText(mConfiguration.languageIndex).getTipsErrorFile(), PLUGINS_NAME, Messages.getInformationIcon());
        }
    }
}