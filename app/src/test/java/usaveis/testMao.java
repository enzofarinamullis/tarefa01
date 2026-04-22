package usaveis;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import usaveis.cartas.Carta;
import usaveis.cartas.CartaDano;
import usaveis.cartas.CartaEscudo;

@DisplayName("Testes da classe Mao")
class testMao{

    private Mao mao;
    private Carta carta1;
    private Carta carta2;
    
    // Para capturar a saída do printMao()
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        mao = new Mao();
        
        // Criando cartas mock para teste
        carta1 = new CartaDano("Espada Curta", 10, 2, "Causa dano físico ao inimigo.");
        carta2 = new CartaEscudo("Escudo de Madeira", 8, 3, "Aumenta defesa");
    }

    @Test
    @DisplayName("Deve criar uma mão vazia")
    void testConstrutorCriaMaoVazia() {
        assertNotNull(mao.cartas);
        assertTrue(mao.cartas.isEmpty());
        assertEquals(0, mao.cartas.size());
    }

    @Test
    @DisplayName("Deve permitir adicionar uma carta à mão")
    void testAdicionarCarta() {
        mao.cartas.add(carta1);
        
        assertEquals(1, mao.cartas.size());
        assertSame(carta1, mao.cartas.get(0));
    }

    @Test
    @DisplayName("Deve permitir adicionar múltiplas cartas à mão")
    void testAdicionarMultiplasCartas() {
        mao.cartas.add(carta1);
        mao.cartas.add(carta2);
      
        
        assertEquals(2, mao.cartas.size());
        assertTrue(mao.cartas.contains(carta1));
        assertTrue(mao.cartas.contains(carta2));
    }

    @Test
    @DisplayName("Deve permitir remover uma carta da mão")
    void testRemoverCarta() {
        mao.cartas.add(carta1);
        mao.cartas.add(carta2);
        
        mao.cartas.remove(carta1);
        
        assertEquals(1, mao.cartas.size());
        assertFalse(mao.cartas.contains(carta1));
        assertTrue(mao.cartas.contains(carta2));
    }

    @Test
    @DisplayName("Deve permitir remover carta por índice")
    void testRemoverCartaPorIndice() {
        mao.cartas.add(carta1);
        mao.cartas.add(carta2);
        
        Carta cartaRemovida = mao.cartas.remove(0);
        
        assertEquals(1, mao.cartas.size());
        assertSame(carta1, cartaRemovida);
        assertFalse(mao.cartas.contains(carta1));
    }

    @Test
    @DisplayName("Deve exibir as cartas da mão corretamente")
    void testPrintMao() throws Exception {
        // Configurar captura da saída do sistema
        System.setOut(new PrintStream(outContent));
        
        mao.cartas.add(carta1);
        mao.cartas.add(carta2);
        
        mao.printMao();
        
        String output = outContent.toString();
        assertTrue(output.contains("Espada Curta"));
        assertTrue(output.contains("Escudo de Madeira"));
        assertTrue(output.contains("Dano: 10"));
        assertTrue(output.contains("Custo: 2"));
        
        // Restaurar saída original
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("Deve exibir mensagem quando mão está vazia")
    void testPrintMaoVazia() {
        System.setOut(new PrintStream(outContent));
        
        mao.printMao();
        
        String output = outContent.toString();
        assertTrue(output.contains("vazia") || output.contains("nenhuma carta"));
        
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("Deve permitir acessar cartas por índice")
    void testAcessarCartaPorIndice() {
        mao.cartas.add(carta1);
        mao.cartas.add(carta2);
        
        Carta primeiraCarta = mao.cartas.get(0);
        Carta segundaCarta = mao.cartas.get(1);
        
        assertSame(carta1, primeiraCarta);
        assertSame(carta2, segundaCarta);
    }

    @Test
    @DisplayName("Deve lançar exceção ao acessar índice inválido")
    void testAcessarIndiceInvalido() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            mao.cartas.get(0);
        });
        
        mao.cartas.add(carta1);
        
        assertThrows(IndexOutOfBoundsException.class, () -> {
            mao.cartas.get(5);
        });
    }

    @Test
    @DisplayName("Deve limpar todas as cartas da mão")
    void testLimparMao() {
        mao.cartas.add(carta1);
        mao.cartas.add(carta2);
        
        mao.cartas.clear();
        
        assertTrue(mao.cartas.isEmpty());
        assertEquals(0, mao.cartas.size());
    }

    @Test
    @DisplayName("Deve verificar se contém uma carta específica")
    void testContemCarta() {
        mao.cartas.add(carta1);
        
        assertTrue(mao.cartas.contains(carta1));
        assertFalse(mao.cartas.contains(carta2));
    }

    @Test
    @DisplayName("Deve retornar o tamanho correto da mão")
    void testTamanhoMao() {
        assertEquals(0, mao.cartas.size());
        
        mao.cartas.add(carta1);
        assertEquals(1, mao.cartas.size());
        
        mao.cartas.add(carta2);
        assertEquals(2, mao.cartas.size());
        
        mao.cartas.remove(carta1);
        assertEquals(1, mao.cartas.size());
    }

    @Test
    @DisplayName("Deve permitir iterar sobre as cartas")
    void testIterarSobreCartas() {
        mao.cartas.add(carta1);
        mao.cartas.add(carta2);
        
        int contador = 0;
        for (Carta carta : mao.cartas) {
            assertNotNull(carta);
            contador++;
        }
        
        assertEquals(3, contador);
    }
}


