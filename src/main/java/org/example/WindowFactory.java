package org.example;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class WindowFactory {
    private final int width, height;
    private final String title;
    public WindowFactory(int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;
    }

    public JFrame createWindow() {
        JFrame window = new JFrame();
        window.setTitle(title);
        window.setSize(width, height);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {}

            @Override
            public void keyPressed(KeyEvent keyEvent) {}

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                if(keyEvent.getKeyCode() == KeyEvent.VK_ESCAPE){
                    window.dispose();
                }
            }
        });
        return window;
    }
}
