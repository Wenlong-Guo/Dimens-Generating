package site.wenlong.dimens.zold;

public class DimensionEntity {

    public DimensionEntity(float originDimension, float toDimension, String qualifierName) {

        this.scale = originDimension / toDimension;
        this.folderName = "values-" + qualifierName + (int) toDimension + "dp";
    }

    private float scale;
    private String folderName;

    public float getScale() {
        return scale;
    }

    public String getFolderName() {
        return folderName;
    }

    public String calculateDimension(float dimens, int length) {
        return Utils.trimDecimal(dimens / scale, length);
    }
}