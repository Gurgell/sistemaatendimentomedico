package br.edu.femass.dao;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;

import br.edu.femass.model.Paciente;

public class PacienteDao extends Persist implements Dao<Paciente>{

    public PacienteDao() {
        super("pacientes.json");
    }

    @Override
    public boolean gravar(Paciente objeto) throws StreamWriteException, IOException {
        Set<Paciente> pacientes = buscar();

        boolean gravou = pacientes.add(objeto);

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, pacientes);
        return gravou;
    }

    @Override
    public boolean excluir(Paciente objeto) throws StreamWriteException, DatabindException, IOException {
        Set<Paciente> pacientes = buscar();
        //boolean gravou = Pacientes.remove(Paciente);
        for (Paciente PacienteSelecionado: pacientes){
            if (PacienteSelecionado.equals(objeto)){
                PacienteSelecionado.setAtivo(false);
            }
        }
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, pacientes);   
        return true;
    }

    @Override
    public Set<Paciente> buscar() throws DatabindException {
        try{
            Set<Paciente> pacientes = objectMapper.readValue(arquivo, new TypeReference<Set<Paciente>>(){});
            Paciente.atualizarUltimoId(pacientes);
            return pacientes;
            }catch(IOException ex){
                return new HashSet<Paciente>();
            }
    }

    @Override
    public List<Paciente> buscarAtivos() throws DatabindException {
        Set<Paciente> pacientes = buscar();

        List<Paciente> pacientesAtivos = pacientes
        .stream()
        .filter(paciente -> paciente.getAtivo())
        .collect(Collectors.toList());
        
        return pacientesAtivos;
    }
    
}
