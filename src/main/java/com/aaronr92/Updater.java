package com.aaronr92;

public class Updater extends Thread {

    RotatingCube panel;
    public Updater(RotatingCube panel) {
        this.panel = panel;
    }

    @Override
    public void run() {
        while (true) {
            panel.repaint();
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            slowRotation();
        }
    }

    private void slowRotation() {
        if (!panel.isAutoRotMode) {
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
