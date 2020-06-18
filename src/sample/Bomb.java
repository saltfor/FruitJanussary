package sample;


import javafx.scene.image.ImageView;

public abstract class Bomb implements Sliceable {
    public abstract void slice(ImageView imageview,Double x,Double y);
}
