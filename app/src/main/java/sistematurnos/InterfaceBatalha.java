package sistematurnos;

import dados.Dados;
import dados.Inimigo;
import dados.ListaInimigos;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InterfaceBatalha {
  /* queremos uma interface de batalha */
  /* para isso precisamos adicionar os inimigos */
  /* vamos colocar pixels 8x16 em ascii para os inimgios e imprimi-los na tela */
  /* iremos usar 8x16 para a printagem ficar bonita no terminal */
  private int qntInimigos;
  private int inimigoPorLinha;
  private int numLinha;
  private Scanner leitor;
  private Dados dados;
  
  public InterfaceBatalha(Dados dados){
    /* vamos colocar apenas 2 inimigos por linha,
    pois sera um tamanho bom para printagem por linha
    */
    qntInimigos = dados.listaInimigos.getTamanho();
    inimigoPorLinha = 2;
    numLinha = 0;
    this.dados = dados;
  }
  private Scanner carregaLeitor(Inimigo inimigo){
    String caminho = inimigo.getCaminho();
    InputStream entrada = getClass().getResourceAsStream(caminho);
    if(entrada == null){
      System.out.println("Arquivo não encontrado");
      return null;
    }
    leitor = new Scanner(entrada);
    return leitor;
  }
  
  /* simplesmente imprimimos arquivo */
  protected void imprimeUmInimigo(Inimigo inimigo){
    leitor = carregaLeitor(inimigo);
    String linha;
    for(int i = 0; i < 8; i++){
      if(leitor.hasNextLine()){
        linha = leitor.nextLine();
        System.out.println(linha);
      }
      numLinha++;
    }
  }
  
  private List<String> carregaASCII(Inimigo inimigo){
    List<String> ASCII = new ArrayList<>();
    String linha;
    Scanner scanner = carregaLeitor(inimigo);
    while(scanner.hasNextLine()){
      linha = scanner.nextLine();
      ASCII.add(linha);
    }
    return ASCII;
  }
  
  
  /* juntamos dois arquivos */
  protected void imprimeDoisInimigos(Inimigo inimigo1, Inimigo inimigo2){
    System.out.println();
    List<String> ASCIIInimigo1 = carregaASCII(inimigo1);
    List<String> ASCIIInimigo2 = carregaASCII(inimigo2);
    
    int numLinhas = 8;
    
    for(int i = 0; i < numLinhas; i++){
      String linha1 = ASCIIInimigo1.get(i);
      String linha2 = ASCIIInimigo2.get(i);
      
      System.out.println(linha1 + linha2);
    }
  }
  
  protected void imprimeTodosInimigos(){
    int inimigosRestante = 0;
    Inimigo inimigo1;
    Inimigo inimigo2;
    ListaInimigos listaInimigos;
    listaInimigos = dados.listaInimigos;
    inimigosRestante = qntInimigos;
    for(int i = 0; i < qntInimigos; i+= 2){
      if(inimigosRestante == 1){
        inimigo1 = listaInimigos.buscarInimigo(i + 1);
        imprimeUmInimigo(inimigo1);
        printaHeaderUmInimigo(inimigo1);
      }
      else{
        inimigo1 = listaInimigos.buscarInimigo(i + 1);
        inimigo2 = listaInimigos.buscarInimigo(i + 2);
        imprimeDoisInimigos(inimigo1, inimigo2);
        printaHeaderDoisInimigos(inimigo1, inimigo2);
      }
      inimigosRestante = inimigosRestante - 2;
    }
    System.out.println();
  }
  
  private void printaHeaderUmInimigo(Inimigo inimigo){
    /* como o inimigo unico somente será impresso sozinho:
      não vamos precisar nos preocupar com alinhamento
     */
    int vida = inimigo.getVida();
    int escudo = inimigo.getEscudo();
    String nome = inimigo.getNome();
    
    System.out.println("Nome: ");
    System.out.println(nome);
    System.out.println("Vida: ");
    System.out.println(vida);
    System.out.println("Escudo: ");
    System.out.println(escudo);
  }
  
  private void imprimeEspacos(int qnt){
    for(int i = 0; i < qnt; i++){
      System.out.print(" ");
    }
  }
  
  private void printaHeaderDoisInimigos(Inimigo inimigo1, Inimigo inimigo2){
    String nome1 = inimigo1.getNome();
    String nome2 = inimigo2.getNome();
    
    int vida1 = inimigo1.getVida();
    int vida2 = inimigo2.getVida();
    
    int escudo1 = inimigo1.getEscudo();
    int escudo2 = inimigo2.getEscudo();
    
    List<String> texto = new ArrayList<>();
    /* queremos tudo alinhado */
    /* "Nome: " ocupa 6 caracteres */
    /* queremos printar: Nome: //////////Nome: \n
                         nomeInimigo1    nomeInimigo2\n
                         Vida: //////////Vida: \n
                         ...
                         Escudo: ////////Escudo:
     */
    
    System.out.print("Nome: ");
    imprimeEspacos(10);
    System.out.print("Nome: \n");
    
    /* agora fazemos a contagem de espacos */
    /* para ficar alinhado eh necessario qntEspacos = 16 - nome1.lenght() */
    int qntEspacos = 16 - nome1.length();
    System.out.print(nome1);
    imprimeEspacos(qntEspacos);
    System.out.print(nome2 + "\n");
    
    System.out.print("Vida: ");
    imprimeEspacos(10);
    System.out.print("Vida: \n");
    
    /* para pegarmos quantos dígitos tem no número de forma fácil,
    * podemos apenas o converter para uma string e ver o seu tamanho */
    int digitosVida1 = String.valueOf(vida1).length();
    System.out.print(vida1);
    imprimeEspacos(16 - digitosVida1);
    System.out.print(vida2 + "\n");
    
    System.out.print("Escudo: ");
    imprimeEspacos(8);
    System.out.print("Escudo: \n");
    
    int digitosEscudo = String.valueOf(escudo1).length();
    System.out.print(escudo1);
    imprimeEspacos(16 - digitosEscudo);
    System.out.print(escudo2 + "\n");
  }
}
