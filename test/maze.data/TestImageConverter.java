package maze.data;

import com.uptc.prg.maze.resource.ImageConverter;

public class TestImageConverter {
    public static void main(String[] args) {
        ImageConverter imageConverter = new ImageConverter();
        imageConverter.convertToGrayScale(
                "res/cat.jpg",
                "res/black_cat.png"
        );
    }
}
