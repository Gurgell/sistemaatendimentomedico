package br.edu.femass.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.edu.femass.dao.EspecialidadeDao;
import br.edu.femass.diversos.MensagensJavaFx;
import br.edu.femass.model.Especialidade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class EspecialidadeController implements Initializable{
    @FXML
    private TextField TxtNomeEspecialidade;

    @FXML
    private TextField txtid;

    @FXML
    private ListView<Especialidade> listaEspecialidade;

    @FXML
    private Pane pane;

    private EspecialidadeDao especialidadeDao = new EspecialidadeDao();
    
    @FXML
    private void gravar_btn(ActionEvent event) throws IOException {
        try {
            Especialidade especialidade = new Especialidade(TxtNomeEspecialidade.getText());

            txtid.setText(especialidade.getId().toString());

            if(!(especialidadeDao.gravar(especialidade))){
                MensagensJavaFx.exibirMensagem("Não foi possível gravar o plano de Saúde!");
                return;
            }

            TxtNomeEspecialidade.setText("");
            txtid.setText("");

            exibirEspecialidades();
            
        } catch (Exception error) {
            MensagensJavaFx.exibirMensagem(error.getMessage());
        }
        
    }    

    @FXML
    private void listaEspecialidade_keyPressed(KeyEvent event){
        exibirDados();
    }
    @FXML
    private void listaEspecialidade_mouseClicked(MouseEvent event){
        exibirDados();
    }
    

    private void exibirDados(){
        Especialidade especialidade = listaEspecialidade.getSelectionModel().getSelectedItem();
        if (especialidade==null) return;

        txtid.setText(especialidade.getId().toString());
        TxtNomeEspecialidade.setText(especialidade.getNome());
    }

    public void exibirEspecialidades(){
        try {
            ObservableList<Especialidade> data = FXCollections.observableArrayList(especialidadeDao.buscarAtivos()); //Convertendo list de planos de saude do dao para observable array list, o tipo de lista do javafx.

            listaEspecialidade.setItems(data);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    
    }

    @FXML
    private void excluir_btn(ActionEvent event) throws IOException {
        Especialidade especialidade = listaEspecialidade.getSelectionModel().getSelectedItem();
            if(especialidade == null) return;
            try{
                if(!(especialidadeDao.excluir(especialidade))){
                    MensagensJavaFx.exibirMensagem("Não foi possível excluir o especialidade selecionado!");
                }
                TxtNomeEspecialidade.setText("");
                txtid.setText("");
                listaEspecialidade.getSelectionModel().clearSelection(); 
                exibirEspecialidades();
            }catch(Exception e){
                e.printStackTrace();
            }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        exibirEspecialidades();
        pane.setOnMouseClicked(event -> {
            TxtNomeEspecialidade.setText("");
            txtid.setText("");
            listaEspecialidade.getSelectionModel().clearSelection(); 
            
        });
    }

}
