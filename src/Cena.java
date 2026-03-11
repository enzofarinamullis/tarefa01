import java.io.File;
import java.io.FileNotFoundException;
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

  public void imprimeArquivo(String caminho){
    /* mostramos o caminho para a animacao */
    this.file = new File(caminho);
    /* Fazemos a tentativa de ler o arquivo */
    try{
      this.leitor = new Scanner(file);
    }
    catch(FileNotFoundException e){
      System.out.println("Erro arquivo não encontrado!\n");
    } 

    String linha = "-";
    if(leitor.hasNextLine()){
      linha = leitor.nextLine();
    }
    for(; leitor.hasNextLine();){
      while(linha.equals(",") == false && leitor.hasNextLine()){
        System.out.println(linha);
        linha = leitor.nextLine();
      }
      /* caso o leitor achou o final da linha */
      if(linha.equals(",")){
        linha = leitor.nextLine();
      }
      this.dados.frame++;
    }
  }
}
