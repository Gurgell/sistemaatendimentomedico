package br.edu.femass.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;


import br.edu.femass.model.Agenda;
import br.edu.femass.model.Especialidade;
import br.edu.femass.model.Medico;
import br.edu.femass.dao.AgendaDao;
import br.edu.femass.dao.Dao;
import br.edu.femass.dao.PacienteDao;
import br.edu.femass.dao.MedicoDao;
import br.edu.femass.diversos.MensagensJavaFx;
import br.edu.femass.model.Paciente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;


public class AgendaController implements Initializable {

    //Pane visualizar agenda por dia
    @FXML
    private TextField txtAgendaDia;

    @FXML 
    private ListView<Agenda> listaAgendaDia;

    @FXML
    private void btn_BuscarAgenda(ActionEvent event) throws IOException{
        exibirAgendasPorDia();
    }

    //Pane visualizar agenda por medico
    @FXML
    private void btn_BuscarAgendaMedico(ActionEvent event) throws IOException{
        exibirAgendasPorDataEMedico();
    }

    @FXML
    private TextField txtAgendaMedicoDia;

    @FXML
    private ComboBox<Medico> cboMedicoAgenda;

    @FXML 
    private ListView<Agenda> listaAgendaMedico;

    //Pane Agendar
    @FXML
    private TextField txtData;

    @FXML
    private TextField txtHora;

    @FXML
    private TextField txtPlanoSaude;

    @FXML
    private CheckBox checkParticular;

    @FXML
    private ComboBox<Medico> cboMedico;

    @FXML
    private ComboBox<Paciente> cboPaciente;

    @FXML
    private ListView<Especialidade> listaEspecialidade;

    @FXML
    private TextField horarioAgenda;

    @FXML
    private TabPane tabPane;
    
    @FXML
    private ListView<Agenda> listaAgenda;

    private Dao<Paciente> pacienteDao = new PacienteDao();
    private Dao<Medico> medicoDao = new MedicoDao();
    private Dao<Agenda> agendaDao = new AgendaDao();

    
    @FXML
    private void listaAgendamento_keyPressed(KeyEvent event){
        exibirDados();
    }
    @FXML
    private void listaAgendamento_mouseClicked(MouseEvent event){
        exibirDados();
    }
    
    @FXML
    private void gravar_btn(ActionEvent event) throws IOException {
        try {
            Agenda agenda = new Agenda(txtData.getText() , txtHora.getText(), cboMedico.getSelectionModel().getSelectedItem(), cboPaciente.getSelectionModel().getSelectedItem(), listaEspecialidade.getSelectionModel().getSelectedItem());
            

            if ((checkParticular.isSelected()) && (!(agenda.getPaciente().getPlano() == null))){ //caso particular esteja selecionado e paciente tenha plano
                agenda.setParticular(true);
                agenda.setPlano(null);
            }
            else if (checkParticular.isSelected()){ //caso particular esteja selecionado e paciente não tenha plano
                agenda.setParticular(true);
            }
            else if (!(checkParticular.isSelected()) && (!(agenda.getPaciente().getPlano() == null))){ //Caso particular não esteja selecionado e paciente tenha plano
                agenda.setParticular(false);
                agenda.setPlano(agenda.getPaciente().getPlano());
            }
            else if ((!checkParticular.isSelected()) && ((agenda.getPaciente().getPlano() == null))){ // Caso particular não esteja selecionado e paciente não tenha plano
                MensagensJavaFx.exibirMensagem("Paciente não possui plano! ");
                return;
            }

            if (cboMedico.getSelectionModel().getSelectedItem() == null){
                MensagensJavaFx.exibirMensagem("Não é possível agendar sem médico!");
                return;
            }
            
            if(!(agendaDao.gravar(agenda))){
                MensagensJavaFx.exibirMensagem("Não foi possível gravar a agenda!");
                return;
            }

            txtData.setText("");
            txtHora.setText("");
            cboPaciente.getSelectionModel().select(null);
            cboMedico.getSelectionModel().select(null);
            listaEspecialidade.setItems(null);
            txtPlanoSaude.setText("");
            checkParticular.setSelected(false);

            exibirAgendas();
            
        } catch (Exception error) {
            MensagensJavaFx.exibirMensagem(error.getMessage());
        }
        
    }      

