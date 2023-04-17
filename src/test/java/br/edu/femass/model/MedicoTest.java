package br.edu.femass.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    void construtorIncorreto(){
        List<Especialidade> especialidades = new ArrayList<>();
        especialidades.add(new Especialidade("Pediatra"));
        assertThrows(
            IllegalArgumentException.class,
            () -> new Medico("22443434", "12345", "Gabriel", "Gabriel@Gabriel", especialidades)
        );
    }

    @Test
    void medicoComUmaEspecialidade(){
        assertEquals(1 , medico.getEspecialidades().size());
    }

    @Test
    void medicoComDuasEspecialidades(){
        List<Especialidade> especialidades = new ArrayList<>();
        especialidades.add(new Especialidade("Pediatra"));
        especialidades.add(new Especialidade("Cirurgião"));
        medico.setEspecialidades(especialidades);
        
        assertEquals(2 , medico.getEspecialidades().size());
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
