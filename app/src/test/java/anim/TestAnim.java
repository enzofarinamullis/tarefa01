package anim;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

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
  
  
}
