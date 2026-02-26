package com.example.unittest;

import org.junit.jupiter.api.Test;
import com.example.unittest.DominioDemo.CalculadoraDescuentos;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculadoraDescuestosTest {

    @Test
    void testAplicarDescuento() {
        // Arrange
        var calculadora = new CalculadoraDescuentos();
        double precioBase = 100;
        double porcentaje = 20;
        double precioEsperado = 80;

        // Act
        var resultado = calculadora.aplicarDescuento(precioBase, porcentaje);

        // Assert
        assertEquals(precioEsperado, resultado);

    }

}
