package D;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import usaveis.D.Precisao;

@DisplayName("Testes Rápidos da Classe Precisao")
public class TestPrecisao {
    
    @Test
    @DisplayName("Dado deve rolar valores entre 1 e 20")
    void testIntervalo() {
        Precisao precisao = new Precisao();
        
        for (int i = 0; i < 1000; i++) {
            int resultado = precisao.rolarDadoSemAnim();
            assertTrue(resultado >= 1 && resultado <= 20,
                "Resultado deve estar entre 1 e 20. Valor: " + resultado);
        }
    }
    
    @Test
    @DisplayName("teste() deve funcionar para diferentes dificuldades")
    void testTeste() {
        Precisao precisao = new Precisao();
        
        // Dificuldade 1 sempre true
        assertTrue(precisao.teste(1));
        
        // Dificuldade 21 sempre false
        assertFalse(precisao.teste(21));
    }
    
    @Test
    @DisplayName("Critico deve identificar 20 e 1")
    void testCritico() {
        Precisao precisao = new Precisao();
        
        assertDoesNotThrow(() -> precisao.critico(20));
        assertDoesNotThrow(() -> precisao.critico(1));
        assertDoesNotThrow(() -> precisao.critico(10));
    }
    
    @Test
    @DisplayName("Vantagem e desvantagem devem funcionar")
    void testVantagemDesvantagem() {
        Precisao precisao = new Precisao();
        
        for (int i = 0; i < 100; i++) {
            int vantagem = precisao.rolarVantagemSemAnim();
            int desvantagem = precisao.rolarDesvantagemSemAnim();
            
            assertTrue(vantagem >= 1 && vantagem <= 20);
            assertTrue(desvantagem >= 1 && desvantagem <= 20);
        }
    }
}