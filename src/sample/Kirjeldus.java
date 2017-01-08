package sample;

import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * Created by The Dog on 30.12.2016.
 */
public class Kirjeldus {
    public static Text proov(TextField etapp, TextField kirjeldus){
        Text etappPealkiri=new Text();
        Text tapp=new Text();
        String vaheEtapp=etapp.getText();
        String vaheKirjeldus=kirjeldus.getText();
        etappPealkiri.setText(vaheEtapp);
        tapp.setText(vaheKirjeldus);
        return etappPealkiri;
        //return tapp;

    }
}
