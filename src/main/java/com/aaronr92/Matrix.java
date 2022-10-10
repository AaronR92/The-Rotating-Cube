package com.aaronr92;

import javax.swing.*;
import java.awt.*;

public class Matrix extends JFrame {

    JPanel panel;

    Matrix() {

        panel = new JPanel();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.getContentPane().setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());

        this.add(panel);
        this.pack();

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;

    }


}
