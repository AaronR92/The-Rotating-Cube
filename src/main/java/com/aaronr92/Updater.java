package com.aaronr92;

public class Updater extends Thread {

    RotatingCube panel;

    public Updater(RotatingCube panel) {
        this.panel = panel;
    }

    @Override
    public void run() {
        int i = 0;
        while (i < 60) {
            panel.paint(panel.getGraphics());
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            i++;

            if (panel.yRot != 0) {
                if (panel.yRot < 0)
                    panel.yRot += 0.00002f;
                if (panel.yRot > 0)
                    panel.yRot -= 0.00002f;
            } if (panel.xRot != 0) {
                if (panel.xRot < 0)
                    panel.xRot += 0.00002f;
                if (panel.xRot > 0)
                    panel.xRot -= 0.00002f;
            }
        }
    }
}
