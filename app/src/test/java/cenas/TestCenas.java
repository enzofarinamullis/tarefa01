package cenas;

import dados.Dados;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class TestCenas {
  Dados dadosMock;
  Cena cena;
  @BeforeEach
  void setUp() {
    dadosMock = mock(Dados.class);
    dadosMock.frame = 0;
    cena = new CenaControle();
    cena.dados = dadosMock;
  }
  @Test
  public void deveImprimirLinha(){
    String arquivoTeste = "linha1\n" +
      "linha2\n" + ", \n";
    
    cena.leitor = new Scanner(arquivoTeste);
    
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    PrintStream original = System.out;
    System.setOut(new PrintStream(out));
    
    cena.imprimeArquivo();
    
    System.setOut(original);
    
    String saida = out.toString();
    
    assertTrue(saida.contains("linha1"));
    assertTrue(saida.contains("linha2"));
  }
  
  @Test
  public void deveAvancarFrameQuandoEncontrarVirgula() {
    String arquivoTeste = "linha1\n" +
                          ",\n" +
                          "linha2\n" +
                          ", \n";
    
    cena.leitor = new Scanner(arquivoTeste);
    
    cena.imprimeArquivo();
    
    assertTrue(dadosMock.frame > 0);
  }
  
  @Test
  public void deveIncrementarFrameVariasVezes() {
    String arquivoTeste = "a\n,\nb\n,\nc\n, \n";
    
    cena.leitor = new Scanner(arquivoTeste);
    
    cena.imprimeArquivo();
    
    assertTrue(dadosMock.frame >= 2);
  }
  
  @Test
  public void naoDeveFalharComArquivoVazio() {
    cena.leitor = new Scanner("");
    cena.imprimeArquivo();
    assertEquals(0, dadosMock.frame);
  }
  
  
  @Test
  public void deveRetornarNullQuandoArquivoNaoExiste() {
    Scanner leitor = cena.carregaLeitor("/inexistente.txt");
    assertNull(leitor);
  }
  
}
