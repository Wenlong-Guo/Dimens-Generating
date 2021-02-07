package site.wenlong.dimens.notification;

import com.intellij.notification.NotificationDisplayType;
import com.intellij.notification.NotificationGroup;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.DumbAwareAction;
import org.jetbrains.annotations.NotNull;

public class UpdateNotification {
    private static String displayId = "UpdateNotification";

    private static String title = "Dimens Generating";

    private static String content = "" +
            "1. fix #6 \n" +
            "2. 增加LOGO \n" +
            "3. 单个xml转换 \n" +
            "4. 批量文件夹内xml转换 \n" +
            "5. 弹窗引导点赞 \n";

    public static void create() {
        new NotificationGroup(displayId, NotificationDisplayType.STICKY_BALLOON,false)
                .createNotification(title, content, NotificationType.INFORMATION, (notification, hyperlinkEvent) -> {

                })
                .setImportant(true)
                .addAction(new DumbAwareAction(){

                    @Override
                    public boolean isDumbAware() {
                        return false;
                    }

                    @Override
                    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {

                    }
                });
    }
}
