package com.example.demo;

import javafx.application.Platform;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.Assert.*;

public class Testclass {
    @Test
    public void Test1() throws IOException {
        boolean res = false;
        Scanner reader = null;
        try{
            reader = new Scanner(new BufferedReader(new FileReader("src/main/java/com/example/demo/Score.txt")));
            while(reader.hasNext()){
                if(reader.hasNextBigInteger()){
                    reader.next();
                    res = true;
                }else {
                    res = false;
                    break;
                }
            }
        }catch (Exception e){
            System.out.println(e.getStackTrace());
            reader.close();
        }
        assertTrue(res);
    }


    @Test
    public void Test2() throws IOException {
        boolean res = false;
        Scanner reader = null;
        try{
            reader = new Scanner(new BufferedReader(new FileReader("src/main/java/com/example/demo/numdiamonds.txt")));
            while(reader.hasNext()){
                if(reader.hasNextBigInteger()){
                    reader.next();
                    res = true;
                }else {
                    res = false;
                    break;
                }
            }
        }catch (Exception e){
            System.out.println(e.getStackTrace());
            reader.close();
        }
        assertTrue(res);
    }

    @Test
    public void testIspresent(){
        Cherry cherry = new Cherry();
        cherry.Eatcherry();
        assertFalse(cherry.isIspresent());
    }

    @Test
    public void initc(){
        Cherry c = new Cherry();
        assertEquals(c.getInitialcherry(),0);
    }

}
