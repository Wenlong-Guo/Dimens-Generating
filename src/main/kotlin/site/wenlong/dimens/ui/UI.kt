package site.wenlong.dimens.ui

import com.intellij.openapi.util.IconLoader
import com.intellij.ui.components.labels.LinkLabel
import com.intellij.util.IconUtil
import com.intellij.util.ui.JBFont
import com.intellij.util.ui.JBUI
import com.intellij.util.ui.UIUtil
import net.miginfocom.layout.CC
import net.miginfocom.layout.LC
import net.miginfocom.layout.LayoutUtil
import net.miginfocom.swing.MigLayout
import java.awt.Color
import java.awt.image.RGBImageFilter
import javax.swing.Icon
import javax.swing.border.Border

/**
 * UI
 */
object UI {

    // 使用`get() = ...`以保证获得实时`ScaledFont`
    val defaultFont: JBFont get() = JBFont.create(UIUtil.getLabelFont(UIUtil.FontSize.NORMAL))

    data class FontPair(val primary: JBFont, val phonetic: JBFont)

    @JvmStatic
    fun Icon.disabled(): Icon = IconUtil.filterIcon(this, { DisabledFilter() }, null)

    private class DisabledFilter(color: Color = JBUI.CurrentTheme.Label.disabledForeground()) : RGBImageFilter() {
        private val rgb = color.rgb

        override fun filterRGB(x: Int, y: Int, argb: Int): Int {
            return argb and -0x1000000 or (rgb and 0x00ffffff)
        }
    }

    @JvmStatic
    fun getBordersColor(): Color = JBUI.CurrentTheme.Popup.borderColor(true)

    fun <T> LinkLabel<T>.setIcons(baseIcon: Icon) {
        icon = baseIcon
        disabledIcon = IconLoader.getDisabledIcon(baseIcon)
        setHoveringIcon(IconUtil.darker(baseIcon, 3))
    }

    fun migLayout(gapX: String = "0!", gapY: String = "0!", insets: String = "0") =
        MigLayout(LC().fill().gridGap(gapX, gapY).insets(insets))

    fun migLayoutVertical() =
        MigLayout(LC().flowY().fill().gridGap("0!", "0!").insets("0"))

    fun spanX(cells: Int = LayoutUtil.INF): CC = CC().spanX(cells)

    fun fill(): CC = CC().grow().push()
    fun fillX(): CC = CC().growX().pushX()
    fun fillY(): CC = CC().growY().pushY()

    fun wrap(): CC = CC().wrap()

    fun emptyBorder(topAndBottom: Int, leftAndRight: Int) = JBUI.Borders.empty(topAndBottom, leftAndRight)

    fun emptyBorder(offsets: Int) = JBUI.Borders.empty(offsets)

    fun lineAbove(): Border = JBUI.Borders.customLine(getBordersColor(), 1, 0, 0, 0)

    fun lineBelow(): Border = JBUI.Borders.customLine(getBordersColor(), 0, 0, 1, 0)

    fun lineToRight(): Border = JBUI.Borders.customLine(getBordersColor(), 0, 0, 0, 1)

    operator fun Border.plus(external: Border): Border = JBUI.Borders.merge(this, external, true)
}