package anim.dialogos.falas;

import constantes.IdsSubscribers;
import dados.Dados;
import dados.Entidade;
import dados.Heroi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sistematurnos.observer.SubscriberEfeito;
import usaveis.cartas.Efeito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestDialogos {

  Dados dadosMock;
  Heroi heroiMock;
  
  @BeforeEach
  void setUp() {
    dadosMock = mock(Dados.class);
    heroiMock = mock(Heroi.class);
    when(heroiMock.getNome()).thenReturn("NomeDeHeroi");
    dadosMock.heroi = heroiMock;
  }
  
  @Test
  public void deveImprimirTexto(){
    Dialogo dialogo = new DialogoControle("caminho", dadosMock);
    
    /* Capturamos o sout */
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));
    
    dialogo.imprimeLinha("teste");
    assertEquals("teste\n", out.toString());
  }
  
  @Test
  public void deveSubstituirPlaceholderHeroi(){
    Dialogo dialogo = new DialogoControle("caminho", dadosMock);
    
    /* Capturamos o sout */
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));
    
    dialogo.imprimeLinha("Olá, $H!");
    String saida = out.toString();
    assertTrue(saida.contains("NomeDeHeroi"));
  }
  
  @Test
  public void deveSubstituirPlaceholderMary(){
    Dialogo dialogo = new DialogoControle("caminho", dadosMock);
    
    /* Capturamos o sout */
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));
    
    dialogo.imprimeLinha("Olá, $M!");
    String saida = out.toString();
    assertTrue(saida.contains("Mary"));
  }
  
  @Test
  public void deveImprimirCifra(){
    Dialogo dialogo = new DialogoControle("caminho", dadosMock);
    
    /* Capturamos o sout */
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));
    
    dialogo.imprimeLinha("$ ");
    String saida = out.toString();
    assertTrue(saida.contains("$\n"));
  }
}
