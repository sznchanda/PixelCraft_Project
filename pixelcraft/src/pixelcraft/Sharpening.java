package pixelcraft;

import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

public class Sharpening extends Converter {

    @Override
    protected BufferedImage processImage(BufferedImage image) {
        // Define a sharpening kernel
        float[] sharpenKernel = {
                0, -1,  0,
                -1,  5, -1,
                0, -1,  0
        };
        int kernelWidth = 3;
        int kernelHeight = 3;

        // Create the convolution operation object
        Kernel kernel = new Kernel(kernelWidth, kernelHeight, sharpenKernel);
        ConvolveOp convolveOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);

        // Apply the sharpening filter
        BufferedImage outputImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        convolveOp.filter(image, outputImage);

        return outputImage;
    }
}
