package info233v2018.labuke11;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.Random;

public class App extends Application {
    private Geemu game;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        newGame(10, 3);

        TextField input = new TextField("Guess from 0 to " + game.getRange());
        TextField inputN = new TextField("Number range");
        TextField inputT = new TextField("Tries");
        Button startButton = new Button("Guess");
        Button newGameButton = new Button("New Game");

        BorderPane borderPane = new BorderPane();
        BorderPane borderPaneTest = new BorderPane();
        borderPane.setTop(input);
        borderPane.setCenter(startButton);
        borderPaneTest.setLeft(inputN);
        borderPaneTest.setRight(inputT);

        ListView<Label> labelListView = new ListView<>();
        borderPane.setBottom(labelListView);

        startButton.setOnAction(
                (event) -> {
                    Label label = new Label(game.guess(Integer.parseInt(input.getCharacters().toString())));
                    labelListView.getItems().add(label);
                    if (game.isGameEnded()) {
                        borderPane.setCenter(newGameButton);
                        borderPane.setTop(borderPaneTest);
                    }
                }
        );

        newGameButton.setOnAction(
                (event) -> {
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

    public void newGame(int t) {
        game = new Geemu(t);
    }

    public void newGame(int n, int t) {
        game = new Geemu(n, t);
    }
}
