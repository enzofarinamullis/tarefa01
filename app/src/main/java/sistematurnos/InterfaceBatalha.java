package sistematurnos;

import dados.Dados;
import dados.Inimigo;
import dados.ListaInimigos;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InterfaceBatalha {
  /* queremos uma interface de batalha */
  /* para isso precisamos adicionar os inimigos */
  /* vamos colocar pixels 8x16 em ascii para os inimgios e imprimi-los na tela */
  /* iremos usar 8x16 para a printagem ficar bonita no terminal */
  private int qntInimigos;
  private int inimigoPorLinha;
  private int numLinha;
  private Scanner leitor;
  private Dados dados;
  
  public InterfaceBatalha(Dados dados){
    /* vamos colocar apenas 2 inimigos por linha,
    pois sera um tamanho bom para printagem por linha
    */
    qntInimigos = dados.listaInimigos.getTamanho();
    inimigoPorLinha = 2;
    numLinha = 0;
    this.dados = dados;
  }
  private Scanner carregaLeitor(Inimigo inimigo){
    String caminho = inimigo.getCaminho();
    InputStream entrada = getClass().getResourceAsStream(caminho);
    if(entrada == null){
      System.out.println("Arquivo não encontrado");
      return null;
    }
    leitor = new Scanner(entrada);
    return leitor;
  }
  
  /* simplesmente imprimimos arquivo */
  protected void imprimeUmInimigo(Inimigo inimigo){
    leitor = carregaLeitor(inimigo);
    String linha;
    for(int i = 0; i < 8; i++){
      if(leitor.hasNextLine()){
        linha = leitor.nextLine();
        System.out.println(linha);
      }
      numLinha++;
    }
  }
  
  private List<String> carregaASCII(Inimigo inimigo){
    List<String> ASCII = new ArrayList<>();
    String linha;
    Scanner scanner = carregaLeitor(inimigo);
    while(scanner.hasNextLine()){
      linha = scanner.nextLine();
      ASCII.add(linha);
    }
    return ASCII;
  }
  
  
  /* juntamos dois arquivos */
  protected void imprimeDoisInimigos(Inimigo inimigo1, Inimigo inimigo2){
    System.out.println();
    List<String> ASCIIInimigo1 = carregaASCII(inimigo1);
    List<String> ASCIIInimigo2 = carregaASCII(inimigo2);
    
    int numLinhas = 8;
    
    for(int i = 0; i < numLinhas; i++){
      String linha1 = ASCIIInimigo1.get(i);
      String linha2 = ASCIIInimigo2.get(i);
      
      System.out.println(linha1 + linha2);
    }
  }
  
  protected void imprimeTodosInimigos(){
    int inimigosRestante = 0;
    Inimigo inimigo1;
    Inimigo inimigo2;
    ListaInimigos listaInimigos;
    listaInimigos = dados.listaInimigos;
    inimigosRestante = qntInimigos;
    for(int i = 0; i < qntInimigos; i+= 2){
      if(inimigosRestante == 1){
        inimigo1 = listaInimigos.buscarInimigo(i + 1);
        imprimeUmInimigo(inimigo1);
      }
      else{
        inimigo1 = listaInimigos.buscarInimigo(i + 1);
        inimigo2 = listaInimigos.buscarInimigo(i + 2);
        imprimeDoisInimigos(inimigo1, inimigo2);
      }
      inimigosRestante = qntInimigos - 2;
    }
    System.out.println();
  }
  
  
  
}
