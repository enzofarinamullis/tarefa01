package anim.dialogos;

import anim.Animacao;
import anim.dialogos.falas.Dialogo;
import constantes.Cores;
import dados.Dados;
import utilitarios.PrintTerminal;
import java.util.Scanner;

public class DialogoInicial extends Dialogo {
  /* Os dialogos serao iguais as animacoes, apenas com um tempo de espera maior entre as falas */
  /* e nao limpando o terminal entre elas */
  
  public DialogoInicial(Dados dados) {
    super("/anim/dialogos/falas/FalaInicial.txt");
    this.dados = dados;
  }
  
  
  public void rodar(){
    run();
  }
}
