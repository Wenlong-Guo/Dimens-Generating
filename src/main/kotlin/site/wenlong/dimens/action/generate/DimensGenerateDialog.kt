package site.wenlong.dimens.action.generate

import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import net.miginfocom.swing.MigLayout
import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import site.wenlong.dimens.constant.Constant
import site.wenlong.dimens.ext.showErrorMessage
import site.wenlong.dimens.ext.showMessage
import site.wenlong.dimens.ext.targetDimension
import site.wenlong.dimens.languages.LanguagesFactory
import site.wenlong.dimens.storages.Configuration
import site.wenlong.dimens.ui.Colors
import site.wenlong.dimens.ui.secondTitleFont
import site.wenlong.dimens.ui.titleFont
import java.awt.Dimension
import java.awt.FlowLayout
import java.awt.Panel
import java.awt.event.ItemEvent
import java.io.File
import javax.swing.*
import javax.swing.event.ChangeEvent
import javax.swing.event.DocumentEvent
import javax.swing.event.DocumentListener
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult
import kotlin.jvm.Throws


/**
 * 生成dimens文件的对话框
 *
 * @author : 郭文龙
 * @Email  : guowenlong20000@sina.com
 * @date   : 2023/5/6  20:12
 */
class DimensGenerateDialog(private val currentFile: VirtualFile, private val project: Project) : JFrame() {

    private val configuration = Configuration.getInstance()

    private val titleLabel by lazy {
        JLabel(LanguagesFactory.createText(configuration.languageIndex).titleOne).also {
            it.isOpaque = true
            it.border = BorderFactory.createEmptyBorder(10, 10, 10, 10)
            it.background = Colors.COLOR_BACKGROUND
            it.titleFont()
        }
    }

    private val modeLabel by lazy {
        JLabel(LanguagesFactory.createText(configuration.languageIndex).titleTwo).also {
            it.isOpaque = true
            it.border = BorderFactory.createEmptyBorder(10, 10, 10, 10)
            it.background = Colors.COLOR_BACKGROUND
            it.titleFont()
        }
    }

    private val languageJComboBox by lazy {
        JComboBox<String>().also {
            it.addItem("English")
            it.addItem("中文")
        }
    }

    private val language by lazy {
        JLabel(LanguagesFactory.createText(configuration.languageIndex).language).also {
            it.isOpaque = true
            it.border = BorderFactory.createEmptyBorder(10, 10, 10, 10)
            it.background = Colors.COLOR_BACKGROUND
            it.preferredSize = Dimension(100, preferredSize.height)
        }
    }

    private val overrideCheckBox by lazy {
        JCheckBox(LanguagesFactory.createText(configuration.languageIndex).cover).also {
            it.border = BorderFactory.createEmptyBorder(10, 10, 10, 10)
            it.background = Colors.COLOR_BACKGROUND
            it.secondTitleFont()
            it.preferredSize = Dimension(500, preferredSize.height)
        }
    }

    private val decimalCheckBox by lazy {
        JCheckBox(LanguagesFactory.createText(configuration.languageIndex).decimal).also {
            it.border = BorderFactory.createEmptyBorder(10, 10, 10, 10)
            it.background = Colors.COLOR_BACKGROUND
            it.secondTitleFont()
            it.preferredSize = Dimension(800, preferredSize.height)
        }
    }

    private val decimalTextField by lazy {
        JTextField(configuration.decimalPlace.toString()).also {
            it.isOpaque = true
            it.border = BorderFactory.createEmptyBorder(5, 10, 5, 10)
            it.preferredSize = Dimension(100, preferredSize.height)
        }
    }

    private val decimalLabel by lazy {
        JLabel(LanguagesFactory.createText(configuration.languageIndex).bit).also {
            it.isOpaque = true
            it.border = BorderFactory.createEmptyBorder(10, 10, 10, 10)
            it.background = Colors.COLOR_BACKGROUND
            it.secondTitleFont()
        }
    }

    private val minWidthCheckBox by lazy {
        JCheckBox(LanguagesFactory.createText(configuration.languageIndex).minWidth).also {
            it.border = BorderFactory.createEmptyBorder(10, 10, 10, 10)
            it.background = Colors.COLOR_BACKGROUND
            it.secondTitleFont()
            it.preferredSize = Dimension(800, preferredSize.height)
        }
    }

