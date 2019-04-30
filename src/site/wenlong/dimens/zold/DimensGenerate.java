package site.wenlong.dimens.zold;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import site.wenlong.dimens.core.Configuration;

import static site.wenlong.dimens.zold.Config.PLUGINS_NAME;

public class DimensGenerate extends AnAction {
    private boolean isCurrentFilePassable(VirtualFile currentFile) {
        return currentFile != null && currentFile.getName().equals(Config.DEFAULT_FILE_NAME);
    }

    @Override
    public void actionPerformed(AnActionEvent event) {
        VirtualFile currentFile = event.getData(PlatformDataKeys.VIRTUAL_FILE);
        // TODO: 2018/10/28 0028  弹窗提示
        if (!isCurrentFilePassable(currentFile)) {
            Messages.showMessageDialog("您选择的文件不是dimens.xml,请重新生成", PLUGINS_NAME, Messages.getInformationIcon());
            return;
        }
        Project project = event.getData(PlatformDataKeys.PROJECT);
        Setting setting = new Setting(currentFile,project);
        setting.setSize(1000, 450);
        setting.setLocationRelativeTo(null);
        setting.setVisible(true);
    }
}