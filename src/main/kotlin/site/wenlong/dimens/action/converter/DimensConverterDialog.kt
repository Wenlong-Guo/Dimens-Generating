package site.wenlong.dimens.action.converter

import com.intellij.openapi.progress.ProgressIndicator
import com.intellij.openapi.progress.ProgressManager
import com.intellij.openapi.progress.Task
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import net.miginfocom.swing.MigLayout
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import site.wenlong.dimens.ext.showErrorMessage
import site.wenlong.dimens.ext.showMessage
import site.wenlong.dimens.languages.LanguagesFactory.createText
import site.wenlong.dimens.storages.Configuration
import site.wenlong.dimens.ui.Colors
import site.wenlong.dimens.ui.secondTitleFont
import site.wenlong.dimens.ui.titleFont
import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.FlowLayout
import java.awt.Panel
import java.awt.event.ItemEvent
import java.io.File
import java.util.*
import javax.swing.*
import javax.swing.event.DocumentEvent
import javax.swing.event.DocumentListener
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult


/**
 * 转换layout.xml或layout文件夹下所有layout的对话框
 *
 * @author : 郭文龙
 * @Email  : guowenlong20000@sina.com
 * @date   : 2023/5/6  20:48
 */
class DimensConverterDialog(
    private val currentFile: VirtualFile,
    private val project: Project,
    private val isFolder: Boolean
) : JFrame() {
    private val configuration = Configuration.getInstance()

    private val languageJComboBox by lazy {
        JComboBox<String>().also {
            it.addItem("English")
            it.addItem("中文")
        }
    }

    private val titleLabel by lazy {
        JLabel(createText(configuration.languageIndex).tipsConvertTitle).also {
            it.isOpaque = true
            it.border = BorderFactory.createEmptyBorder(10, 10, 10, 10)
            it.background = Colors.COLOR_BACKGROUND
            it.titleFont()
        }
    }

    private val language by lazy {
        JLabel(createText(configuration.languageIndex).language).also {
            it.isOpaque = true
            it.border = BorderFactory.createEmptyBorder(10, 10, 10, 10)
            it.background = Colors.COLOR_BACKGROUND
            it.preferredSize = Dimension(100, preferredSize.height)
        }
    }

    private val tips by lazy {
        JLabel(createText(configuration.languageIndex).tipsConvertTip1).also {
            it.isOpaque = true
            it.border = BorderFactory.createEmptyBorder(10, 10, 10, 10)
            it.background = Colors.COLOR_BACKGROUND
        }
    }

    private val convertDp by lazy {
        JLabel(createText(configuration.languageIndex).tipsConvertDP).also {
            it.isOpaque = true
            it.border = BorderFactory.createEmptyBorder(10, 10, 10, 10)
            it.background = Colors.COLOR_BACKGROUND
            it.secondTitleFont()
        }
    }

    private val convertDpTextField by lazy {
        JTextField(configuration.dp).also {
            it.isOpaque = true
            it.border = BorderFactory.createEmptyBorder(5, 10, 5, 10)
            it.preferredSize = Dimension(100, preferredSize.height)
        }
    }

    private val convertDpTips by lazy {
        JLabel(createText(configuration.languageIndex).tipsConvertEP1).also {
            it.isOpaque = true
            it.border = BorderFactory.createEmptyBorder(10, 10, 10, 10)
            it.background = Colors.COLOR_BACKGROUND
        }
    }

    private val convertSp by lazy {
        JLabel(createText(configuration.languageIndex).tipsConvertSP).also {
            it.isOpaque = true
            it.border = BorderFactory.createEmptyBorder(10, 10, 10, 10)
            it.background = Colors.COLOR_BACKGROUND
            it.secondTitleFont()
        }
    }
    private val convertSpTextField by lazy {
        JTextField(configuration.sp).also {
            it.isOpaque = true
            it.border = BorderFactory.createEmptyBorder(5, 10, 5, 10)
            it.preferredSize = Dimension(100, preferredSize.height)
        }
    }

    private val convertSpTips by lazy {
        JLabel(createText(configuration.languageIndex).tipsConvertEP2).also {
            it.isOpaque = true
            it.border = BorderFactory.createEmptyBorder(10, 10, 10, 10)
            it.background = Colors.COLOR_BACKGROUND
        }
    }

    private val converter by lazy {
        JButton(createText(configuration.languageIndex).tipsConvertButton).also {
            it.border = BorderFactory.createEmptyBorder(10, 20, 10, 20)
            it.secondTitleFont()
        }
    }

    init {
        val root = JPanel()
        val migLayout = MigLayout("wrap")
        root.layout = migLayout

        root.add(titleLabel)

        val languagePanel = Panel()
        val languageBoxLayout = BoxLayout(languagePanel, BoxLayout.X_AXIS)
        languagePanel.background = Colors.COLOR_BACKGROUND
        languagePanel.foreground = Colors.COLOR_BACKGROUND
        languagePanel.layout = languageBoxLayout
        languagePanel.add(language)
        languagePanel.add(languageJComboBox)
        root.add(languagePanel)

        root.add(tips)

        root.add(convertDp)
        val dpPanel = JPanel()
        val dpLayout = BoxLayout(dpPanel, BoxLayout.X_AXIS)
        dpPanel.layout = dpLayout
        dpPanel.add(convertDpTextField, BorderLayout.CENTER)
        dpPanel.border = BorderFactory.createEmptyBorder(0, 10, 0, 10)
        dpPanel.add(convertDpTextField)
        dpPanel.add(convertDpTips)
        root.add(dpPanel)

        root.add(convertSp)
        val spPanel = JPanel()
        val spLayout = BoxLayout(spPanel, BoxLayout.X_AXIS)
        spPanel.layout = spLayout
        spPanel.add(convertSpTextField, BorderLayout.CENTER)
        spPanel.border = BorderFactory.createEmptyBorder(0, 10, 0, 10)
        spPanel.add(convertSpTextField)
        spPanel.add(convertSpTips)
        root.add(spPanel)

        val buttonJPanel = JPanel(FlowLayout(FlowLayout.CENTER, 0, 20))
        buttonJPanel.add(converter)
        buttonJPanel.preferredSize = Dimension(600, preferredSize.height)

        root.add(buttonJPanel)

        add(root)

        pack()
        initSaveConfiguration()
        initClick()
        initTextField()
        initData(currentFile, project)
    }

    private fun initSaveConfiguration() {
        addWindowStateListener { configuration.state }
    }

    private fun initTextField() {
        convertDpTextField.document.addDocumentListener(object : DocumentListener {
            override fun insertUpdate(e: DocumentEvent) {
                configuration.dp = convertDpTextField.text.toString()
            }

            override fun removeUpdate(e: DocumentEvent) {
                configuration.dp = convertDpTextField.text.toString()
            }

            override fun changedUpdate(e: DocumentEvent) {}
        })

        convertSpTextField.document.addDocumentListener(object : DocumentListener {
            override fun insertUpdate(e: DocumentEvent) {
                configuration.sp = convertSpTextField.text.toString()
            }

            override fun removeUpdate(e: DocumentEvent) {
                configuration.sp = convertSpTextField.text.toString()
            }

            override fun changedUpdate(e: DocumentEvent) {}
        })
    }

    private fun initClick() {
        languageJComboBox.addItemListener { e: ItemEvent ->
            if (e.stateChange == ItemEvent.SELECTED) {
                configuration.languageIndex = languageJComboBox.selectedIndex
                toggleLanguage()
            }
        }
    }

    fun showFrame() {
        title = createText(configuration.languageIndex).tipsConvertTitle
        setSize(640, 450)
        setLocationRelativeTo(null)
        isVisible = true
    }

    private fun toggleLanguage() {
        title = createText(configuration.languageIndex).tipsConvertTitle
        titleLabel.text = createText(configuration.languageIndex).tipsConvertTitle
        language.text = createText(configuration.languageIndex).language
        tips.text = createText(configuration.languageIndex).tipsConvertTip1
        convertDp.text = createText(configuration.languageIndex).tipsConvertDP
        convertDpTips.text = createText(configuration.languageIndex).tipsConvertEP1
        convertSp.text = createText(configuration.languageIndex).tipsConvertSP
        convertSpTips.text = createText(configuration.languageIndex).tipsConvertEP2
        converter.text = createText(configuration.languageIndex).tipsConvertButton
    }

    private fun initData(currentFile: VirtualFile, project: Project) {
        languageJComboBox.selectedIndex = configuration.languageIndex
        converter.addActionListener {
            if (isFolder) {
                convertLayoutFolder(currentFile, project)
            } else {
                convertOneLayoutXmlFile(currentFile, project)
            }
            dispose()
        }
    }

    private fun convertOneLayoutXmlFile(currentFile: VirtualFile, project: Project) {
        ProgressManager.getInstance()
            .run(object : Task.Backgroundable(project, createText(configuration.languageIndex).converting) {
                override fun run(progressIndicator: ProgressIndicator) {
                    try {
                        convert(currentFile)
                        showMessage(createText(configuration.languageIndex).tipsConvertSuccess)
                    } catch (e: Exception) {
                        showErrorMessage(createText(configuration.languageIndex).tipsConvertFail)
                    }
                }
            })
    }

    private fun convertLayoutFolder(currentFile: VirtualFile, project: Project?) {
        ProgressManager.getInstance()
            .run(object : Task.Backgroundable(project, createText(configuration.languageIndex).converting) {
                override fun run(progressIndicator: ProgressIndicator) {
                    try {
                        val children = currentFile.children
                        for (child in children) {
                            convert(child)
                        }
                        showMessage(createText(configuration.languageIndex).tipsConvertSuccess)
                    } catch (e: Exception) {
                        showErrorMessage(createText(configuration.languageIndex).tipsConvertFail)
                    }
                }
            })
    }

    @Throws(Exception::class)
    private fun convert(child: VirtualFile) {
        val factory = DocumentBuilderFactory.newInstance()
        val builder = factory.newDocumentBuilder()
        val file = File(
            child.canonicalPath ?: throw Exception(createText(configuration.languageIndex).exceptionOfFileError)
        )
        val document = builder.parse(file)
        val root: Element = document.documentElement
        traverseNode(root.childNodes)
        val transformerFactory = TransformerFactory.newInstance()
        val transformer = transformerFactory.newTransformer()
        val source = DOMSource(document)
        val result = StreamResult(file)
        transformer.transform(source, result)
    }

    private fun traverseNode(nodeList: NodeList) {
        for (i in 0 until nodeList.length) {
            val node: Node = nodeList.item(i)
            if (node.nodeType == Element.ELEMENT_NODE) {
                // 处理元素节点
                val element = node as Element
                println("Element name: ${element.nodeName}")
                traverseAttributes(element)

                // 遍历子节点
                if (element.hasChildNodes()) {
                    traverseNode(element.childNodes)
                }
            }
        }
    }

    private fun traverseAttributes(element: Element) {
        for (i in 0 until element.attributes.length) {
            val item = element.attributes.item(i)
            println("Attribute name: ${item.nodeName}")
            println("Attribute value: ${item.nodeValue}")
            when {
                item.nodeValue.endsWith("dip") -> {
                    item.nodeValue =
                        "@dimen/" + configuration.dp + item.nodeValue.substring(0, item.nodeValue.length - 3)
                }

                item.nodeValue.endsWith("dp") -> {
                    item.nodeValue =
                        "@dimen/" + configuration.dp + item.nodeValue.substring(0, item.nodeValue.length - 2)
                }

                item.nodeValue.endsWith("sp") -> {
                    item.nodeValue =
                        "@dimen/" + configuration.sp + item.nodeValue.substring(0, item.nodeValue.length - 2)
                }

                item.nodeValue.endsWith("px") -> {
                    //todo 后续版本完善
                }
            }
        }
    }
}