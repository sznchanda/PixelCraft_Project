# PixelCraft: The Image Processor

# Description
In this project, we'll dive into the exciting world of digital image processing by developing a Java-based command-line application named PixelCraft. We will apply and expand our problem solving skills by implementing various image processing techniques. We'll be transforming images through operations that modifies the colour and positions of individual pixels. Let's unleash our creativity and technical skills to craft some pixel magic!

# Learning Objectives
Understand how digital images are represented and how to manipulate pixels to apply various effects.
Implement image processing algorithms with iterative and recursive solutions.
Apply object-oriented programming (OOP) principles in Java: Use classes, inheritance, and polymorphism to design and develop your software.
Document source code to clearly communicate the design and implementation details of the software product.

# Specification
The following sections outline the requirements and the expected deliverables for the PixelCraft project.

# The final product
After building your code successfully, you should be able to run the PixelCraft app with command lines in the terminal as follows (in the project's base folder).

java -cp "path/to/builtClasses" PixelCraft [ConverterName] [input_image.png]

The output image file is automatically named input_file_ConverterName.png. Below is a real concrete example of running a Grayscale Converter on a sample image file named toronto.png.

java -cp "out/production/Project1" PixelCraft Grayscale toronto.png

The output file would be named toronto_Grayscale.png. Below are how the input and output files would look like.

![image](https://github.com/sznchanda/PixelCraft_Project/assets/100498985/cb88fef2-c2e5-4c46-8c2a-3890bb80d932)


# The converters
We will implement a number of various converters to your project. To follow the unified class interface defined in the main file (all converters can be referred to as a Converter), we need to make each concrete converter class (e.g., Grayscale) inherit from the common abstract based class Converter. The base class should implement the common operations shared by all of its subclasses, e.g., reading the input file, naming and writing the output file. The Java class that we use to store and manipulate the image is java.awt.image.BufferedImage.
The default image format for this project is PNG. This should be the format of both the input and output images.

# Our Tasks
Our tasks in this project are to implement a series of converters that perform different image processing operations. We have three basic converters that you are required to implement, and we have created a six (6) more converts of our own choice: Binary, Sepia, Brightness, Sharpening, Inversion and Saturation.

The three required converters are:

Grayscale : Convert the image to grayscale. This is can be achieved by averaging the red, green, and blue values of each pixel.
Rotate: Rotate the image 90 degrees clockwise. Note that counterclockwise rotation will NOT be accepted.
Blur: Apply a simple blur effect to the image. This can be achieved by averaging the colour of the surrounding pixels. Below is an example of the blurred image.

![image](https://github.com/sznchanda/PixelCraft_Project/assets/100498985/444d45e0-f8dd-4ad8-95c1-f031c399e59a)

Note: for different converters to be considered "different", there should be a significant difference in their implementation. For example, two colour-adjusting converts that only differ by the weight in RGB value calculation would not be considered significantly different. 
