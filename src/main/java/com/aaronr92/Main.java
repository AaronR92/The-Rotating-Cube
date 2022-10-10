package com.aaronr92;

import javax.swing.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("The Matrix");

        RotatingCube cube = new RotatingCube();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(cube);
        frame.pack();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        cube.updateCube();
    }
}