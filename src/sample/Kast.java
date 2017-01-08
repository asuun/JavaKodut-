package sample;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.time.LocalDate;
import java.util.Date;

/**
 * Created by Alvar on 03.01.2017.
 */
public class Kast extends Rectangle{
    public static Text alustamineKaardile(double xx,double yy,String t1){
        Text algKaart=new Text();
        algKaart.setText(t1);
        algKaart.setTranslateY(yy+8);
        algKaart.setTranslateX(xx-30);
        return algKaart;
    };
    public static Text lõpetamineKaardile(double xx,double yy,String t2){
        Text loppKaart=new Text();
        loppKaart.setText(t2);
        loppKaart.setTranslateY(yy+25);
        loppKaart.setTranslateX(xx+30);
        return loppKaart;
    }
    public static Text kastNimi(double xx,double yy,String eNimi){
        Text nimi=new Text();
        nimi.setText(eNimi);
        nimi.setTranslateX(xx);
        nimi.setTranslateY(yy-25);
        return nimi;

    }

    public int id;          //Loodava kasti id
    public int origin;      //Menuu objekti ID

    public String kastKirjeldus;
    public String kastNimi;
    public String kustuta;


    public LocalDate kastAlgus;
    public LocalDate kastLõpp;


}
