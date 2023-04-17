package br.edu.femass.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AgendaTest {

    private Agenda agenda;
    private Paciente paciente;
    private Medico medico;
    private Especialidade especialidade;

    @BeforeEach
    public void beforeEach() {
        List<Especialidade> especialidades = new ArrayList<>();

        paciente = new Paciente("19149480766", "João", "joão@joão");
        medico = new Medico("19149480766", "1234567", "fulano", "fulano@fulano", especialidades);
        especialidade = new Especialidade("Cirugião");
        agenda = new Agenda("22/08/2023", "15:34", medico, paciente, especialidade);

        agenda.setPlano(null);
        agenda.setParticular(false);
    }


    @Test
    public void testGetters() {
        assertEquals("22/08/2023", agenda.getData());
        assertEquals("15:34", agenda.getHora());
        assertEquals(medico, agenda.getMedico());
        assertEquals(paciente, agenda.getPaciente());
        assertEquals(especialidade, agenda.getEspecialidade());
        assertNull(agenda.getPlano());
        assertFalse(agenda.getParticular());
        assertTrue(agenda.getAtivo());
    }

    @Test
    public void testSetters() {
        PlanoSaude plano = new PlanoSaude("Unimed");
        agenda.setPlano(plano);
        assertEquals("Unimed", agenda.getPlano().toString());

        agenda.setParticular(true);
        assertTrue(agenda.getParticular());

        agenda.setAtivo(false);
        assertFalse(agenda.getAtivo());
    }
}
