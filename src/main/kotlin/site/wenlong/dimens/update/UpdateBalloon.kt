package site.wenlong.dimens.update

import com.intellij.ide.util.PropertiesComponent
import com.intellij.openapi.ui.popup.Balloon
import com.intellij.openapi.ui.popup.JBPopupFactory
import com.intellij.ui.awt.RelativePoint
import com.intellij.util.ui.JBUI
import site.wenlong.dimens.ext.VERSION_PROPERTY
import site.wenlong.dimens.ext.version
import java.awt.*
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.net.URI
import javax.swing.BoxLayout
import javax.swing.JLabel
import javax.swing.JPanel


/**
 * 更新提示
 *
 * @author : 郭文龙
 * @Email  : guowenlong20000@sina.com
 * @date   : 2023/5/20  22:53
 */
class UpdateBalloon {

    private val properties: PropertiesComponent by lazy { PropertiesComponent.getInstance() }

    private val currentVersionString by lazy { version }

    var balloon: Balloon? = null

    fun showBalloonWithLink(title: String, message: String, linkUrl: String) {
        val panel = JPanel()
        panel.layout = BoxLayout(panel, BoxLayout.Y_AXIS)
        val titleLabel = JLabel(title)
        val messageLabel = JLabel(message)
        panel.add(titleLabel)
        panel.add(messageLabel)
        val linkLabel = JLabel("<html><body style='padding: 10px'><font size='5' color='#007FFF'><u>Please give me a Star. Thank you. </u></font></body></html>")
        linkLabel.cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
        linkLabel.addMouseListener(object : MouseAdapter() {
            override fun mouseClicked(e: MouseEvent?) {
                try {
                    Desktop.getDesktop().browse(URI(linkUrl))
                    properties.setValue(VERSION_PROPERTY, currentVersionString)
                    balloon?.dispose()
                } catch (ex: Exception) {
                    ex.printStackTrace()
                }
            }
        })
        panel.add(linkLabel)
        balloon = JBPopupFactory.getInstance()
            .createBalloonBuilder(panel)
            .setCloseButtonEnabled(false)
            .setShadow(true)
            .setHideOnAction(false)
            .setContentInsets(JBUI.insets(30))
            .setHideOnClickOutside(false)
            .setHideOnFrameResize(false)
            .createBalloon()
        val point = Point(Toolkit.getDefaultToolkit().screenSize.width - 60, 60)
        val rPoint = RelativePoint(point)
        balloon?.show(rPoint, Balloon.Position.above)
    }
}