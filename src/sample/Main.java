package sample;


import javafx.application.Application;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;


public class Main extends Application{

    /*popup akna tagastatavad väärtused*/
    TextField eetapp = new TextField();
    TextField eekirjeldus = new TextField();
    DatePicker eeaegLõpp = new DatePicker();
    DatePicker eeaegAlgus = new DatePicker();
    Kast uusKast=new Kast();
    Liide ylemine=new Liide();
    Liide alumine=new Liide();
    Button popupkinnita=new Button();

    /*Koordinaatide saamiseks vajalikud vahemuutujad*/
    double xx;
    double yy;
    double x0;
    double y0;
    double xd;
    double yd;

    Text loppKaart=new Text();
    Text algusKaart=new Text();
    Text etappKaardile=new Text("uus etapp");

    /*objektide conterid*/
    int counterRuut = 0;
    int counterPunkt=0;

    /*Erinevad muutujad kuupäeva  teisenduseks*/
    LocalDate täna = new java.sql.Date( new java.util.Date().getTime() ).toLocalDate();
    LocalDate alustamine;
    LocalDate lõpetamine;
    Calendar cal = Calendar.getInstance();
    String pattern = "yyyy-MM-dd";
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    Instant ajaTeisendamine1;
    Instant ajaTeisendamine2;
    Date aegSöödav1;
    Date aegSöödav2;
    String t1=new String();
    String t2=new String();

    /*Kaardi piiride kättesaamiseks*/
    double akenx1;
    double akeny1;

    /*vahemuutuja aktiivse objekti leidmiseks*/
    Object aktiivne;
    int id;

/*Kaardile kuvamine*/
    String eNimi=new String();
    String eKirjeldus=new String();
    Stage pop=new Stage();

