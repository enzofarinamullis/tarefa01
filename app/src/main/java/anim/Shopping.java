package anim;

import anim.dialogos.falas.Dialogo;
import dados.Dados;

public class Shopping extends Animacao {

  public Shopping() {
    super("/imagens/shopping.txt");
  }
  @Override
  public void imprimeLinha(String linha){
    System.out.println(linha);
  }
}
