package dados;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestInimigo {
  private Heroi heroi;
  private Inimigo inimigo;
  @BeforeEach
  void setUp() {
    heroi = new Heroi();
    inimigo = new Inimigo("Inimigo genérico",50, 10, 15, "Anuncio");
  }
  @Test
  @DisplayName("Deve inicializar com valores corretos")
  void testConstrutorInicializacao() {
      assertEquals("Inimigo genérico", inimigo.getNome(),
          "Nome deve ser 'Inimigo genérico'");
      assertEquals(50, inimigo.getVida(),
          "Vida inicial deve ser 50");
      assertEquals(10, inimigo.getEscudo(),
          "Escudo inicial deve ser 10");
      assertEquals(15, inimigo.getDano(),
          "Dano inicial deve ser 15");
      assertEquals("Anuncio", inimigo.getAnuncio(),
          "Anúncio deve ser 'Anuncio'");
      assertEquals(-1, inimigo.getId(),
          "ID inicial deve ser -1");
      assertFalse(inimigo.temEfeitos(),
          "Inimigo não deve ter efeitos inicialmente");
      assertEquals(0, inimigo.getQuantidadeEfeitos(),
          "Quantidade de efeitos deve ser 0");
  }
  @Test
  @DisplayName("Testes de dano")
  void testDano() {
    /*Dano menor que o escudo */
    int vidaInicial = inimigo.getVida();
    int escudoInicial = inimigo.getEscudo();
    inimigo.receberDano(5);
    assertEquals(escudoInicial - 5, inimigo.getEscudo(), "Escudo absorvendo todo o dano");
    /*Dano igual ao escudo */
    inimigo.setaEscudo(10);
    inimigo.setVida(50);
    inimigo.receberDano(10);
    assertEquals(0,inimigo.getEscudo(), "Escudo absorvendo todo o dano e sendo quebrado após isso");
    /*Dano maior que o escudo */
    inimigo.setaEscudo(10);
    inimigo.setVida(50);
    inimigo.receberDano(15);
    assertEquals(0, inimigo.getEscudo(), "O escudo deve zerar");
    assertEquals(45, inimigo.getVida(),"O inimigo tem que tomar 5 de dano");
    /*Dano sem escudo*/
    inimigo.setaEscudo(0);
    inimigo.setVida(50);
    inimigo.receberDano(10);
    assertEquals(0, inimigo.getEscudo(), "Escudo deve continuar zero");
    assertEquals(40, inimigo.getVida(),"O inimigo sofrerá todo o dano");
    /*Casos de morte */
    inimigo.setVida(50);
    inimigo.receberDano(60);
    assertEquals(0, inimigo.getVida(), "Não há vida negativa");
  
}

@Test
  @DisplayName("Deve retornar anúncio corretamente")
  void testGetAnuncio() {
      assertEquals("Anuncio", inimigo.getAnuncio(),
          "Deve retornar o anúncio correto");
  }

  @Test
  @DisplayName("Deve anunciar sem lançar exceção")
  void testAnunciar() {
      assertDoesNotThrow(() -> inimigo.anunciar(),
          "anunciar() não deve lançar exceção");
  }
  @Test
  @DisplayName("Deve exibir anúncio formatado corretamente")
  void testAnuncioFormatado() {
      ByteArrayOutputStream outContent = new ByteArrayOutputStream();
      System.setOut(new PrintStream(outContent));
      
      inimigo.anunciar();
      
      String output = outContent.toString();
      assertTrue(output.contains("Anúncio:"),
          "Deve mostrar 'Anuncio:'");
      assertTrue(output.contains("Inimigo genérico"),
          "Deve mostrar o nome do inimigo");
      assertTrue(output.contains("Anuncio"),
          "Deve mostrar a mensagem de anúncio");
      
      System.setOut(System.out);
  }
}

