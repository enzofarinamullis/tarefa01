package anim.dialogos;

import anim.Animacao;
import constantes.Cores;
import dados.Dados;
import utilitarios.PrintTerminal;
import java.util.Scanner;

public class DialogoInicial extends Animacao {
  /* Os dialogos serao iguais as animacoes, apenas com um tempo de espera maior entre as falas */
  /* e nao limpando o terminal entre elas */
  
  public DialogoInicial(Dados dados) {
    super("src/anim/dialogos/falas/FalaInicial.txt");
    this.dados = dados;
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
    for(int i = 0; i < linha.length(); i++){
      
      /* PLACEHOLDERS */
      if(linha.charAt(i) == '$'){
        i++;
        if(linha.charAt(i) == ' '){
          System.out.print("$");
        }
        else if(linha.charAt(i) == 'H'){
          System.out.print(Cores.ANSI_CYAN + dados.heroi.getNome() + Cores.ANSI_RESET);
        }
        else if(linha.charAt(i) == 'M'){
          System.out.print(Cores.ANSI_CYAN + "Mary" + Cores.ANSI_RESET);
        }
      }
      else{
        System.out.print(linha.charAt(i));
      }
    }
    System.out.print("\n");
  }
}
