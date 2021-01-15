package gui;

import db.Controller;
import db.DBAdapter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Model;

import java.sql.SQLException;

public class Main extends Application {
    public static void main(String[] args) {
        DBAdapter db = new DBAdapter();

        try {
            db.resetDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Model model = new Model(db);
        Controller controller = new Controller();
        //controller.getInvoiceData();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("View.fxml"));
        Parent root = loader.load();
        primaryStage.setScene(new Scene(root, 900,800));
        primaryStage.show();
    }
}