    private val minWidthTextField by lazy {
        JTextField(configuration.originWidth.toString()).also {
            it.isOpaque = true
            it.border = BorderFactory.createEmptyBorder(5, 10, 5, 10)
            it.preferredSize = Dimension(100, preferredSize.height)
        }
    }

    private val minWidthLabel by lazy {
        JLabel(Constant.DP).also {
            it.isOpaque = true
            it.border = BorderFactory.createEmptyBorder(10, 10, 10, 10)
            it.background = Colors.COLOR_BACKGROUND
            it.secondTitleFont()
        }
    }

    private val folderCheckBox by lazy {
        JCheckBox(LanguagesFactory.createText(configuration.languageIndex).folder).also {
            it.border = BorderFactory.createEmptyBorder(10, 10, 10, 10)
            it.background = Colors.COLOR_BACKGROUND
            it.secondTitleFont()
            it.preferredSize = Dimension(800, preferredSize.height)
        }
    }

    private val folderTextField by lazy {
        JTextField(configuration.folderName).also {
            it.isOpaque = true
            it.border = BorderFactory.createEmptyBorder(5, 10, 5, 10)
            it.preferredSize = Dimension(100, preferredSize.height)
        }
    }

    private val singleRadioButton by lazy {
        JRadioButton(LanguagesFactory.createText(configuration.languageIndex).single).also {
            it.border = BorderFactory.createEmptyBorder(10, 10, 10, 10)
            it.background = Colors.COLOR_BACKGROUND
            it.secondTitleFont()
            it.preferredSize = Dimension(800, preferredSize.height)
        }
    }

    private val singleTextField by lazy {
        JTextField(configuration.single).also {
            it.isOpaque = true
            it.border = BorderFactory.createEmptyBorder(5, 10, 5, 10)
            it.preferredSize = Dimension(100, preferredSize.height)
        }
    }

    private val singleLabel by lazy {
        JLabel(Constant.DP).also {
            it.isOpaque = true
            it.border = BorderFactory.createEmptyBorder(10, 10, 10, 10)
            it.background = Colors.COLOR_BACKGROUND
            it.secondTitleFont()
        }
    }

    private val multiRadioButton by lazy {
        JRadioButton(LanguagesFactory.createText(configuration.languageIndex).multi).also {
            it.border = BorderFactory.createEmptyBorder(10, 10, 10, 10)
            it.background = Colors.COLOR_BACKGROUND
            it.secondTitleFont()
            it.preferredSize = Dimension(150, preferredSize.height)
        }
    }

    private val multiTextField by lazy {
        JTextField(configuration.multi).also {
            it.isOpaque = true
            it.border = BorderFactory.createEmptyBorder(5, 10, 5, 10)
            it.preferredSize = Dimension(750, preferredSize.height)
        }
    }

    private val multiLabel by lazy {
        JLabel(Constant.DP).also {
            it.isOpaque = true
            it.border = BorderFactory.createEmptyBorder(10, 10, 10, 10)
            it.background = Colors.COLOR_BACKGROUND
            it.secondTitleFont()
        }
    }

