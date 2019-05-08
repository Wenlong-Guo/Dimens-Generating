package site.wenlong.dimens.tools;

import site.wenlong.dimens.exception.InputEmptyException;
import site.wenlong.dimens.exception.InputZeroException;

import java.util.ArrayList;

public class InputTools {
    public static int getInt(String inputString, boolean isSelected, int defaultNumber) throws NumberFormatException, InputEmptyException {
        if (null == inputString || "".equals(inputString)) throw new InputEmptyException();
        return isSelected ? Integer.valueOf(inputString) : defaultNumber;
    }

    public static float getFloat(String inputString, boolean isSelected, float defaultNumber) throws NumberFormatException, InputEmptyException, InputZeroException {
        if (null == inputString || "".equals(inputString)) throw new InputEmptyException();
        float input = isSelected ? Float.valueOf(inputString) : defaultNumber;
        if (input == 0F) throw new InputZeroException();
        return input;
    }

    public static String getString(String inputString, boolean isSelected, String defaultString) throws NumberFormatException {
        return isSelected ? inputString : defaultString;
    }

    public static float getFloat(String inputString) throws NumberFormatException, InputEmptyException, InputZeroException {
        if (null == inputString || "".equals(inputString)) throw new InputEmptyException();
        Float input = Float.valueOf(inputString);
        if (input.equals(0F)) throw new InputZeroException();
        return input;
    }

    public static ArrayList<Float> getMultipleNumber(String inputString) throws NumberFormatException, InputEmptyException, InputZeroException {
        if (null == inputString || "".equals(inputString)) throw new InputEmptyException();
        String[] split = inputString.split(",");
        ArrayList<Float> floats = new ArrayList<>();
        for (String num : split) {
            Float input = Float.valueOf(num);
            if (input.equals(0F)) throw new InputZeroException();
            floats.add(input);
        }
        return floats;
    }
}