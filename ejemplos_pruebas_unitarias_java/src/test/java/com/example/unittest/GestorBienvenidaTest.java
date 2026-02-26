package com.example.unittest;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.example.unittest.DominioDemo.ClienteEmail;
import com.example.unittest.DominioDemo.GestorBienvenida;
import com.example.unittest.DominioDemo.Usuario;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GestorBienvenidaTest {

    @Mock
    private ClienteEmail clienteEmail;

    @InjectMocks
    private GestorBienvenida gestorBienvenida;

    @Test
    void testRegistrarUsuario() {
        // Arrange
        var usuario = new Usuario("Juan", "juan@upb.edu.com");
        var assertUser = "juan@upb.edu.com";
        var assertMessage = "¡Bienvenido Juan!";

        // Act
        gestorBienvenida.registrarUsuario(usuario);

        // Assert
        verify(clienteEmail, times(1)).enviar(assertUser, assertMessage);

    }

    @Test
    void testRegistrarUsuarioConEmailInvalido() {
        // Arrange
        var usuario = new Usuario("Juan", "");

        // Act and assert
        assertThrows(IllegalArgumentException.class, () -> {
            gestorBienvenida.registrarUsuario(usuario);
        });

        verify(clienteEmail, times(0)).enviar(anyString(), anyString());
    }

}
