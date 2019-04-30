package site.wenlong.dimens.core;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@State(name = "Configuration", storages = {@com.intellij.openapi.components.Storage(value = "$APP_CONFIG$/Configuration.xml")})
public class Configuration implements PersistentStateComponent<Configuration> {
    public boolean isCover;
    public boolean isKeepPoint;
    public boolean isMinWidth;
    public boolean isReName;
    public boolean isSingle;
    public boolean isMultiple;
    public String mBit = "2";
    public String mOriginWidth = "360";
    public String mRename = "sw";
    public String mSingle = "400";
    public String mMulitple = "300,320,340,360,380,400,420,440,460,480,500";

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
