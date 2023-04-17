package br.edu.femass.model;

import java.util.Set;

public class Especialidade {
    private String nome;
    private Long id;
    private boolean ativo;

    private static Long ultimoCodigo = 0L;

    public Especialidade(){
    }

    public Especialidade(String nome){
        this.nome = nome;
        this.ativo = true;

        this.id = ultimoCodigo + 1;
        ultimoCodigo++;   
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
        Especialidade other = (Especialidade) obj;
        return this.getNome().compareTo(other.getNome());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
        Especialidade other = (Especialidade) obj;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        return true;
    }

    public static void atualizarUltimoId(Set<Especialidade> especialidades){
        for (Especialidade especialidade: especialidades){
            if (especialidade.getId().longValue() > ultimoCodigo){
                ultimoCodigo = especialidade.getId();
            }
        }
    }
}
