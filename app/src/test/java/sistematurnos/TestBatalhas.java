package sistematurnos;

import dados.Dados;
import dados.Heroi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sistematurnos.batalhas.BatalhaLesmasESlimes;
import sistematurnos.batalhas.BatalhaNSlimes;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestBatalhas {
  private Heroi heroi;
  private Dados dados;
  
  @BeforeEach
  void setup(){
    heroi = new Heroi();
    dados = new Dados(heroi);
  }
  
  @Test
  @DisplayName("Deve adicionar a quantidade correta de inimigos")
  void deveAdicionarQuantidadeCorretaInimigos(){
    int qntSlimes = 10;
    int qntLesmas = 20;
    Batalha batalha = new BatalhaNSlimes(dados, qntSlimes);
    batalha.adicionarInimigos();
    int totalInimigos = dados.listaInimigos.getTamanho();
    assertEquals(qntSlimes, totalInimigos);
    
    dados.listaInimigos.limparListaInimigos();
    
    batalha = new BatalhaLesmasESlimes(dados, qntLesmas, qntSlimes);
    batalha.adicionarInimigos();
    totalInimigos = dados.listaInimigos.getTamanho();
    
    assertEquals(qntLesmas + qntSlimes, totalInimigos);
  }
  
}
