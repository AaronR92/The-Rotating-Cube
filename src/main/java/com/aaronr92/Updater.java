package com.aaronr92;

public class Updater extends Thread {

    RotatingCube cube;

    public Updater(RotatingCube cube) {
        this.cube = cube;
    }

    @Override
    public void run() {
        while (true) {
            cube.repaint();
            try {
                Thread.sleep(8);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            cube.decelerate();
        }
    }
}
