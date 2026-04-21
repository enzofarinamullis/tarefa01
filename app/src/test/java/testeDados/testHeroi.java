package testeDados;

import org.junit.jupiter.api.BeforeEach;//Executa o método antes do teste
import org.junit.jupiter.api.Test;// Marca como teste
import org.junit.jupiter.api.DisplayName;//Nome descritivo
import static org.junit.jupiter.api.Assertions.*;//Métodos para checar resultados

import dados.Heroi;
import dados.Inimigo;
import usaveis.Mao;
import dados.inimigos.*;
import dados.ListaInimigos;
import usaveis.cartas.danos.*;
import usaveis.pilhas.PilhaCompra;
import usaveis.pilhas.PilhaDescarte;
import usaveis.cartas.*;
import java.util.*;



public class testHeroi {
  private Heroi heroi;
  private ListaInimigos inimigos;
  @BeforeEach
  void setUp() {
    heroi = new Heroi();
    inimigos = new ListaInimigos();
    for (int i = 0; i < 20; i++) {
      Slime slime = new Slime();
      inimigos.adicionarInimigo(slime);
    }
  }

  @Test
    @DisplayName("Deve inicializar com valores corretos")
    void testConstrutorInicializacao() {
      // Testando valores iniciais através de reflexão ou getters
      assertEquals(5, heroi.getVida(), "Vida inicial deve ser 5");
      assertEquals(0, heroi.getEscudo(), "Escudo inicial deve ser 0");
      assertEquals(20, heroi.getEnergia(), "Energia inicial deveria deve 20");
      assertEquals(20, heroi.getEnergiaLimite(), "Limite de energia deve ser 20");
        
      // Verificando se os componentes foram inicializados
      assertNotNull(heroi.getMao(), "Mão não deveria ser null");
      assertNotNull(heroi.getPilhaCompra(), "Pilha de compra não deve ser null");
      assertNotNull(heroi.getPilhaDescarte(), "Pilha de descarte não deve ser null");
        
      // Verificando se a pilha de compra foi criada e embaralhada
      assertNotNull(heroi.getPilhaCompra(), "Pilha de compra deve ter cartas");

  }

  @Test
    @DisplayName("Deve verificar corretamente se tem energia suficiente")
    void testTemEnergia() {
      assertTrue(heroi.temEnergia(5), "Deveria ter energia para carta custo 5");
      assertTrue(heroi.temEnergia(20), "Deveria ter energia para carta custo 20");
      assertFalse(heroi.temEnergia(21), "Não deveria ter energia para carta custo 21");
  }
  
