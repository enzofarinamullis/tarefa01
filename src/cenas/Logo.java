package cenas;
import java.util.Scanner;
import constantes.Cores;
import dados.Dados;
import java.io.File;
import java.io.FileNotFoundException;

public class Logo extends Cena {

  /* Init */
  public Logo(Dados dados){
    this.dados = dados;
    this.nome = "LOGO";
    this.leitor = carregaLeitor("src/animacoes/Cabecalho2.txt");
  }

  /* Carrega a Cena */
  @Override
  public void renderizaCena(){
    imprimeArquivo();
  }

  /* Atualiza a Cena */
  @Override
  public void atualizaCena(){
    System.out.println("Bem vindo, " + dados.heroi.getNome() + ", ao mundo de " +
    Cores.COR_SANGUE_2 + "Sangue" + Cores.COR_MUSGO_1 + " e " +
    Cores.COR_RUNA_1 + "Runas" + Cores.ANSI_RESET + "!");
  }


  @Override
  public void imprimeArquivo(){
    String linha = "-";
    int comprimento;
    if(leitor.hasNextLine()){
      linha = leitor.nextLine();
    }
    for(; leitor.hasNextLine();){
      while(leitor.hasNextLine()){
        if(linha.equals(";")){
          frame++;
          linha = leitor.nextLine();
        }

        comprimento = linha.length();
        for(int i = 0; i < comprimento; i++){
          if(frame == 0){
            if(linha.charAt(i) == '1'){
              System.out.print(Cores.COR_SANGUE_1 + "█" + Cores.ANSI_RESET);
            }
            else if(linha.charAt(i) == '2'){
              System.out.print(Cores.COR_SANGUE_2 + "█" + Cores.ANSI_RESET);
            }
            else if(linha.charAt(i) == '3'){
              System.out.print(Cores.COR_CIMENTO_1 + '█' + Cores.ANSI_RESET);
            }
            else if(linha.charAt(i) == '4'){
              System.out.print(Cores.COR_CIMENTO_2 + '█' + Cores.ANSI_RESET);
            }
            else if(linha.charAt(i) == '█'){
              System.out.print(Cores.COR_CIMENTO_3 + linha.charAt(i) + Cores.ANSI_RESET);
            }
            else if(linha.charAt(i)== '▄'  || linha.charAt(i) == '▀' || linha.charAt(i) == '▐' || linha.charAt(i) == '▌' ){
              System.out.print(Cores.COR_SANGUE_1 + linha.charAt(i) + Cores.ANSI_RESET);
            }
            else if(linha.charAt(i) == '▓'){
              System.out.print(Cores.COR_SANGUE_2 + linha.charAt(i) + Cores.ANSI_RESET);
            }
            else if(linha.charAt(i) == '▒'){
              System.out.print(Cores.COR_SANGUE_3 + linha.charAt(i) + Cores.ANSI_RESET);
            }
            else if(linha.charAt(i) == '░'){
              System.out.print(Cores.COR_SANGUE_4 + linha.charAt(i) + Cores.ANSI_RESET);
            }
            else{
              System.out.print(Cores.ANSI_GREEN + linha.charAt(i) + Cores.ANSI_RESET);
            }
          }

          if(frame == 1){
            if(linha.charAt(i) == '1'){
              System.out.print(Cores.COR_MUSGO_1 + "█" + Cores.ANSI_RESET);
            }
            else if(linha.charAt(i) == '2'){
              System.out.print(Cores.COR_MUSGO_1 + "▓" + Cores.ANSI_RESET);
            }
            else if(linha.charAt(i) == '3'){
              System.out.print(Cores.COR_CIMENTO_1 + '█' + Cores.ANSI_RESET);
            }
            else if(linha.charAt(i) == '4'){
              System.out.print(Cores.COR_CIMENTO_2 + '█' + Cores.ANSI_RESET);
            }
            else if(linha.charAt(i) == '█'){
              System.out.print(Cores.COR_CIMENTO_3 + linha.charAt(i) + Cores.ANSI_RESET);
            }
            else if(linha.charAt(i)== '▄'  || linha.charAt(i) == '▀' || linha.charAt(i) == '▐' || linha.charAt(i) == '▌' ){
              System.out.print(Cores.COR_MUSGO_1 + linha.charAt(i) + Cores.ANSI_RESET);
            }
            else if(linha.charAt(i) == '▓'){
              System.out.print(Cores.COR_MUSGO_1 + linha.charAt(i) + Cores.ANSI_RESET);
            }
            else if(linha.charAt(i) == '▒'){
              System.out.print(Cores.COR_MUSGO_3 + linha.charAt(i) + Cores.ANSI_RESET);
            }
            else if(linha.charAt(i) == '░'){
              System.out.print(Cores.COR_MUSGO_4 + linha.charAt(i) + Cores.ANSI_RESET);
            }
            else{
              System.out.print(Cores.ANSI_GREEN + linha.charAt(i) + Cores.ANSI_RESET);
            }
          }

          if(frame == 2){
            if(linha.charAt(i) == '1'){
              System.out.print(Cores.COR_RUNA_1 + "█" + Cores.ANSI_RESET);
            }
            else if(linha.charAt(i) == '2'){
              System.out.print(Cores.COR_MUSGO_1 + "█" + Cores.ANSI_RESET);
            }
            else if(linha.charAt(i) == '3'){
              System.out.print(Cores.COR_CIMENTO_1 + "█" + Cores.ANSI_RESET);
            }
            else if(linha.charAt(i) == '4'){
              System.out.print(Cores.COR_CIMENTO_2 + "█" + Cores.ANSI_RESET);
            }
            else if(linha.charAt(i) == '█'){
              System.out.print(Cores.COR_CIMENTO_3 + linha.charAt(i) + Cores.ANSI_RESET);
            }
            else if(linha.charAt(i)== '▄'  || linha.charAt(i) == '▀' || linha.charAt(i) == '▐' || linha.charAt(i) == '▌' ){
              System.out.print(Cores.COR_CIMENTO_2 + linha.charAt(i) + Cores.ANSI_RESET);
            }
            else if(linha.charAt(i) == '▓'){
              System.out.print(Cores.COR_RUNA_1 + linha.charAt(i) + Cores.ANSI_RESET);
            }
            else if(linha.charAt(i) == '▒'){
              System.out.print(Cores.COR_RUNA_3 + linha.charAt(i) + Cores.ANSI_RESET);
            }
            else if(linha.charAt(i) == '░'){
              System.out.print(Cores.COR_RUNA_4 + linha.charAt(i) + Cores.ANSI_RESET);
            }
            else{
              System.out.print(Cores.ANSI_GREEN + linha.charAt(i) + Cores.ANSI_RESET);
            }
          }
        }
        linha = leitor.nextLine();
        System.out.print("\n");
      }
    }
  }
}