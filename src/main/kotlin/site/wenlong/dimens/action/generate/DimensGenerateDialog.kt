package site.wenlong.dimens.action.generate

import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import site.wenlong.dimens.base.BaseDialogWrapper
import site.wenlong.dimens.constant.Constant
import java.awt.GridBagLayout
import javax.swing.JComponent
import javax.swing.JPanel

/**
 * 生成dimens文件的对话框
 *
 * @author : 郭文龙
 * @Email  : guowenlong20000@sina.com
 * @date   : 2023/5/6  20:12
 */
class DimensGenerateDialog(currentFile: VirtualFile, project: Project) : BaseDialogWrapper(false) {

    override fun createCenterPanel(): JComponent? {
        return JPanel(GridBagLayout())
    }

    override fun showDialog() {
        setSize(1000, 450)
        show()
    }
}