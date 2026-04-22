package anim;

import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class TestAnim {

  @Test
  public void nullQuandoArquivoNaoExistir(){
    Animacao animacao = new AnimacaoControle("caminhoInexistente");
    assertNull(animacao.leitor);
  }
  
  @Test
  public void leitorNaoEhNullQuandoArquivoExistir(){
    Animacao animacao = new AnimacaoControle("/animacoes/FireCor.txt");
    assertNotNull(animacao.leitor);
  }
  
  @Test
  public void deveImprimirFrameCorretamente() {
    AnimacaoControle animacao = new AnimacaoControle("caminho");
    
    String arquivoTeste = "frame0Linha1\n" +
                          "frame0Linha2\n" +
                          ",\n" +
                          "frame1Linha1\n" +
                          "frame1Linha2\n" +
                          ",\n";
    
    animacao.leitor = new Scanner(arquivoTeste);
    animacao.frame = 0;
    /* como modificamos nossa imprimeLinha para adicionar as linhas em uma lista */
    /* podemos verificar se as linhas foram adicionadas corretamente */
    animacao.imprimeAnimacao();
    
    assertTrue(animacao.linhasImpressas.contains("frame0Linha1"));
    assertTrue(animacao.linhasImpressas.contains("frame0Linha2"));
    
    animacao.imprimeAnimacao();
    assertTrue(animacao.linhasImpressas.contains("frame1Linha1"));
    assertTrue(animacao.linhasImpressas.contains("frame1Linha2"));
  }
  
}
