package com.aaronr92;

import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        JFrame frame = new JFrame("The Cube");

        RotatingCube cube = new RotatingCube();

        frame.setIconImage(new ImageIcon("resources/icon.png").getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(cube);
        frame.pack();
        frame.setResizable(false);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        cube.updateCube();
    }
}