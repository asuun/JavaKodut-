package sample;


import com.sun.org.apache.xerces.internal.dom.ChildNode;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;


/**
 * Created by The Dog on 30.12.2016.
 */
public class Objektid {
    public static Kast etapiAegAlgus(double xx, double yy,int counterRuut,String t1){
        Kast algus=new Kast();
        Text algusaeg=new Text();
        //ruut.lõpetamineKaardile.setText(t2);
       // algus.alustamineKaardile.setText("yoyoyooyoyo");
       // algus.alustamineKaardile.setTranslateX(xx);
       // algus.alustamineKaardile.setTranslateY(yy);
        //ruut.lõpetamineKaardile.setTranslateX(xx+25);
        //ruut.lõpetamineKaardile.setTranslateY(yy);
        //ruut.etappKaardile.setTranslateX(xx);
        //ruut.etappKaardile.setTranslateY(yy-20);
        return algus;


    }

    public static Kast etapiLisamine(double xx, double yy, int counterRuut, String eNimi, String eKirjeldus) {

        Kast ruut = new Kast();
        ruut.setWidth(140);
        ruut.setHeight(70);
        ruut.setFill(Color.TRANSPARENT);
        ruut.setStroke(Color.BLUE);
        ruut.setStrokeWidth(10);
        ruut.setTranslateX(xx);
        ruut.setTranslateY(yy);
        ruut.id = counterRuut;
        ruut.kastNimi=(String.valueOf(eNimi));
        ruut.kastKirjeldus=(String.valueOf(eKirjeldus));
        return ruut;
    }

    public static Liide ylemine( Object aktiivne,int counterpunkt,double xx,double yy){
        Liide ylemine=new Liide();
        ylemine.setRadius(5);
        ylemine.setTranslateX(xx);
        ylemine.setTranslateY(yy-((Kast)aktiivne).getHeight()/2-4);
        ylemine.liiteID=counterpunkt;
        return ylemine;
    }
    public static Liide alumine(Object aktiivne,int counterpunkt,double xx,double yy){
        Liide alumine=new Liide();
        alumine.setRadius(5);
        alumine.setTranslateX(xx);
        alumine.setTranslateY(yy+((Kast)aktiivne).getHeight()/2+4);
        alumine.liiteID=counterpunkt;
        return alumine;
    }


}



