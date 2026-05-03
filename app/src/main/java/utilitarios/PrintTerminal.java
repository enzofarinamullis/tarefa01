package utilitarios;
import constantes.Cores;

public class PrintTerminal {
  public static void printLinha(String cor, int tamanho){
    int tamanhoLinha = 20;
    for(int i = 0; i < tamanhoLinha; i++){
      System.out.print(cor + "-");
    }
    System.out.println(Cores.ANSI_RESET + "");
  }
  
  public static void limparTerminal(){
    for(int i = 0; i < 100; i++){
      System.out.println();
    }
  }

  public static void pausa(int ms){
    try{
      Thread.sleep(ms);
    }
    catch (Exception e){}
  }
}