    public void exibirAgendas(){
        try {
            ObservableList<Agenda> data = FXCollections.observableArrayList(agendaDao.buscarAtivos()); 

            listaAgenda.setItems(data);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void exibirAgendasPorDataEMedico(){
        try {
            Medico medicoSelecionado = cboMedicoAgenda.getSelectionModel().getSelectedItem();
            if (medicoSelecionado != null) {
                List<Agenda> agendas;
                agendas = agendaDao.buscarAtivos();
                List<Agenda> agendasPorMedico = agendas.stream()
                .filter(agenda -> agenda.getMedico().getNome().equals(medicoSelecionado.getNome()))
                .collect(Collectors.toList());
                
                List<Agenda> agendasPorDataEMedico = agendasPorMedico.stream()
                .filter(agenda -> agenda.getData().equals(txtAgendaMedicoDia.getText()))
                .collect(Collectors.toList());

                ObservableList<Agenda> data = FXCollections.observableArrayList(agendasPorDataEMedico); 
                listaAgendaMedico.setItems(data);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void exibirAgendasPorDia(){
        try {
            if (txtAgendaDia.getText() != null) {
                List<Agenda> agendas;
                agendas = agendaDao.buscarAtivos();
                List<Agenda> agendasPorDia = agendas.stream()
                .filter(agenda -> agenda.getData().equals(txtAgendaDia.getText()))
                .collect(Collectors.toList());
                
                ObservableList<Agenda> data = FXCollections.observableArrayList(agendasPorDia); 
                listaAgendaDia.setItems(data);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void exibirDados(){
        Agenda agenda = listaAgenda.getSelectionModel().getSelectedItem();
        if (agenda==null) return;

        cboPaciente.getSelectionModel().select(agenda.getPaciente());
        cboMedico.getSelectionModel().select(agenda.getMedico());
        listaEspecialidade.getSelectionModel().select(agenda.getEspecialidade());
        txtData.setText(agenda.getData());
        txtHora.setText(agenda.getHora());

        if (agenda.getParticular() == true){
            checkParticular.setSelected(true);
            txtPlanoSaude.setText("");
        }
        else{
            checkParticular.setSelected(false);
            txtPlanoSaude.setText(agenda.getPlano().toString());
        }
    }

    public void exibirMedicosAgenda(){
        try {
            ObservableList<Medico> data = FXCollections.observableArrayList(medicoDao.buscarAtivos()); 

            cboMedicoAgenda.setItems(data);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void exibirMedicos(){
        try {
            ObservableList<Medico> data = FXCollections.observableArrayList(medicoDao.buscarAtivos()); 

            cboMedico.setItems(data);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void exibirPacientes(){
        try {
            ObservableList<Paciente> data = FXCollections.observableArrayList(pacienteDao.buscarAtivos()); 

            cboPaciente.setItems(data);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void excluir_btn(ActionEvent event) throws IOException {
        Agenda agenda = listaAgenda.getSelectionModel().getSelectedItem();
            if(agenda == null) return;
            try{
                if(!(agendaDao.excluir(agenda))){
                    MensagensJavaFx.exibirMensagem("Não foi possível excluir a agenda selecionado!");
                }
                txtData.setText("");
                txtHora.setText("");
                cboPaciente.getSelectionModel().select(null);
                cboMedico.getSelectionModel().select(null);
                listaEspecialidade.setItems(null);
                txtPlanoSaude.setText("");
                checkParticular.setSelected(false);
                exibirAgendas();
            }catch(Exception e){
                e.printStackTrace();
            }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        exibirAgendas();
        exibirMedicos();
        exibirPacientes();
        exibirMedicosAgenda();

        tabPane.setOnMouseClicked(event -> {
            //Limpando pane crud agenda
            txtData.setText("");
            txtHora.setText("");
            txtPlanoSaude.setText("");
            cboPaciente.getSelectionModel().select(null);
            cboMedico.getSelectionModel().select(null);
            listaEspecialidade.setItems(null);
            listaAgenda.getSelectionModel().clearSelection(); 
            checkParticular.setSelected(false);

            //Limpando pane Agenda por médico por dia
            cboMedicoAgenda.getSelectionModel().select(null);
            listaAgendaMedico.setItems(null);
            txtAgendaMedicoDia.setText("");

            //Limpando pane agenda por dia
            txtAgendaDia.setText("");
            listaAgendaDia.setItems(null);
        });
        
        //Quando selecionar o médico no combobox, aparecer suas especialidades no listView
        cboMedico.setOnAction(event -> {          
            Medico medicoSelecionado = cboMedico.getSelectionModel().getSelectedItem();
            if (medicoSelecionado != null) {
                ObservableList<Especialidade> especialidades = FXCollections.observableArrayList(medicoSelecionado.getEspecialidades());
                listaEspecialidade.setItems(especialidades);
            } else {
                listaEspecialidade.getSelectionModel().select(null);
            }
        });

        //Quando selecionar o paciente no combobox, já vai aparecer o plano que ele possui
        cboPaciente.setOnAction(event -> {          
            Paciente pacienteSelecionado = cboPaciente.getSelectionModel().getSelectedItem();
            if (pacienteSelecionado != null) {
                if (!(pacienteSelecionado.getPlano() == null))
                    txtPlanoSaude.setText(pacienteSelecionado.getPlano().toString());
                else
                    txtPlanoSaude.setText("");
            } else {
                txtPlanoSaude.setText("");
            }
            
        });

        //Caso o combobox de particular esteja marcado, apagar plano
        checkParticular.setOnAction(event -> {
            Paciente pacienteSelecionado = cboPaciente.getSelectionModel().getSelectedItem();
            if (checkParticular.isSelected()) {
                txtPlanoSaude.setText("");
            } else {
                if (pacienteSelecionado.getPlano() == null)
                    txtPlanoSaude.setText("");
                else
                    txtPlanoSaude.setText(pacienteSelecionado.getPlano().toString());
            }
        });
    }
}