    @Override
    public void start(Stage primaryStage) throws Exception {
        /*KAART JA TEMA OBJEKTID*/
        StackPane kaart=createkaart();

        /*MENUU OBJEKTID JA MENUU     */
        Kast protsessiAlgus=createProtsessiAlgus();
        Kast protsessiVaheEtapp=createProtsessiVaheEtapp();
        Kast protsessiEtapp=createProtsessiEtapp();
        Kast protsessiLõpp=createProtsessiLõpp();
        Text textAlgus=createtextAlgus();
        Text textEtapp=createTextEtapp();
        Text textVaheEtapp=createTextvaheEtapp();
        Text textLõpp=createTextLõpp();


        VBox menuu = new VBox(8);
        menuu.setSpacing(10);
        menuu.getChildren().addAll(textAlgus,protsessiAlgus,textEtapp, protsessiEtapp,textVaheEtapp, protsessiVaheEtapp,textLõpp, protsessiLõpp);

        menuu.setStyle("-fx-background-color: #C0C0C0;");

        /*JUHTIMISAKNA OBJEKTID*/
        VBox juhtPaneel = new VBox(4);
        juhtPaneel.setSpacing(5);
        TextField etappJuhtpaneel=createEtapp();
        TextField kirjeldusJuhtpaneel=createKirjeldus();
        DatePicker algusJuhtpaneel=createAegAlgus();
        DatePicker lõppJuhtpaneel=createAegLõpp();

        Button kinnitaJuhtpaneel=createKinnita();
        juhtPaneel.getChildren().addAll(etappJuhtpaneel, kirjeldusJuhtpaneel, algusJuhtpaneel, lõppJuhtpaneel, kinnitaJuhtpaneel);

        /*LOGI LOOMINE*/
        TextArea loggiraamat=new TextArea();

        /*AKENTE PAIGUTUS PROGRAMMIS*/
        BorderPane tooaken = new BorderPane();
        tooaken.setCenter(kaart);
        tooaken.setLeft(menuu);
        tooaken.setRight(juhtPaneel);
        tooaken.setBottom(loggiraamat);

        primaryStage.setTitle("Flowchart");
        primaryStage.setScene(new Scene(tooaken));
        primaryStage.show();

        /*hiire koordinaatide saamine ja kuvamine*/
        tooaken.setOnMousePressed(event->{
            aktiivne=event.getTarget();
            x0=event.getSceneX();
            y0=event.getSceneY();
        });

        /*kasti lohistamine töölauale,lihtsalt lohistamiseks*/
        tooaken.setOnMouseDragged(event -> {
            if(aktiivne instanceof Kast) {
            }
                aktiivne = event.getTarget();
                id = ((Kast) aktiivne).origin;

                    if (((Kast) aktiivne).origin >= 1) {
                        kaart.getChildren().remove(protsessiAlgus);
                        xd = event.getSceneX() - x0;
                        yd = event.getSceneY() - y0;
                }else{
                        return;
                    }
           if(aktiivne instanceof StackPane){
               return;
           }
            xx=kaart.getTranslateX()-kaart.getWidth()/2+xd-70;
            yy=kaart.getTranslateY()-kaart.getHeight()/2+yd+y0;
            ((Kast)aktiivne).setTranslateX(xd+menuu.getWidth()-140);
            ((Kast)aktiivne).setTranslateY(yd);

        if(aktiivne instanceof Kast){
            ((Kast)aktiivne).setOnMouseReleased(objektiViskamine -> {
                akenx1=kaart.getWidth();
                akeny1=kaart.getHeight();
                /*Piiride kontrollimine*/
                if(((Kast)aktiivne).getTranslateX()>=akenx1-70){
                    loggiraamat.appendText("Objekt töölaualt väljas!"+"\n");
                }
                else if (((Kast)aktiivne).getTranslateY()>=akeny1-35){
                    loggiraamat.appendText("objekti y liiga suur"+"\n");
                }
                else if(((Kast)aktiivne).getTranslateY()<=35){
                    loggiraamat.appendText("objekti y liiga väike"+"\n");

                }
                else if(((Kast)aktiivne).getTranslateX()<=140){
                    loggiraamat.appendText("objekti X liiga väike"+"\n");
                    loggiraamat.appendText("translate x on"+((Kast)aktiivne).getTranslateX()+"\n");
                }else{
                    pop=Popup.popupAken(id,täna,xx,yy,counterRuut,popupkinnita,eetapp,eekirjeldus,eeaegLõpp,eeaegAlgus);
                        pop.show();

                    popupkinnita.setOnAction(kinnituseks -> {
                        /*hüpikakna ja tööakna vahelised vahemuutujad*/
                        algusJuhtpaneel.setValue(alustamine);
                        eNimi = eetapp.getText();
                        eKirjeldus = eekirjeldus.getText();
                        alustamine = eeaegAlgus.getValue();
                        lõpetamine = eeaegLõpp.getValue();
                        ajaTeisendamine1=alustamine.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
                        ajaTeisendamine2=lõpetamine.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
                        /*Aeg*/
                        aegSöödav1= Date.from(ajaTeisendamine1);
                        aegSöödav2=Date.from(ajaTeisendamine2);
                        t1=formatter.format(aegSöödav1);
                        t2=formatter.format(aegSöödav2);
                        counterRuut=counterRuut+1;
                        counterPunkt=counterPunkt+1;
                        /*Uute objektide loomine*/
                        alumine=Objektid.alumine(aktiivne,counterPunkt,xx,yy);
                        ylemine=Objektid.ylemine(aktiivne,counterPunkt,xx,yy);
                        uusKast=Objektid.etapiLisamine(xx,yy,counterRuut,eNimi,eKirjeldus);
                        etappKaardile=Kast.kastNimi(xx,yy,eNimi);
                        algusKaart=Kast.alustamineKaardile(xx,yy,t1);
                        loppKaart=Kast.lõpetamineKaardile(xx,yy,t2);

                        kaart.getChildren().addAll(uusKast,alumine,ylemine,algusKaart,loppKaart,etappKaardile);
                        pop.hide();
                    });
                }
                /*menuuobjektide tagastamine*/
                ((Kast)aktiivne).setTranslateX(0);
                ((Kast)aktiivne).setTranslateY(0);
            });

        }

        });

        kaart.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            /*KAARDIL OPEREERIMINE*/
            @Override
            public void handle(MouseEvent mouseEvent) {
                    if(mouseEvent.getButton()==MouseButton.SECONDARY){
                        aktiivne=mouseEvent.getTarget();
                        if(aktiivne instanceof Kast){
                            kaart.getChildren().remove(((Kast)aktiivne));

                        }

                    }
                    if(aktiivne instanceof Kast){
                    /*Kui tegemist on Kast objektiga siis tahan selle andmeid lugeda ja muuta*/
                        etappJuhtpaneel.setText(String.valueOf(((Kast) aktiivne).kastNimi));
                        kirjeldusJuhtpaneel.setText(String.valueOf(((Kast) aktiivne).kastKirjeldus));
                        algusJuhtpaneel.setValue(((Kast)aktiivne).kastAlgus);
                        lõppJuhtpaneel.setValue(((Kast)aktiivne).kastLõpp);

                        kinnitaJuhtpaneel.setOnAction(ülekirjutamine -> {
                            ((Kast) aktiivne).kastNimi = etappJuhtpaneel.getText();
                            System.out.println(((Kast) aktiivne).kastNimi );
                            ((Kast) aktiivne).kastKirjeldus= kirjeldusJuhtpaneel.getText();
                            ((Kast) aktiivne).kastAlgus=algusJuhtpaneel.getValue();
                            ((Kast) aktiivne).kastLõpp=lõppJuhtpaneel.getValue();
                                /*aeg ka veel vaja lisada*/
                        });
                    } else {

                };

                /*OBJEKTI KIRJELDUSTE LUGEMINE*/
                kaart.setOnMouseMoved(event -> {

                    Object aktiivne = event.getTarget();
                    if (aktiivne instanceof Kast) {
                        loggiraamat.appendText("kirjeldus on" + ((Kast) aktiivne).kastKirjeldus+"\n");
                        loggiraamat.appendText("Kast: " + ((Kast) aktiivne).id+"\n");
                        loggiraamat.appendText("etapinimi on" + ((Kast) aktiivne).kastNimi+"\n");
                    }
                });

            }
        });

    }

    public static void main(String[] args) {
        launch(args);
    }
    /*Tööaknal olevad objektid*/
    private Kast createProtsessiAlgus() {
        Kast protsessiAlgus = new Kast();
        protsessiAlgus.setWidth(140);
        protsessiAlgus.setHeight(70);
        protsessiAlgus.origin=1;
        protsessiAlgus.setStroke(Color.BLUEVIOLET);
        protsessiAlgus.setFill(Color.TRANSPARENT);
        protsessiAlgus.setStrokeWidth(10);
        return protsessiAlgus;
    };
    private Text createtextAlgus(){
        Text textAlgus=new Text();
        textAlgus.setStrokeWidth(3);
        textAlgus.setText("Protsessi algus");
        return textAlgus;
    }
    private Text createTextEtapp() {
        Text textEtapp = new Text();
        textEtapp.setStrokeWidth(3);
        textEtapp.setText("Etapp");
        return textEtapp;
    }
    private Kast createProtsessiEtapp(){
        Kast protsessiEtapp=new Kast();
        protsessiEtapp.setWidth(140);
        protsessiEtapp.setHeight(70);
        protsessiEtapp.origin=2;
        protsessiEtapp.setStroke(Color.RED);
        protsessiEtapp.setFill(Color.TRANSPARENT);
        protsessiEtapp.setStrokeWidth(10);
        return protsessiEtapp;
    };
    private Text createTextvaheEtapp(){
        Text createTextvaheEtapp=new Text();
        createTextvaheEtapp.setStrokeWidth(3);
        createTextvaheEtapp.setText("Vaheetapp");
        return createTextvaheEtapp;
    }
    private Text createTextLõpp(){
        Text createTextLõpp=new Text();
        createTextLõpp.setStrokeWidth(3);
        createTextLõpp.setText("Lõpp");
        return createTextLõpp;
    }
    private Kast createProtsessiVaheEtapp(){
        Kast protsessivaheetapp=new Kast();
        protsessivaheetapp.setWidth(140);
        protsessivaheetapp.setHeight(70);
        protsessivaheetapp.origin=3;
        protsessivaheetapp.setFill(Color.TRANSPARENT);
        protsessivaheetapp.setStroke(Color.GREEN);
        protsessivaheetapp.setStrokeWidth(10);
        return protsessivaheetapp;
    };

    private Kast createProtsessiLõpp(){
        Kast protsessiLõpp=new Kast();
        protsessiLõpp.setWidth(140);
        protsessiLõpp.setHeight(70);
        protsessiLõpp.origin=4;
        protsessiLõpp.setFill(Color.TRANSPARENT);
        protsessiLõpp.setStroke(Color.BLACK);
        protsessiLõpp.setStrokeWidth(10);
        return protsessiLõpp;
    };

    private StackPane createkaart(){
        StackPane kaart=new StackPane();
        kaart.setPrefSize(600, 600);
        kaart.setStyle("-fx-background-color: #FFFAAF;");
        return kaart;
    }

    private TextField createEtapp(){
        TextField etapp=new TextField();
        etapp.setPromptText("etapp");
        return etapp;
    }
    private TextField createKirjeldus() {
        TextField kirjeldus = new TextField();
        kirjeldus.setPromptText("kirjeldus");
        return kirjeldus;
    }
    private DatePicker createAegAlgus() {
        DatePicker aegAlgus = new DatePicker();
        aegAlgus.setPromptText("Protsessi algus");
        return aegAlgus;
    }

    private DatePicker createAegLõpp(){
        DatePicker aegLõpp = new DatePicker();
        aegLõpp.setPromptText("Protsessi lõpp");
        return aegLõpp;

    }
    private Button createKinnita(){
        Button kinnita = new Button();
        kinnita.setText("Kinnita");
        return kinnita;
    }

}



