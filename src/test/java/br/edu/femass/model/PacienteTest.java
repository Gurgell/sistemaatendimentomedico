package br.edu.femass.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PacienteTest {
    private Paciente paciente;

    @BeforeEach
    public void beforeEach() {
        paciente = new Paciente("19149480766", "Gabriel", "Gurgel@Gurgel");
    }

    @Test
    void construtorIncorreto(){
        assertThrows(
            IllegalArgumentException.class,
            () -> new Paciente("8232323", "Gabriel", "gurgel@gurgel")
        );
    }

    @Test
    public void testGetters() {
        assertEquals("19149480766", paciente.getCpf());
        assertEquals("Gabriel", paciente.getNome());
        assertEquals("Gurgel@Gurgel", paciente.getEmail());
    }

    @Test
    public void testSetters() {
        paciente.setEndereco("rua doze");
        assertEquals("rua doze", paciente.getEndereco());

        paciente.setPlano(new PlanoSaude("Unimed"));
        assertEquals("Unimed", paciente.getPlano().toString());
    }
}
