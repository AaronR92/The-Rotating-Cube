package com.aaronr92;

import javax.swing.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("The Cube");

        RotatingCube cube = new RotatingCube();

        frame.setIconImage(new ImageIcon("src/main/resources/icon.png").getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(cube);
        frame.pack();
        frame.setResizable(false);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        cube.updateCube();
    }
}