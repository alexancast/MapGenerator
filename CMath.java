import java.awt.*;

public class CMath {

    public static float normalize(float value, float dataLow, float dataHigh, float normalizedLow,
            float normalizedHigh) {
        return ((value - dataLow) / (dataHigh - dataLow)) * (normalizedHigh - normalizedLow) + normalizedLow;
    }

    public static float pointDistance(float x1, float y1, float x2, float y2) {

        double x = Math.pow(x2 - x1, 2);
        double y = Math.pow(y2 - y1, 2);
        return (float) Math.sqrt(x + y);
    }

    public static Color mixColors(Color color1, Color color2, double percent) {
        double inverse_percent = 1.0 - percent;
        int redPart = (int) (color1.getRed() * percent + color2.getRed() * inverse_percent);
        int greenPart = (int) (color1.getGreen() * percent + color2.getGreen() * inverse_percent);
        int bluePart = (int) (color1.getBlue() * percent + color2.getBlue() * inverse_percent);
        return new Color(redPart, greenPart, bluePart);
    }

}
