package br.edu.femass.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.edu.femass.dao.PlanoSaudeDao;
import br.edu.femass.diversos.MensagensJavaFx;
import br.edu.femass.model.PlanoSaude;
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

public class PlanoSaudeController implements Initializable {
    @FXML
    private TextField TxtNomePlano;

    @FXML
    private TextField txtid;

    @FXML
    private Pane pane;

    @FXML
    private ListView<PlanoSaude> listaPlanoSaude;

    private PlanoSaudeDao planoSaudeDao = new PlanoSaudeDao();
    
    @FXML
    private void gravar_btn(ActionEvent event) throws IOException {
        try {
            PlanoSaude planoSaude = new PlanoSaude(TxtNomePlano.getText());

            txtid.setText(planoSaude.getId().toString());

            if(!(planoSaudeDao.gravar(planoSaude))){
                MensagensJavaFx.exibirMensagem("Não foi possível gravar o plano de Saúde!");
                return;
            }

            TxtNomePlano.setText("");
            txtid.setText("");

            exibirPlanos();
            
        } catch (Exception error) {
            MensagensJavaFx.exibirMensagem(error.getMessage());
        }
        
    }    

    @FXML
    private void listaPlano_keyPressed(KeyEvent event){
        exibirDados();
    }
    @FXML
    private void listaPlano_mouseClicked(MouseEvent event){
        exibirDados();
    }
    

    private void exibirDados(){
        PlanoSaude planoSaude = listaPlanoSaude.getSelectionModel().getSelectedItem();
        if (planoSaude==null) return;

        txtid.setText(planoSaude.getId().toString());
        TxtNomePlano.setText(planoSaude.getNome());
    }

    public void exibirPlanos(){
        try {
            ObservableList<PlanoSaude> data = FXCollections.observableArrayList(planoSaudeDao.buscarAtivos()); //Convertendo list de planos de saude do dao para observable array list, o tipo de lista do javafx.

            listaPlanoSaude.setItems(data);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    
    }

    @FXML
    private void excluir_btn(ActionEvent event) throws IOException {
        PlanoSaude planoSaude = listaPlanoSaude.getSelectionModel().getSelectedItem();
            if(planoSaude == null) return;
            try{
                if(!(planoSaudeDao.excluir(planoSaude))){
                    MensagensJavaFx.exibirMensagem("Não foi possível excluir o planoSaude selecionado!");
                }
                txtid.setText("");
                TxtNomePlano.setText("");
                listaPlanoSaude.getSelectionModel().clearSelection(); 
                exibirPlanos();
            }catch(Exception e){
                e.printStackTrace();
            }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        exibirPlanos();

        pane.setOnMouseClicked(event -> {
            txtid.setText("");
            TxtNomePlano.setText("");
            listaPlanoSaude.getSelectionModel().clearSelection(); 
        });  
    }

}
