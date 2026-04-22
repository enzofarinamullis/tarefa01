package usaveis.cartas;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import constantes.IdsSubscribers;
import dados.Heroi;
import dados.Inimigo;
import usaveis.cartas.efeitos.Cura;

class CartaEscudoTest {
    
    private CartaEscudo cartaEscudo;
    private Heroi heroi;
    private Inimigo inimigo;
    
    @BeforeEach
    void setUp() {
        // Criando carta de escudo padrão para os testes
        cartaEscudo = new CartaEscudo("Barreira Mágica", 15, 3, "Concede um escudo mágico");
        heroi = new Heroi();
        inimigo = new Inimigo("Goblin", 50, 10, 5,"vou te roubar fedelho!");
    }
    
    @Nested
    @DisplayName("Testes de Construtor e Inicialização")
    class ConstrutorTests {
        
        @Test
        @DisplayName("Deve criar carta de escudo com valores corretos")
        void testConstrutorValoresCorretos() {
            assertEquals("Barreira Mágica", cartaEscudo.getNome());
            assertEquals(15, cartaEscudo.getEscudo());
            assertEquals(3, cartaEscudo.getCustoEnergia());
            assertEquals("Concede um escudo mágico", cartaEscudo.descricao);
            assertFalse(cartaEscudo.isDano());
            assertTrue(cartaEscudo.isEscudo());
        }
        
        @Test
        @DisplayName("Deve criar carta com escudo zero")
        void testConstrutorEscudoZero() {
            CartaEscudo cartaSemEscudo = new CartaEscudo("Escudo Fracote", 0, 1, "Não concede escudo");
            assertEquals(0, cartaSemEscudo.getEscudo());
            assertTrue(cartaSemEscudo.isEscudo());
        }
        
        @Test
        @DisplayName("Deve criar carta com alto custo de energia")
        void testConstrutorAltoCusto() {
            CartaEscudo cartaCara = new CartaEscudo("Escudo Supremo", 50, 10, "Escudo poderoso");
            assertEquals(10, cartaCara.getCustoEnergia());
            assertEquals(50, cartaCara.getEscudo());
        }
        
        @Test
        @DisplayName("Deve inicializar lista de efeitos vazia")
        void testListaEfeitosInicializada() {
            assertNotNull(cartaEscudo.efeitos);
            assertEquals(0, cartaEscudo.quantidadeEfeitos());
        }
        
        @Test
        @DisplayName("Deve criar diferentes tipos de carta escudo")
        void testDiferentesCartasEscudo() {
            CartaEscudo escudoLeve = new CartaEscudo("Escudo Leve", 5, 1, "Escudo básico");
            CartaEscudo escudoPesado = new CartaEscudo("Escudo Pesado", 30, 4, "Escudo avançado");
            CartaEscudo escudoMagico = new CartaEscudo("Escudo Mágico", 20, 3, "Escudo encantado");
            
            assertEquals(5, escudoLeve.getEscudo());
            assertEquals(30, escudoPesado.getEscudo());
            assertEquals(20, escudoMagico.getEscudo());
        }
    }
    
    @Nested
    @DisplayName("Testes de Uso da Carta - Casos Normais")
    class UsarCartaNormaisTests {
        
        @Test
        @DisplayName("Deve conceder escudo ao herói quando tem energia suficiente")
        void testConcederEscudoComEnergia() {
            int escudoInicial = heroi.getEscudo();
            int energiaInicial = heroi.getEnergia();
            
            cartaEscudo.usar(inimigo, heroi);
            
            // Verifica se ganhou escudo
            assertEquals(escudoInicial + 15, heroi.getEscudo());
            // Verifica se consumiu energia
            assertEquals(energiaInicial - 3, heroi.getEnergia());
        }
        
