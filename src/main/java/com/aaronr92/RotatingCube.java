package com.aaronr92;

import javax.swing.*;
import java.awt.*;

import static java.lang.Math.*;

public class RotatingCube extends JPanel {
    private Vec3[] vertices = {
            new Vec3(100, 100, 100),
            new Vec3(200, 100, 100),
            new Vec3(200, 200, 100),
            new Vec3(100, 200, 100),

            new Vec3(100, 100, 200),
            new Vec3(200, 100, 200),
            new Vec3(200, 200, 200),
            new Vec3(100, 200, 200)
    };
    private Vec3 centroid;

    RotatingCube() {
        this.setPreferredSize(new Dimension(800, 600));
        this.setBackground(Color.darkGray);
        this.setFocusable(true);

        centroid = new Vec3(0, 0, 0);

        // Centroid calculation
        for (Vec3 vec3 : vertices) {
            centroid.x += vec3.x;
            centroid.y += vec3.y;
            centroid.z += vec3.z;
        }
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        Graphics2D g = (Graphics2D) graphics;

        // center align
        g.translate(getWidth() / 3, getHeight() / 3);
        g.setColor(Color.WHITE);

        for (Vec3 vec3 : vertices) {
            rotate(vec3, 0.003, 0.002, 0.004);
        }
        for (Vec3 vec3 : vertices) {
            g.drawLine((int) vec3.x, (int) vec3.y, (int) vec3.x, (int) vec3.y);
        }
    }

    private void rotate(Vec3 point, double x, double y, double z) {
        double rad;

        rad = x;
        point.y = cos(rad) * point.y - sin(rad) * point.z;
        point.z = sin(rad) * point.y + cos(rad) * point.z;

        rad = y;
        point.x = cos(rad) * point.x + sin(rad) * point.z;
        point.z = -sin(rad) * point.x + cos(rad) * point.z;

        rad = z;
        point.x = cos(rad) * point.x - sin(rad) * point.y;
        point.y = sin(rad) * point.x + cos(rad) * point.y;

    }

    public void updateCube() {
        while (true) {
            try {
                Thread.sleep(8);
                paint(getGraphics());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
