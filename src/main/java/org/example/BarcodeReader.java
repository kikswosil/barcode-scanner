package org.example;

import com.github.sarxos.webcam.Webcam;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import javax.swing.*;
import java.awt.*;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.Optional;

public class BarcodeReader implements Runnable {
    private final Webcam webcam;
    private final JFrame window;
    private final int WINDOW_HEIGHT = 800, WINDOW_WIDTH = 1200;
    public BarcodeReader() {
        // webcam setup.
        this.webcam = Webcam.getDefault();
        this.webcam.setViewSize(new Dimension(640, 480));
        // window setup.
        WindowFactory windowFactory = new WindowFactory(WINDOW_WIDTH, WINDOW_HEIGHT, "camera view.");
        this.window = windowFactory.createWindow();

    }

    @Override
    public void run() {
        this.webcam.open();
        while(this.window.isShowing()) loop();
        this.webcam.close();
        System.out.println("webcam closed");
    }



    private void loop() {
        BufferedImage image = this.webcam.getImage();
        if(image == null || !this.window.isShowing()) return;
        this.window.getGraphics().drawImage(
                image.getScaledInstance(this.window.getWidth(), this.window.getHeight(), Image.SCALE_SMOOTH),
                0,
                0,
                null
        );
        LuminanceSource source = new BufferedImageLuminanceSource(image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

        // decode the qr code and display the output in the console.
        String result = BitmapDecoder.decode(bitmap).orElse("");
        if(!result.equals("")) System.out.println(result);
    }
}
