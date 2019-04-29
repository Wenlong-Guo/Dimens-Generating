package site.wenlong.dimens.core;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@State(name = "Configuration", storages = {@com.intellij.openapi.components.Storage(value = "$APP_CONFIG$/Configuration.xml")})
public class Configuration implements PersistentStateComponent<Configuration> {
    private boolean isCover;
    private boolean isKeepPoint;
    private boolean isMinWidth;
    private boolean isReName;
    private boolean isSingle;
    private boolean isMultiple;
    private String mBit = "2";
    private String mOriginWidth = "360";
    private String mRename = "sw";
    private String mSingle = "400";
    private String mMulitple = "300,320,340,360,380,400,420,440,460,480,500";

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
}
