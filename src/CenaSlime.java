import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class CenaSlime extends Cena{
  public CenaSlime(Dados dados) {
    super("Inimigo: Slime", dados);
    this.dados.qntInimigos = 1;
  }

 /* Carregamento da Cena */
  @Override
  public void carregaCena(){
    /* mostramos o caminho para a animacao */
    this.file = new File("AnimacaoSlime.txt");
    /* Fazemos a tentativa de ler o arquivo */
    try{
      this.leitor = new Scanner(file);
    }
    catch(FileNotFoundException e){
      System.out.println("Erro arquivo não encontrado");
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
} 
