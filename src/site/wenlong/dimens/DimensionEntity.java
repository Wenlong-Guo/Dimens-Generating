package site.wenlong.dimens;

import java.text.DecimalFormat;

import static site.wenlong.dimens.Config.DEFAULT_ORIGIN_DIMENS;

public class DimensionEntity {
    public DimensionEntity(float toDimension) {
        this.toDimension = toDimension;
        this.scale = originDimension / toDimension;
        this.folderName = "values-sw" + (int) toDimension + "dp";
    }

    public DimensionEntity(float originDimension, float toDimension) {
        this.originDimension = originDimension;
        this.toDimension = toDimension;
    }

    private float originDimension = DEFAULT_ORIGIN_DIMENS;
    private float toDimension;
    private float scale;
    private String folderName;

    public float getScale() {
        return scale;
    }

    public String getFolderName() {
        return folderName;
    }

    public String calculateDimension(float dimens) {
        return new DecimalFormat("#.00").format(dimens / scale);
    }
}