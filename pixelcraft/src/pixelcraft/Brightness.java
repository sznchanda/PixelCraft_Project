package pixelcraft;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Brightness extends Converter {

    private final int brightnessChange; // This will hold the value to increase or decrease the brightness.

    // No-argument constructor with a default brightness change
    public Brightness() {
        this.brightnessChange = 30;  // Default brightness increase
    }

    // Constructor to set a specific brightness level
    public Brightness(int brightnessChange) {
        this.brightnessChange = brightnessChange;
    }

    @Override
    protected BufferedImage processImage(BufferedImage image) throws IOException {
        int width = image.getWidth();
        int height = image.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = image.getRGB(x, y);
                int alpha = (rgb >> 24) & 0xff;
                int red = (rgb >> 16) & 0xff;
                int green = (rgb >> 8) & 0xff;
                int blue = rgb & 0xff;

                // Adjusting the brightness
                red = clamp(red + brightnessChange);
                green = clamp(green + brightnessChange);
                blue = clamp(blue + brightnessChange);

                // Set the new pixel value
                int newRgb = (alpha << 24) | (red << 16) | (green << 8) | blue;
                image.setRGB(x, y, newRgb);
            }
        }
        return image;
    }

    // Helper method to clamp values to the range 0-255
    private int clamp(int value) {
        if (value < 0) return 0;
        if (value > 255) return 255;
        return value;
    }
}
