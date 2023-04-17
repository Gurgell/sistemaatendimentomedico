package br.edu.femass.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlanoSaudeTest {
    private PlanoSaude planoSaude;

    @BeforeEach
    public void beforeEach() {
        planoSaude = new PlanoSaude("Bradesco");

    }
    
    @Test
    public void testGetters() {
        assertEquals("Bradesco", planoSaude.getNome());
        assertTrue(planoSaude.getAtivo());
    }

    @Test
    public void testSetters() {
        planoSaude.setAtivo(false);
        assertFalse(planoSaude.getAtivo());

        planoSaude.setNome("Unimed");
        assertEquals("Unimed", planoSaude.getNome());
    }
}
