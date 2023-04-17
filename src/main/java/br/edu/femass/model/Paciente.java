package br.edu.femass.model;

import java.util.Set;

import br.edu.femass.diversos.ValidadorCpf;

public class Paciente {
    private Long id;
    private String cpf;
    private String nome;
    private String endereco;
    private String email;
    private PlanoSaude plano;
    private boolean ativo;

    private static Long ultimoCodigo = 0L;

    public Paciente(){ 
    }

    public Paciente(String cpf, String nome, String email){
        if(ValidadorCpf.validarCPF(cpf) == false) throw new IllegalArgumentException("CPF Inválido");{}
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.ativo = true;

        this.id = ultimoCodigo + 1;
        ultimoCodigo++;   
    }

    public PlanoSaude getPlano() {
        return plano;
    }

    public void setPlano(PlanoSaude plano) {
        this.plano = plano;
    }

    public Long getId() {
        return id;
    }

    public String getCpf() {
        return cpf;
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
        Paciente other = (Paciente) obj;
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
        Paciente other = (Paciente) obj;
        if (cpf == null) {
            if (other.cpf != null)
                return false;
        } else if (!cpf.equals(other.cpf))
            return false;
        return true;
    }

    public static void atualizarUltimoId(Set<Paciente> pacientes){
        for (Paciente paciente: pacientes){
            if (paciente.getId().longValue() > ultimoCodigo){
                ultimoCodigo = paciente.getId();
            }
        }
    }
    
}
