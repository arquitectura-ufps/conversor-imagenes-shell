package co.edu.ufps.shell;

import co.edu.ufps.commons.Converter;
import co.edu.ufps.commons.ConverterException;
import co.edu.ufps.commons.ImageFormat;
import co.edu.ufps.core.ImageConverter;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        File image = new File("image");

        Converter converter = new ImageConverter();
        converter.defineFormatImages(ImageFormat.PNG, ImageFormat.JPG);
        converter.source(image);
        converter.setFolder("/");

        try {
            File imageOut = converter.startProcess();
            System.out.println(imageOut.getAbsolutePath());
        } catch (ConverterException e) {
            e.printStackTrace();
        }

    }
}