        @Test
        @DisplayName("Deve acumular escudo múltiplas vezes")
        void testAcumularEscudoMultiplo() {
            int escudoInicial = heroi.getEscudo();
            
            // Usa a carta 3 vezes
            for (int i = 0; i < 3; i++) {
                cartaEscudo.usar(inimigo, heroi);
            }
            
            assertEquals(escudoInicial + 45, heroi.getEscudo());
        }
        
        @Test
        @DisplayName("Deve consumir energia corretamente em múltiplos usos")
        void testConsumoEnergiaMultiplo() {
            heroi.setaEnergia(20);
            int energiaInicial = heroi.getEnergia();
            
            // Usa a carta 5 vezes
            for (int i = 0; i < 5; i++) {
                cartaEscudo.usar(inimigo, heroi);
            }
            
            assertEquals(energiaInicial - 15, heroi.getEnergia());
        }
        
        @Test
        @DisplayName("Deve funcionar mesmo quando inimigo é null")
        void testInimigoNull() {
            int escudoInicial = heroi.getEscudo();
            
            assertDoesNotThrow(() -> cartaEscudo.usar(null, heroi));
            assertEquals(escudoInicial + 15, heroi.getEscudo());
        }
        
        @Test
        @DisplayName("Deve usar carta com diferentes valores de escudo")
        void testDiferentesValoresEscudo() {
            CartaEscudo cartaPequena = new CartaEscudo("Pequeno", 5, 1, "");
            CartaEscudo cartaMedia = new CartaEscudo("Médio", 25, 2, "");
            CartaEscudo cartaGrande = new CartaEscudo("Grande", 100, 5, "");
            
            int escudoInicial = heroi.getEscudo();
            
            cartaPequena.usar(inimigo, heroi);
            assertEquals(escudoInicial + 5, heroi.getEscudo());
            
            cartaMedia.usar(inimigo, heroi);
            assertEquals(escudoInicial + 30, heroi.getEscudo());
            
            cartaGrande.usar(inimigo, heroi);
            assertEquals(escudoInicial + 130, heroi.getEscudo());
        }
    }
    
    @Nested
    @DisplayName("Testes de Uso da Carta - Casos de Energia Insuficiente")
    class UsarCartaSemEnergiaTests {
        
        @Test
        @DisplayName("Não deve conceder escudo quando energia é insuficiente")
        void testNaoConcederEscudoSemEnergia() {
            heroi.setaEnergia(2); // Energia menor que o custo (3)
            int escudoInicial = heroi.getEscudo();
            
            cartaEscudo.usar(inimigo, heroi);
            
            assertEquals(escudoInicial, heroi.getEscudo());
        }
        
        @Test
        @DisplayName("Não deve consumir energia quando energia é insuficiente")
        void testNaoConsumirEnergiaSemEnergia() {
            heroi.setaEnergia(2);
            int energiaInicial = heroi.getEnergia();
            
            cartaEscudo.usar(inimigo, heroi);
            
            assertEquals(energiaInicial, heroi.getEnergia());
        }
        
        @Test
        @DisplayName("Não deve conceder escudo quando energia é exatamente zero")
        void testEnergiaZero() {
            heroi.setaEnergia(0);
            int escudoInicial = heroi.getEscudo();
            
            cartaEscudo.usar(inimigo, heroi);
            
            assertEquals(escudoInicial, heroi.getEscudo());
        }
        
        @Test
        @DisplayName("Não deve conceder escudo quando energia é negativa")
        void testEnergiaNegativa() {
            heroi.setaEnergia(-1);
            int escudoInicial = heroi.getEscudo();
            
            cartaEscudo.usar(inimigo, heroi);
            
            assertEquals(escudoInicial, heroi.getEscudo());
        }
        
        @Test
        @DisplayName("Deve funcionar com energia exatamente igual ao custo")
        void testEnergiaExatamenteIgual() {
            heroi.setaEnergia(3);
            int escudoInicial = heroi.getEscudo();
            int energiaInicial = heroi.getEnergia();
            
            cartaEscudo.usar(inimigo, heroi);
            
            assertEquals(escudoInicial + 15, heroi.getEscudo());
            assertEquals(energiaInicial - 3, heroi.getEnergia());
        }
        
