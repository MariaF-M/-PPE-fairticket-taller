package com.example.unittest;

public class DominioDemo {

	// --- EJEMPLO 1: Lógica Pura ---
	public static class CalculadoraDescuentos {
		public double aplicarDescuento(double precioBase, double porcentaje) {
			if (precioBase < 0 || porcentaje < 0)
				throw new IllegalArgumentException("Valores inválidos");
			return precioBase - (precioBase * (porcentaje / 100));
		}
	}

	// --- EJEMPLO 2: Dependencia Simple ---
	public interface RepositorioClima {
		String obtenerClimaActual(String ciudad);
	}

	public static class GeneradorReporteClima {
		private final RepositorioClima repositorio;

		public GeneradorReporteClima(RepositorioClima repositorio) {
			this.repositorio = repositorio;
		}

		public String generarResumen(String ciudad) {
			var clima = repositorio.obtenerClimaActual(ciudad);
			return "El clima en " + ciudad + " es: " + clima;
		}
	}

	// --- EJEMPLO 3: Interacciones y Excepciones ---
	public record Usuario(String nombre, String email) {
	}

	public interface ClienteEmail {
		void enviar(String destino, String mensaje);
	}

	public static class GestorBienvenida {
		private final ClienteEmail clienteEmail;

		public GestorBienvenida(ClienteEmail clienteEmail) {
			this.clienteEmail = clienteEmail;
		}

		public void registrarUsuario(Usuario usuario) {
			if (usuario.email().isBlank()) {
				throw new IllegalArgumentException("El email no puede estar vacío");
			}
			clienteEmail.enviar(usuario.email(), "¡Bienvenido " + usuario.nombre() + "!");
		}
	}
}
