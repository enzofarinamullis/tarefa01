package anim;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
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
  
  @Test
  public void deveIgnorarEspaco(){
    AnimacaoFogo animacao = new AnimacaoFogo();
    
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));
    
    animacao.imprimeLinha(" ");
    String saida = out.toString();
    
    /* neste caso a saída deve ser somente um quebra de linha */
    assertTrue(saida.contains("\n"));
    assertFalse(saida.contains(" "));
  }
  
  @Test void deveImprimirPonto(){
    AnimacaoFogo animacao = new AnimacaoFogo();
    
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));
    
    animacao.imprimeLinha("b");
    String saida = out.toString();
    
    /* neste caso a saída deve ser somente um ponto */
    assertTrue(saida.contains("."));
  }
  
  @Test
  public void deveImprimirMultiplosCaracteres(){
    AnimacaoFogo animacao = new AnimacaoFogo();
    
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));
    
    animacao.imprimeLinha("bd");
    String saida = out.toString();
    
    /* neste caso a saída deve ser somente um ponto e dois pontos */
    assertTrue(saida.contains("."));
    assertTrue(saida.contains(":"));
  }
  
  @Test
  public void deveImprimirArroba(){
    AnimacaoFogo animacao = new AnimacaoFogo();
    
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));
    
    animacao.imprimeLinha("o");
    String saida = out.toString();
    
    /* neste caso a saída deve ser somente um arroba */
    assertTrue(saida.contains("@"));
  }
  
  @Test
  public void deveTerminarComNovaLinha(){
    AnimacaoFogo animacao = new AnimacaoFogo();
    
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));
    
    animacao.imprimeLinha("b");
    String saida = out.toString();
    
    assertTrue(saida.endsWith("\n"));
  }
  
}
