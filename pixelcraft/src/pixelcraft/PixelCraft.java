package pixelcraft;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
public class PixelCraft {

    /**
     * Append converter name to the input filename, before the file extension.
     * For example, if the input filename is "image.png" and the converter name is "GrayScale",
     * the output filename will be "image_GrayScale.png".
     */
    static String getOutputFilename(String inputFileName, String converterName) {
        int dotIndex = inputFileName.lastIndexOf(".");
        if (dotIndex == -1) {
            return inputFileName + "_" + converterName;  // Handle files without an extension
        }
        return inputFileName.substring(0, dotIndex) + "_" + converterName + inputFileName.substring(dotIndex);
    }

    public static void main(String[] args) {
        // Ensure that a converter name and a filename have been provided
        if (args.length < 2) {
            System.out.println("Usage: java -cp \"out/production\" pixelcraft.PixelCraft <ConverterName> <image_file.png>");
            System.exit(1);
        }

        String converterName = args[0];
        String inputFileName = args[1];
        String outputFileName = getOutputFilename(inputFileName, converterName);

        try {
            // Append package name to converter name to form the fully qualified class name
            String className = "pixelcraft." + converterName;
            Class<?> clazz = Class.forName(className);
            Converter converter = (Converter) clazz.getDeclaredConstructor().newInstance();
            converter.convert(inputFileName, outputFileName);

            System.out.println("Conversion successful. Output file is " + outputFileName);
        } catch (ClassNotFoundException e) {
            System.out.println("Converter class not found: " + e.getMessage());
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            System.out.println("Error creating converter instance: " + e.getMessage());
            e.printStackTrace(); // Provide stack trace for detailed error analysis
        } catch (IOException e) {
            System.out.println("IO Error: " + e.getMessage());
        }
    }
}
