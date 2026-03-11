package cenas;
import java.util.Scanner;

import dados.Dados;
import dados.Inimigo;
import dados.ListaInimigos;

import java.io.File;
import java.io.FileNotFoundException;

public class CenaSlime extends Cena{
  public CenaSlime(Dados dados) {
    super("Inimigo: Slime", dados);
    this.dados.listaInimigos = new ListaInimigos();
    Inimigo slime = new Inimigo("Slime Selvagem", 5, 10, 1);
    dados.listaInimigos.adicionarInimigo(slime);
  }

 /* Carregamento da Cena */
  @Override
  public void carregaCena(){
    /* mostramos o caminho para a animacao */
    this.file = new File("animacoes/AnimacaoSlime.txt");
    /* Fazemos a tentativa de ler o arquivo */
    try{
      this.leitor = new Scanner(file);
      System.out.println("Você encontrou inimigos! Cuidado!\n");
      dados.listaInimigos.mostrarInimigos(); // mostramos quais inimigos estao na tela!
    }
    catch(FileNotFoundException e){
      System.out.println("Erro arquivo não encontrado!\n");
    } 
  }

  @Override
  public void atualizaCena(){
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
