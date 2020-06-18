package sample;

import com.sun.corba.se.spi.orbutil.fsm.Action;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.TableRow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.event.ActionEvent;
import java.beans.EventHandler;
import java.nio.file.Path;

public abstract class Fruit implements  Sliceable {
    ImageView fruitimageview;
    int sayac=0;
    Timeline splashtime;
    public boolean isSlice;
    public abstract void slice(ImageView imageview,Double x,Double y);
    public abstract int puan_Guncelle(Fruit fruit);
    public ImageView splash(Double ekranx,Double ekrany){
        ImageView splashimage=new ImageView(new Image("file:src/images/fruitsplace.png"));
        splashimage.setX(ekranx-50);
        splashimage.setY(ekrany);
        splashimage.setFitWidth(100);
        splashimage.setFitHeight(100);
        splashtime=new Timeline(new KeyFrame(Duration.millis(10),e->{
            if (sayac<50){
                sayac++;
            }
            else{
                splashimage.setVisible(false);
                sayac=0;
                splashimage.setImage(null);
                splashtime.stop();
            }
        }));
        splashtime.setCycleCount(Timeline.INDEFINITE);
        splashtime.play();
        return splashimage;
    }
}
