import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/* ref: https://www.w3schools.com/java/java_files_read.asp */
/* ref: https://docs.oracle.com/javase/8/docs/api/java/util/Scanner.html  */

public class AnimacaoPortaAbrindo extends Cena {

  /* Init */
  public AnimacaoPortaAbrindo(Dados dados){
    super("Animação Porta Abrindo", dados);
  }


  /* Carregamento da Cena */
  @Override
  public void carregaCena(){
    /* mostramos o caminho para a animacao */
    this.file = new File("AnimacaoPortaAbrindo.txt");
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
    /* Agora para a animcao iremos ler uma linha e parar caso esta linha for "," */
    /* na nossa animcao ascii o divisor de frames é o "," */
    String linha = "-";
    if(leitor.hasNextLine()){
      linha = leitor.nextLine();
    }
    /* Fazemos a leitura enquanto tiver proxima linha */
    for(int i = 0; leitor.hasNextLine(); i++){
      /* Enquanto o leitor nao encontrar o final do frame */
      /* e enquanto existir um proxima linha */
      while(linha.equals(",") == false && leitor.hasNextLine()){
        /* como o leitor le o arquivo inteiro, linha por linha */
        /* e como nao temos como especificar a linha de leitura */
        /* temos que garantir que ele so imprima a linha que for correspondente do */
        /* frame da animacao */
        System.out.println(linha);
        if(i == dados.frame){
          System.out.println(linha);
        }
        linha = leitor.nextLine();
      }
      /* caso o leitor achou o final da linha */
      if(linha.equals(",")){
        linha = leitor.nextLine();
      }
    }
    /* quando terminarmos de fazer o frame */
    this.dados.frame++; // aumentamos o valor do frame em 1;
  }
}
