package br.edu.femass.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.edu.femass.dao.PacienteDao;
import br.edu.femass.diversos.MensagensJavaFx;
import br.edu.femass.model.Paciente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;


public class PacienteController implements Initializable {
    
    @FXML
    private TextField TxtNome;

    @FXML
    private TextField Txtcpf;

    @FXML
    private TextField TxtEndereco;

    @FXML
    private TextField TxtEmail;

    @FXML
    private TextField txtid;

    @FXML
    private ListView<Paciente> listaPaciente;

    private PacienteDao pacienteDao = new PacienteDao();
    
    @FXML
    private void gravar_btn(ActionEvent event) throws IOException {
        try {
            Paciente paciente = new Paciente(Txtcpf.getText(), TxtNome.getText(), TxtEmail.getText());

            paciente.setEndereco(TxtEndereco.getText());
            txtid.setText(paciente.getId().toString());

            if(!(pacienteDao.gravar(paciente))){
                MensagensJavaFx.exibirMensagem("Não foi possível gravar o paciente!");
                return;
            }

            Txtcpf.setText("");
            TxtEmail.setText("");
            TxtEndereco.setText("");
            TxtNome.setText("");
            txtid.setText("");

            exibirPacientes();
            
        } catch (Exception error) {
            MensagensJavaFx.exibirMensagem(error.getMessage());
        }
        
    }    

    @FXML
    private void listaPaciente_keyPressed(KeyEvent event){
        exibirDados();
    }
    @FXML
    private void listaPaciente_mouseClicked(MouseEvent event){
        exibirDados();
    }
    

    private void exibirDados(){
        Paciente paciente = listaPaciente.getSelectionModel().getSelectedItem();
        if (paciente==null) return;

        txtid.setText(paciente.getId().toString());
        Txtcpf.setText(paciente.getCpf());
        TxtEmail.setText(paciente.getEmail());
        TxtEndereco.setText(paciente.getEndereco());
        TxtNome.setText(paciente.getNome());
    }

    public void exibirPacientes(){
        try {
            ObservableList<Paciente> data = FXCollections.observableArrayList(pacienteDao.buscarAtivos()); //Convertendo list de Pacientes do dao para observable array list, o tipo de lista do javafx.

            listaPaciente.setItems(data);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    
    }

    @FXML
    private void excluir_btn(ActionEvent event) throws IOException {
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        exibirPacientes();
    }

}
