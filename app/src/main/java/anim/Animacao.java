package anim;

import java.io.File;
import java.io.InputStream;
import java.util.Scanner;
import java.io.FileNotFoundException;

import constantes.Cores;
import dados.Dados;
import utilitarios.PrintTerminal;

public abstract class Animacao extends Thread {
  public String nome;
  public File arquivo;
  public Scanner leitor;
  public int frame;
  public Dados dados;
  
  public Animacao(String caminho) {
    leitor = carregaLeitor(caminho);
  }
  
  
  /**
   * Executa uma animacao, movendo o cursor para o topo do terminal
   * e imprimindo ela
   */
  @Override
  public void run() {
    try {
      while(leitor.hasNextLine()) {
        /* para tentarmos diminuir as piscagens da animacao */
        /* inves de limparmos o terminal a cada frame */
        /* movemos o cursor para o topo do terminal */
        System.out.print("\033[H");
        imprimeAnimacao();
        sleep(100);
      }
    }
    catch (Exception e){}
    
  }
  
  /**
   * Prepara o leitor da animacao
   * fornecendo o caminho para busca do arquivo em /resources
   */
  public Scanner carregaLeitor(String caminho){
    /* mostramos o caminho para a animacao */
    InputStream entrada = getClass().getResourceAsStream(caminho);
    
    /* Fazemos a tentativa de ler o arquivo */
    if(entrada == null){
      System.out.println("Arquivo Nao encontrado");
      return null;
    }
    this.leitor = new Scanner(entrada);
    return leitor;
  }
  
  /**
   * Imprime linha será distinto em cada arquivo,
   * imprimeLinha fornecerá os comandos de cores a usar
   * para cada caracter da animação ASCII
   */
  public void imprimeLinha(String linha){
  }
  
  public void imprimeAnimacao(){
    String linha = "-";
    if(leitor.hasNextLine()){
      linha = leitor.nextLine();
    }
    else{
      return;
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
