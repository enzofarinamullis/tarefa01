import java.io.File;
import java.util.Scanner;

public class Cena {
  String nome;
  Dados dados;
  File file;
  Scanner leitor;
  int frame;

  /* Construtor servirá como nosso Init */
  public Cena(String nome, Dados dados){
    this.nome = nome;
    this.dados = dados;
    this.frame = 0;

    this.file = null;
    this.leitor = null;
  }

  /* Update da cena */
  public void atualizaCena(){}

  /* Carregamento da Cena */
  public void carregaCena(){}

  /* Unload sera feito pelo proprio Java */
  
  public void limpaTerminal(){
    for(int i = 0; i < 100; i++){
      System.out.println("\n");
    }
  }
}
