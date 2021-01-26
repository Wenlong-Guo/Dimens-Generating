package site.wenlong.dimens.core;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * 插件保存数据
 *
 * @author : 郭文龙
 * @date : 2019/4/30  23:30
 */
@State(name = "Configuration", storages = {@com.intellij.openapi.components.Storage(value = "$APP_CONFIG$/Configuration.xml")})
public class Configuration implements PersistentStateComponent<Configuration> {
    public final static String DEFAULT_FILE_NAME = "dimens.xml";
    public final static String DEFAULT_LAYOUT_FOLDER_NAME = "layout";
    public final static String DEFAULT_LAYOUT_XML_NAME = ".xml";
    public final static String PLUGINS_NAME = "Dimens Generating Tools";
    public boolean isCover;
    public boolean isKeepPoint;
    public boolean isMinWidth;
    public boolean isReName;
    public boolean isSingle;
    public int mBit = 2;
    public float mOriginWidth = 360F;
    public String mRename = "sw";
    public String mSingle = "400";
    public String mMulitple = "300,320,340,360,380,400,420,440,460,480,500";
    public int languageIndex = 0;
    public String mDP = "length";
    public String mSP = "font";
    @Nullable
    @Override
    public Configuration getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull Configuration element) {
        XmlSerializerUtil.copyBean(element, this);
    }

    @Override
    public void noStateLoaded() {

    }

    public static Configuration getInstance() {
        return ServiceManager.getService(Configuration.class);
    }
}