package com.aaronr92;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static java.lang.Math.*;

public class RotatingCube extends JPanel {
    private final Vec3[] vertices = {
            // back
            new Vec3(100, 100, 100),    // top left
            new Vec3(200, 100, 100),    // top right
            new Vec3(200, 200, 100),    // bottom right
            new Vec3(100, 200, 100),    // bottom left

            // front
            new Vec3(100, 100, 200),    // top left
            new Vec3(200, 100, 200),    // top right
            new Vec3(200, 200, 200),    // bottom right
            new Vec3(100, 200, 200)     // bottom left
    };
    private final Edge[] edges = {
            new Edge(0, 4),
            new Edge(1, 5),
            new Edge(2, 6),
            new Edge(3, 7),

            new Edge(0, 1),
            new Edge(1, 2),
            new Edge(2, 3),
            new Edge(3, 0),

            new Edge(4, 5),
            new Edge(5, 6),
            new Edge(6, 7),
            new Edge(7, 4)
    };
    private final Vec3 centroid;
    public float xRot;
    public float yRot;
    public float zRot;
    public boolean isAutoRotMode;
    public boolean isXKeyPressed;
    public boolean isYKeyPressed;
    public boolean isZKeyPressed;

    RotatingCube() {
        this.setPreferredSize(new Dimension(800, 600));
        this.setBackground(Color.darkGray);
        this.setFocusable(true);

        isAutoRotMode = false;
        isXKeyPressed = false;
        isYKeyPressed = false;
        isZKeyPressed = false;

        xRot = 0;
        yRot = 0;
        zRot = 0;

        centroid = new Vec3(0, 0, 0);

        // centroid calculation
        for (Vec3 vec3 : vertices) {
            centroid.x += vec3.x;
            centroid.y += vec3.y;
            centroid.z += vec3.z;
        }

        centroid.x /= vertices.length;
        centroid.y /= vertices.length;
        centroid.z /= vertices.length;

        // action listener
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {

            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                if (keyEvent.getKeyChar() == 'a') {
                    isYKeyPressed = true;
                    yRot = -0.006f;
                }
                if (keyEvent.getKeyChar() == 'd') {
                    isYKeyPressed = true;
                    yRot = 0.006f;
                }
                if (keyEvent.getKeyChar() == 'w') {
                    isXKeyPressed = true;
                    xRot = 0.006f;
                }
                if (keyEvent.getKeyChar() == 's') {
                    isXKeyPressed = true;
                    xRot = -0.006f;
                }
                if (keyEvent.getKeyChar() == 'q') {
                    isZKeyPressed = true;
                    zRot = -0.008f;
                }
                if (keyEvent.getKeyChar() == 'e') {
                    isZKeyPressed = true;
                    zRot = 0.008f;
                }
                if (keyEvent.getKeyChar() == 'r') {
                    isAutoRotMode = !isAutoRotMode;
                    xRot = 0.006f;
                    yRot = 0.004f;
                    zRot = 0.008f;
                }
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                if (keyEvent.getKeyChar() == 'a' ||
                keyEvent.getKeyChar() == 'd') {
                    isYKeyPressed = false;
                }
                if (keyEvent.getKeyChar() == 'w' ||
                keyEvent.getKeyChar() == 's') {
                    isXKeyPressed = false;
                }
                if (keyEvent.getKeyChar() == 'q' ||
                keyEvent.getKeyChar() == 'e') {
                    isZKeyPressed = false;
                }
            }
        });
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
            // center vertices
            vertex.x -= centroid.x;
            vertex.y -= centroid.y;
            vertex.z -= centroid.z;

            rotate(vertex, xRot, yRot, zRot);

            // rollback to actual values
            vertex.x += centroid.x;
            vertex.y += centroid.y;
            vertex.z += centroid.z;
        }

        // drawing edges
        for (Edge edge : edges) {
            g.setStroke(new BasicStroke(2));
            g.setColor(Color.white);

            if (edge.a == 2 && edge.b == 3)
                g.setColor(Color.red);
            if (edge.a == 3 && edge.b == 0)
                g.setColor(Color.green);
            if (edge.a == 3 && edge.b == 7)
                g.setColor(Color.blue);

            g.drawLine((int) vertices[edge.a].x, (int) vertices[edge.a].y,
                    (int) vertices[edge.b].x, (int) vertices[edge.b].y);
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
        Updater updater = new Updater(this);
        updater.start();
    }

    public void decelerate() {
        if (!isAutoRotMode) {
            if (yRot != 0 && !isYKeyPressed) {
                if (yRot < 0)
                    yRot += 0.00004f;
                if (yRot > 0)
                    yRot -= 0.00004f;
            }
            if (xRot != 0 && !isXKeyPressed) {
                if (xRot < 0)
                    xRot += 0.00004f;
                if (xRot > 0)
                    xRot -= 0.00004f;
            }
            if (zRot != 0 && !isZKeyPressed) {
                if (zRot < 0)
                    zRot += 0.00004f;
                if (zRot > 0)
                    zRot -= 0.00004f;
            }
        }
    }
}
