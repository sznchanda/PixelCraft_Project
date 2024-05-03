package pixelcraft;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Binary extends Converter {

    private final int threshold; // Luminance threshold for binarization

    // No-argument constructor with default threshold
    public Binary() {
        this.threshold = 128; // Default threshold for binary conversion
    }

    // Constructor with threshold parameter
    public Binary(int threshold) {
        this.threshold = threshold;
    }

    @Override
    protected BufferedImage processImage(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage binaryImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = new Color(image.getRGB(x, y));
                int luminance = (int) (0.2126 * color.getRed() + 0.7152 * color.getGreen() + 0.0722 * color.getBlue());
                int binaryValue = (luminance > threshold) ? 255 : 0; // Above threshold, set to white; otherwise, black
                Color binaryColor = new Color(binaryValue, binaryValue, binaryValue);
                binaryImage.setRGB(x, y, binaryColor.getRGB());
            }
        }

        return binaryImage;
    }
}
