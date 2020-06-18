package sample;

import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class Karpuz extends Fruit {
    @Override
    public void slice (ImageView imageview,Double x,Double y)
    {
        Image image=new Image("file:src/images/kesilmiskarpuz.png");
        imageview.setImage(image);
        imageview.setFitWidth(100);
        imageview.setFitHeight(100);
        Line line = new Line(x, y, x, 675);
        PathTransition path = new PathTransition();
        path.setDuration(Duration.millis(1000));
        path.setPath(line);
        path.setNode(imageview);
        path.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        path.setAutoReverse(false);
        path.play();
    }
    @Override
    public int puan_Guncelle(Fruit fruit){
        return 15;
    }
}
