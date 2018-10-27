package site.wenlong.dimens;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;

public class DimensGenerate extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        VirtualFile file = e.getData(PlatformDataKeys.VIRTUAL_FILE);
        if (file != null && file.getName().endsWith(".xml")) {
//            Messages.showMessageDialog(file.getPath()+":"+file.getCanonicalPath(), "Dimens Generating Tools", Messages.getInformationIcon());
            SAXReader saxReader = new SAXReader();
            try {
                Document document = saxReader.read(new File(file.getCanonicalPath()));
//                List list = document.selectNodes("*");// 用xpath查找节点
                Element rootElement = document.getRootElement();
                Messages.showMessageDialog(rootElement.getName(), "Dimens Generating Tools", Messages.getInformationIcon());

//                Iterator iter = list.iterator();
//                StringBuffer stringBuffer = new StringBuffer();
//                while (iter.hasNext()) {
//                    Attribute attribute = (Attribute) iter.next();
//                    stringBuffer.append(attribute.getValue());
//                }

            } catch (DocumentException e1) {
                e1.printStackTrace();
            }
        }

    }
}
