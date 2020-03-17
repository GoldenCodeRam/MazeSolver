package com.uptc.prg.maze.resource;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * <b>Image Converter class</b>
 * Utilities for converting and changing the values of the pixels of a certain image.
 *
 * @author Luis Alejandro Quiroga Gómez
 * @since 4/02/2020
 */
public class ImageConverter {
    private static final int COLOR_THRESHOLD = 500;

    /**
     * Changes the color of an image to a gray scale version of it. Given the file input path and an
     * file output path.
     *
     * @param filePathInput  The path to the image to change.
     * @param filePathOutput The path to the gray scaled image.
     * @author Luis Alejandro Quiroga Gómez
     * @author MEMORYNOTFOUND (https://memorynotfound.com/convert-image-grayscale-java/)
     */
    public final void convertToGrayScale(String filePathInput, String filePathOutput) {
        File outputImage;
        File inputImage = new File(filePathInput);
        try {
            BufferedImage image = ImageIO.read(inputImage);
            BufferedImage result = new BufferedImage(
                    image.getWidth(),
                    image.getHeight(),
                    BufferedImage.TYPE_INT_RGB
            );
            this.convertImageRGBtoGrayScale(image, result);
            outputImage = new File(filePathOutput);
            ImageIO.write(result, "png", outputImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Changes the color of an image to a gray scale version of it. Given a certain
     * {@link BufferedImage}.
     *
     * @param imageToConvert The image to convert to gray scale.
     * @return The image converted to gray scale with a certain threshold.
     * @author Luis Alejandro Quiroga Gómez
     * @author MEMORYNOTFOUND (https://memorynotfound.com/convert-image-grayscale-java/)
     */
    public final BufferedImage convertToGrayScale(BufferedImage imageToConvert) {
        BufferedImage result = new BufferedImage(
                imageToConvert.getWidth(),
                imageToConvert.getHeight(),
                BufferedImage.TYPE_INT_RGB
        );
        this.convertImageRGBtoGrayScale(imageToConvert, result);
        return result;
    }

    /*====================================== PRIVATE METHODS =====================================*/

    private void convertImageRGBtoGrayScale(BufferedImage image, BufferedImage result) {
        Color color;
        Color newColor;
        int red;
        int blue;
        int green;
        for (int i = 0; i < image.getWidth(); i++) {
            for (int e = 0; e < image.getHeight(); e++) {
                color = new Color(image.getRGB(i, e));
                red = (int) (color.getRed() * 0.299);
                blue = (int) (color.getBlue() * 0.587);
                green = (int) (color.getGreen() * 0.114);
                newColor = new Color(
                        red + blue + green,
                        red + blue + green,
                        red + blue + green
                );
                newColor = this.colorToBlackOrWhite(newColor);
                result.setRGB(i, e, newColor.getRGB());
            }
        }
    }

    private Color colorToBlackOrWhite(Color color) {
        int colorValue = color.getBlue() + color.getGreen() + color.getRed();
        if (colorValue > ImageConverter.COLOR_THRESHOLD) {
            return Color.WHITE;
        } else {
            return Color.BLACK;
        }
    }
}
