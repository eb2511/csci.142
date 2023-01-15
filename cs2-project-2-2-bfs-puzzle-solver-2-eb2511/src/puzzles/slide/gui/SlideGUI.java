package puzzles.slide.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import puzzles.common.Observer;
import puzzles.slide.model.SlideClientData;
import puzzles.slide.model.SlideModel;



import java.io.File;
import java.io.IOException;

import static jdk.jfr.consumer.EventStream.openFile;


public class SlideGUI extends Application implements Observer<SlideModel, SlideClientData> {
    private SlideModel model;

    /** The size of all icons, in square dimension */
    private final static int ICON_SIZE = 75;
    /** the font size for labels and buttons */
    private final static int BUTTON_FONT_SIZE = 20;
    private final static int FONT_SIZE = 12;
    private final static int NUMBER_FONT_SIZE = 24;
    /** Colored buttons */
    private final static String EVEN_COLOR = "#ADD8E6";
    private final static String ODD_COLOR = "#FED8B1";
    private final static String EMPTY_COLOR = "#FFFFFF";

    private Stage stage;
    private GridPane gridPane;
    private Label message;


    public SlideGUI() throws IOException {
        init();
    }

    @Override
    public void init() throws IOException {
        // get the file name from the command line
        if(null != getParameters()){
            String filename = getParameters().getRaw().get(0);
            this.model = new SlideModel(filename);
            this.model.addObserver(this);
        }


    }//done, i think



    private void setGrids(){
        for (int i = 0;i<this.model.getRows();i++){
            for(int j=0;j<this.model.getCols();j++){
                Button button = new Button();
                button.setStyle(
                        "-fx-font-family: Arial;" +
                                "-fx-font-size: " + BUTTON_FONT_SIZE + ";" +
                                "-fx-font-weight: bold;");
                button.setStyle("-fx-font-weight: bold;");
                if(this.model.getGrids()[i][j]==999){
                    button.setText(".");
                    button.setStyle("-fx-background-color: " + EMPTY_COLOR + ";" );
                }else if(this.model.getGrids()[i][j]%2!=0){//odd
                    button.setStyle("-fx-background-color: " + ODD_COLOR + ";" );
                    button.setText(String.valueOf(this.model.getGrids()[i][j]));
                }else{button.setStyle("-fx-background-color: " + EVEN_COLOR + ";" );
                    button.setText(String.valueOf(this.model.getGrids()[i][j]));
                }


                button.setMinSize(ICON_SIZE, ICON_SIZE);
                button.setMaxSize(ICON_SIZE, ICON_SIZE);
                int finalI = i;
                int finalJ = j;
                button.setOnAction(event -> this.message.setText(model.selelct(finalI, finalJ)));
                this.gridPane.add(button,j,i);
            }
        }
    }//used in start and update, updates grids


    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
//        Button button1 = new Button();
//        button1.setStyle(
//                "-fx-font-family: Arial;" +
//                "-fx-font-size: " + BUTTON_FONT_SIZE + ";" +
//                        "-fx-background-color: " + ODD_COLOR + ";" +
//                        "-fx-font-weight: bold;");
//        button1.setText(String.valueOf(this.model.getGrids()[i][j]));
//        button1.setMinSize(ICON_SIZE, ICON_SIZE);
//        button1.setMaxSize(ICON_SIZE, ICON_SIZE);
        this.gridPane=new GridPane();
        this.setGrids();
        BorderPane pane = new BorderPane();
        this.message = new Label("Loaded: "+getParameters().getRaw().get(0));
        pane.setTop(this.message);//changes later
        pane.setCenter(this.gridPane);
        HBox hBox = new HBox();

        final FileChooser fileChooser = new FileChooser();
        Button load = new Button();
        load.setText("Load");//does nothing for now
        load.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                        File file = fileChooser.showOpenDialog(stage);
                        if (file != null) {
                            try {
                                model=new SlideModel("data/slide/"+file.toPath().getFileName().toString());
                                init();
                                update(model,null);
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                        message.setText("loaded: "+file.toPath().getFileName().toString());

                    }
                });
        Button reset = new Button();
        reset.setText("Reset");
        reset.setOnAction(event -> this.message.setText(model.reset()));
        Button hint = new Button();
        hint.setText("Hint");
        hint.setOnAction(event -> this.message.setText(model.hint()));
        hBox.getChildren().addAll(load,reset,hint);
        pane.setBottom(hBox);



        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setTitle("Slide GUI");
        stage.show();
    }//done, i think


    @Override
    public void update(SlideModel model, SlideClientData data) {
        this.model=model;
        this.setGrids();
    }


    public static void main(String[] args) {
        if(args.length==0){System.out.println("eh no filename try again");}
        else{
        Application.launch(args);
    }}
}
