package com.example.demo;

import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;


public class HelloApplication extends Application {
    public static Rectangle rect = new Rectangle(20,20, Paint.valueOf("BLACK"));
    private Group root = new Group();
    private Group hscrroot = new Group();

    public static Character char1 = Character.getInstance();


    public static ScaleTransition t1 = new ScaleTransition();
    public static Timeline timeLine1 = new Timeline();

    public static Scene homescene;

    public static HelloController c = new HelloController();
    public static Homecontroller ch = new Homecontroller();

    @Override
    public void start(Stage stage) throws IOException {
        Random ran = new Random();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        FXMLLoader fxmlLoader1 = new FXMLLoader(HelloApplication.class.getResource("homescreen.fxml"));
        root.getChildren().add(fxmlLoader.load());
        root.getChildren().add(rect);
        hscrroot.getChildren().add(fxmlLoader1.load());
        c = fxmlLoader.getController();
        ch = fxmlLoader1.getController();
        fxmlLoader1.setController(null);
        fxmlLoader.setController(null);
        c.setA(this);
        Homecontroller.controller = c;
        Homecontroller.application = this;
        Cherry cherry = new Cherry();
        /*Image i = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/demo/cherry.png")));
        ImageView niv = new ImageView(i);
        cherry.setSprite(niv);
        cherry.getSprite().setFitWidth(30);
        cherry.getSprite().setFitHeight(30);
        c.getC1().setCurrcherry(cherry);
        cherry.setIspresent(ran.nextBoolean());
        if(cherry.isIspresent()) {
            cherry.setStart(ran.nextInt(110, (int) c.getC1().getNextblock().getLayoutX()-30));
            cherry.getSprite().setLayoutX(cherry.getStart());
            cherry.getSprite().setLayoutY(305);
            root.getChildren().add(cherry.getSprite());
        }*/
        Homecontroller.cherry = cherry;
        Scene gamescreen = new Scene(root, 600, 400);

        Group root1 = new Group();
        FXMLLoader fxmlLoader2 = new FXMLLoader(HelloApplication.class.getResource("game over.fxml"));
        root1.getChildren().add(fxmlLoader2.load());
        Character.gameoverscreen = new Scene(root1);

        Homecontroller.newscene = gamescreen;
        homescene = new Scene(hscrroot,600,400);
        stage.setResizable(false);
        stage.setTitle("Stick hero");
        stage.setScene(homescene);
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/demo/icon.png"))));
        stage.show();
        // Registering a shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Going byee!");
            PrintWriter out= null;
            try{
                out = new PrintWriter(new FileWriter("src/main/java/com/example/demo/numdiamonds.txt"));
                out.println(c.getC1().getNumcherries());
            }catch (Exception e){
            }finally {
                out.close();
            }
            getC().getC1().setNumcherries(0);
        }));

    }

    public static void main(String[] args) {
        launch();
    }

    public Rectangle getRect() {
        return rect;
    }

    public void setRect(Rectangle rect) {
        this.rect = rect;
    }

    public Group getRoot() {
        return root;
    }

    public void setRoot(Group root) {
        this.root = root;
    }

    public HelloController getC() {
        return c;
    }

    public void setC(HelloController c) {
        this.c = c;
    }

    public Homecontroller getCh() {
        return ch;
    }

    public void setCh(Homecontroller ch) {
        HelloApplication.ch = ch;
    }
}


