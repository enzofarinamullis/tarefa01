package sistematurnos;

import dados.Dados;
import dados.Inimigo;

import java.io.InputStream;
import java.util.Scanner;

public class InterfaceBatalha {
  /* queremos uma interface de batalha */
  /* para isso precisamos adicionar os inimigos */
  /* vamos colocar pixels 8x16 em ascii para os inimgios e imprimi-los na tela */
  /* iremos usar 8x16 para a printagem ficar bonita no terminal */
  private int qntInimigos;
  private int inimigoPorLinha;
  private int linha;
  private Scanner leitor;
  
  public InterfaceBatalha(Dados dados){
    /* vamos colocar apenas 2 inimigos por linha,
    pois sera um tamanho bom para printagem por linha
    */
    qntInimigos = dados.listaInimigos.getTamanho();
    inimigoPorLinha = 2;
    linha = 0;
  }
  private Scanner carregaLeitor(String caminho){
    InputStream entrada = getClass().getResourceAsStream(caminho);
    if(entrada == null){
      System.out.println("Arquivo não encontrado");
      return null;
    }
    leitor = new Scanner(caminho);
    return leitor;
  }
  
  protected void imprimeUmInimigo(Inimigo inimigo){
    for(int i = 0; i < 8; i++){
      /* imrpime linha i */
      linha++;
    }
  }
  
  protected void imprimeDoisInimigos(Inimigo inimigo){
    System.out.println();
    for(int i = 0; i < 8; i++) {
      /* imprime linha i inimigo 1 */
      /* imprime linha i inimigo 2 */
      linha++;
    }
  }
  
  protected void imprimeTodosInimigos(){
    int inimigosRestante = 0;
    for(int i = 0; i < qntInimigos; i+= 2){
      inimigosRestante = qntInimigos - i;
      if(inimigosRestante == 1){
      
      }
    }
    System.out.println();
  }
  
}
