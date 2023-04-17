package br.edu.femass.dao;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;

import br.edu.femass.model.Medico;

public class MedicoDao extends Persist implements Dao<Medico>{

    public MedicoDao() {
        super("medicos.json");
    }

    @Override
    public boolean gravar(Medico objeto) throws StreamWriteException, IOException {
        Set<Medico> medicos = buscar();

        boolean gravou = medicos.add(objeto);

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, medicos);
        return gravou;
    }

    @Override
    public boolean excluir(Medico objeto) throws StreamWriteException, DatabindException, IOException {
        Set<Medico> medicos = buscar();
        for (Medico medicoSelecionado: medicos){
            if (medicoSelecionado.equals(objeto)){
                medicoSelecionado.setAtivo(false);
            }
        }
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, medicos);   
        return true;
    }

    @Override
    public Set<Medico> buscar() throws DatabindException {
        try{
            Set<Medico> medicos = objectMapper.readValue(arquivo, new TypeReference<Set<Medico>>(){});
            Medico.atualizarUltimoId(medicos);
            return medicos;
            }catch(IOException ex){
                return new HashSet<Medico>();
            }
    }

    @Override
    public List<Medico> buscarAtivos() throws DatabindException {
        Set<Medico> medicos = buscar();

        List<Medico> medicosAtivos = medicos
        .stream()
        .filter(medico -> medico.getAtivo())
        .collect(Collectors.toList());
        
        return medicosAtivos;
    }
    
}
