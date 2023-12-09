package com.example.demo;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;

public class fall implements Gamemusic{
    String musicFile4 = "src/main/java/com/example/demo/fall.mp3";     // For example

    Media sound4 = new Media(new File(musicFile4).toURI().toString());
    MediaPlayer mediaPlayer4 = new MediaPlayer(sound4);

    @Override
    public void playmusic() {
        mediaPlayer4.seek(Duration.seconds(0));
        mediaPlayer4.play();
    }

    @Override
    public void stopmusic() {
        mediaPlayer4.stop();
    }
}
