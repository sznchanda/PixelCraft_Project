package pixelcraft;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Saturation extends Converter {

    private final double saturationScale;

    // No-argument constructor with a default saturation scale
    public Saturation() {
        this.saturationScale = 1.2; // Default increase of 20% saturation
    }

    // Constructor with saturation scale parameter
    public Saturation(double saturationScale) {
        this.saturationScale = saturationScale;
    }

    @Override
    protected BufferedImage processImage(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage outputImage = new BufferedImage(width, height, image.getType());

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = image.getRGB(x, y);
                float[] hsbValues = Color.RGBtoHSB((rgb >> 16) & 0xff, (rgb >> 8) & 0xff, rgb & 0xff, null);

                // Increase saturation and ensure it remains within the range [0, 1]
                hsbValues[1] = (float)Math.min(1.0, hsbValues[1] * saturationScale);

                // Convert back to RGB and write the new pixel value
                int newRgb = Color.HSBtoRGB(hsbValues[0], hsbValues[1], hsbValues[2]);
                newRgb = (rgb & 0xff000000) | (newRgb & 0x00ffffff); // Preserve the original alpha channel
                outputImage.setRGB(x, y, newRgb);
            }
        }

        return outputImage;
    }
}
