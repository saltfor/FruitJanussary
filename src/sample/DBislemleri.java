package sample;


import javafx.scene.control.Alert;


import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DBislemleri {

    baglantisinifi baglanti = new baglantisinifi();
    Connection kayitURL = baglanti.getBaglanti();

    public void kayitOl(String ad, String password) throws SQLException {
        String query = "INSERT INTO kullanici(kullaniciAdi, sifre) VALUES('" + ad + "','" + password + "')";
        Statement statement = kayitURL.createStatement();
        statement.executeUpdate(query);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Kayıt İşlemi");
        alert.setHeaderText("Başarılı !");
        alert.setContentText("Başarıyla Kayıt Olundu!");
        alert.showAndWait();
    }

    public boolean giris(String kadi, String sifre)throws SQLException {
        PreparedStatement prepstate = null;
        ResultSet resultSet = null;
        String query = "SELECT * from kullanici WHERE kullaniciAdi= ? and sifre= ?";
        prepstate = kayitURL.prepareStatement(query);
        prepstate.setString(1, kadi);
        prepstate.setString(2, sifre);
        resultSet = prepstate.executeQuery();
        if (resultSet.next()){
            kullaniciAdi=resultSet.getString("kullaniciAdi");
            kullaniciID=resultSet.getInt("kullaniciID");
            GUI.kAdi=kullaniciAdi;
            GUI.kID=kullaniciID;
            return true;
        }
        else
            return false;
    }
    String kullaniciAdi;
    int kullaniciID;

    public void bestGame(){
        try{
            PreparedStatement prepstate = null;
            ResultSet resultSet = null;
            String query = "SELECT kullaniciadi,score from scoretablosu ORDER BY score DESC";
            prepstate = kayitURL.prepareStatement(query);
            resultSet = prepstate.executeQuery();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("BEST SCORES");
            alert.setHeaderText("LIST OF BEST GAMES WITH NAMES");
            String [] kayitlar=new String[10];
            int sayac=0;
            while (resultSet.next()&&sayac<10){
                kayitlar[sayac]= resultSet.getString("kullaniciadi")+" - "+resultSet.getInt("score");
                alert.setContentText(alert.getContentText()+"\n"+kayitlar[sayac]);
                sayac++;
            }
            alert.showAndWait();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void playerbestGame(){
        try{
            PreparedStatement prepstate = null;
            ResultSet resultSet = null;
            String query = "SELECT score,bitirmezamani,tarih from scoretablosu WHERE kullaniciadi= ? " +
                    "and kullaniciID= ? ORDER BY score DESC";
            prepstate = kayitURL.prepareStatement(query);
            prepstate.setString(1, GUI.kAdi);
            prepstate.setInt(2, GUI.kID);
            resultSet = prepstate.executeQuery();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(GUI.kAdi+" BEST SCORES");
            alert.setHeaderText("LIST OF YOUR BEST GAMES");
            String [] kayitlar=new String[10];
            int sayac=0;
            while (resultSet.next()&&sayac<10){
                kayitlar[sayac]= resultSet.getInt("score")+" - "+resultSet.getString("bitirmezamani")+" - "+resultSet.getString("tarih");
                alert.setContentText(alert.getContentText()+"\n"+kayitlar[sayac]);
                sayac++;
            }
            alert.showAndWait();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void playerlastGame(){
        try{
            PreparedStatement prepstate = null;
            ResultSet resultSet = null;
            String query = "SELECT score,bitirmezamani,tarih from scoretablosu WHERE kullaniciadi= ? " +
                    "and kullaniciID= ?";
            prepstate = kayitURL.prepareStatement(query);
            prepstate.setString(1, GUI.kAdi);
            prepstate.setInt(2, GUI.kID);
            resultSet = prepstate.executeQuery();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(GUI.kAdi+" LAST GAMES");
            alert.setHeaderText("LIST OF YOUR LAST GAMES");
            String [] kayitlar=new String[10];
            int sayac=0;
            while (resultSet.next()&&sayac<10){
                kayitlar[sayac]= resultSet.getInt("score")+" - "+resultSet.getString("bitirmezamani")+" - "+resultSet.getString("tarih");
                alert.setContentText(alert.getContentText()+"\n"+kayitlar[sayac]);
                sayac++;
            }
            alert.showAndWait();
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }
    public void ScorEkle(String sure,int skor)throws SQLException{
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter myDateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date=myDateFormatter.format(now);
        String query = "INSERT INTO scoretablosu(kullaniciID,kullaniciadi,bitirmezamani,tarih,score) " +
                "VALUES('" + kullaniciID + "','" + kullaniciAdi + "','" + sure +"','" + date + "','" + skor + "')";
        Statement statement = kayitURL.createStatement();
        statement.executeUpdate(query);
    }

}
