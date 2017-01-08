package sample;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Popup;

/**
 * Created by The Dog on 02.01.2017.
 */
public class Etapp {
    public static Group täitmine(double xx, double yy, int counter){
        final Popup tabel=new Popup();
        tabel.setX(600);
        tabel.setY(300);
        tabel.setHeight(300);
        tabel.setWidth(400);

        /*popup küsimused ja variandid*/
        TextField esimene=new TextField();
        esimene.setPromptText("etapp");
        esimene.setTranslateX(100);
        esimene.setTranslateY(0);

        TextField teine=new TextField();
        teine.setPromptText("kirjeldus");
        teine.setTranslateX(100);
        teine.setTranslateY(40);

        Button kinnitus=new Button();
        kinnitus.setText("Lisa");
        kinnitus.setTranslateX(0);
        kinnitus.setTranslateY(240);

        Button tagastus=new Button();
        tagastus.setText("Tagasi");
        tagastus.setTranslateX(50);
        tagastus.setTranslateY(240);

        Popup popupp=new Popup();
        //popupp.getContent().add(esimene,teine,tagastus,kinnitus);

        String etapinimi=new String();
        etapinimi=esimene.getText();
        String kirjeldusnimi=new String();
        kirjeldusnimi=teine.getText();


        Group uuskast=new Group();


        Rectangle chasis=new Rectangle(300,300, Color.BLUE);
        chasis.setX(400);
        chasis.setY(600);
        chasis.setLayoutX(400);
        chasis.setLayoutY(600);
        Button tagasi=new Button();
        tagasi.setText("Tagasi");
        tagasi.setLayoutY(40);
        return uuskast;

    };

}
