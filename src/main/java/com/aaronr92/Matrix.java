package com.aaronr92;

import javax.swing.*;
import java.awt.*;

public class Matrix extends JPanel {

    private final int GRID_SIZE = 20;

    Matrix() {
        this.setPreferredSize(new Dimension(600, 400));
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawLine(0, 0, getWidth(), getHeight());
    }


}
