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

public class AtendenteController implements Initializable{

    @FXML
        private void paciente_btn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Paciente.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        scene.getRoot().setStyle("-fx-font-family: 'serif'");

        Stage stage = new Stage();
        stage.setTitle("Gerenciar Pacientes");
        stage.setScene(scene);
        stage.show();

        stage.setResizable(false); //Não permitindo aumentar a tela
    }

    @FXML
    private void medico_btn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Medico.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        scene.getRoot().setStyle("-fx-font-family: 'serif'");

        Stage stage = new Stage();
        stage.setTitle("Gerenciar Médicos");
        stage.setScene(scene);
        stage.show();

        stage.setResizable(false); //Não permitindo aumentar a tela
    }

    @FXML
    private void agenda_btn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Agenda.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        scene.getRoot().setStyle("-fx-font-family: 'serif'");

        Stage stage = new Stage();
        stage.setTitle("Gerenciar Agendas");
        stage.setScene(scene);
        stage.show();

        stage.setResizable(false); //Não permitindo aumentar a tela
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        

    }
    
}
