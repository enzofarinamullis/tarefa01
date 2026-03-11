package cenas;
import java.util.Scanner;

import dados.Dados;

import java.io.File;
import java.io.FileNotFoundException;

public class CenaInicial extends Cena {

  /* Init */
  public CenaInicial(Dados dados){
    super("Cena Inicial", dados);
  }

  /* Carrega a Cena */
  @Override
  public void carregaCena(){
    imprimeArquivo("animacoes/CabecalhoInicial.txt");
  }

  /* Atualiza a Cena */
  @Override
  public void atualizaCena(){
    System.out.println("Bem vindo, " + dados.heroi.nome + ", ao mundo de Sangue e Runas!");
  }

  public static final String ANSI_RED = "\u001B[31m";
  public static final String ANSI_RESET = "\u001B[0m";

  @Override
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
        System.out.println(ANSI_RED + linha + ANSI_RESET);
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