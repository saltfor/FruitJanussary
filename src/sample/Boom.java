package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Boom extends Bomb {
    int sayac=0;
    Timeline splashtime;
    @Override
    public void slice(ImageView imageview,Double x,Double y){
        Image image=new Image("file:src/images/explosion.png");
        imageview.setImage(image);
        imageview.setFitWidth(200);
        imageview.setFitHeight(200);
        splashtime=new Timeline(new KeyFrame(Duration.millis(10),e->{
            if (sayac<150){
                sayac++;
            }
            else{
                imageview.setVisible(false);
                sayac=0;
                imageview.setImage(null);
                splashtime.stop();
            }
        }));
        splashtime.setCycleCount(Timeline.INDEFINITE);
        splashtime.play();
    }
}
