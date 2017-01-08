package sample;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.LocalDate;

/**
 Uue objekti väärtuste omistamiseks luuakse hüpikaken
 */
public class Popup {

    public static Stage popupAken(int id,LocalDate täna, double xx, double yy, int counterRuut, Button popupkinnita,TextField eetapp, TextField eekirjeldus, DatePicker eeaegLõpp, DatePicker eeaegAlgus){
        Stage createpopup = new Stage();
        VBox popupKere = new VBox();
        TextField eexx=new TextField("x-koordinaadid");
        TextField eeyy=new TextField("y-koordinaadid");
        eetapp.setPromptText("etapp");
        eekirjeldus.setPromptText("kirjeldus");
        eekirjeldus.setPrefSize(100,40);
        eeaegAlgus.setValue(täna);
        eeaegLõpp.setValue(täna);
        popupkinnita.setText("Kinnita");
        eexx.setText(String.valueOf(xx));
        eeyy.setText(String.valueOf(yy));

        switch (id) {
            case 1:
                eetapp.setText("protsessialgus");
                popupKere.getChildren().addAll(eetapp,eexx,eeyy,eeaegAlgus,eekirjeldus,popupkinnita);
                break;
            case  2:
                popupKere.getChildren().addAll(eexx,eeyy,eetapp, eekirjeldus, eeaegAlgus, eeaegLõpp, popupkinnita);
                break;
            case 3:
                eekirjeldus.setText("Vaheetapp");
                popupKere.getChildren().addAll(eexx,eeyy,eekirjeldus,popupkinnita);
                break;
            case 4:
                popupkinnita.setText("Tagasi");
                popupKere.getChildren().addAll(eexx,eeyy,popupkinnita);
                break;
        }

        createpopup.initModality(Modality.APPLICATION_MODAL);

        Scene popup = new Scene(popupKere, 300, 300);
        createpopup.setScene(popup);
        Label popupPealkiri=new Label("Popup Window");
        popupPealkiri.setText(String.valueOf(counterRuut));

    return createpopup;

    };

}
