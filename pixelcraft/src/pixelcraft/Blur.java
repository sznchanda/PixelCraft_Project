package pixelcraft;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Blur extends Converter {

    // Define a 9x9 kernel for a very strong blur effect
    private static final int KERNEL_SIZE = 9;
    private static final int KERNEL_AREA = KERNEL_SIZE * KERNEL_SIZE;

    @Override
    protected BufferedImage processImage(BufferedImage originalImage) throws IOException {
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        BufferedImage blurredImage = new BufferedImage(width, height, originalImage.getType());

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                blurredImage.setRGB(x, y, computeBlurredPixel(originalImage, x, y));
            }
        }

        return blurredImage;
    }

    private int computeBlurredPixel(BufferedImage image, int x, int y) {
        int redSum = 0, greenSum = 0, blueSum = 0;
        int alpha = (image.getRGB(x, y) >> 24) & 0xff; // Preserve the original alpha channel

        int kernelRadius = KERNEL_SIZE / 2;
        int pixelsInKernel = 0;

        for (int row = -kernelRadius; row <= kernelRadius; row++) {
            for (int col = -kernelRadius; col <= kernelRadius; col++) {
                int currentX = Math.min(Math.max(x + col, 0), image.getWidth() - 1);
                int currentY = Math.min(Math.max(y + row, 0), image.getHeight() - 1);
                int currentPixel = image.getRGB(currentX, currentY);

                redSum += (currentPixel >> 16) & 0xff;
                greenSum += (currentPixel >> 8) & 0xff;
                blueSum += currentPixel & 0xff;
                pixelsInKernel++;
            }
        }

        // Calculate the average values
        int avgRed = redSum / pixelsInKernel;
        int avgGreen = greenSum / pixelsInKernel;
        int avgBlue = blueSum / pixelsInKernel;

        // Construct a new pixel value using the average colors
        return (alpha << 24) | (avgRed << 16) | (avgGreen << 8) | avgBlue;
    }
}
