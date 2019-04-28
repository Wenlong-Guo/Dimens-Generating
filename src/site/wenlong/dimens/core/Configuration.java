package site.wenlong.dimens.core;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;



public class Configuration implements PersistentStateComponent<Configuration> {
    private boolean isCover;
    private boolean isKeepPoint;
    private boolean isMinWidth;
    private boolean isReName;
    private boolean isSingle;
    private boolean isMultiple;

    @Nullable
    @Override
    public Configuration getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull Configuration element) {
        XmlSerializerUtil.copyBean(element,this);
    }

    @Override
    public void noStateLoaded() {

    }
}
