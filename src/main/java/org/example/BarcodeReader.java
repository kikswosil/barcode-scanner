package org.example;

import com.github.sarxos.webcam.Webcam;

import javax.swing.*;
import java.awt.*;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

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
        String result = BitmapDecoder.decode(ImageToBitmapConverter.convert(image)).orElse("");
        if(!result.isEmpty()) System.out.println(result);
    }
}
