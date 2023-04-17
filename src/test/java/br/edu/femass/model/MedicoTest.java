package br.edu.femass.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MedicoTest {

    private Medico medico;

    @BeforeEach
    public void beforeEach() {
        List<Especialidade> especialidades = new ArrayList<>();
        especialidades.add(new Especialidade("Cirurgião"));
        medico = new Medico("19149480766", "1234567", "fulano", "fulano@fulano", especialidades);
        
    }
    
    @Test
    public void testGetters() {
        List<Especialidade> especialidades = new ArrayList<>();
        especialidades.add(new Especialidade("Cirurgião"));

        assertEquals("19149480766", medico.getCpf());
        assertEquals("1234567", medico.getCrm());
        assertEquals("fulano", medico.getNome());
        assertEquals("fulano@fulano", medico.getEmail());
        assertEquals(especialidades, medico.getEspecialidades());
    }

    @Test
    public void testSetters() {
        medico.setEndereco("rua treze");;
        assertEquals("rua treze", medico.getEndereco());

        medico.setAtivo(false);
        assertFalse(medico.getAtivo());
    }
}