    private val generateButton by lazy {
        JButton(LanguagesFactory.createText(configuration.languageIndex).genarate).also {
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

        root.add(overrideCheckBox)

        val decimalPanel = Panel()
        val decimalBoxLayout = BoxLayout(decimalPanel, BoxLayout.X_AXIS)
        decimalPanel.background = Colors.COLOR_BACKGROUND
        decimalPanel.foreground = Colors.COLOR_BACKGROUND
        decimalPanel.layout = decimalBoxLayout
        decimalPanel.add(decimalCheckBox)
        decimalPanel.add(decimalTextField)
        decimalPanel.add(decimalLabel)
        root.add(decimalPanel)

        val minWidthPanel = Panel()
        val minWidthBoxLayout = BoxLayout(minWidthPanel, BoxLayout.X_AXIS)
        minWidthPanel.background = Colors.COLOR_BACKGROUND
        minWidthPanel.foreground = Colors.COLOR_BACKGROUND
        minWidthPanel.layout = minWidthBoxLayout
        minWidthPanel.add(minWidthCheckBox)
        minWidthPanel.add(minWidthTextField)
        minWidthPanel.add(minWidthLabel)
        root.add(minWidthPanel)

        val folderPanel = Panel()
        val folderBoxLayout = BoxLayout(folderPanel, BoxLayout.X_AXIS)
        folderPanel.background = Colors.COLOR_BACKGROUND
        folderPanel.foreground = Colors.COLOR_BACKGROUND
        folderPanel.layout = folderBoxLayout
        folderPanel.add(folderCheckBox)
        folderPanel.add(folderTextField)
        root.add(folderPanel)

        root.add(modeLabel)

        val singlePanel = Panel()
        val singleBoxLayout = BoxLayout(singlePanel, BoxLayout.X_AXIS)
        singlePanel.background = Colors.COLOR_BACKGROUND
        singlePanel.foreground = Colors.COLOR_BACKGROUND
        singlePanel.layout = singleBoxLayout
        singlePanel.add(singleRadioButton)
        singlePanel.add(singleTextField)
        singlePanel.add(singleLabel)
        root.add(singlePanel)

        val multiPanel = Panel()
        val multiBoxLayout = BoxLayout(multiPanel, BoxLayout.X_AXIS)
        multiPanel.background = Colors.COLOR_BACKGROUND
        multiPanel.foreground = Colors.COLOR_BACKGROUND
        multiPanel.layout = multiBoxLayout
        multiPanel.add(multiRadioButton)
        multiPanel.add(multiTextField)
        multiPanel.add(multiLabel)
        root.add(multiPanel)

        val buttonJPanel = JPanel(FlowLayout(FlowLayout.CENTER, 0, 20))
        buttonJPanel.add(generateButton)
        buttonJPanel.preferredSize = Dimension(900, preferredSize.height)
        root.add(buttonJPanel)

        add(root)

        pack()
        initSaveConfiguration()
        reStoreConfiguration()

        initClick()
        initJRadioButton()
        initCheckBox()
        initTextField()
        initData(currentFile, project)
    }

    private fun initCheckBox() {
        overrideCheckBox.addChangeListener { e: ChangeEvent ->
            val jCheckBox = e.source as JCheckBox
            configuration.isOverride = jCheckBox.isSelected
        }
        decimalCheckBox.addChangeListener { e: ChangeEvent ->
            val jCheckBox = e.source as JCheckBox
            configuration.isKeepDecimal = jCheckBox.isSelected
        }
        minWidthCheckBox.addChangeListener { e: ChangeEvent ->
            val jCheckBox = e.source as JCheckBox
            configuration.isMinWidth = jCheckBox.isSelected
        }
        folderCheckBox.addChangeListener { e: ChangeEvent ->
            val jCheckBox = e.source as JCheckBox
            configuration.isReName = jCheckBox.isSelected
        }
    }

    private fun initData(currentFile: VirtualFile, project: Project) {
        generateButton.addActionListener {
            var decimal = 0
            if (decimalTextField.text.isNullOrBlank()) {
                showErrorMessage(LanguagesFactory.createText(configuration.languageIndex).tipsInputEmpty)
                return@addActionListener
            } else {
                decimal = if (configuration.isKeepDecimal) decimalTextField.text.toInt() else 0
            }

            var folderName = if (configuration.isReName) {
                folderTextField.text
            } else {
                Constant.LAYOUT_FOLDER_NAME
            }

            var minWidth = 0f
            when {
                minWidthTextField.text.isNullOrBlank() -> {
                    showErrorMessage(LanguagesFactory.createText(configuration.languageIndex).tipsInputEmpty)
                    return@addActionListener
                }

                minWidthTextField.text == "0" -> {
                    showErrorMessage(LanguagesFactory.createText(configuration.languageIndex).tipsInputZero)
                    return@addActionListener
                }

                else -> {
                    minWidth = if (configuration.isMinWidth) minWidthTextField.text.toFloat() else 360f
                }
            }

            if (multiTextField.text.isNullOrBlank()) {
                showErrorMessage(LanguagesFactory.createText(configuration.languageIndex).tipsInputEmpty)
                return@addActionListener
            }
            val split = multiTextField.text.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val targets = split.map {
                if (it.trim() == "0") {
                    showErrorMessage(LanguagesFactory.createText(configuration.languageIndex).tipsInputZero)
                    return@addActionListener
                }
                it.toFloat()
            }

            val target: Float
            if (singleTextField.text.isNullOrBlank()) {
                showErrorMessage(LanguagesFactory.createText(configuration.languageIndex).tipsInputEmpty)
                return@addActionListener
            } else if (singleTextField.text == "0") {
                showErrorMessage(LanguagesFactory.createText(configuration.languageIndex).tipsInputZero)
                return@addActionListener
            } else {
                target = singleTextField.text.toFloat()
            }
            try {
                when {
                    configuration.isSingle -> {
                        generateSingle(
                            currentFile,
                            configuration.isOverride,
                            decimal,
                            minWidth,
                            folderName,
                            target
                        )
                    }

                    else -> {
                        generateMulti(
                            currentFile,
                            configuration.isOverride,
                            decimal,
                            minWidth,
                            folderName,
                            targets
                        )
                    }
                }
                showMessage(LanguagesFactory.createText(configuration.languageIndex).tipsGenerateSuccess)
            } catch (e: Exception) {
                showErrorMessage(e.message ?: LanguagesFactory.createText(configuration.languageIndex).tipsGenerateFail)
            }
            currentFile.refresh(false,true)
            dispose()
        }
    }

    private fun generateMulti(
        currentFile: VirtualFile,
        isOverride: Boolean,
        decimal: Int,
        originDimens: Float,
        folderName: String,
        targetDimens: List<Float>
    ) {
        for (target in targetDimens) {
            generateSingle(currentFile, isOverride, decimal, originDimens, folderName, target)
        }
    }

    @Throws(Exception::class)
    private fun generateSingle(
        currentFile: VirtualFile,
        isOverride: Boolean,
        decimal: Int,
        originDimens: Float,
        folderName: String,
        targetDimens: Float
    ) {
        val factory = DocumentBuilderFactory.newInstance()
        val builder = factory.newDocumentBuilder()
        val file = File(
            currentFile.canonicalPath
                ?: throw Exception(LanguagesFactory.createText(configuration.languageIndex).exceptionOfFileError)
        )
        val document = builder.parse(file)
        val scale = originDimens / targetDimens
        println("scale: $scale")
        transformerDimens(document.getElementsByTagName("dimen"), decimal, scale)
        generateDimens(
            document, currentFile.parent.parent.canonicalPath ?: throw Exception(
                LanguagesFactory.createText(
                    configuration.languageIndex
                ).exceptionOfFileError
            ), "values-" + folderName + targetDimens.toInt() + "dp", isOverride
        )
    }

    private fun transformerDimens(nodeList: NodeList, decimal: Int, scale: Float) {
        for (i in 0 until nodeList.length) {
            val item = nodeList.item(i) as Element
            println("Attribute name: ${item.nodeName}")
            println("Attribute value: ${item.nodeValue}")
            when {
                item.textContent.endsWith("dip") -> {
                    val itemValue = item.textContent.substring(0, item.textContent.length - 3).toFloat()
                    item.textContent = targetDimension(itemValue, decimal, scale) + "dp"
                }

                item.textContent.endsWith("dp") -> {
                    val itemValue = item.textContent.substring(0, item.textContent.length - 2).toFloat()
                    item.textContent = targetDimension(itemValue, decimal, scale) + "dp"
                }

                item.textContent.endsWith("sp") -> {
                    val itemValue = item.textContent.substring(0, item.textContent.length - 2).toFloat()
                    item.textContent = targetDimension(itemValue, decimal, scale) + "sp"
                }

                item.textContent.endsWith("px") -> {
                    //todo 后续版本完善
                }
            }
            println("textContent: ${item.textContent}")
        }
    }

    @Throws(Exception::class)
    private fun generateDimens(document: Document, parentPath: String, folderName: String, isOverride: Boolean) {
        val folderPath = parentPath + File.separator + folderName
        val folderFile = File(folderPath)
        if (!folderFile.exists() && !folderFile.mkdir()) {
            throw Exception(
                LanguagesFactory.createText(
                    configuration.languageIndex
                ).tipsCreateFileFailed
            )
        }
        val dimensFile = File(folderPath + File.separator + Constant.DIMENS_XML_NAME)
        if (!isOverride && dimensFile.exists()) {
            throw Exception(
                String.format(
                    LanguagesFactory.createText(
                        configuration.languageIndex
                    ).tipsDimensExists, folderName
                )
            )
        }
        if (!dimensFile.delete()) {
            println("删除文件失败失败:" + dimensFile.name)
        }
        val transformerFactory = TransformerFactory.newInstance()
        val transformer = transformerFactory.newTransformer()
        val source = DOMSource(document)
        val result = StreamResult(dimensFile)
        transformer.transform(source, result)
    }

    private fun initTextField() {
        decimalTextField.document.addDocumentListener(object : DocumentListener {
            override fun insertUpdate(e: DocumentEvent) {
                configuration.decimalPlace = decimalTextField.text.toInt()
            }

            override fun removeUpdate(e: DocumentEvent) {
                configuration.decimalPlace = decimalTextField.text.toInt()
            }

            override fun changedUpdate(e: DocumentEvent) {}
        })
        minWidthTextField.document.addDocumentListener(object : DocumentListener {
            override fun insertUpdate(e: DocumentEvent) {
                configuration.originWidth = minWidthTextField.text.toFloat()
            }

            override fun removeUpdate(e: DocumentEvent) {
                configuration.originWidth = minWidthTextField.text.toFloat()
            }

            override fun changedUpdate(e: DocumentEvent) {}
        })
        folderTextField.document.addDocumentListener(object : DocumentListener {
            override fun insertUpdate(e: DocumentEvent) {
                configuration.folderName = folderTextField.text.toString()
            }

            override fun removeUpdate(e: DocumentEvent) {
                configuration.folderName = folderTextField.text.toString()
            }

            override fun changedUpdate(e: DocumentEvent) {}
        })
        singleTextField.document.addDocumentListener(object : DocumentListener {
            override fun insertUpdate(e: DocumentEvent) {
                configuration.single = singleTextField.text.toString()
            }

            override fun removeUpdate(e: DocumentEvent) {
                configuration.single = singleTextField.text.toString()
            }

            override fun changedUpdate(e: DocumentEvent) {}
        })
        multiTextField.document.addDocumentListener(object : DocumentListener {
            override fun insertUpdate(e: DocumentEvent) {
                configuration.multi = multiTextField.text.toString()
            }

            override fun removeUpdate(e: DocumentEvent) {
                configuration.multi = multiTextField.text.toString()
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

    private fun toggleLanguage() {
        title = LanguagesFactory.createText(configuration.languageIndex).titleOne
        titleLabel.text = LanguagesFactory.createText(configuration.languageIndex).titleOne
        modeLabel.text = LanguagesFactory.createText(configuration.languageIndex).titleTwo
        language.text = LanguagesFactory.createText(configuration.languageIndex).language
        overrideCheckBox.text = LanguagesFactory.createText(configuration.languageIndex).cover
        decimalCheckBox.text = LanguagesFactory.createText(configuration.languageIndex).decimal
        decimalLabel.text = LanguagesFactory.createText(configuration.languageIndex).bit
        minWidthCheckBox.text = LanguagesFactory.createText(configuration.languageIndex).minWidth
        folderCheckBox.text = LanguagesFactory.createText(configuration.languageIndex).folder
        singleRadioButton.text = LanguagesFactory.createText(configuration.languageIndex).single
        multiRadioButton.text = LanguagesFactory.createText(configuration.languageIndex).multi
        generateButton.text = LanguagesFactory.createText(configuration.languageIndex).genarate
    }

    private fun initJRadioButton() {
        val bg = ButtonGroup()
        bg.add(singleRadioButton)
        bg.add(multiRadioButton)
        singleRadioButton.isSelected = configuration.isSingle
        multiRadioButton.isSelected = !configuration.isSingle
        singleRadioButton.addChangeListener {
            configuration.isSingle = singleRadioButton.isSelected
        }
        multiRadioButton.addChangeListener {
            configuration.isSingle = !multiRadioButton.isSelected
        }
    }

    private fun initSaveConfiguration() {
        addWindowStateListener { configuration.state }
    }

    private fun reStoreConfiguration() {
        languageJComboBox.selectedIndex = configuration.languageIndex
        overrideCheckBox.isSelected = configuration.isOverride
        decimalCheckBox.isSelected = configuration.isKeepDecimal
        minWidthCheckBox.isSelected = configuration.isMinWidth
        folderCheckBox.isSelected = configuration.isReName
        decimalTextField.text = configuration.decimalPlace.toString()
        minWidthTextField.text = configuration.originWidth.toString()
        folderTextField.text = configuration.folderName
        singleTextField.text = configuration.single
        multiTextField.text = configuration.multi
        toggleLanguage()
    }

    fun showFrame() {
        title = LanguagesFactory.createText(configuration.languageIndex).titleOne
        setSize(980, 600)
        setLocationRelativeTo(null)
        isVisible = true
    }
}