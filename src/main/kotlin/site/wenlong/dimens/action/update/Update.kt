package site.wenlong.dimens.action.update

import com.intellij.ide.util.PropertiesComponent
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.StartupActivity
import site.wenlong.dimens.ext.INITIAL_VERSION
import site.wenlong.dimens.ext.VERSION_PROPERTY
import site.wenlong.dimens.ext.showMessage
import site.wenlong.dimens.ext.version

/**
 * 更新监查
 *
 * @author : 郭文龙
 * @Email  : guowenlong20000@sina.com
 * @date   : 2023/5/20  1:27
 */
class Update : StartupActivity {

    private val properties: PropertiesComponent by lazy { PropertiesComponent.getInstance() }

    private val lastVersionString by lazy { properties.getValue(VERSION_PROPERTY, INITIAL_VERSION) }

    private val currentVersionString by lazy { version }

    override fun runActivity(project: Project) {
        if (isUpdated()) {
            UpdateBalloon().showBalloonWithLink(
                "<html><body style='padding: 10px'><b><font size='5'>" +
                        "Dimens Generating has benn updated to V$currentVersionString" +
                        "</font></b></body></html>",
                "<html><body style='padding: 10px'><font size='5'>" +
                        "Thank you for downloading Dimens Generating!" +
                        "&nbsp;<br>" +
                        "If you find it helpful, please click the button at the bottom to give me a Star." +
                        "&nbsp;<br>" +
                        "This is the biggest encouragement for me." +
                        "<br>&nbsp;<br>" +
                        "Update Notes : " +
                        "<br>&nbsp;<br>" +
                        updateNotes(currentVersionString) +
                        "<br>" +
                        "</font></body><html>",
                "https://github.com/Wenlong-Guo/Dimens-Generating"
            )
        }
    }

    private fun isUpdated(): Boolean = currentVersionString != lastVersionString

    private fun updateNotes(currentVersionString: String): String {
        VersionEnum.values().find {
            currentVersionString == it.version
        }?.also {
            return it.content
        }
        return VersionEnum.UNKNOWN.content
    }
}