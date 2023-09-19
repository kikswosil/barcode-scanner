package org.example;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;

import java.util.Optional;

public class BitmapDecoder {
    public static Optional<String> decode(BinaryBitmap bitmap) {
        String result;
        try{
            result = new MultiFormatReader().decode(bitmap).getText();
        } catch (NotFoundException e) {
            result = null;
        }
        return Optional.ofNullable(result);
    }
}
