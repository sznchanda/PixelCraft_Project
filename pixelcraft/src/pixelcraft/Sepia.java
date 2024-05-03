package pixelcraft;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Sepia extends Converter {
    @Override
    protected BufferedImage processImage(BufferedImage image) throws IOException {
        return applySepia(image, 0, 0, image.getWidth(), image.getHeight());
    }

    private BufferedImage applySepia(BufferedImage image, int x, int y, int width, int height) {
        if (width <= 1 && height <= 1) {
            // Process a single pixel
            processPixel(image, x, y);
            return image;
        }

        // Divide the area into four quadrants and process each recursively
        int midX = width / 2;
        int midY = height / 2;
        if (width > 1 && height > 1) {
            applySepia(image, x, y, midX, midY);
            applySepia(image, x + midX, y, width - midX, midY);
            applySepia(image, x, y + midY, midX, height - midY);
            applySepia(image, x + midX, y + midY, width - midX, height - midY);
        } else if (width > 1) {
            // Process horizontally if only width is greater than 1
            applySepia(image, x, y, midX, height);
            applySepia(image, x + midX, y, width - midX, height);
        } else {
            // Process vertically if only height is greater than 1
            applySepia(image, x, y, width, midY);
            applySepia(image, x, y + midY, width, height - midY);
        }

        return image;
    }

    private void processPixel(BufferedImage image, int x, int y) {
        int rgb = image.getRGB(x, y);
        int alpha = (rgb >> 24) & 0xff;
        int red = (rgb >> 16) & 0xff;
        int green = (rgb >> 8) & 0xff;
        int blue = rgb & 0xff;

        // Calculate new RGB values
        int newRed = (int)(0.393 * red + 0.769 * green + 0.189 * blue);
        int newGreen = (int)(0.349 * red + 0.686 * green + 0.168 * blue);
        int newBlue = (int)(0.272 * red + 0.534 * green + 0.131 * blue);

        // Clamp values to the maximum of 255
        red = Math.min(255, newRed);
        green = Math.min(255, newGreen);
        blue = Math.min(255, newBlue);

        // Set the new pixel value
        int newColor = (alpha << 24) | (red << 16) | (green << 8) | blue;
        image.setRGB(x, y, newColor);
    }
}
