package br.edu.femass.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class EspecialidadeTest {

    @Test
    public void testGetters() {
        Especialidade especialidade = new Especialidade("Oftalmologista");
        assertEquals("Oftalmologista", especialidade.getNome());

        assertTrue(especialidade.getAtivo());
    }

    @Test
    public void testSetters() {
        Especialidade especialidade = new Especialidade("Oftalmologista");
        especialidade.setAtivo(false);
        assertFalse(especialidade.getAtivo());
    }
}
