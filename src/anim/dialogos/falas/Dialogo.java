package anim.dialogos.falas;

import anim.Animacao;
import constantes.Cores;
import constantes.Dialogos;
import utilitarios.PrintTerminal;

public abstract class Dialogo extends Animacao {
  
  public Dialogo(String caminho){
    super(caminho);
  }
  
  @Override
  public void run() {
    try {
      PrintTerminal.limparTerminal();
      while(leitor.hasNextLine()) {
        imprimeAnimacao();
        sleep(Dialogos.PAUSA_MEDIA);
      }
    }
    catch (Exception _){}
  }
  
  public abstract void rodar();
  
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
