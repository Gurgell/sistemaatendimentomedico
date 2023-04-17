package br.edu.femass.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AdministradorController implements Initializable {

    @FXML
    private void plano_btn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/PlanoSaude.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        scene.getRoot().setStyle("-fx-font-family: 'serif'");

        Stage stage = new Stage();
        stage.setTitle("Plano de saude");
        stage.setScene(scene);
        stage.show();

        stage.setResizable(false); //Não permitindo aumentar a tela
    }

    @FXML
    private void especialidade_btn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Especialidade.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        scene.getRoot().setStyle("-fx-font-family: 'serif'");

        Stage stage = new Stage();
        stage.setTitle("Especialidade");
        stage.setScene(scene);
        stage.show();

        stage.setResizable(false); //Não permitindo aumentar a tela
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    
}
