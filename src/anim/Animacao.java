package anim;

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

import constantes.Cores;
import utilitarios.PrintTerminal;

public abstract class Animacao extends Thread {
  public String nome;
  public File arquivo;
  public Scanner leitor;
  public int frame;
  
  public Animacao(String caminho) {
    leitor = carregaLeitor(caminho);
  }
  
  @Override
  public void run() {
    try {
      while(leitor.hasNextLine()) {
        PrintTerminal.limparTerminal();
        imprimeAnimacao();
        sleep(100);
      }
    }
    catch (Exception e){}
    
  }
  
  public Scanner carregaLeitor(String caminho){
    /* mostramos o caminho para a animacao */
    this.arquivo = new File(caminho);
    /* Fazemos a tentativa de ler o arquivo */
    try{
      this.leitor = new Scanner(arquivo);
      return leitor;
    }
    catch(FileNotFoundException e){
      System.out.println("Erro arquivo não encontrado!\n");
      return null;
    }
  }
  
  public void imprimeLinha(String linha){
  }
  
  public void imprimeAnimacao(){
    String linha = "-";
    if(leitor.hasNextLine()){
      linha = leitor.nextLine();
    }
    /* Fazemos a leitura enquanto tiver proxima linha */
    for(int i = 0; leitor.hasNextLine(); i++){
      /* Enquanto o leitor nao encontrar o final do frame */
      /* e enquanto existir um proxima linha */
      while(!linha.equals(",") && leitor.hasNextLine()){
        /* como o leitor le o arquivo inteiro, linha por linha */
        /* e como nao temos como especificar a linha de leitura */
        /* temos que garantir que ele so imprima a linha que for correspondente do */
        /* frame da animacao */
        if(i == frame){
          imprimeLinha(linha);
          //System.out.println(linha);
        }
        linha = leitor.nextLine();
      }
      /* caso percorremos o arquivo e preenchemos o frame */
      /* nao queremos ele percorrendo desnecessariamente */
      if(i == frame){
        return;
      }
      /* caso o leitor achou o final da linha */
      if(linha.equals(",")){
        /* removemos o "," */
        linha = leitor.nextLine();
        this.frame++;
      }
    }
  }

}
