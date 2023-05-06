package site.wenlong.dimens.base

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import site.wenlong.dimens.storages.Configuration

/**
 * AnAction的基类
 *
 * @author : 郭文龙
 * @Email  : guowenlong20000@sina.com
 * @date   : 2023/5/6  20:22
 */
abstract class BaseAnAction : AnAction() {
    val configuration = Configuration.getInstance()
}