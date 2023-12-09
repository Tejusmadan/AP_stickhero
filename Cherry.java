package com.example.demo;

import javafx.scene.image.ImageView;

public class Cherry {
    private ImageView sprite;
    private boolean ispresent;
    private double start;

    private int initialcherry = 0;

    public void Eatcherry(){
        setIspresent(false);
    }

    public ImageView getSprite() {
        return sprite;
    }

    public void setSprite(ImageView sprite) {
        this.sprite = sprite;
    }

    public boolean isIspresent() {
        return ispresent;
    }

    public void setIspresent(boolean ispresent) {
        this.ispresent = ispresent;
    }

    public double getStart() {
        return start;
    }

    public void setStart(double start) {
        this.start = start;
    }

    public int getInitialcherry() {
        return initialcherry;
    }

    public void setInitialcherry(int initialcherry) {
        this.initialcherry = initialcherry;
    }
}
