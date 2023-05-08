package site.wenlong.dimens.action.converter

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.vfs.VirtualFile
import site.wenlong.dimens.base.BaseAnAction
import site.wenlong.dimens.constant.Constant
import site.wenlong.dimens.ext.showErrorMessage
import site.wenlong.dimens.languages.LanguagesFactory

/**
 * 转换layout.xml或layout文件夹下所有layout的action
 *
 * @author : 郭文龙
 * @Email  : guowenlong20000@sina.com
 * @date   : 2023/5/6  20:52
 */
class DimensConverter : BaseAnAction() {
    override fun actionPerformed(event: AnActionEvent) {
        val currentFile = event.getData(PlatformDataKeys.VIRTUAL_FILE) ?: throw Exception("plugins compatibility error")
        val project = event.getData(PlatformDataKeys.PROJECT) ?: throw Exception("plugins compatibility error")
        when {
            isLayoutFolder(currentFile) -> {
                DimensConverterDialog(currentFile, project, true).showFrame()
            }

            isLayoutXml(currentFile) -> {
                DimensConverterDialog(currentFile, project, false).showFrame()
            }

            else -> {
                showErrorMessage(LanguagesFactory.createText(configuration.languageIndex).layoutTipsErrorFile)
            }
        }
    }

    /**
     * 是否是layout文件夹
     */
    private fun isLayoutFolder(currentFile: VirtualFile?): Boolean {
        return currentFile != null && currentFile.name == Constant.LAYOUT_FOLDER_NAME
    }

    /**
     * 是否是layout.xml文件
     */
    private fun isLayoutXml(currentFile: VirtualFile?): Boolean {
        return currentFile != null && currentFile.name.endsWith(Constant.LAYOUT_XML_NAME)
    }
}