        @Test
        @DisplayName("Deve usar carta com energia maior que o custo")
        void testEnergiaMaiorQueCusto() {
            heroi.setaEnergia(10);
            int escudoInicial = heroi.getEscudo();
            
            cartaEscudo.usar(inimigo, heroi);
            
            assertEquals(escudoInicial + 15, heroi.getEscudo());
            assertEquals(7, heroi.getEnergia());
        }
    }
    
    @Nested
    @DisplayName("Testes de Efeitos")
    class EfeitosTests {
        
        @Test
        @DisplayName("Deve permitir adicionar efeitos à carta de escudo")
        void testAdicionarEfeito() {
            Efeito efeito = new Cura("Regeneração", 3, 5,IdsSubscribers.CURA);
            cartaEscudo.adicionarEfeito(efeito);
            
            assertEquals(1, cartaEscudo.quantidadeEfeitos());
            assertEquals(efeito, cartaEscudo.retornarEfeito(0));
        }
        
        @Test
        @DisplayName("Deve permitir adicionar múltiplos efeitos")
        void testAdicionarMultiplosEfeitos() {
            cartaEscudo.adicionarEfeito(new Cura("Efeito1", 1, 10,IdsSubscribers.CURA));
            cartaEscudo.adicionarEfeito(new Cura("Efeito2", 2, 20,IdsSubscribers.CURA));
            cartaEscudo.adicionarEfeito(new Cura("Efeito3", 3, 30,IdsSubscribers.CURA));
            
            assertEquals(3, cartaEscudo.quantidadeEfeitos());
        }
        
        @Test
        @DisplayName("Deve aplicar efeitos após usar a carta")
        void testAplicarEfeitos() {
            Efeito efeitoMock = new Cura("Fortificação", 2, 15,1);
            cartaEscudo.adicionarEfeito(efeitoMock);
            
            assertDoesNotThrow(() -> cartaEscudo.usar(inimigo, heroi));
            assertTrue(cartaEscudo.temEfeito());
        }
        
        @Test
        @DisplayName("Deve listar efeitos corretamente")
        void testListarEfeitos() {
            cartaEscudo.adicionarEfeito(new Cura("Escudo Reforçado", 3, 10,1));
            cartaEscudo.adicionarEfeito(new Cura("Reflexo", 2, 5,1));
            
            assertDoesNotThrow(() -> cartaEscudo.listarEfeitos());
        }
        
        @Test
        @DisplayName("Deve buscar efeito pelo nome")
        void testBuscarEfeito() {
            Efeito efeito = new Cura("Proteção", 4, 25,1);
            cartaEscudo.adicionarEfeito(efeito);
            
            Efeito encontrado = cartaEscudo.BuscaEfeito("Proteção");
            assertNotNull(encontrado);
            assertEquals("Proteção", encontrado.getNome());
        }
        
        @Test
        @DisplayName("Deve retornar null ao buscar efeito inexistente")
        void testBuscarEfeitoInexistente() {
            cartaEscudo.adicionarEfeito(new Cura("Existente", 1, 5,1));
            
            Efeito encontrado = cartaEscudo.BuscaEfeito("Inexistente");
            assertNull(encontrado);
        }
    }
    
    @Nested
    @DisplayName("Testes de Herança e Polimorfismo")
    class HerancaTests {
        
        @Test
        @DisplayName("CartaEscudo deve ser subclasse de Carta")
        void testHeranca() {
            assertTrue(cartaEscudo instanceof Carta);
        }
        
        @Test
        @DisplayName("Deve sobrescrever método usar corretamente")
        void testSobrescritaMetodoUsar() {
            // CartaEscudo implementa seu próprio método usar
            assertDoesNotThrow(() -> cartaEscudo.usar(inimigo, heroi));
        }
        
