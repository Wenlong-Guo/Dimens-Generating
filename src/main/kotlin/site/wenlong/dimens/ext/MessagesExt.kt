package site.wenlong.dimens.ext

import com.intellij.openapi.ui.Messages
import site.wenlong.dimens.constant.Constant

/**
 * Messages扩展
 *
 * @author : 郭文龙
 * @Email  : guowenlong20000@sina.com
 * @date   : 2023/5/6  22:58
 */

fun showErrorMessage(message: String) {
    Messages.showMessageDialog(
        message,
        Constant.PLUGINS_NAME,
        Messages.getErrorIcon()
    )
}

fun showMessage(message: String) {
    Messages.showMessageDialog(
        message,
        Constant.PLUGINS_NAME,
        Messages.getInformationIcon()
    )
}