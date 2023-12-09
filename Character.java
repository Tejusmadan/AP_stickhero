package com.example.demo;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Character {
    private ImageView sprite;
    private HelloApplication a;
    private Rectangle currentblock;
    private boolean flipped;
    private String runfolder;
    private Rectangle nextblock;
    private Bridge currentbridge;
    static Scene gameoverscreen;
    static Bridge prevbridge = null;
    private Cherry currcherry;
    private int numcherries;
    private boolean downpressed = false;
    private int score;
    private String folder;
    private AtomicBoolean fircherr = new AtomicBoolean(true);
    private ArrayList<Integer> Scoreslst = new ArrayList<>();



    private ArrayList<Image> images = new ArrayList<Image>();
    private boolean runongoing;



    public ImageView getSprite() {
        return sprite;
    }

    public void setSprite(ImageView sprite) {
        this.sprite = sprite;
    }
    private Musicfactory musicfactory = new Musicfactory();
    private Gamemusic bgm = musicfactory.GetSFX("bgm");
    private Gamemusic fall = musicfactory.GetSFX("fall");

    private Gamemusic check = musicfactory.GetSFX("checkpoint");

    private static Character instance;

    private Character(){

    }

    /*usage of the singleton design pattern*/
    public static Character getInstance() {
        if (instance == null) {
            instance = new Character();
        }
        return instance;
    }
    public void idleanimation(){
        System.out.println("Idle animation begins");
        System.out.println(nextblock);
        if (HelloApplication.timeLine1 != null && HelloApplication.t1 != null) {
            System.out.println("Stopping previous animations");
            HelloApplication.timeLine1.stop();
            HelloApplication.timeLine1.getKeyFrames().clear(); // Clear existing keyframes
            images.clear();
        }
        bgm.playmusic();
        for(int i = 100;i<116;i++){
            Image newimg = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/demo/"+folder+"/Untitled-"+i+".png")));
            images.add(newimg);
        }
        Collection<KeyFrame> frames = HelloApplication.timeLine1.getKeyFrames();
        Duration frameGap = Duration.millis(180);
        Duration frameTime = Duration.millis(0.2) ;
        for (Image img : images) {
            frameTime = frameTime.add(frameGap);
            frames.add(new KeyFrame(frameTime, e -> sprite.setImage(img)));
        }
        HelloApplication.timeLine1.setAutoReverse(true);
        HelloApplication.timeLine1.setCycleCount(Timeline.INDEFINITE);
        HelloApplication.timeLine1.play();
    }

    public void run(double tox, boolean tofall){
        fircherr = new AtomicBoolean(true);
        System.out.println("tx: "+sprite.getTranslateX());
        setRunongoing(true);
        ArrayList<Image> images1 = new ArrayList<Image>();
        TranslateTransition tt = new TranslateTransition();
        tt.setNode(sprite);
        tt.setToX(tox-50);
        tt.setDuration(Duration.seconds(2));
        final boolean[] first = {false};
        sprite.translateXProperty().addListener((observable, oldValue, newValue) -> {
            double currentX = newValue.doubleValue();
            // Check if the object has reached the target x-coordinate
            if (!first[0] && currentX >= nextblock.getLayoutX() + nextblock.getTranslateX() - 105) {
                    first[0] = true;
                    if(isFlipped()) {
                        fall();
                    }else {
                        setRunongoing(false);
                    }
            }
            if(!(currcherry == null)) {
                if (currcherry.isIspresent()) {
                    double layx = currcherry.getSprite().getLayoutX() + currcherry.getSprite().getTranslateX();
                    if (currentX >= layx - 105 && currentX <= layx - 70) {
                        if (isFlipped() && fircherr.compareAndSet(true, false)) {
                            currcherry.getSprite().setVisible(false);
                            currcherry.getSprite().setDisable(true);
                            currcherry.setIspresent(false);
                            numcherries += 1;
                            check.playmusic();
                            a.getC().getText1().setText(String.valueOf(numcherries));
                            PrintWriter out= null;
                            try{
                                out = new PrintWriter(new FileWriter("src/main/java/com/example/demo/numdiamonds.txt"));
                                out.println(numcherries);
                            }catch (Exception e){
                            }finally {
                                out.close();
                            }
                        }
                    }
                }
            }
        });

        tt.play();
        for(int i = 10001;i<10013;i++){
            Image newimg = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/demo/"+runfolder+"/Untitled-"+i+".png")));
            images1.add(newimg);
        }
        Timeline timeLine = new Timeline();
        Collection<KeyFrame> frames = timeLine.getKeyFrames();
        Duration frameGap = Duration.millis(45);
        Duration frameTime = Duration.millis(0.05) ;
        if (tofall){
            for(int i = 0; i<3;i++) {
                images1.remove(images1.get(images1.size() - 1));
            }
        }
        for (Image img : images1) {
            frameTime = frameTime.add(frameGap);
            frames.add(new KeyFrame(frameTime, e -> sprite.setImage(img)));
        }
        timeLine.setCycleCount(4);
        timeLine.play();
        if(tofall) {
            timeLine.setOnFinished(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    fall();
                }
            });
        }else {
            timeLine.setOnFinished(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    setRunongoing(false);
                    if(currcherry != null) {
                        currcherry.getSprite().setVisible(false);
                    }
                    Cherry cherry = new Cherry();
                    currcherry = cherry;
                    Image i = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/demo/cherry.png")));
                    ImageView niv = new ImageView(i);
                    cherry.setSprite(niv);
                    cherry.getSprite().setFitWidth(30);
                    cherry.getSprite().setFitHeight(30);
                    Rectangle rect1 = new Rectangle(20,20, Paint.valueOf("BLACK"));
                    Random ran = new Random();
                    cherry.setIspresent(false);
                    int start = ran.nextInt(430);
                    start+=120;
                    System.out.println("start: "+start);
                    int len = ran.nextInt(560-start);
                    len +=40;
                    rect1.setLayoutX(600);
                    rect1.setLayoutY(300);
                    rect1.setWidth(len);
                    rect1.setHeight(100);
                    if(start-(currentblock.getLayoutX()+ currentblock.getWidth())>50){
                        cherry.setIspresent(ran.nextBoolean());
                        if(cherry.isIspresent()) {
                            cherry.setStart(ran.nextInt(110, 110 + (int) ((start - (currentblock.getLayoutX() + currentblock.getWidth())) - 30)));
                            cherry.getSprite().setLayoutX(600);
                            cherry.getSprite().setLayoutY(305);
                            a.getRoot().getChildren().add(cherry.getSprite());
                        }
                    }
                    a.getRoot().getChildren().add(rect1);

                    TranslateTransition tl = new TranslateTransition();
                    tl.setDuration(Duration.seconds(1));
                    tl.setNode(currentblock);
                    TranslateTransition tl1 = new TranslateTransition();
                    tl1.setDuration(Duration.seconds(1));
                    try {
                        tl1.setNode(currentbridge.getBridgerect());
                    }catch (Exception e){

                    }
                    TranslateTransition tl2 = new TranslateTransition();
                    tl2.setDuration(Duration.seconds(1));
                    tl2.setNode(nextblock);
                    TranslateTransition tl3 = new TranslateTransition();
                    tl3.setDuration(Duration.seconds(1));
                    tl3.setNode(sprite);
                    if(nextblock.getWidth()>110) {
                        System.out.println("Greater" + (nextblock.getWidth() - 110));
                        double dist = nextblock.getLayoutX();
                        System.out.println(dist);
                        tl2.setByX(-dist - (nextblock.getWidth() - 110));
                        tl2.play();
                        tl1.setByX((-dist - (nextblock.getWidth() - 110)));
                        tl1.play();
                        tl.setByX((-dist - (nextblock.getWidth() - 110)));
                        tl.play();
                        tl3.setByX((-dist - (nextblock.getWidth() - 110)));
                        tl3.play();
                    }else {
                        tl2.setByX((-1)*(nextblock.getLayoutX()-(110-nextblock.getWidth())));
                        tl1.setByX((-1)*(nextblock.getLayoutX()-(110-nextblock.getWidth())));
                        tl1.play();
                        tl.setByX((-1)*(nextblock.getLayoutX()-(110-nextblock.getWidth())));
                        tl.play();
                        tl3.setByX((-1)*(nextblock.getLayoutX()-(110-nextblock.getWidth())));
                        tl3.play();
                    }
                    tl2.play();
                    TranslateTransition tl4 = new TranslateTransition();
                    tl4.setDuration(Duration.seconds(1));
                    tl4.setNode(rect1);
                    tl4.setByX(-1*(rect1.getLayoutX()-start));
                    tl4.setOnFinished(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            if(prevbridge != null){
                                prevbridge.getBridgerect().setVisible(false);
                            }
                            sprite.setTranslateX(10);
                            rect1.setLayoutX(rect1.getLayoutX()+rect1.getTranslateX());
                            rect1.setTranslateX(0);
                            currentblock.setLayoutX(currentblock.getLayoutX()+currentblock.getTranslateX());
                            currentblock.setTranslateX(0);
                            nextblock.setLayoutX(nextblock.getLayoutX()+nextblock.getTranslateX());
                            nextblock.setTranslateX(0);
                            currentblock = nextblock;
                            nextblock = rect1;
                            prevbridge = currentbridge;
                            currentbridge = null;
                            a.getC().setEpressed(false);
                        }
                    });
                    if(cherry.isIspresent()){
                        TranslateTransition tlch = new TranslateTransition();
                        tlch.setDuration(Duration.seconds(1));
                        tlch.setNode(cherry.getSprite());
                        tlch.setByX(-1*(rect1.getLayoutX()-cherry.getStart()));
                        System.out.println("hello");
                        tlch.play();
                    }
                    tl4.play();
                }
            });
        }
    }

    public void fall(){
        fall.playmusic();
        TranslateTransition tt = new TranslateTransition();
        tt.setNode(sprite);
        tt.setByX(20);
        tt.setToY(450);
        tt.setDuration(Duration.seconds(1.2));
        tt.play();
        Rotate r = new Rotate();
        r.setPivotX(0);
        r.setPivotY(getCurrentbridge().getBridgerect().getHeight() / 2);

        Timeline timeline = new Timeline();
        Duration frameGap = Duration.millis(5);
        Duration frameTime = Duration.millis(0.2);
        int a1;
        double opacity = 100;
        for (a1 = 360; a1 <= 450; a1++) {
            final int currentAngle = a1;
            opacity -= 1.11111111;
            frameTime = frameTime.add(frameGap);
            double finalOpacity = opacity;
            KeyFrame keyFrame = new KeyFrame(frameTime, e -> {
                r.setAngle(currentAngle);
                getCurrentbridge().getBridgerect().getTransforms().setAll(r);
                getCurrentbridge().getBridgerect().setOpacity(finalOpacity);
            });
            timeline.getKeyFrames().add(keyFrame);
        }

        timeline.setCycleCount(1);
        timeline.play();
        timeline.setOnFinished(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent actionEvent) {
                /*change scene to gameover screen and display highest score*/
                System.out.println(score);
                Scoreslst.add(score);
                FadeTransition nf = new FadeTransition(Duration.seconds(1));
                nf.setNode(sprite.getScene().getRoot());
                nf.setFromValue(1);
                nf.setToValue(0);
                nf.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        FadeTransition nf1 = new FadeTransition(Duration.seconds(1));
                        nf1.setNode(sprite.getScene().getRoot());
                        nf1.setFromValue(0);
                        nf1.setToValue(1);
                        nf1.play();
                        try {
                            Scene newsc = Character.gameoverscreen;
                            Stage currstg = (Stage) sprite.getScene().getWindow();
                            currstg.setScene(newsc);
                            System.out.println("aftergameover: "+sprite.getTranslateX()+", "+sprite.getX()+", "+ sprite.getLayoutX());
                        }catch (Exception e){
                        }
                        if(currcherry != null) {
                            currcherry.getSprite().setVisible(false);
                            currcherry.getSprite().setDisable(true);
                            currcherry.setIspresent(false);
                        }
                        System.out.println("Game is over!");
                        getA().getC().gameover = true;
                        try {
                            FileWriter fw = new FileWriter("src/main/java/com/example/demo/Score.txt",true);
                            BufferedWriter out = new BufferedWriter(fw);
                            out.write(Integer.toString(score)+"\n");
                            out.close();
                            fw.close();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        BufferedReader input = null;
                        int max = 0;
                        try {
                            input = new BufferedReader(new FileReader("src/main/java/com/example/demo/score.txt"));
                            String line;
                            while ((line = input.readLine()) != null) {
                                if(Integer.parseInt(line) > max){
                                    max = Integer.parseInt(line);
                                }
                            }
                        }catch (Exception e){

                        }finally {
                            try {
                                input.close();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        a.getCh().getHigh().setText(Integer.toString(max));
                        URL url = null;
                        ResourceBundle rb = null;
                        a.getC().initialize(url, rb);
                    }
                });
                nf.play();
            }
        });
    }

    public Rectangle getCurrentblock() {
        return currentblock;
    }

    public void setCurrentblock(Rectangle currentblock) {
        this.currentblock = currentblock;
    }

    public Bridge getCurrentbridge() {
        return currentbridge;
    }

    public void setCurrentbridge(Bridge currentbridge) {
        this.currentbridge = currentbridge;
    }

    public Rectangle getNextblock() {
        return nextblock;
    }

    public void setNextblock(Rectangle nextblock) {
        this.nextblock = nextblock;
    }

    public HelloApplication getA() {
        return a;
    }

    public void setA(HelloApplication a) {
        this.a = a;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public ArrayList<Integer> getScoreslst() {
        return Scoreslst;
    }

    public void setScoreslst(ArrayList<Integer> scoreslst) {
        Scoreslst = scoreslst;
    }

    public Cherry getCurrcherry() {
        return currcherry;
    }

    public void setCurrcherry(Cherry currcherry) {
        this.currcherry = currcherry;
    }

    public boolean isFlipped() {
        return flipped;
    }

    public void setFlipped(boolean flipped) {
        this.flipped = flipped;
    }

    public boolean isRunongoing() {
        return runongoing;
    }

    public void setRunongoing(boolean runongoing) {
        this.runongoing = runongoing;
    }

    public boolean isDownpressed() {
        return downpressed;
    }

    public void setDownpressed(boolean downpressed) {
        this.downpressed = downpressed;
    }

    public int getNumcherries() {
        return numcherries;
    }

    public void setNumcherries(int numcherries) {
        this.numcherries = numcherries;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public String getRunfolder() {
        return runfolder;
    }

    public void setRunfolder(String runfolder) {
        this.runfolder = runfolder;
    }

    public Gamemusic getBgm() {
        return bgm;
    }

    public void setBgm(Gamemusic bgm) {
        this.bgm = bgm;
    }

    public Gamemusic getFall() {
        return fall;
    }

    public void setFall(Gamemusic fall) {
        this.fall = fall;
    }

    public Gamemusic getCheck() {
        return check;
    }

    public void setCheck(Gamemusic check) {
        this.check = check;
    }
}
