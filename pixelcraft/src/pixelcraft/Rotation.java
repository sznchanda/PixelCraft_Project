package pixelcraft;

import java.awt.image.BufferedImage;

public class Rotation extends Converter {

    @Override
    protected BufferedImage processImage(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        // Create a new image with swapped dimensions
        BufferedImage rotatedImage = new BufferedImage(height, width, image.getType());

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // Rotate 90 degrees clockwise by reassigning pixel positions
                rotatedImage.setRGB(height - 1 - y, x, image.getRGB(x, y));
            }
        }

        return rotatedImage;
    }
}
