package site.wenlong.dimens.ui

import java.awt.Font
import javax.swing.JComponent

/**
 * 文本
 *
 * @author : 郭文龙
 * @Email  : guowenlong20000@sina.com
 * @date   : 2023/5/8  14:15
 */

fun JComponent.titleFont() {
    this.font = Font(this.font.name, Font.PLAIN, 18)
}

fun JComponent.secondTitleFont() {
    this.font = Font(this.font.name, Font.PLAIN, 16)
}