package com.prestamos.service;

import com.prestamos.dao.CronogramaDAO;
import com.prestamos.exception.BusinessRuleException;
import com.prestamos.model.cronograma.Cronogramas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CronogramaServiceTest {

    private CronogramaDAO cronogramaDAO;
    private CronogramaService cronogramaService;

    @BeforeEach
    void configurar() {
        cronogramaDAO = mock(CronogramaDAO.class);
        cronogramaService = new CronogramaService(cronogramaDAO);
    }

    @Test
    void generarDebeRechazarCodigoPrestamoVacio() {
        assertThrows(
            BusinessRuleException.class,
            () -> cronogramaService.generar("", "A001")
        );

        verifyNoInteractions(cronogramaDAO);
    }

    @Test
    void generarDebeRechazarCodigoAnalistaVacio() {
        assertThrows(
            BusinessRuleException.class,
            () -> cronogramaService.generar("P001", " ")
        );

        verifyNoInteractions(cronogramaDAO);
    }

    @Test
    void generarDebeDevolverCodigoGenerado() throws SQLException {
        when(cronogramaDAO.generar("P001", "A001"))
            .thenReturn("CR001");

        when(cronogramaDAO.obtenerPorPrestamo("P001"))
            .thenReturn(new Cronogramas());

        String resultado =
            cronogramaService.generar("P001", "A001");

        assertEquals("CR001", resultado);

        verify(cronogramaDAO).generar("P001", "A001");
        verify(cronogramaDAO).obtenerPorPrestamo("P001");
    }

    @Test
    void generarDebeFallarSiDaoNoDevuelveCodigo()
            throws SQLException {

        when(cronogramaDAO.generar("P001", "A001"))
            .thenReturn(null);

        assertThrows(
            BusinessRuleException.class,
            () -> cronogramaService.generar("P001", "A001")
        );
    }
}