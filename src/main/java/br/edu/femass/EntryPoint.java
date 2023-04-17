package br.edu.femass;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class EntryPoint extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Main.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        scene.getRoot().setStyle("-fx-font-family: 'serif'");

        stage.setTitle("Menu Principal");
        stage.setScene(scene);
        stage.show();

        stage.setResizable(false); //Não permitindo aumentar a tela
    }

    public static void main(String[] args) {
        launch(args);
    }

}
