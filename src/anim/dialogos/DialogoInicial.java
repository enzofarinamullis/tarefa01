package anim.dialogos;

import anim.Animacao;
import utilitarios.PrintTerminal;
import java.util.Scanner;

public class DialogoInicial extends Animacao {
  /* Os dialogos serao iguais as animacoes, apenas com um tempo de espera maior entre as falas */
  /* e nao limpando o terminal entre elas */
  
  public DialogoInicial() {
    super("src/anim/dialogos/falas/FalaInicial.txt");
  }
  
  
  @Override
  public void run() {
    try {
      PrintTerminal.limparTerminal();
      while(leitor.hasNextLine()) {
        imprimeAnimacao();
        sleep(5000);
      }
    }
    catch (Exception e){}
  }
  
  @Override
  public void imprimeLinha(String linha){
    System.out.println(linha);
  }
}
