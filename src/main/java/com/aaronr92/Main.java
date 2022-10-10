package com.aaronr92;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("The Matrix");

        Matrix matrix = new Matrix();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(matrix);
        frame.pack();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}