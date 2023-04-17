package br.edu.femass.model;

import java.util.Objects;

public class Agenda {
    private Long id;
    private String data;
    private String hora;
    private Medico medico;
    private Paciente paciente;
    private Especialidade especialidade;
    private Boolean ativo;
    private boolean particular;
    private PlanoSaude plano;

    private static Long ultimoCodigo = 0L;
    
    public Agenda(){
    }

    public Agenda(String data, String hora, Medico medico, Paciente paciente , Especialidade especialidade){

        this.data = data;
        this.hora = hora;
        this.paciente = paciente;
        this.medico = medico;
        this.ativo = true;
        this.especialidade = especialidade;

        this.id = ultimoCodigo + 1;
        ultimoCodigo++;
    }

    public PlanoSaude getPlano() {
        return plano;
    }

    public void setPlano(PlanoSaude plano) {
        this.plano = plano;
    }

    public boolean getParticular() {
        return particular;
    }

    public void setParticular(boolean particular) {
        this.particular = particular;
    }

    public Especialidade getEspecialidade(){
        return especialidade;
    }

    public Long getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    public String getHora() {
        return hora;
    }

    public Medico getMedico() {
        return medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
    
    @Override
    public String toString() {
        return  "Data e hora: " + data + " - " +  hora  +
        "\nMedico = " + medico.getNome() + 
        "\nPaciente = " + paciente.getNome();
    }

    public int compareTo(Object obj){
        if (this == obj)
            return -1;  
        if (obj == null)
            return -1;
        if (getClass() != obj.getClass())
            return -1;
        Agenda other = (Agenda) obj;
        return this.getId().compareTo(other.getId()); 
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Objects.hash(id, data, hora, medico, paciente);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Agenda other = (Agenda) obj;
        if (!id.equals(other.id))
            return false;
        if (!data.equals(other.data))
            return false;
        if (!hora.equals(other.hora))
            return false;
        if (!medico.equals(other.medico))
            return false;
        if (!paciente.equals(other.paciente))
            return false;
        return true;
    }
    
}