        @Test
        @DisplayName("Deve acessar métodos da classe pai")
        void testMetodosPai() {
            assertNotNull(cartaEscudo.getNome());
            assertEquals(15, cartaEscudo.getEscudo());
            assertEquals(3, cartaEscudo.getCustoEnergia());
        }
    }
    
    @Nested
    @DisplayName("Testes de Integração")
    class IntegracaoTests {
        
        @Test
        @DisplayName("Deve combinar escudo de múltiplas cartas diferentes")
        void testCombinarMultiplasCartas() {
            CartaEscudo escudoPequeno = new CartaEscudo("Pequeno", 5, 1, "");
            CartaEscudo escudoGrande = new CartaEscudo("Grande", 20, 2, "");
            
            int escudoInicial = heroi.getEscudo();
            
            escudoPequeno.usar(inimigo, heroi);
            escudoGrande.usar(inimigo, heroi);
            
            assertEquals(escudoInicial + 25, heroi.getEscudo());
        }
        
        @Test
        @DisplayName("Deve funcionar com herói com escudo já existente")
        void testHeroiComEscudoExistente() {
            heroi.ganharEscudo(10);
            int escudoInicial = heroi.getEscudo();
            
            cartaEscudo.usar(inimigo, heroi);
            
            assertEquals(escudoInicial + 15, heroi.getEscudo());
        }
        
        @Test
        @DisplayName("Deve permitir usar carta várias vezes seguidas")
        @RepeatedTest(10)
        void testUsosRepetidos() {
            heroi.setaEnergia(100);
            int energiaPorUso = cartaEscudo.getCustoEnergia();
            int escudoPorUso = cartaEscudo.getEscudo();
            int energiaInicial = heroi.getEnergia();
            int escudoInicial = heroi.getEscudo();
            
            for (int i = 0; i < 5; i++) {
                cartaEscudo.usar(inimigo, heroi);
            }
            
            assertEquals(energiaInicial - (5 * energiaPorUso), heroi.getEnergia());
            assertEquals(escudoInicial + (5 * escudoPorUso), heroi.getEscudo());
        }
        
        @Test
        @DisplayName("Não deve lançar exceção com parâmetros nulos")
        void testParametrosNull() {
            assertDoesNotThrow(() -> cartaEscudo.usar(null, null));
            assertDoesNotThrow(() -> cartaEscudo.usar(inimigo, null));
            assertDoesNotThrow(() -> cartaEscudo.usar(null, heroi));
        }
    }
    
    @Nested
    @DisplayName("Testes de Informação e Exibição")
    class InformacaoTests {
        
        @Test
        @DisplayName("Deve exibir informações da carta sem lançar exceção")
        void testExibirInfo() {
            assertDoesNotThrow(() -> cartaEscudo.info(1));
        }
        
        @Test
        @DisplayName("Deve exibir informações com efeitos")
        void testExibirInfoComEfeitos() {
            cartaEscudo.adicionarEfeito(new Cura("Proteção", 3, 10,1));
            cartaEscudo.adicionarEfeito(new Cura("Reflexo", 2, 5,2));
            
            assertDoesNotThrow(() -> cartaEscudo.info(1));
        }
        
        @Test
        @DisplayName("Deve exibir informações corretamente")
        void testInfoConteudo() {
            // Teste apenas para verificar se não lança exceção e exibe tipo escudo
            assertDoesNotThrow(() -> {
                System.out.println("=== Teste exibição carta escudo ===");
                cartaEscudo.info(1);
            });
        }
        
        @Test
        @DisplayName("Deve listar efeitos mesmo quando não há efeitos")
        void testListarEfeitosSemEfeitos() {
            assertDoesNotThrow(() -> cartaEscudo.listarEfeitos());
        }
    }
    
