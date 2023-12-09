package com.example.demo;

public class Musicfactory {
    public Gamemusic GetSFX(String SFXType){
        if(SFXType == null){
            return null;
        } else if (SFXType.equalsIgnoreCase("bgm")) {
            return new Bgm();
        } else if (SFXType.equalsIgnoreCase("fall")) {
            return new fall();
        } else if (SFXType.equalsIgnoreCase("checkpoint")) {
            return new Checkpoint();
        } else if (SFXType.equalsIgnoreCase("extend")) {
            return new extension();
        } else if (SFXType.equalsIgnoreCase("swoosh")) {
            return new swoosh();
        }
        return null;
    }
}
