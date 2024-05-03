package pixelcraft;

import java.awt.image.BufferedImage;

public class Inversion extends Converter {

    @Override
    protected BufferedImage processImage(BufferedImage image) {
        return invertColorsBlock(image, 0, 0, image.getWidth(), image.getHeight());
    }

    // Recursively invert colors in blocks
    private BufferedImage invertColorsBlock(BufferedImage original, int startX, int startY, int width, int height) {
        // Base case, small enough block, process pixel by pixel
        if (width <= 10 || height <= 10) {
            return processBlock(original, startX, startY, width, height);
        }

        // Recursive case, split the block into four and process each recursively
        int midX = startX + width / 2;
        int midY = startY + height / 2;
        int halfWidth = width / 2;
        int halfHeight = height / 2;

        invertColorsBlock(original, startX, startY, halfWidth, halfHeight);
        invertColorsBlock(original, midX, startY, width - halfWidth, halfHeight);
        invertColorsBlock(original, startX, midY, halfWidth, height - halfHeight);
        invertColorsBlock(original, midX, midY, width - halfWidth, height - halfHeight);

        return original;
    }

    // Process smaller blocks of pixels directly
    private BufferedImage processBlock(BufferedImage original, int startX, int startY, int width, int height) {
        for (int y = startY; y < startY + height; y++) {
            for (int x = startX; x < startX + width; x++) {
                int rgba = original.getRGB(x, y);
                int alpha = (rgba >> 24) & 0xFF;
                int red = 255 - ((rgba >> 16) & 0xFF);
                int green = 255 - ((rgba >> 8) & 0xFF);
                int blue = 255 - (rgba & 0xFF);
                rgba = (alpha << 24) | (red << 16) | (green << 8) | blue;
                original.setRGB(x, y, rgba);
            }
        }
        return original;
    }
}
