package br.edu.femass.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.edu.femass.dao.EspecialidadeDao;
import br.edu.femass.dao.MedicoDao;
import br.edu.femass.diversos.MensagensJavaFx;
import br.edu.femass.model.Especialidade;
import br.edu.femass.model.Medico;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;


public class MedicoController implements Initializable {
    
    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCrm;

    @FXML
    private TextField txtEndereco;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtid;

    @FXML
    private TextField txtCpf;

    @FXML
    private ListView<Medico> listaMedico;

    @FXML
    private Pane pane;

    @FXML
    private ListView<Especialidade> listaEspecialidade;

    private MedicoDao MedicoDao = new MedicoDao();
    private EspecialidadeDao especialidadeDao = new EspecialidadeDao();
    
    @FXML
    private void gravar_btn(ActionEvent event) throws IOException {
        try {
            Medico medico = new Medico(txtCpf.getText(), txtCrm.getText(), txtNome.getText(), txtEmail.getText(), listaEspecialidade.getSelectionModel().getSelectedItems());

            medico.setEndereco(txtEndereco.getText());
            txtid.setText(medico.getId().toString());

            if(!(MedicoDao.gravar(medico))){
                MensagensJavaFx.exibirMensagem("Não foi possível gravar o medico!");
                return;
            }

            txtCpf.setText("");
            txtCrm.setText("");
            txtEmail.setText("");
            txtEndereco.setText("");
            txtNome.setText("");
            txtid.setText("");
            listaEspecialidade.getSelectionModel().select(null);

            exibirMedicos();
            exibirEspecialidades();
            
        } catch (Exception error) {
            MensagensJavaFx.exibirMensagem(error.getMessage());
        }
        
    }    

    @FXML
    private void listaMedico_keyPressed(KeyEvent event){
        exibirDados();
    }
    @FXML
    private void listaMedico_mouseClicked(MouseEvent event){
        exibirDados();
    }

    private void exibirDados(){
        Medico medico = listaMedico.getSelectionModel().getSelectedItem();
        
        if (medico==null) return;

        txtCpf.setText(medico.getCpf());
        txtid.setText(medico.getId().toString());
        txtCrm.setText(medico.getCrm());
        txtEmail.setText(medico.getEmail());
        txtEndereco.setText(medico.getEndereco());
        txtNome.setText(medico.getNome());

        ObservableList<Especialidade> especialidades = FXCollections.observableArrayList(medico.getEspecialidades());
        listaEspecialidade.setItems(especialidades);

    }

    public void exibirMedicos(){
        try {
            ObservableList<Medico> data = FXCollections.observableArrayList(MedicoDao.buscarAtivos()); 

            listaMedico.setItems(data);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void exibirEspecialidades(){
        try {
            ObservableList<Especialidade> data = FXCollections.observableArrayList(especialidadeDao.buscarAtivos()); 

            listaEspecialidade.setItems(data);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void excluir_btn(ActionEvent event) throws IOException {
        Medico medico = listaMedico.getSelectionModel().getSelectedItem();
            if(medico == null) return;
            try{
                if(!(MedicoDao.excluir(medico))){
                    MensagensJavaFx.exibirMensagem("Não foi possível excluir o medico selecionado!");
                }
                txtCpf.setText("");
                txtCrm.setText("");
                txtEmail.setText("");
                txtEndereco.setText("");
                txtNome.setText("");
                txtid.setText("");
                listaEspecialidade.setItems(null);
                exibirMedicos();
            }catch(Exception e){
                e.printStackTrace();
            }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        exibirMedicos();
        exibirEspecialidades();
        listaEspecialidade.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
        //Método para que quando clicar fora da lista de médicos, zere todas as informações na direita, mostre novamente todas as especialidades disponiveis e também desmarque o médico escolhido
        pane.setOnMouseClicked(event -> {
            txtCpf.setText("");
            txtCrm.setText("");
            txtEmail.setText("");
            txtEndereco.setText("");
            txtNome.setText("");
            txtid.setText("");
            listaEspecialidade.getSelectionModel().select(null);
            listaMedico.getSelectionModel().clearSelection(); 
            exibirEspecialidades();
        });       
    }
}