  @Test
  @DisplayName("Deve verificar corretamente se existe carta jogável na mão")
  void testVerificaEnergia() {
    /*Mão vazia */
    assertFalse(heroi.verificaEnergia(),"Mão vazia, nenhuma carta jogável");
    /*Carta cara */
    CartaDano cartaCara = new CartaDano("cartaCara", 30, 10, "Caro");
    heroi.getMao().cartas.add(cartaCara);
    assertFalse(heroi.verificaEnergia(), "Tem apenas a carta cara não jogável");
    /*carta barata */
    CartaEscudo cartaBarata = new CartaEscudo("carta barata", 1,1, "escudo barato");
    heroi.getMao().cartas.add(cartaBarata);
    assertTrue(heroi.verificaEnergia());
    /*deck misto */

    heroi.getMao().cartas.clear();
    espadaCorrupta espadaCorrupta = new espadaCorrupta();
    heroi.getMao().cartas.add(espadaCorrupta);
    espadaCurta espadaCurta = new espadaCurta();
    heroi.getMao().cartas.add(espadaCurta);
    espadaDaLuaSangrenta espadaDaLuaSangrenta = new espadaDaLuaSangrenta();
    heroi.getMao().cartas.add(espadaDaLuaSangrenta);
    espadaEnvenenada espadaEnvenenada = new espadaEnvenenada();
    heroi.getMao().cartas.add(espadaEnvenenada);
    heroi.getMao().cartas.add(cartaCara);
    CartaDano cartaCarissima = new CartaDano("carta carrisima", 100, 100, "muito caro");
    heroi.getMao().cartas.add(cartaCarissima);

    Collections.shuffle(heroi.getMao().cartas);
    assertTrue(heroi.verificaEnergia(), "Deve encontrar carta jogável");
    heroi.getMao().cartas.clear();

  }

@Test
  @DisplayName("Deve retornar componentes corretamente")
  void testGetters() {
      Mao mao = heroi.getMao();
      PilhaCompra pilhaCompra = heroi.getPilhaCompra();
      PilhaDescarte pilhaDescarte = heroi.getPilhaDescarte();
      
      assertNotNull(mao, "Mao getter não deveria retornar null");
      assertNotNull(pilhaCompra, "PilhaCompra getter não deveria retornar null");
      assertNotNull(pilhaDescarte, "PilhaDescarte getter não deveria retornar null");
  }
@Test
  @DisplayName("Deve atualizar energia corretamente ao usar cartas")
  void testGastarEnergia() {
      int energiaInicial = heroi.getEnergia();
      assertEquals(20, energiaInicial, "Energia inicial deve ser 20");
      /*Teste para uma carta*/
      heroi.getMao().cartas.clear();

      espadaCurta espadaCurta = new espadaCurta();
      heroi.getMao().cartas.add(espadaCurta);

      int energiaConsumida = 0;
      if (heroi.temEnergia(espadaCurta.getCustoEnergia())) {
        for (int i = 0; i < inimigos.getTamanho(); i++) {
          if (inimigos.buscarInimigo(i).estaVivoSemPrint()) {
            heroi.getMao().cartas.get(0).usar(inimigos.buscarInimigo(i), heroi);
            break;
          }
        }
        energiaConsumida += espadaCurta.getCustoEnergia();
      }

      int Eesperada = 20 - espadaCurta.getCustoEnergia();
       assertEquals(Eesperada, heroi.getEnergia(), String.format("Energia deveria ser %d após consumir %d de energia", Eesperada, energiaConsumida));

      /*varias cartas */
      heroi.getMao().cartas.clear();
      espadaCorrupta espadaCorrupta = new espadaCorrupta();
      heroi.getMao().cartas.add(espadaCorrupta);
      heroi.getMao().cartas.add(espadaCurta);
      espadaDaLuaSangrenta espadaDaLuaSangrenta = new espadaDaLuaSangrenta();
      heroi.getMao().cartas.add(espadaDaLuaSangrenta);
      espadaEnvenenada espadaEnvenenada = new espadaEnvenenada();
      heroi.getMao().cartas.add(espadaEnvenenada);
      CartaDano cartaCara = new CartaDano("cartaCara", 30, 10, "Caro");
      heroi.getMao().cartas.add(cartaCara);
      CartaDano cartaCarissima = new CartaDano("carta carrisima", 100, 100, "muito caro");
      heroi.getMao().cartas.add(cartaCarissima);
      Collections.shuffle(heroi.getMao().cartas);

      int energiaConsumida2 = 0;
      for (int i = 0; i < heroi.getMao().cartas.size(); i++) {
        if (heroi.temEnergia(heroi.getMao().cartas.get(i).getCustoEnergia())) {
          for (int j = 0; j < inimigos.getTamanho(); j++) {
            if (inimigos.buscarInimigo(j).estaVivoSemPrint()) {
              heroi.getMao().cartas.get(i).usar(inimigos.buscarInimigo(j), heroi);
              break;
            }
          }
          energiaConsumida2 += heroi.getMao().cartas.get(i).getCustoEnergia();

        }
      }
      int energiaEsperada = 20 - energiaConsumida2;
      assertEquals(energiaEsperada, heroi.getEnergia(), String.format("Energia deveria ser %d após consumir %d de energia", energiaEsperada, energiaConsumida2));
  }
@Test
  @DisplayName("Deve gerenciar ciclo de turno corretamente")
  void testCicloDeTurno() {
      int energiaInicial = heroi.getEnergia();
      assertEquals(20, energiaInicial, "ENergia inicial = 20");
  }
@Test
  @DisplayName("Deve exibir status sem lançar exceções")
  void testStatus() {
      assertDoesNotThrow(() -> heroi.status(), 
          "Método status não deveria lançar exceção");
  }
@Test
  @DisplayName("Deve gerenciar vida e morte corretamente")
  void testVidaEMorte() {
      int vidaInicial = heroi.getVida();
      // Código comentado aguardando implementação
  }








}
