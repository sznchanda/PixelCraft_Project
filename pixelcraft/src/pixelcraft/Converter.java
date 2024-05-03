package pixelcraft;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public abstract class Converter {
    private BufferedImage inputImage;

    public void convert(String inputFileName, String outputFileName) throws IOException {
        File inputFile = new File(inputFileName);
        double saturationFactor = 2.0;  // Adjust this value for stronger or subtler effects
        double contrastFactor = 1.5;  // Increase contrast by 50%
        int threshold = 128;  // Midpoint threshold for binary conversion\
        BufferedImage image = ImageIO.read(inputFile);
        BufferedImage result = processImage(image);
        File outputFile = new File(outputFileName);
        ImageIO.write(result, "PNG", outputFile);
    }

    protected abstract BufferedImage processImage(BufferedImage image) throws IOException;

    public void setArgs(String[] args) {
    }
}
