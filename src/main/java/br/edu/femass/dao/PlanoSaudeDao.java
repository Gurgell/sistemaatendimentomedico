package br.edu.femass.dao;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;

import br.edu.femass.model.PlanoSaude;

public class PlanoSaudeDao extends Persist implements Dao<PlanoSaude>{
    public PlanoSaudeDao() {
        super("planosSaude.json");
    }

    @Override
    public boolean gravar(PlanoSaude objeto) throws StreamWriteException, IOException {
        Set<PlanoSaude> planosSaude = buscar();

        boolean gravou = planosSaude.add(objeto);

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, planosSaude);
        return gravou;
    }

    @Override
    public boolean excluir(PlanoSaude objeto) throws StreamWriteException, DatabindException, IOException {
        Set<PlanoSaude> planosSaude = buscar();
        for (PlanoSaude planoSelecionado: planosSaude){
            if (planoSelecionado.equals(objeto)){
                planoSelecionado.setAtivo(false);
            }
        }
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, planosSaude);   
        return true;
    }

    @Override
    public Set<PlanoSaude> buscar() throws DatabindException {
        try{
            Set<PlanoSaude> planosSaude = objectMapper.readValue(arquivo, new TypeReference<Set<PlanoSaude>>(){});
            PlanoSaude.atualizarUltimoId(planosSaude);
            return planosSaude;
            }catch(IOException ex){
                return new HashSet<PlanoSaude>();
            }
    }

    @Override
    public List<PlanoSaude> buscarAtivos() throws DatabindException {
        Set<PlanoSaude> planosSaude = buscar();

        List<PlanoSaude> planosAtivos = planosSaude
        .stream()
        .filter(planoSaude -> planoSaude.getAtivo())
        .collect(Collectors.toList());
        
        return planosAtivos;
    }
    
}
