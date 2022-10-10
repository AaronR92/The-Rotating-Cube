package com.aaronr92;

import javax.swing.*;
import java.awt.*;

import static java.lang.Math.*;

public class RotatingCube extends JPanel {
    private final Vec3[] vertices = {
            new Vec3(100, 100, 100),
            new Vec3(200, 100, 100),
            new Vec3(200, 200, 100),
            new Vec3(100, 200, 100),

            new Vec3(100, 100, 200),
            new Vec3(200, 100, 200),
            new Vec3(200, 200, 200),
            new Vec3(100, 200, 200)
    };
    private final Vec3 centroid;

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

        centroid.x /= vertices.length;
        centroid.y /= vertices.length;
        centroid.z /= vertices.length;
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        Graphics2D g = (Graphics2D) graphics;

        // cam center align
        g.translate(getWidth() / 3.3, getHeight() / 3.8);
        g.setColor(Color.WHITE);

        // rotation
        for (Vec3 vertex : vertices) {
            // center points
            vertex.x -= centroid.x;
            vertex.y -= centroid.y;
            vertex.z -= centroid.z;

            rotate(vertex, 0.003, 0.002, 0.004);

            vertex.x += centroid.x;
            vertex.y += centroid.y;
            vertex.z += centroid.z;
        }

        // drawing
        for (Vec3 vertex : vertices) {
            g.drawLine((int) vertex.x, (int) vertex.y, (int) vertex.x, (int) vertex.y);
        }
    }

    private void rotate(Vec3 vertex, double x, double y, double z) {
        double rad;

        rad = x;
        vertex.y = cos(rad) * vertex.y - sin(rad) * vertex.z;
        vertex.z = sin(rad) * vertex.y + cos(rad) * vertex.z;

        rad = y;
        vertex.x = cos(rad) * vertex.x + sin(rad) * vertex.z;
        vertex.z = -sin(rad) * vertex.x + cos(rad) * vertex.z;

        rad = z;
        vertex.x = cos(rad) * vertex.x - sin(rad) * vertex.y;
        vertex.y = sin(rad) * vertex.x + cos(rad) * vertex.y;

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