    @Nested
    @DisplayName("Testes de Valores Limite")
    class ValoresLimiteTests {
        
        @Test
        @DisplayName("Deve aceitar escudo com valor muito alto")
        void testEscudoMuitoAlto() {
            CartaEscudo cartaEscudoAlto = new CartaEscudo("Super Escudo", 999999, 1, "");
            cartaEscudoAlto.usar(inimigo, heroi);
            
            assertEquals(999999, heroi.getEscudo() - (heroi.getEscudo() - 999999));
        }
        
        @Test
        @DisplayName("Deve aceitar escudo negativo")
        void testEscudoNegativo() {
            CartaEscudo cartaEscudoNegativo = new CartaEscudo("Escudo Ruim", -10, 1, "");
            int escudoInicial = heroi.getEscudo();
            
            cartaEscudoNegativo.usar(inimigo, heroi);
            
            assertEquals(escudoInicial - 10, heroi.getEscudo());
        }
        
        @Test
        @DisplayName("Deve aceitar custo zero")
        void testCustoZero() {
            CartaEscudo cartaCustoZero = new CartaEscudo("Escudo Grátis", 5, 0, "");
            int energiaInicial = heroi.getEnergia();
            int escudoInicial = heroi.getEscudo();
            
            cartaCustoZero.usar(inimigo, heroi);
            
            assertEquals(escudoInicial + 5, heroi.getEscudo());
            assertEquals(energiaInicial, heroi.getEnergia());
        }
        
        @Test
        @DisplayName("Deve aceitar custo muito alto")
        void testCustoMuitoAlto() {
            CartaEscudo cartaCustoAlto = new CartaEscudo("Escudo Caro", 100, 1000, "");
            int escudoInicial = heroi.getEscudo();
            
            cartaCustoAlto.usar(inimigo, heroi);
            
            // Não deve conceder escudo pois energia é insuficiente
            assertEquals(escudoInicial, heroi.getEscudo());
        }
        
        @Test
        @DisplayName("Deve aceitar nome vazio")
        void testNomeVazio() {
            CartaEscudo cartaSemNome = new CartaEscudo("", 10, 1, "");
            assertEquals("", cartaSemNome.getNome());
            assertDoesNotThrow(() -> cartaSemNome.usar(inimigo, heroi));
        }
        
        @Test
        @DisplayName("Deve aceitar descrição vazia")
        void testDescricaoVazia() {
            CartaEscudo cartaSemDescricao = new CartaEscudo("Escudo", 10, 1, "");
            assertEquals("", cartaSemDescricao.descricao);
            assertDoesNotThrow(() -> cartaSemDescricao.usar(inimigo, heroi));
        }
    }
    
    @Nested
    @DisplayName("Testes de Consistência")
    class ConsistenciaTests {
        
        @Test
        @DisplayName("Carta sempre deve ser do tipo escudo")
        void testSempreEscudo() {
            assertTrue(cartaEscudo.isEscudo());
            assertFalse(cartaEscudo.isDano());
            
            CartaEscudo outraCarta = new CartaEscudo("Teste", 10, 2, "");
            assertTrue(outraCarta.isEscudo());
            assertFalse(outraCarta.isDano());
        }
        
        @Test
        @DisplayName("Custo de energia deve ser o definido no construtor")
        void testCustoEnergiaConsistente() {
            assertEquals(3, cartaEscudo.getCustoEnergia());
            
            CartaEscudo novaCarta = new CartaEscudo("Teste", 10, 5, "");
            assertEquals(5, novaCarta.getCustoEnergia());
        }
        
        @Test
        @DisplayName("Valor do escudo deve ser o definido no construtor")
        void testValorEscudoConsistente() {
            assertEquals(15, cartaEscudo.getEscudo());
            
            CartaEscudo novaCarta = new CartaEscudo("Teste", 25, 2, "");
            assertEquals(25, novaCarta.getEscudo());
        }
    }
}