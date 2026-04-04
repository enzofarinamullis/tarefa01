package cenas;
import dados.Dados;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;


public abstract class Cena {
  String nome;
  Dados dados;
  File file;
  Scanner leitor;
  int frame;

  /* Construtor servirá como nosso carregar Cena */
  public Cena(){
  }

  /* Update da cena */
  public void atualizaCena(){}
  
  /* renderiza cena como nosso Draw */
  public void renderizaCena(){}
  
  /* Unload sera feito pelo proprio Java */
  
  public Scanner carregaLeitor(String caminho){
    InputStream entrada = getClass().getResourceAsStream(caminho);
    if(entrada == null){
      System.out.println("Arquivo nao encontrado");
      return null;
    }
    
    return new Scanner(entrada);
  }
  
  public void imprimeArquivo(){
    String linha = "-";
    if(leitor.hasNextLine()){
      linha = leitor.nextLine();
    }
    for(; leitor.hasNextLine();){
      while(!linha.equals(",") && leitor.hasNextLine()){
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
