package site.wenlong.dimens.core;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import site.wenlong.dimens.form.ConverterDialog;
import site.wenlong.dimens.languages.LanguagesFactory;
import site.wenlong.dimens.utils.LegalUtils;

import static site.wenlong.dimens.core.Configuration.PLUGINS_NAME;

/**
 * Dimens转换插件的Action
 *
 * @author : 郭文龙
 * @date : 2020/6/22  17:30
 */
public class DimensConverter extends AnAction {
    private final Configuration mConfiguration = Configuration.getInstance();

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        VirtualFile currentFile = event.getData(PlatformDataKeys.VIRTUAL_FILE);
        Project project = event.getData(PlatformDataKeys.PROJECT);
        ConverterDialog converterDialog = null;
        if (LegalUtils.isLayoutFolder(currentFile)) {
            converterDialog = new ConverterDialog(currentFile, project, true);
        } else if (LegalUtils.isLayoutXml(currentFile)) {
            converterDialog = new ConverterDialog(currentFile, project, false);
        } else {
            Messages.showMessageDialog(LanguagesFactory.createText(mConfiguration.languageIndex).getLayoutTipsErrorFile(), PLUGINS_NAME, Messages.getInformationIcon());
        }
        if (converterDialog != null) {
            converterDialog.setSize(640, 480);
            converterDialog.setLocationRelativeTo(null);
            converterDialog.setVisible(true);
        }
    }
}
