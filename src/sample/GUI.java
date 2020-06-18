package sample;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GUI implements Initializable {
    Parent root;
    Stage stage;
    public static String kAdi;
    public static int kID;

    @FXML
    private Button btngiris;

    @FXML
    private Button scoreButton;

    @FXML
    private AnchorPane anchorRoot;

    @FXML
    private StackPane parentContainer;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private TextArea area1;

    @FXML
    public void scoreBoard(ActionEvent event) throws IOException {
        root=FXMLLoader.load(getClass().getResource("/sample/scene2.fxml"));
        Stage scorestage=new Stage();
        scorestage.setScene(new Scene(root,Color.BLUE));
        scorestage.show();
    }

    @FXML
    public void bestgame(){
        db.bestGame();
    }

    @FXML
    public void yourbest(){
        db.playerbestGame();
    }

    @FXML
    public void yourlast(){
        db.playerlastGame();
    }


    @FXML
    private TextField txtkadi;

    @FXML
    private PasswordField txtsifre;

    DBislemleri db=new DBislemleri();

    @FXML
    private void oyunEkrani(ActionEvent event) throws IOException {
        try {
            if(db.giris(txtkadi.getText(),txtsifre.getText())){
                root = FXMLLoader.load(getClass().getResource("/sample/oyun.fxml"));
                Game game=new Game();
                game.pane =  (Pane) root;
                game.gui=this;
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Başarılı");
                alert.setHeaderText("Hoşgeldiniz "+db.kullaniciAdi);
                alert.showAndWait();
                stage=new Stage();
                stage.setTitle("Fruit Janissary");
                stage.setScene(new Scene(root,Color.BLUE));
                stage.show();
                game.play();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Hata");
                alert.setHeaderText("Giriş Yapılamadı !");
                alert.setContentText("Kullanıcı adınızı ve şifrenizi doğru girdiğinizden emin olun!");
                alert.showAndWait();
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }
    public void game_Exit(){
        stage.close();
    }

    @FXML
    private Button btnkayit;

    @FXML
    private TextField txtadkayit;

    @FXML
    private PasswordField txtsifrekayit;

    @FXML
    private void kayit(){
        try {
            if(txtadkayit.getText().trim()!=""&&txtsifrekayit.getText().trim()!="")
                db.kayitOl(txtadkayit.getText(),txtsifrekayit.getText());
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void kayitEkrani(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/sample/scene4.fxml"));
        Scene scene = btnkayit.getScene();
        root.translateXProperty().set(scene.getWidth());

        StackPane parentContainer = (StackPane) btnkayit.getScene().getRoot();

        parentContainer.getChildren().add(root);

        Timeline timeline1 = new Timeline();
        KeyValue kv2 = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf2 = new KeyFrame(Duration.seconds(1), kv2);
        timeline1.getKeyFrames().add(kf2);
        timeline1.setOnFinished(t -> {
            parentContainer.getChildren().remove(parentContainer);
        });
        timeline1.play();
    }
    @FXML
    private Button btnAnaMenu;
    @FXML
    private Button btnAnaMenu2;

    @FXML
    private void girisYonlendirme(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sample/scene1.fxml"));
        Scene scene = btnAnaMenu.getScene();
        root.translateXProperty().set(scene.getWidth());

        StackPane parentContainer = (StackPane) btnAnaMenu.getScene().getRoot();

        parentContainer.getChildren().add(root);

        Timeline timeline1 = new Timeline();
        KeyValue kv3 = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf3 = new KeyFrame(Duration.seconds(1), kv3);
        timeline1.getKeyFrames().add(kf3);
        timeline1.setOnFinished(t -> {
            parentContainer.getChildren().remove(parentContainer);
        });
        timeline1.play();
    }
    @FXML
    private void girisYonlendirme2(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sample/scene1.fxml"));
        Scene scene = btnAnaMenu2.getScene();
        root.translateXProperty().set(scene.getWidth());

        StackPane parentContainer = (StackPane) btnAnaMenu2.getScene().getRoot();

        parentContainer.getChildren().add(root);

        Timeline timeline1 = new Timeline();
        KeyValue kv4 = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf4 = new KeyFrame(Duration.seconds(1), kv4);
        timeline1.getKeyFrames().add(kf4);
        timeline1.setOnFinished(t -> {
            parentContainer.getChildren().remove(parentContainer);
        });
        timeline1.play();
    }
}
