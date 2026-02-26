package com.example.unittest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import com.example.unittest.DominioDemo.GeneradorReporteClima;
import com.example.unittest.DominioDemo.RepositorioClima;

public class GeneradorReporteClimaTest {

    @Test
    void testGenerarResumen() {
        // Arrange
        var repositorioMock = mock(RepositorioClima.class);
        when(repositorioMock.obtenerClimaActual("Cali")).thenReturn("Soleado");

        var generador = new GeneradorReporteClima(repositorioMock);
        String ciudad = "Cali";
        String resumenEsperado = "El clima en Cali es: Soleado";

        // Act
        String resultado = generador.generarResumen(ciudad);

        // Assert
        assertEquals(resumenEsperado, resultado);
    }
}
