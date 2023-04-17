package br.edu.femass.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.edu.femass.dao.Dao;
import br.edu.femass.dao.PacienteDao;
import br.edu.femass.dao.PlanoSaudeDao;
import br.edu.femass.diversos.MensagensJavaFx;
import br.edu.femass.model.Paciente;
import br.edu.femass.model.PlanoSaude;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;


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
    private ComboBox<PlanoSaude> cboPlano;

    @FXML
    private ListView<Paciente> listaPaciente;

    @FXML
    private Pane pane;

    private PacienteDao pacienteDao = new PacienteDao();
    private Dao<PlanoSaude> planoSaudeDao= new PlanoSaudeDao();

    @FXML
    private void gravar_btn(ActionEvent event) throws IOException {
        try {
            Paciente paciente = new Paciente(Txtcpf.getText(), TxtNome.getText(), TxtEmail.getText());

            paciente.setEndereco(TxtEndereco.getText());
            txtid.setText(paciente.getId().toString());

            paciente.setPlano((PlanoSaude) cboPlano.getSelectionModel().getSelectedItem());

            if(!(pacienteDao.gravar(paciente))){
                MensagensJavaFx.exibirMensagem("Não foi possível gravar o paciente!");
                return;
            }

            Txtcpf.setText("");
            TxtEmail.setText("");
            TxtEndereco.setText("");
            TxtNome.setText("");
            txtid.setText("");
            cboPlano.getSelectionModel().select(null);

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
        cboPlano.getSelectionModel().select(paciente.getPlano());

    }

    public void exibirPlanos(){
        try {
            ObservableList<PlanoSaude> data = FXCollections.observableArrayList(planoSaudeDao.buscarAtivos());
            
            cboPlano.setItems(data);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
        Paciente paciente = listaPaciente.getSelectionModel().getSelectedItem();
            if(paciente == null) return;
            try{
                if(!(pacienteDao.excluir(paciente))){
                    MensagensJavaFx.exibirMensagem("Não foi possível excluir o paciente selecionado!");
                }
                Txtcpf.setText("");
                TxtEmail.setText("");
                TxtEndereco.setText("");
                TxtNome.setText("");
                txtid.setText("");
                cboPlano.getSelectionModel().select(null);

                exibirPacientes();
            }catch(Exception e){
                e.printStackTrace();
            }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        exibirPacientes();
        exibirPlanos();
        pane.setOnMouseClicked(event -> {
            Txtcpf.setText("");
            TxtEmail.setText("");
            TxtEndereco.setText("");
            TxtNome.setText("");
            txtid.setText("");
            cboPlano.getSelectionModel().select(null);
            listaPaciente.getSelectionModel().clearSelection(); 
        });
    }

}
