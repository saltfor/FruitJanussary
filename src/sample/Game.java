package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.omg.CORBA.MARSHAL;

import java.awt.*;
import java.io.IOException;
import java.security.Key;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Random;

import static java.lang.Boolean.TRUE;

public class Game{
    GUI gui;
    Pane pane;
    Bomb bomb;
    Timeline animation,time;
    Text textpuan,textcan,textgameover,texttime;
    int dk,saniye;
    Image resim=null;
    int puan=0;
    int can;
    PathTransition pt;
    ImageView imageview;
    int pausetoogle=0;
    Fruit [] fruit;
    int uretilen=0;
    boolean isSlice=true;
    ImageView mousemove=new ImageView();
    private String sure;
    private void ekran_Hazirla(){
        Button btnnew=new Button("New Game");
        btnnew.setFont(Font.font(11));
        btnnew.setLayoutX(180);
        btnnew.setLayoutY(20);
        btnnew.setMinWidth(80);
        btnnew.setOnAction(e->newGame());
        Button btnpause=new Button("Pause Game");
        btnnew.setFont(Font.font(11));
        btnpause.setLayoutX(270);
        btnpause.setLayoutY(20);
        btnpause.setMinWidth(80);
        btnpause.setOnAction(e-> {
            if (pausetoogle==0) {
                pausetoogle = 1;
                pause();
            }
            else {
                pausetoogle = 0;
                continueGame();
            }
                });
        Button btnstop=new Button("Stop Game");
        btnstop.setFont(Font.font(11));
        btnstop.setLayoutX(360);
        btnstop.setLayoutY(20);
        btnstop.setMinWidth(80);
        btnstop.setOnAction(e->game_Over());
        Button btncik=new Button("Exit");
        btncik.setFont(Font.font(11));
        btncik.setLayoutX(450);
        btncik.setLayoutY(20);
        btncik.setMinWidth(60);
        btncik.setOnAction(e->gui.game_Exit());
        Button btnscoreboard=new Button("Score Board");
        btnscoreboard.setFont(Font.font(11));
        btnscoreboard.setLayoutX(520);
        btnscoreboard.setLayoutY(20);
        btnscoreboard.setMinWidth(80);
        btnscoreboard.setOnAction(e->{
            try {
                gui.scoreBoard(e);
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        });
        textpuan=new Text("Score : 0");
        textpuan.setFont(Font.font(25));
        textpuan.setX(20);
        textpuan.setY(40);
        textcan=new Text("Health : 3");
        textcan.setFont(Font.font(25));
        textcan.setX(475);
        textcan.setY(580);
        textgameover=new Text();
        textgameover.setFont(Font.font(35));
        textgameover.setFill(Color.RED);
        textgameover.setX((pane.getWidth()/2)-230);
        textgameover.setY(pane.getHeight()/2);
        texttime=new Text("Time : 0:00");
        texttime.setFont(Font.font(25));
        texttime.setX(20);
        texttime.setY(580);
        pane.getChildren().addAll(textpuan,textcan,textgameover,texttime,btnnew,btnpause,btnstop,btncik,btnscoreboard,mousemove);
    }
    public void newGame(){
        mousemove.setImage(new Image("file:src/images/trace.png"));
        mousemove.setFitHeight(30);
        mousemove.setFitWidth(30);
        pane.setOnMouseMoved(e->{
            mousemove.setX(e.getSceneX());
            mousemove.setY(e.getSceneY());
        });
        isSlice=false;
        fruit=new Fruit[100];
        pane.getChildren().clear();
        ekran_Hazirla();
        can=3;
        uretilen=0;
        dk=0;
        saniye=0;
        puan=0;
        EventHandler<ActionEvent> firlat = e -> {
            if((isSlice==false)&&(fruit[uretilen]!=null)){
                can--;
            }
            textcan.setText("Health : "+can);
            if (can>0){
                uretilen++;
                thrown_Object();
            }
            else
                game_Over();
        };
        animation = new Timeline(new KeyFrame(Duration.millis(2010), firlat));
        animation.setCycleCount(Timeline.INDEFINITE);
        time=new Timeline(new KeyFrame(Duration.seconds(1),e->{write_Time();}));
        time.setCycleCount(Timeline.INDEFINITE);
        time.play();
        animation.play();
        thrown_Object();
    }
    public void pause(){
        time.pause();
        animation.pause();
        pt.pause();
    }
    public void continueGame(){
        time.play();
        animation.play();
        pt.play();
    }
    public void play(){
        ekran_Hazirla();
        pane.setOnDragDetected(e->pane.startFullDrag());
    }
    private void write_Time(){
        saniye++;
        if (saniye==60) {
            dk++;
            saniye=0;
        }
        if (saniye<10)
            texttime.setText("Time : "+dk+":0"+saniye);
        else
            texttime.setText("Time : "+dk+":"+saniye);
        sure=texttime.getText().substring(7); // sure degiskenine alması için
    }
    public void thrown_Object(){
        isSlice=false;
        int obj= (int)( Math.random()*5.0);
        switch (obj) {
            case 0:
                fruit[uretilen]=new Elma();
                resim=new Image("file:src/images/elma.png");
                break;
            case 1:
                fruit[uretilen]=new Limon();
                resim = new Image("file:src/images/limon.png");
                break;
            case 2:
                fruit[uretilen]=new Karpuz();
                resim = new Image("file:src/images/karpuz.png");
                break;
            case 3:
                fruit[uretilen]=new Seftali();
                resim = new Image("file:src/images/seftali.png");
                break;
            case 4:
                fruit[uretilen]=null;
                bomb = new Boom();
                resim = new Image("file:src/images/bomb.png");
                break;
            default:
                System.out.println("NULL FRUIT OR BOMB");
                break;
        }
        if(fruit[uretilen]==null)
            isSlice=true;
        double xmax=pane.getWidth();
        double ymax=pane.getHeight();
        double xstart=0,ystart=0;
        xstart=35+(Math.random()*(xmax-75));
        ystart=ymax+75;
        Line line = new Line(xstart,ystart,xstart,75);
        imageview=new ImageView(resim);
        imageview.setFitWidth(75);
        imageview.setFitHeight(75);
        imageview.setX(xstart);
        imageview.setY(ystart);
        pt=new PathTransition();
        pt.setDuration ( Duration . millis (1000));
        pt.setPath (line);
        pt.setNode (imageview);
        pane.getChildren().addAll(imageview);
        pt. setOrientation ( PathTransition . OrientationType . ORTHOGONAL_TO_TANGENT );
        pt. setCycleCount(2);
        pt. setAutoReverse ( true );
        pt.play();
        imageview.setOnMouseDragEntered(olay->{ slice(fruit[uretilen],imageview,olay.getSceneX(),olay.getSceneY());
        });
    }
    private void puan_Guncelle(Fruit fruit){
        puan+=fruit.puan_Guncelle(fruit);
        textpuan.setText("Score : "+puan);
    }
    public void slice(Fruit fruit,ImageView imageview,Double x,Double y){
        pt.stop();
        if (fruit!=null) { //meyveyse
            if (isSlice == false) { // daha önceden kesilmediyse
                isSlice = true;
                pane.getChildren().add(fruit.splash(x, y));
                fruit.slice(imageview,x,y);
                puan_Guncelle(fruit);
            }
        }
        else{
            bomb.slice(imageview,x,y);
            game_Over();
        }
    }
    public void game_Over() {
        try {
            gui.db.ScorEkle(sure,puan);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        mousemove.setImage(null);
        pt.stop();
        time.stop();
        animation.stop();
        textgameover.setText("GAME OVER.YOUR SCORE : "+puan);
        puan=0;
        can=3;
    }

}
