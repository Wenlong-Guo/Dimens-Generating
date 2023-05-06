package site.wenlong.dimens.action.converter

import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import site.wenlong.dimens.base.BaseDialogWrapper
import java.awt.GridBagLayout
import javax.swing.JComponent
import javax.swing.JPanel


/**
 * 转换layout.xml或layout文件夹下所有layout的对话框
 *
 * @author : 郭文龙
 * @Email  : guowenlong20000@sina.com
 * @date   : 2023/5/6  20:48
 */
class DimensConverterDialog(currentFile: VirtualFile, project: Project, isFolder: Boolean) : BaseDialogWrapper(false) {

    override fun createCenterPanel(): JComponent {
        return JPanel(GridBagLayout())
    }

    override fun showDialog() {
        setSize(640,480)
        show()
    }
}