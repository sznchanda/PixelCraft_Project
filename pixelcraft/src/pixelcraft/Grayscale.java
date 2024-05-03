package pixelcraft;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Grayscale extends Converter {

    @Override
    protected BufferedImage processImage(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage grayscaleImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color originalColor = new Color(image.getRGB(x, y), true);
                int red = originalColor.getRed();
                int green = originalColor.getGreen();
                int blue = originalColor.getBlue();
                int alpha = originalColor.getAlpha();

                // Calculate luminance in a way that simulates human vision
                int luminance = (int)(0.2126 * red + 0.7152 * green + 0.0722 * blue);
                Color newColor = new Color(luminance, luminance, luminance, alpha);

                grayscaleImage.setRGB(x, y, newColor.getRGB());
            }
        }

        return grayscaleImage;
    }
}
