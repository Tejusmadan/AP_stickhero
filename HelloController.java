package com.example.demo;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.geometry.Point3D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private Button music;

    @FXML
    private Text text1;

    private boolean musicon;

    @FXML
    private Rectangle contdiv1;

    private boolean rotatebool;
    private int ang = 0;
    @FXML
    private Text Score;

    private Image im = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/demo/idle/Untitled-100.png")));
    private Image im1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/demo/idle2/Untitled-100.png")));

    @FXML
    private ImageView spiv = new ImageView(im);
    private HelloApplication a;
    @FXML
    private Rectangle rect2;

    @FXML
    private Rectangle contdiv;

    public void setA(HelloApplication a) {
        this.a = a;
        c1.setA(a);
    }

    @FXML
    private Button contr;


    @FXML
    private Rectangle rotrect;

    private boolean epressed;
    public static boolean gameover;
    TranslateTransition tt = new TranslateTransition();
    int x = 100;

    private Character c1;
    KeyCode lastkey;

    @FXML
    private Button back;
    boolean downreleased;
    boolean upreleased;
    boolean firstcall = true;

    public Character getC1() {
        return c1;
    }

    public void setC1(Character c1) {
        this.c1 = c1;
    }




    Musicfactory musicfactory = new Musicfactory();
    Gamemusic exten = musicfactory.GetSFX("extend");

    Gamemusic swoosh = musicfactory.GetSFX("swoosh");


    @FXML
    void rotate(KeyEvent event) {
        if ((!epressed && event.getCode().equals(KeyCode.SPACE)) || (lastkey == KeyCode.SPACE && event.getCode().equals(KeyCode.SPACE))) {
            if (c1.getCurrentbridge() == null) {
                double x = c1.getCurrentblock().getLayoutX();
                double y = c1.getCurrentblock().getLayoutY();
                Rectangle bridge = new Rectangle(0, 6, Paint.valueOf("BLACK"));
                bridge.setStroke(Paint.valueOf("transparent"));
                bridge.setLayoutX(x + c1.getCurrentblock().getWidth());
                bridge.setLayoutY(y);
                Rotate r = new Rotate();
                r.setPivotX(r.getPivotX());
                r.setPivotY(r.getPivotY() + (bridge.getHeight() / 2));
                System.out.println(bridge.getLayoutX() + "," + bridge.getLayoutY());
                r.setAngle(270);
                bridge.getTransforms().add(r);
                Bridge br = new Bridge();
                br.setBridgerect(bridge);
                c1.setCurrentbridge(br);
                a.getRoot().getChildren().add(c1.getCurrentbridge().getBridgerect());
            } else {
                c1.getCurrentbridge().getBridgerect().setWidth(c1.getCurrentbridge().getBridgerect().getWidth() + 10);
            /*Rotate r = new Rotate();
            r.setPivotX(r.getPivotX());
            r.setPivotY(r.getPivotY()+(c1.getCurrentbridge().getHeight()/2));
            r.setAngle(a1);
            a1 += 4;
            c1.getCurrentbridge().getTransforms().add(r);*/

            }
            exten.playmusic();
            lastkey = KeyCode.SPACE;
            setEpressed(true);
        }else if(lastkey == null){
                lastkey = KeyCode.E;
            }else if(event.getCode().equals(KeyCode.DOWN)){
            if (!c1.isFlipped() && c1.isRunongoing()) {
                swoosh.playmusic();
                Translate flipTranslation = new Translate(0, c1.getSprite().getBoundsInLocal().getHeight());
                Rotate flipRotation = new Rotate(-180, 15, 15);
                c1.getSprite().setScaleX(-1);
                c1.getSprite().setTranslateY(18);
                c1.getSprite().getTransforms().addAll(flipTranslation, flipRotation);
                System.out.println("DOWN");
                lastkey = KeyCode.DOWN;
                downreleased = true;
                c1.setFlipped(true);
            }
        }else if(event.getCode().equals(KeyCode.UP)){
            if (c1.isFlipped() && c1.isRunongoing()) {
                swoosh.playmusic();
                Translate flipTranslation = new Translate(0, c1.getSprite().getBoundsInLocal().getHeight());
                Rotate flipRotation = new Rotate(180, 15, 15);
                c1.getSprite().setScaleX(1);
                c1.getSprite().setTranslateY(0);
                c1.getSprite().getTransforms().addAll(flipTranslation, flipRotation);
                System.out.println("UP");
                lastkey = KeyCode.UP;
                upreleased = true;
                c1.setFlipped(false);
            }
        }
    }
    @FXML
    void stoptrans(KeyEvent event) {
        if (epressed && lastkey.equals(KeyCode.SPACE)) {
            exten.stopmusic();
            lastkey = null;
            if (c1.getCurrentbridge() != null) {
                System.out.println(c1.getCurrentbridge().getBridgerect().getWidth());
                /*currentbridge falls to next block */
                double b1 = c1.getNextblock().getLayoutX();
                double b2 = (c1.getCurrentblock().getLayoutX() + c1.getCurrentblock().getWidth());
                if (c1.getCurrentbridge().getBridgerect().getWidth() >= b1 - b2 && c1.getCurrentbridge().getBridgerect().getWidth() <= (b1 - b2 + c1.getNextblock().getWidth())) {
                    /*bridge is of the right length*/
                    c1.getCurrentbridge().fallscorrect(c1);
                } else if (c1.getCurrentbridge().getBridgerect().getWidth() < b1 - b2) {
                    /*falls short*/
                    c1.getCurrentbridge().fallsshortanim(c1);
                } else {
                    /*bridge too long*/
                    c1.getCurrentbridge().toolong(c1);
                }
        }
        }else if(downreleased){
            lastkey =  null;
            downreleased = false;
            System.out.println("This happens on releasing down");
        }
    }



    @Override
    @FXML
    public void initialize(URL url, ResourceBundle rb){
        musicon = true;
        System.out.println(c1);
        if(c1 == null) {
            spiv.setImage(im1);
            HelloApplication.char1.setSprite(spiv);
            HelloApplication.char1.setFolder("idle2");
            HelloApplication.char1.setRunfolder("running2");
            c1 = HelloApplication.char1;
        }
        System.out.println(c1.getFolder());
        BufferedReader in = null;
        try{
            in = new BufferedReader(new FileReader("src/main/java/com/example/demo/numdiamonds.txt"));
            c1.setNumcherries(Integer.parseInt(in.readLine()));
        }catch (Exception e){
        }finally {
            try {
                in.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        getText1().setText(Integer.toString(c1.getNumcherries()));
        c1.setRunongoing(false);
        downreleased = false;
        upreleased = false;
        c1.setCurrentbridge(null);
        Score.setText(String.valueOf(c1.getScore()));
        /*
        tt.setDuration(Duration.seconds(3));
        tt.setNode(rotrect);
        tt.setToX(x);
        tt.setAutoReverse(true);
        tt.setCycleCount(TranslateTransition.INDEFINITE);
        tt.play();
         */
        setEpressed(false);
        spiv.setImage(im);
        c1.setSprite(spiv);
        System.out.println(c1.getSprite().getTranslateX()+", "+ c1.getSprite().getLayoutX()+", "+ c1.getSprite().getX());
        if(!c1.isFlipped()) {
            c1.getSprite().setTranslateX(10);
            c1.getSprite().setTranslateY(0);
            c1.getSprite().setLayoutX(53);
            c1.getSprite().setLayoutY(252);
        }
        if(c1.getNextblock() != null && c1.getCurrentblock() != null) {
            if(c1.getCurrentblock() != rotrect) {
                c1.getCurrentblock().setVisible(false);
            }
        }else if(c1.getNextblock() == null){
            Rectangle rect = new Rectangle();
            rect = HelloApplication.rect;
            Random ran = new Random();
            int start = ran.nextInt(430);
            start+=120;
            int len = ran.nextInt(560-start);
            len +=40;
            rect.setLayoutX(start);
            rect.setLayoutY(300);
            rect.setWidth(len);
            rect.toFront();
            rect.setHeight(100);
            c1.setNextblock(rect);
        }
        rotrect.setTranslateX(0);
        rotrect.setLayoutX(0);
        c1.setCurrentblock(rotrect);
        if(c1.isFlipped()){
            Translate flipTranslation = new Translate(0, c1.getSprite().getBoundsInLocal().getHeight());
            Rotate flipRotation = new Rotate(180, 15, 15);
            c1.getSprite().setScaleX(1);
            c1.getSprite().setTranslateY(0);
            c1.getSprite().setTranslateX(10);
            c1.getSprite().getTransforms().addAll(flipTranslation, flipRotation);
            System.out.println("X: "+c1.getSprite().getX()+"layoutX: "+c1.getSprite().getLayoutX());
            c1.setFlipped(false);
        }
        c1.idleanimation();

    }

    @FXML
    void stopmusic(MouseEvent event) {
        if(musicon) {
            c1.getBgm().stopmusic();
            musicon =false;
            music.setId("nut1");
            contdiv1.setWidth(contdiv1.getWidth()-5);
        }else {
            c1.getBgm().playmusic();
            musicon = true;
            music.setId("butt1");
            contdiv1.setWidth(contdiv1.getWidth()+5);

        }

    }


    @FXML
    void back(MouseEvent event) {
        Homecontroller.ap.setOpacity(1);
        Scene newsc = HelloApplication.homescene;
        Stage currstg = (Stage) c1.getSprite().getScene().getWindow();
        currstg.setScene(newsc);
    }

    public boolean isEpressed() {
        return epressed;
    }

    public void setEpressed(boolean epressed) {
        this.epressed = epressed;
    }

    public Text getScore() {
        return Score;
    }

    public void setScore(Text score) {
        Score = score;
    }

    public Text getText1() {
        return text1;
    }

    public void setText1(Text text1) {
        this.text1 = text1;
    }

    public boolean isMusicon() {
        return musicon;
    }

    public void setMusicon(boolean musicon) {
        this.musicon = musicon;
    }

    public int getAng() {
        return ang;
    }

    public void setAng(int ang) {
        this.ang = ang;
    }
}
