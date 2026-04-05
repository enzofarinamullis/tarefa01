package anim;

import constantes.Cores;

public class AnimacaoFogo extends Animacao {
  public AnimacaoFogo() {
    super("/animacoes/FireCor.txt");
  }
  
  @Override
  public void imprimeLinha(String linha){
    char valor;
    for(int i = 0; i < linha.length(); i++) {
      valor = linha.charAt(i);
      if(valor == ' '){
        continue;
      }
      else if(valor == 'a') {
        System.out.print(Cores.ANSI_BLACK + " ");
      }
      else if(valor == 'b') {
        System.out.print(Cores.cor03071e + ".");
      }
      else if(valor == 'c') {
        System.out.print(Cores.cor1f1b1c + ".");
      }
      else if(valor == 'd') {
        System.out.print(Cores.cor1f1b1c + ":");
      }
      else if(valor == 'e') {
        System.out.print(Cores.cor5b373a + ":");
      }
      else if(valor == 'f') {
        System.out.print(Cores.cor9d0208 + "+");
      }
      else if(valor == 'g') {
        System.out.print(Cores.cor9d0208 + ":");
      }
      else if(valor == 'h') {
        System.out.print(Cores.cor5b373a + "+");
      }
      else if(valor == 'i') {
        System.out.print(Cores.ANSI_BLACK + "+");
      }
      else if(valor == 'j') {
        System.out.print(Cores.ANSI_BLACK + "*");
      }
      else if(valor == 'k') {
        System.out.print(Cores.core85d04 + "#");
      }
      else if(valor == 'l') {
        System.out.print(Cores.core85d04 + "*");
      }
      else if(valor == 'm') {
        System.out.print(Cores.cordc2f02 + "*");
      }
      else if(valor == 'n') {
        System.out.print(Cores.corf48c06 + "#");
      }
      else if(valor == 'o') {
        System.out.print(Cores.corf48c06 + "@");
      }
      else if(valor == 'p') {
        System.out.print(Cores.corfaa307 + "@");
      }
      else if(valor == 'q') {
        System.out.print(Cores.corfaa307 + "#");
      }
      else if(valor == 'r') {
        System.out.print(Cores.ANSI_WHITE + "@");
      }
      else if(valor == 's') {
        System.out.print(Cores.cor03071e + ":");
      }
      else if(valor == 't') {
        System.out.print(Cores.cordc2f02 + "+");
      }
      else if(valor == 'u') {
        System.out.print(Cores.cordc2f02 + "#");
      }
    }
    /* printamos a quebra de linha */
    System.out.print(Cores.ANSI_RESET + "\n");
  }
}
