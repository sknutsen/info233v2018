package info233v2018.labuke11;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class App extends Application {
    private Geemu game;
    private BorderPane borderPaneTest;
    private TextField inputN;
    private TextField inputT;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane bp = new BorderPane();
        borderPaneTest = new BorderPane();
        Button okButton = new Button("Start Game");
        inputN = new TextField("Number range");
        inputT = new TextField("Tries");

        bp.setTop(borderPaneTest);
        bp.setCenter(okButton);
        borderPaneTest.setLeft(inputN);
        borderPaneTest.setRight(inputT);

        okButton.setOnAction(
                (event) -> {
                    newGame(Integer.parseInt(inputN.getCharacters().toString()),
                            Integer.parseInt(inputT.getCharacters().toString()));

                    TextField input = new TextField("Guess from 0 to " + game.getRange());
                    Button startButton = new Button("Guess");
                    Button newGameButton = new Button("New Game");
                    ListView<Label> labelListView = new ListView<>();
                    BorderPane borderPane = new BorderPane();

                    borderPane.setTop(input);
                    borderPane.setCenter(startButton);
                    borderPane.setBottom(labelListView);

                    startButton.setOnAction(
                            (event1) -> {
                                Label label = new Label(game.guess(Integer.parseInt(input.getCharacters().toString())));
                                labelListView.getItems().add(label);
                                if (game.isGameEnded()) {
                                    borderPane.setCenter(newGameButton);
                                    borderPane.setTop(borderPaneTest);
                                }
                            }
                    );

                    newGameButton.setOnAction(
                            (event1) -> {
                                newGame(Integer.parseInt(inputN.getCharacters().toString()),
                                        Integer.parseInt(inputT.getCharacters().toString()));
                                borderPane.setCenter(startButton);
                                borderPane.setTop(input);
                            }
                    );

                    Scene mainScene = new Scene(borderPane, 400,600);

                    primaryStage.setTitle("Guessing Game");
                    primaryStage.setScene(mainScene);
                    primaryStage.show();
                }
        );

        Scene popupScene = new Scene(bp, 400, 200);
        popupScene.getStylesheets().add("info233v2018/labuke11/Stylesheet.css");

        primaryStage.setTitle("Specify range and attempts");
        primaryStage.setScene(popupScene);
        primaryStage.show();
    }

    private void newGame(int n, int t) {
        game = new Geemu(n, t);
    }
}
