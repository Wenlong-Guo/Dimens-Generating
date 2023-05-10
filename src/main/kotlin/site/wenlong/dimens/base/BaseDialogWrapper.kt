package site.wenlong.dimens.base

import com.intellij.openapi.ui.DialogWrapper

/**
 * DialogWrapper的基类
 *
 * @author : 郭文龙
 * @Email  : guowenlong20000@sina.com
 * @date   : 2023/5/6  21:00
 */
abstract class BaseDialogWrapper(canBeParent: Boolean) : DialogWrapper(canBeParent) {
    abstract fun showDialog()
}