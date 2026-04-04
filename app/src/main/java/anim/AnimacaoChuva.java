package anim;

import constantes.Cores;

public class AnimacaoChuva extends Animacao {
  public AnimacaoChuva() {
    super("src/anim/Rain.txt");
  }
  
  @Override
  public void imprimeLinha(String linha){
    char valor;
    for(int i = 0; i < linha.length(); i++) {
      valor = linha.charAt(i);
      if(valor == ' '){
        continue;
      }
      if(valor == 'a') {
        System.out.print(Cores.cor1e90ff + "+");
      }
      else if(valor == 'b') {
        System.out.print(Cores.ANSI_WHITE + "@");
      }
      else if(valor == 'c') {
        System.out.print(Cores.corb3dbff + "#");
      }
      else if(valor == 'd') {
        System.out.print(Cores.cor4169e1 + "+");
      }
      else if(valor == 'e') {
        System.out.print(Cores.cor483d8b + ".");
      }
      else if(valor == 'f') {
        System.out.print(Cores.cor0000cd + ";");
      }
      else if(valor == 'g') {
        System.out.print(Cores.cor191970 + ".");
      }
      else if(valor == 'h') {
        System.out.print(Cores.corcce7ff + "#");
      }
      else if(valor == 'i') {
        System.out.print(Cores.ANSI_WHITE + "@");
      }
      else if(valor == 'j') {
        System.out.print(Cores.cor4169e1 + ";");
      }
      else if(valor == 'k') {
        System.out.print(Cores.cor0000cd + ";");
      }
      else if(valor == 'l') {
        System.out.print(Cores.cor87cefa + "*");
      }
      else if(valor == 'm') {
        System.out.print(Cores.cor6495ed + "+");
      }
      else if(valor == 'n') {
        System.out.print(Cores.cor0000cd + ":");
      }
      else if(valor == 'o') {
        System.out.print(Cores.cor000080 + ":");
      }
      else if(valor == 'p') {
        System.out.print(Cores.corcce7ff + "@");
      }
      else if(valor == 'q') {
        System.out.print(Cores.cor000080 + ":");
      }
      else if(valor == 'r') {
        System.out.print(Cores.cor00bfff + "+");
      }
      else if(valor == 's') {
        System.out.print(Cores.cor87ceeb + "#");
      }
      else if(valor == 't') {
        System.out.print(Cores.cor00bfff + "*");
      }
      else if(valor == 'u') {
        System.out.print(Cores.cor000080 + ".");
      }
      else if(valor == 'v') {
        System.out.print(Cores.cor87ceeb + "*");
      }
    }
    /* printamos a quebra de linha */
    System.out.print(Cores.ANSI_RESET + "\n");
  }
}
