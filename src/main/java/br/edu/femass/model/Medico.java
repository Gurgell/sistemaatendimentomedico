package br.edu.femass.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import br.edu.femass.diversos.ValidadorCpf;

public class Medico {
    private Long id;
    private String crm;
    private String cpf;
    private String nome;
    private String endereco;
    private String email;
    private List<Especialidade> especialidades = new ArrayList<Especialidade>();
    private boolean ativo;

    private static Long ultimoId = 0L;

    public Medico(){  
    }

    public Medico(String cpf, String crm, String nome, String email, List<Especialidade> especialidades){
        if(ValidadorCpf.validarCPF(cpf) == false) throw new IllegalArgumentException("CPF Inválido");{}
        
        this.cpf = cpf;
        this.crm = crm;
        this.nome = nome;
        this.email = email;
        this.ativo = true;
        this.especialidades = especialidades;

        this.id = ultimoId + 1;
        ultimoId++;   
    }
    
    public List<Especialidade> getEspecialidades() {
        return especialidades;
    }

    public String getCpf(){
        return cpf;
    }

    public Long getId() {
        return id;
    }

    public String getCrm() {
        return crm;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEspecialidades(List<Especialidade> especialidades) {
        this.especialidades = especialidades;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }


    @Override
    public String toString(){
        return this.nome;
    }

    public int compareTo(Object obj){
        if (this == obj)
            return -1;
        if (obj == null)
            return -1;
        if (getClass() != obj.getClass())
            return -1;
        Medico other = (Medico) obj;
        return this.getNome().compareTo(other.getNome());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
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
        Medico other = (Medico) obj;
        if (cpf == null) {
            if (other.cpf != null)
                return false;
        } else if (!cpf.equals(other.cpf))
            return false;
        return true;
    }

    public static void atualizarUltimoId(Set<Medico> medicos){
        for (Medico medico: medicos){
            if (medico.getId().longValue() > ultimoId){
                ultimoId = medico.getId();
            }
        }
    }
}
