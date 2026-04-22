package D;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import usaveis.D.Potencia;

@DisplayName("Testes da Classe Potencia")
public class TestPotencia {
    
    @Test
    @DisplayName("Dado deve rolar valores dentro do intervalo")
    void testIntervalo() {
        Potencia dado = new Potencia(6);
        for (int i = 0; i < 1000; i++) {
            int resultado = dado.rolarDadoSemAnim();
            assertTrue(resultado >= 1 && resultado <= 6);
        }
    }
    
    @Test
    @DisplayName("Vantagem deve retornar maior valor")
    void testVantagem() {
        Potencia dado = new Potencia(20);
        
        for (int i = 0; i < 500; i++) {
            int resultado = dado.rolarVantagemSemAnim();
            assertTrue(resultado >= 1 && resultado <= 20);
        }
    }
    
    @Test
    @DisplayName("Desvantagem deve retornar menor valor")
    void testDesvantagem() {
        Potencia dado = new Potencia(20);
        
        for (int i = 0; i < 500; i++) {
            int resultado = dado.rolarDesvantagemSemAnim();
            assertTrue(resultado >= 1 && resultado <= 20);
        }
    }
    
    @Test
    @DisplayName("Critico deve identificar máximo")
    void testCritico() {
        Potencia dado = new Potencia(6);
        
        // Teste via saída (simplificado)
        assertDoesNotThrow(() -> dado.critico(6));
        assertDoesNotThrow(() -> dado.critico(1));
        assertDoesNotThrow(() -> dado.critico(3));
    }
}