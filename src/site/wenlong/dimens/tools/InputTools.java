package site.wenlong.dimens.tools;

import java.util.ArrayList;

public class InputTools {
    public static int getInt(String inputString, boolean isSelected, int defaultNumber) throws NumberFormatException {
        return isSelected ? Integer.valueOf(inputString) : defaultNumber;
    }

    public static float getFloat(String inputString, boolean isSelected, float defaultNumber) throws NumberFormatException {
        return isSelected ? Float.valueOf(inputString) : defaultNumber;
    }

    public static String getString(String inputString, boolean isSelected, String defaultString) throws NumberFormatException {
        return isSelected ? String.valueOf(inputString) : defaultString;
    }

    public static float getFloat(String inputString) throws NumberFormatException {
        return Float.valueOf(inputString);
    }

    public static ArrayList<Float> getMultipleNumber(String inputString) throws NumberFormatException {
        String[] split = inputString.split(",");
        ArrayList<Float> floats = new ArrayList<>();
        for (String num : split) {
            floats.add(Float.valueOf(num));
        }
        return floats;
    }
}