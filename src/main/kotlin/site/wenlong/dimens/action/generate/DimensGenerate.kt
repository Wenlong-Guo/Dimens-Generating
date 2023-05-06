package site.wenlong.dimens.action.generate

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.vfs.VirtualFile
import site.wenlong.dimens.base.BaseAnAction
import site.wenlong.dimens.constant.Constant
import site.wenlong.dimens.ext.showErrorMessage
import site.wenlong.dimens.languages.LanguagesFactory.createText

/**
 * 生成dimens文件
 *
 * @author : 郭文龙
 * @Email  : guowenlong20000@sina.com
 * @date   : 2023/5/7  0:58
 */
class DimensGenerate : BaseAnAction() {
    override fun actionPerformed(event: AnActionEvent) {
        val currentFile = event.getData(PlatformDataKeys.VIRTUAL_FILE) ?: throw Exception("plugins compatibility error")
        val project = event.getData(PlatformDataKeys.PROJECT) ?: throw Exception("plugins compatibility error")
        when {
            isDimensFile(currentFile) -> {
                DimensGenerateDialog(currentFile, project).showDialog()
            }

            else -> {
                showErrorMessage(createText(configuration.languageIndex).tipsErrorFile)
            }
        }
    }

    private fun isDimensFile(currentFile: VirtualFile): Boolean {
        return currentFile.name == Constant.DIMENS_XML_NAME
    }

}